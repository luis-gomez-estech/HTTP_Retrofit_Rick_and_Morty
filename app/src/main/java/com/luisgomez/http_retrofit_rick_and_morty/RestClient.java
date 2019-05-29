package com.luisgomez.http_retrofit_rick_and_morty;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestClient {

       @GET("api/character/")
     Call <Example> getData();
}
