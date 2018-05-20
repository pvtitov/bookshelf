package ru.github.pvtitov.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ru.github.pvtitov.bookshelf.book_details_contract.BookDetails;

public class DetailActivity extends AppCompatActivity {

    String mUrlPart;
    ProgressBar mProgressBar;
    Gson mGson = new Gson();
    BookDetails mBookDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mProgressBar = findViewById(R.id.progress_details);
        mProgressBar.setVisibility(View.VISIBLE);


        TextView headerTextView = findViewById(R.id.details_header);
        TextView descriptionTextView = findViewById(R.id.details_description);

        headerTextView.setText("mBookDetails.getVolumeInfo().getTitle()mBookDetails.getVolumeInfo().getTitle()mBookDetails.getVolumeInfo().getTitle()mBookDetails.getVolumeInfo().getTitle()");
        descriptionTextView.setText("mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()mBookDetails.getVolumeInfo().getDescription()");

        mUrlPart = getIntent().getStringExtra(MainActivity.BooksAdapter.EXTRA_LIST_POSITION);

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.googleapis.com" + mUrlPart).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mBookDetails = mGson.fromJson(response.body().string(), BookDetails.class)
            }
        });
    }
}
