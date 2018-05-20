package ru.github.pvtitov.bookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        mIndex = getIntent().getIntExtra(MainActivity.BooksAdapter.EXTRA_LIST_POSITION, 0);
        Toast.makeText(this, "Index: " + mIndex, Toast.LENGTH_SHORT).show();
    }
}
