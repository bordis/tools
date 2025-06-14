package br.com.bordi.tools.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record BitcoinResponseRecord(
    @JsonProperty("price")
    @JsonPropertyDescription("Current price of the asset")
    String price,

    @JsonProperty("timestamp")
    @JsonPropertyDescription("Unix timestamp when the data was recorded")
    long timestamp,

    @JsonProperty("24h_price_change")
    @JsonPropertyDescription("Price change over the last 24 hours")
    String priceChange24h,

    @JsonProperty("24h_price_change_percent")
    @JsonPropertyDescription("Percentage price change over the last 24 hours")
    String priceChangePercent24h,

    @JsonProperty("24h_high")
    @JsonPropertyDescription("Highest price in the last 24 hours")
    String high24h,

    @JsonProperty("24h_low")
    @JsonPropertyDescription("Lowest price in the last 24 hours")
    String low24h,

    @JsonProperty("24h_volume")
    @JsonPropertyDescription("Trading volume in the last 24 hours")
    String volume24h
) {

}
