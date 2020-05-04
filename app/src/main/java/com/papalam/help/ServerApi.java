package com.papalam.help;

import com.papalam.help.model.Test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerApi {

    @GET("/api/contacts")
    Call<ContactsResponse> getContacts();

    @GET("/api/test_list")
    Call<TestsResponse> getTests();

    @GET("/api/test/{id}")
    Call<Test> getTestById(@Path("id") int id);

    @GET("/client/get_pain_regions")
    Call<PainAreasResponse> getPainAreas(@Query("login") String login, @Query("date") String date);
}
