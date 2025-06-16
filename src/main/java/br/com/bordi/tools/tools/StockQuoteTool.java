package br.com.bordi.tools.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import br.com.bordi.tools.models.StockExchangeRecordRequest;
import br.com.bordi.tools.models.StockQuoteRecordRequest;
import br.com.bordi.tools.models.StockQuoteRecordResponse;

@Component
public class StockQuoteTool {

    private static final Logger log = LoggerFactory.getLogger(BitcoinTool.class);

    @Value("${API_NINJA_KEY}")
    private String apiNinjaKey;

    @Value("${API_NINJA_STOCK_QUOTE}")
    private String apiNinjaUrl;

    @Tool(description = "Returns the current price information for any given stock ticker symbol.")
    public StockQuoteRecordResponse getStockQuote(StockQuoteRecordRequest request) {
        RestClient restClient = RestClient.builder()
                .baseUrl(apiNinjaUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjaKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        try {
            log.info("TOOL: Fetching stock quote data from API: {}", apiNinjaUrl);
            ResponseEntity<StockQuoteRecordResponse> response = restClient.get()
                    .uri(uriBuilder -> uriBuilder.queryParam("ticker", request.ticker()).build())
                    .retrieve()
                    .toEntity(StockQuoteRecordResponse.class);
            log.info("TOOL: Received response: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("TOOL: Error fetching stock quote data: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch stock quote data", e);
        }
    }
    
}
