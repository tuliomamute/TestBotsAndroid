package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Resource {
    @JsonProperty("extras")
    public Extras Extras;

}