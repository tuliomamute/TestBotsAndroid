package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BotBasicInformation {
    @JsonProperty("id")
    public Long Id;
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
    @JsonProperty("created")
    public String Created;
    @JsonProperty("updated")
    public String Updated;

    public BotBasicInformation() {
    }

    public BotBasicInformation(Long Id, String ShortName, String Name, String Description, String Template, String[] ActivatedServices, String ImageUri, String Created, String Updated) {
        this.Id = Id;
        this.ShortName = ShortName;
        this.Name = Name;
        this.Description = Description;
        this.Template = Template;
        this.ActivatedServices = ActivatedServices;
        this.ImageUri = ImageUri;
        this.Created = Created;
        this.Updated = Updated;
    }
}
