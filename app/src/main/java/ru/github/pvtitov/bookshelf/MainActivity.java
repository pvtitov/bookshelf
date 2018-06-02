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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.github.pvtitov.bookshelf.search_books_contracts.Book;
import ru.github.pvtitov.bookshelf.search_books_contracts.SearchResult;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    BooksAdapter mAdapter;
    GoogleBooksService mHttpService;
    ProgressBar mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.books_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BooksAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mProgress = findViewById(R.id.progress_main);
        App app = (App) getApplication();
        mHttpService = app.getHttpService();

        String query = handleSearch(getIntent());
        if (query.compareTo("") > 0) {
            loadResults(query);
        }

    }

    private void loadResults(String query) {
        mProgress.setVisibility(View.VISIBLE);
        mHttpService.queryGBServerFor(query, Utils.API_KEY).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
                if (response.isSuccessful()) {
                    mAdapter.setData(response.body());
                    mAdapter.notifyDataSetChanged();
                }
                else {
                    Log.e("Internet", "Response code: " + response.code());
                    if (response.errorBody() != null) {
                        try {
                            Log.e("Internet", "Error body: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mProgress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                Log.e("Internet", t.getMessage());
                mProgress.setVisibility(View.GONE);
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
        searchView.setSearchableInfo(
                searchManager != null ?
                        searchManager.getSearchableInfo(getComponentName()) :
                        null);

        return true;
    }

    public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>{

        public static final String EXTRA_LIST_POSITION = "position";

        SearchResult mSearchResult;

        void setData(SearchResult searchResult){
            mSearchResult = searchResult;
        }


        @NonNull
        @Override
        public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BooksViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false)
            );
        }

        @Override
        public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
            if (mSearchResult.getItems() != null) {
                Book book = mSearchResult.getItems().get(position);
                holder.titleView.setText(
                        book.getVolumeInfo().getTitle());
                holder.authorsView.setText(
                        Utils.join(book.getVolumeInfo().getAuthors(), ", "));
                holder.layout.setOnClickListener(v -> {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    String urlPart = mSearchResult.getItems().get(position).getSelfLink()
                            .replace(getString(R.string.base_google_url), "");
                    intent.putExtra(EXTRA_LIST_POSITION, urlPart);
                    startActivity(intent);
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mSearchResult != null)
                return mSearchResult.getItems().size();
            else return 0;

        }



        class BooksViewHolder extends RecyclerView.ViewHolder{

            TextView titleView;
            TextView authorsView;
            LinearLayout layout;

            BooksViewHolder(View itemView) {
                super(itemView);
                titleView = itemView.findViewById(R.id.book_title);
                authorsView = itemView.findViewById(R.id.book_authors);
                layout = itemView.findViewById(R.id.book_item);
            }
        }

    }
}
