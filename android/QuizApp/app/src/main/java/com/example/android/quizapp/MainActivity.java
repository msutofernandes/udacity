package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarQuiz(View view)
    {
        TextView nome = (TextView) findViewById(R.id.nome);
        String name = nome.getText().toString();
        if (name != null  && !name.isEmpty()) {
        Intent intent = new Intent(MainActivity.this, Questions.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Fill out your name to start", Toast.LENGTH_SHORT).show();
        }
    }
}
