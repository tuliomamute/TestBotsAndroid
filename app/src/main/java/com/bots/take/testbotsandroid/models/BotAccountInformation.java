package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BotAccountInformation {
    @JsonProperty("type")
    public String Type;
    @JsonProperty("resource")
    public Resource Resource;
    @JsonProperty("method")
    public String Method;
    @JsonProperty("status")
    public String Status;
    @JsonProperty("id")
    public String Id;
    @JsonProperty("from")
    public String From;
    @JsonProperty("to")
    public String To;

}
