package com.bots.take.testbotsandroid.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.PublicKey;

public class UserInformation {
    @JsonProperty("fullName")
    public String FullName;
    @JsonProperty("phoneNumber")
    public String PhoneNumber;
    @JsonProperty("company")
    public String Company;
    @JsonProperty("isPending")
    public Boolean IsPending;
    @JsonProperty("id")
    public Long Id;
    @JsonProperty("email")
    public String Email;

    public UserInformation() {
    }

    public UserInformation(String FullName, String PhoneNumber, String Company, Boolean IsPending, Long Id, String Email) {
        this.FullName = FullName;
        this.PhoneNumber = PhoneNumber;
        this.Company = Company;
        this.IsPending = IsPending;
        this.Id = Id;
        this.Email = Email;
    }
}
