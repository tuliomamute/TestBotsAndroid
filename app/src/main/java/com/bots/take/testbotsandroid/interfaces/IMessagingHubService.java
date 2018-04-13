package com.bots.take.testbotsandroid.interfaces;

import com.bots.take.testbotsandroid.models.BotAdvancedInformation;
import com.bots.take.testbotsandroid.models.BotBasicInformation;
import com.bots.take.testbotsandroid.models.UserInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IMessagingHubService {
    @GET("accounts/me")
    Call<UserInformation> AuthenticateUser(@Header("Authorization") String authorization);

    @GET("applications/mine")
    Call<List<BotBasicInformation>> GetBotsList(@Header("Authorization") String authorization);

    @GET("applications/{botIdentifier}")
    Call<BotAdvancedInformation> GetDetailedBot(@Header("Authorization") String authorization, @Path("botIdentifier") String botIdentifier);
}
