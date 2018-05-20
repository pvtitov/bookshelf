package ru.github.pvtitov.bookshelf;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.github.pvtitov.bookshelf.book_details_contract.BookDetails;
import ru.github.pvtitov.bookshelf.search_books_contracts.SearchResult;

public interface GoogleBooksService {

    @GET("/books/v1/volumes?maxResults=25")
    Call<SearchResult> queryGBServerFor(@Query("q") String search, @Query("key") String apiKey);
}
