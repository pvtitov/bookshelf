package ru.github.pvtitov.bookshelf;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

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
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mProgressBar = findViewById(R.id.progress_details);
        mProgressBar.setVisibility(View.VISIBLE);


        TextView headerTextView = findViewById(R.id.details_header);
        TextView authorsTextView = findViewById(R.id.details_authors);
        TextView descriptionTextView = findViewById(R.id.details_description);
        ImageView imageView = findViewById(R.id.details_image);

        mUrlPart = getIntent().getStringExtra(MainActivity.BooksAdapter.EXTRA_LIST_POSITION);

        mHandler = new Handler(Looper.getMainLooper());

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://www.googleapis.com" + mUrlPart).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null) {
                    mBookDetails = mGson.fromJson(response.body().string(), BookDetails.class);
                    Drawable image = new BitmapDrawable(Picasso.get().load(mBookDetails.getVolumeInfo().getImageLinks().getThumbnail()).get());
                    mHandler.post(() -> {
                        mProgressBar.setVisibility(View.GONE);
                        headerTextView.setText(mBookDetails.getVolumeInfo().getTitle());
                        authorsTextView.setText(
                                Utils.join(mBookDetails.getVolumeInfo().getAuthors(), ", "));
                        imageView.setImageDrawable(image);
                        descriptionTextView.setText(mBookDetails.getVolumeInfo().getDescription());
                    });
                }
            }
        });
    }
}
