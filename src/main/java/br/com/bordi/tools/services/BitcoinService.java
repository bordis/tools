package br.com.bordi.tools.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import br.com.bordi.tools.tools.BitcoinTool;

@Service
public class BitcoinService {

    private static final Logger log = LoggerFactory.getLogger(BitcoinService.class);

    private final ChatClient chatClient;
    private final BitcoinTool bitcoinTool;

    public BitcoinService(ChatClient.Builder chatClientBuilder, BitcoinTool bitcoinTool) {
        this.chatClient = chatClientBuilder.build();
        this.bitcoinTool = bitcoinTool;
    }

    public String getBitcoinPrice() {
        PromptTemplate promptTemplate = new PromptTemplate("""
                You are a helpful assistant that provides the latest Bitcoin price and market data.
                    """);
        log.info("Creating prompt for Bitcoin price retrieval e using tool: {}", bitcoinTool.getClass().getSimpleName());
        ChatResponse chatReponse = chatClient.prompt(promptTemplate.create())
                .tools(bitcoinTool)
                .call()
                .chatResponse();

        String response = chatReponse.getResult().getOutput().getText();

        if (response == null || response.isEmpty()) {
            log.error("Received empty or null response from Bitcoin tool.");
            throw new RuntimeException("Failed to retrieve Bitcoin price.");
        }
        log.info("Bitcoin price response: {}", response);
        return response;
    }

}
