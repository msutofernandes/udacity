package com.example.android.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String name = null;

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
        Intent intent = new Intent(MainActivity.this, Pergunta1.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Preencha o seu nome para começar", Toast.LENGTH_SHORT).show();
        }
    }
}
