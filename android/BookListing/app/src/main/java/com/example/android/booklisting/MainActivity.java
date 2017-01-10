    package com.example.android.booklisting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

    public class MainActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            final EditText keywordEditText = (EditText) findViewById(R.id.keyword);
            String keyword = keywordEditText.getText().toString();

            Button search = (Button) findViewById(R.id.search_button);

            // Set a click listener on that View
            search.setOnClickListener(new Button.OnClickListener() {
                // The code in this method will be executed when the numbers View is clicked on.
                @Override
                public void onClick(View view) {
                    Intent bookListIntent = new Intent(MainActivity.this, BookList.class);
                    bookListIntent.putExtra("keyword", keywordEditText.getText().toString());
                    startActivity(bookListIntent);
                }
            });

        }
    }
