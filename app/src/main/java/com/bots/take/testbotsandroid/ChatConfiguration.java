package com.bots.take.testbotsandroid;

import net.take.blipchat.AuthType;
import java.io.Serializable;

public class ChatConfiguration implements Serializable {
    public String UserIdentifier;
    public String UserPassWord;
    public String UserName;
    public String UserEmail;
    public String BotIdentifier;
    public AuthType AuthType;
}
