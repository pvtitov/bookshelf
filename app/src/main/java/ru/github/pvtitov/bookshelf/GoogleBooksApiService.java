package ru.github.pvtitov.bookshelf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleBooksApiService {

    @GET("/books/v1/volumes?maxResults=25")
    Call<SearchResult> queryGBServerFor(@Query("q") String search, @Query("key") String apiKey);
}
