package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApplicationUserPermissionModel {
    @JsonProperty("permissionClaim")
    public Long PermissionClaim;
    @JsonProperty("permissionAction")
    public Long PermissionAction;


    public ApplicationUserPermissionModel() {
    }

    public ApplicationUserPermissionModel(long PermissionClaim, long PermissionAction) {
        this.PermissionClaim = PermissionClaim;
        this.PermissionAction = PermissionAction;
    }
}