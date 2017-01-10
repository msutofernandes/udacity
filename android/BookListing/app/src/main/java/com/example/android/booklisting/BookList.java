package com.example.android.booklisting;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.android.booklisting.HttpConnection.LOG_TAG;
import static com.example.android.booklisting.HttpConnection.makeHttpRequest;

/**
 * Created by msuto on 08/01/2017.
 */

public class BookList extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;

    // URL to get contacts JSON
    private static String requestUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    private static String maxResults = "&maxResults=5";
    private static String jsonResponse = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        new GetBooks().execute();

        final ArrayList<Book> booklist = extractBooks();

        BookAdapater itemAdapter = new BookAdapater(this, booklist);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);
    }

    public class GetBooks extends AsyncTask<URL, Void, Void> {

        @Override
        public Void doInBackground(URL... urls) {
            // Create URL object concatenating the keyword + max results = 10
            Intent intent = getIntent();
            String str = intent.getStringExtra("keyword");
            String urlPlusKeyword = requestUrl + str + maxResults;
            URL url = createUrl(urlPlusKeyword);

            // Perform HTTP request to the URL and receive a JSON response back
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error trying to make Http request");
            }
            return null;
        }

}

    public static ArrayList<Book> extractBooks() {

        ArrayList<Book> booklist = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(jsonResponse);

            if (TextUtils.isEmpty(jsonResponse)){
                return null;
            }

            JSONArray itemsArray = jsonObject.getJSONArray("items");

            // If there are results in the features array
            if (itemsArray.length() > 0) {

                for (int i = 0; i < itemsArray.length(); i++) {

                    // Extract out the first feature volumeInfo
                    JSONObject currentBook = itemsArray.getJSONObject(i);
                    JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                    // Extract out the title and publisher values
                    String title = volumeInfo.getString("title");
                    String publisher = volumeInfo.getString("publisher");

                    Book list = new Book(title, publisher);
                    booklist.add(list);
                }
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing JSON results", e);
        }
        return null;
    }


    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException exception) {
            Log.e(LOG_TAG, "Error with creating URL", exception);
            return null;
        }
        return url;
    }
}
