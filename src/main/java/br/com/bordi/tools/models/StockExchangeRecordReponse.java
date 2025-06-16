package br.com.bordi.tools.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockExchangeRecordReponse(
    
    @JsonProperty("mic")
    @JsonPropertyDescription("Market Identifier Code of the exchange")
    String mic,

    @JsonProperty("name")
    @JsonPropertyDescription("Full name of the stock exchange")
    String name,

    @JsonProperty("city")
    @JsonPropertyDescription("City where the exchange is located")
    String city,

    @JsonProperty("country")
    @JsonPropertyDescription("Country where the exchange is located")
    String country,

    @JsonProperty("iso2")
    @JsonPropertyDescription("ISO 3166-1 alpha-2 country code")
    String iso2,

    @JsonProperty("description")
    @JsonPropertyDescription("Brief description of the stock exchange")
    String description,

    @JsonProperty("address")
    @JsonPropertyDescription("Physical address of the exchange")
    String address,

    @JsonProperty("website")
    @JsonPropertyDescription("Official website of the exchange")
    String website,

    @JsonProperty("founded")
    @JsonPropertyDescription("Date when the exchange was founded (ISO format)")
    String founded, // Ou LocalDate, se for desserializado como data

    @JsonProperty("num_listings")
    @JsonPropertyDescription("Number of listed companies in the exchange")
    int numListings,

    @JsonProperty("market_cap_usd")
    @JsonPropertyDescription("Total market capitalization in USD")
    long marketCapUsd,

    @JsonProperty("currency")
    @JsonPropertyDescription("Trading currency used in the exchange")
    String currency,

    @JsonProperty("timezone")
    @JsonPropertyDescription("Time zone of the exchange location")
    String timezone,

    @JsonProperty("market_open")
    @JsonPropertyDescription("Time when the market opens")
    String marketOpen,

    @JsonProperty("market_close")
    @JsonPropertyDescription("Time when the market closes")
    String marketClose

) {}
