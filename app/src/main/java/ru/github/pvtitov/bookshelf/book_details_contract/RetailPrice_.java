package ru.github.pvtitov.bookshelf.book_details_contract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetailPrice_ {

    @SerializedName("amountInMicros")
    @Expose
    private float amountInMicros;
    @SerializedName("currencyCode")
    @Expose
    private String currencyCode;

    public float getAmountInMicros() {
        return amountInMicros;
    }

    public void setAmountInMicros(float amountInMicros) {
        this.amountInMicros = amountInMicros;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}