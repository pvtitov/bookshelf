package ru.github.pvtitov.bookshelf;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    BooksAdapter mAdapter;
    Retrofit mRetrofit;
    GoogleBooksApiService mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.books_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BooksAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://www.googleapis.com").build();
        mService = mRetrofit.create(GoogleBooksApiService.class);

        String query = handleSearch(getIntent());

        mService.queryGBServerFor(query, Utils.API_KEY).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful()) {
                    mAdapter.setData(response.body());
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        JSONObject errorObject = jsonObject.getJSONObject("error");
                        Log.d("Internet", errorObject.getString("message"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e("Internet", t.getMessage());
            }
        });
    }

    private String handleSearch(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
            return intent.getStringExtra(SearchManager.QUERY);
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>{

        public static final String EXTRA_LIST_POSITION = "position";

        class BooksViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            BooksViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.book_title);
            }

        }

        SearchResult mSearchResult;

        void setData(SearchResult searchResult){
            mSearchResult = searchResult;
        }

        @NonNull
        @Override
        public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BooksViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
            if (mSearchResult.getItems() != null)
                holder.textView.setText(
                        mSearchResult.getItems().get(position)
                                .getVolumeInfo().getTitle()
            );
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(EXTRA_LIST_POSITION, position);
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            if (mSearchResult != null)
                return mSearchResult.getItems().size();
            else return 0;

        }

    }
}
