package ru.github.pvtitov.bookshelf;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class Utils {
    public static final String API_KEY = "AIzaSyAczCVs4cLPIFosD6JD7-Y9HkUUOs-mgHk";

    public static String errorResponseMessage(Response<?> response) {
        try {
            JSONObject jsonObject = new JSONObject(response.errorBody().string());
            JSONObject errorObject = jsonObject.getJSONObject("error");
            return errorObject.getString(errorObject.getString("code")
                    + " - " + errorObject.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Parsing error response failed";
    }

    public static String join(List<String> list, String delimiter) {

        StringBuilder sb = new StringBuilder();
        String precedingDelimiter = "";

        if (list != null) {
            for(String s : list) {
                sb.append(precedingDelimiter);
                sb.append(s);

                precedingDelimiter = delimiter;
            }
        }

        return sb.toString();
    }
}
