package ru.github.pvtitov.bookshelf;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private GoogleBooksService mService;

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(getString(R.string.base_google_url)).build();
        mService = retrofit.create(GoogleBooksService.class);
    }

    public GoogleBooksService getHttpService() {
        return mService;
    }
}
