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

import static com.example.android.booklisting.HttpConnection.makeHttpRequest;

/**
 * Created by msuto on 08/01/2017.
 */

public class BookList extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    // URL to get contacts JSON
    private static String requestUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    private static String maxResults = "&maxResults=15";
    private static String jsonResponse = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity);

        new GetBooks().execute();

    }

    public class GetBooks extends AsyncTask<URL, Void, ArrayList> {

        @Override
        public ArrayList<Book> doInBackground(URL... urls) {
            // Create URL object concatenating the keyword + max results = 15
            Intent intent = getIntent();
            String str = intent.getStringExtra("keyword");
            String urlPlusKeyword = requestUrl + str + maxResults;
            URL url = createUrl(urlPlusKeyword);


            // Perform HTTP request to the URL and receive a JSON response back
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                Log.e(TAG, "HTTP request error");
            }

            final ArrayList<Book> booklist = new ArrayList<>();

            try {

                JSONObject jsonObject = new JSONObject(jsonResponse);

                if (TextUtils.isEmpty(jsonResponse)) {
                    return null;
                }

                JSONArray itemsArray = jsonObject.getJSONArray("items");

                // If there are results in the features array
                if (itemsArray.length() > 0) {

                    for (int i = 0; i < itemsArray.length(); i++) {

                        int firstAuthor = 0;

                        // Extract out the first feature volumeInfo
                        JSONObject currentBook = itemsArray.getJSONObject(i);
                        JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");

                        // Extract out the title and author values
                        String title = volumeInfo.getString("title");

                        if (volumeInfo.has("authors")) {
                            JSONArray authors = volumeInfo.getJSONArray("authors");
                            String author = authors.getString(firstAuthor);
                            Book list = new Book(title, author);
                            booklist.add(list);
                        }

                        Book list = new Book(title, "No author available");
                        booklist.add(list);

                    }
                }
            } catch (JSONException e) {
                Log.e(TAG, "Problem parsing JSON results", e);
                booklist.add(null);
            }
            return booklist;
        }

        @Override
        protected void onPostExecute(ArrayList booklist) {
            if (booklist.size() <= 1) {

                ListView listView = (ListView) findViewById(R.id.list);
                listView.setEmptyView(findViewById(R.id.empty_list));

            } else {

                BookAdapater itemAdapter = new BookAdapater(BookList.this, booklist);
                itemAdapter.addAll(booklist);

                ListView listView = (ListView) findViewById(R.id.list);
                listView.setAdapter(itemAdapter);

            }
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Log.e(TAG, "Error with creating URL", exception);
                return null;
            }
            return url;
        }
    }
}
