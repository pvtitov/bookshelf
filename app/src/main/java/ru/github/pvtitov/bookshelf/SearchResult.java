package ru.github.pvtitov.bookshelf;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchResult {

    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("items")
    @Expose
    private List<Book> mBooks = null;
    @SerializedName("totalItems")
    @Expose
    private int totalItems;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Book> getItems() {
        return mBooks;
    }

    public void setItems(List<Book> books) {
        this.mBooks = books;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

}