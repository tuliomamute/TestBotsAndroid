package com.bots.take.testbotsandroid.models;

import com.orm.SugarRecord;

import net.take.blipchat.AuthType;

public class ChatConfiguration extends SugarRecord<ChatConfiguration> {
    public String BotAlias;
    public String UserIdentifier;
    public String UserPassWord;
    public String UserName;
    public String UserEmail;
    public String BotAppKey;
    public String AuthType;
    public String UserExtras;

    public ChatConfiguration() {
    }

    public ChatConfiguration(String UserIdentifier, String UserPassWord, String UserName, String UserEmail, String BotAppKey, String BotAlias, String AuthType, String UserExtras) {
        this.BotAlias = BotAlias;
        this.UserIdentifier = UserIdentifier;
        this.UserPassWord = UserPassWord;
        this.UserName = UserName;
        this.UserEmail = UserEmail;
        this.BotAppKey = BotAppKey;
        this.AuthType = AuthType;
        this.UserExtras = UserExtras;
    }

}
