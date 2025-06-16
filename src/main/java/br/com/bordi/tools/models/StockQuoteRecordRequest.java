package br.com.bordi.tools.models;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockQuoteRecordRequest(

    @JsonProperty("ticker")
    @JsonPropertyDescription("Stock or index ticker symbol (e.g., AAPL or ^DJI).")
    @NotNull(message = "Ticker symbol is required")
    String ticker

) {}