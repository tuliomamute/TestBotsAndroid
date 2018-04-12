package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BotBasicInformation {
    @JsonProperty("id")
    public String Id;
    @JsonProperty("shortName")
    public String ShortName;
    @JsonProperty("name")
    public String Name;
    @JsonProperty("description")
    public String Description;
    @JsonProperty("template")
    public String Template;
    @JsonProperty("activatedServices")
    public String[] ActivatedServices;
    @JsonProperty("imageUri")
    public String ImageUri;

    public BotBasicInformation() {
    }

    public BotBasicInformation(String Id, String ShortName, String Name, String Description, String Template, String[] ActivatedServices, String ImageUri) {
        this.Id = Id;
        this.ShortName = ShortName;
        this.Name = Name;
        this.Description = Description;
        this.Template = Template;
        this.ActivatedServices = ActivatedServices;
        this.ImageUri = ImageUri;
    }
}
