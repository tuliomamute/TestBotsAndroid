package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Extras {
    @JsonProperty("blipchat-app-key")
    public String BlipchatAppKey;
}