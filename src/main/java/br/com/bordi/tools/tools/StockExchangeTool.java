package br.com.bordi.tools.tools;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import br.com.bordi.tools.models.StockExchangeRecordReponse;
import br.com.bordi.tools.models.StockExchangeRecordRequest;

@Component
public class StockExchangeTool {

    private static final Logger log = LoggerFactory.getLogger(BitcoinTool.class);

    @Value("${API_NINJA_KEY}")
    private String apiNinjaKey;

    @Value("${API_NINJA_STOCK}")
    private String apiNinjaUrl;

    @Tool(description = "Returns stock exchange information based on the provided criteria.")
    public List<StockExchangeRecordReponse> getStockExchange(StockExchangeRecordRequest request) {
        RestClient restClient = RestClient.builder()
                .baseUrl(apiNinjaUrl)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjaKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        try {
            log.info("TOOL: Fetching stock exchange data from API: {}", apiNinjaUrl);
            ResponseEntity<List<StockExchangeRecordReponse>> response = restClient.get()
                    .uri(uriBuilder -> {
                        if (request.mic() != null)
                            uriBuilder.queryParam("mic", request.mic());
                        if (request.name() != null)
                            uriBuilder.queryParam("name", request.name());
                        if (request.city() != null)
                            uriBuilder.queryParam("city", request.city());
                        if (request.country() != null)
                            uriBuilder.queryParam("country", request.country());
                        return uriBuilder.build();
                    })
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<List<StockExchangeRecordReponse>>() {
                    });
            log.info("TOOL: Received response: {}", response.getBody());
            return response.getBody();
        } catch (Exception e) {
            log.error("TOOL: Error fetching stock exchange data: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to fetch stock exchange data", e);
        }
    }

}
