package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAccountInformation {
    @JsonProperty("method")
    public String Method;
    @JsonProperty("uri")
    public String Uri;
    @JsonProperty("id")
    public String Id;
}
