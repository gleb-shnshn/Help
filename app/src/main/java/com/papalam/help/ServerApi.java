package com.papalam.help;

import com.papalam.help.model.LoginAndPassword;
import com.papalam.help.model.PainPoint;
import com.papalam.help.model.RegistrationData;
import com.papalam.help.model.Test;
import com.papalam.help.responses.ContactsResponse;
import com.papalam.help.responses.DefaultResponse;
import com.papalam.help.responses.MessagesResponse;
import com.papalam.help.responses.PainAreasResponse;
import com.papalam.help.responses.TestResultResponse;
import com.papalam.help.responses.TestsResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerApi {

    @POST("/client/auth")
    Call<DefaultResponse> login(@Body LoginAndPassword loginAndPassword);

    @POST("/client/register")
    Call<DefaultResponse> register(@Body RegistrationData registrationData);

    @GET("/api/contacts")
    Call<ContactsResponse> getContacts();

    @GET("/api/test_list")
    Call<TestsResponse> getTests();

    @GET("/api/test/{id}")
    Call<Test> getTestById(@Path("id") int id);

    @GET("/client/get_pain_regions")
    Call<PainAreasResponse> getPainAreas(@Query("login") String login, @Query("date") String date);

    @Multipart
    @POST("/client/add_pain_region")
    Call<DefaultResponse> addPainArea(@Part MultipartBody.Part image, @Part("point") PainPoint point);

    @GET("/client/get_chat_messages")
    Call<MessagesResponse> getChatMessages();

    @GET("/client/send_chat_message")
    Call<DefaultResponse> sendMessage(@Query("login") String login, @Query("text") String text);

    @GET("/api/get_test_result")
    Call<TestResultResponse> getTestResult(@Query("login") String login, @Query("test_id") int test_id, @Query("score") int score);
}
