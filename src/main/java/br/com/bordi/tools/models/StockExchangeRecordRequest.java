package br.com.bordi.tools.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StockExchangeRecordRequest(

    @JsonProperty("mic")
    @JsonPropertyDescription("Market Identifier Code (e.g., XNYS).")
    String mic,

    @JsonProperty("name")
    @JsonPropertyDescription("Stock exchange name (supports partial matching).")
    String name,

    @JsonProperty("city")
    @JsonPropertyDescription("City where the exchange is located.")
    String city,

    @JsonProperty("country")
    @JsonPropertyDescription("Country code in ISO2 format (e.g., US).")
    String country

) {}
