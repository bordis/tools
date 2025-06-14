package br.com.bordi.tools.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import br.com.bordi.tools.models.BitcoinResponseRecord;

@Component
public class BitcoinTool {

    private static final Logger log = LoggerFactory.getLogger(BitcoinTool.class);

    @Value("${API_NINJA_KEY}")
    private String apiNinjaKey;

    @Value("${API_NINJA_URL_BITCOIN}")
    private String apiNinjaUrl;

    @Tool(description = "Returns the latest Bitcoin price in USD and 24-hour market data.")
    public BitcoinResponseRecord getBitcoinPrice() {
        RestClient restClient = RestClient.builder()
                .baseUrl(apiNinjaUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjaKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        try {
            log.info("Fetching Bitcoin price from API: {}", apiNinjaUrl);
            ResponseEntity<BitcoinResponseRecord> response = restClient.get()
                    .uri(apiNinjaUrl)
                    .retrieve()
                    .toEntity(BitcoinResponseRecord.class);
            log.info("Received response: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("Error fetching Bitcoin price: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch Bitcoin price", e);
        }
    }

}
