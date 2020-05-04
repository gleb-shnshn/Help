package com.papalam.help;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {

    @GET("/api/contacts")
    Call<ContactsResponse> getContacts();

}
