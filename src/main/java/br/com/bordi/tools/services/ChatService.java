package br.com.bordi.tools.services;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import br.com.bordi.tools.models.AwnserRecord;
import br.com.bordi.tools.models.QuestionRecord;
import br.com.bordi.tools.tools.BitcoinTool;
import br.com.bordi.tools.tools.StockExchangeTool;
import br.com.bordi.tools.tools.StockQuoteTool;

@Service
public class ChatService {

    private static final Logger log = LoggerFactory.getLogger(ChatService.class);

    private final ChatClient chatClient;
    private final BitcoinTool bitcoinTool;
    private final StockExchangeTool stockTool;
    private final StockQuoteTool stockQuoteTool;
    public ChatService(
        ChatClient.Builder chatClientBuilder, 
        BitcoinTool bitcoinTool, 
        StockExchangeTool stockTool,
        StockQuoteTool stockQuoteTool
        ) {
        this.chatClient = chatClientBuilder.build();
        this.bitcoinTool = bitcoinTool;
        this.stockTool = stockTool;
        this.stockQuoteTool = stockQuoteTool;
    }

    @Value("classpath:/prompt.st")
    private Resource systemMessage;

    public AwnserRecord getAwnser(QuestionRecord question) {
        log.info("Received question in the service: {}", question.question());
        PromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemMessage);
        
        Message systemMessage = systemPromptTemplate.createMessage();

        UserMessage userMessage = new UserMessage(question.question());

        Prompt prompt = new Prompt(systemMessage, userMessage);
        log.info("Prompt created: {}", prompt);

         ChatResponse chatReponse = chatClient.prompt(prompt)
                .tools(bitcoinTool, stockTool, stockQuoteTool)
                .call()
                .chatResponse();

        String response = chatReponse.getResult().getOutput().getText();

        return new AwnserRecord(response);
    }

}
