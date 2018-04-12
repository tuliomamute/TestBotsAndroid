package com.bots.take.testbotsandroid.models;

public class ApplicationUserPermissionModel {
    public Long PermissionClaim;
    public Long PermissionAction;


    public ApplicationUserPermissionModel() {
    }

    public ApplicationUserPermissionModel(long PermissionClaim, long PermissionAction) {
        this.PermissionClaim = PermissionClaim;
        this.PermissionAction = PermissionAction;
    }
}