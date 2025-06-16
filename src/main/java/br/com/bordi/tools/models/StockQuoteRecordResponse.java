package br.com.bordi.tools.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockQuoteRecordResponse(

    @JsonProperty("ticker")
    @JsonPropertyDescription("Stock ticker symbol (e.g., AAPL)")
    String ticker,

    @JsonProperty("name")
    @JsonPropertyDescription("Full name of the company")
    String name,

    @JsonProperty("price")
    @JsonPropertyDescription("Current stock price")
    double price,

    @JsonProperty("exchange")
    @JsonPropertyDescription("Stock exchange where the stock is listed (e.g., NASDAQ)")
    String exchange,

    @JsonProperty("updated")
    @JsonPropertyDescription("Last updated timestamp (Unix format)")
    long updated,

    @JsonProperty("currency")
    @JsonPropertyDescription("Currency of the stock price (e.g., USD)")
    String currency

) {}
