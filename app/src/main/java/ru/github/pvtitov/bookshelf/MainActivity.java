package ru.github.pvtitov.bookshelf;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    BooksAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] testData = {"one", "two", "three", "four", "five"};

        mRecyclerView = findViewById(R.id.books_list);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new BooksAdapter();
        mAdapter.setData(testData);
        mRecyclerView.setAdapter(mAdapter);

    }

    public static class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>{

        public static class BooksViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;
            public BooksViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.book_title);
            }

        }

        Book[] mBooks;

        void setData(String[] data){
            mBooks = new Book[data.length];
            for (int i = 0; i < data.length; i++) {
                mBooks[i] = new Book();
                mBooks[i].setTitle(data[i]);
            }
        }

        @NonNull
        @Override
        public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BooksViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
            holder.textView.setText(mBooks[position].getTitle());
        }

        @Override
        public int getItemCount() {
            return mBooks.length;
        }
    }
}
