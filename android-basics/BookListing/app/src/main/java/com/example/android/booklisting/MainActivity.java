    package com.example.android.booklisting;

    import android.content.Context;
    import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
    import android.widget.Toast;

    public class  MainActivity extends AppCompatActivity {

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

                    if (AppStatus.getInstance(getApplicationContext()).isOnline()) {

                        Intent bookListIntent = new Intent(MainActivity.this, BookList.class);
                        bookListIntent.putExtra("keyword", keywordEditText.getText().toString());
                        startActivity(bookListIntent);

                    } else {
                        Context contexto = MainActivity.this;
                        String texto = "Please check your internet connection";
                        int duracao = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(contexto, texto, duracao);
                        toast.show();
                    }
                }
            });

        }
    }
