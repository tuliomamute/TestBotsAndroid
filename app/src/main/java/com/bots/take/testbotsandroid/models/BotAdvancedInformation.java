package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BotAdvancedInformation extends BotBasicInformation {
    @JsonProperty("accessKey")
    public String AccessKey;
    @JsonProperty("applicationUserPermissionModel")
    public ApplicationUserPermissionModel[] ApplicationUserPermissionModel;

    public BotAdvancedInformation() {
    }

    public BotAdvancedInformation(String AccessKey, ApplicationUserPermissionModel[] ApplicationUserPermissionModel) {
        this.AccessKey = AccessKey;
        this.ApplicationUserPermissionModel = ApplicationUserPermissionModel;
    }
}
