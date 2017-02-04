package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //avoid OnScreenKeyboard to open automatically
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void mostraResultado(View view) {
        int pontuacao = somaPontos();
        montaResultado("Points: " + pontuacao +"/6");
    }

    public void montaResultado(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.resultado);
        orderSummaryTextView.setText(message);
    }

    public int somaPontos() {
        int points = 0;
        RadioButton radio2 = (RadioButton) findViewById(R.id.resp1_2);
        boolean refChecked2 = radio2.isChecked();
        if (refChecked2) {
            points = points + 1;
        }
        RadioButton radio4 = (RadioButton) findViewById(R.id.resp2_1);
        boolean refChecked4 = radio4.isChecked();
        if (refChecked4) {
            points = points + 1;
        }
        CheckBox check1 = (CheckBox) findViewById(R.id.resp3_1);
        boolean isChecked1 = check1.isChecked();
        CheckBox check4 = (CheckBox) findViewById(R.id.resp3_4);
        boolean isChecked4 = check4.isChecked();
        if (isChecked1 && isChecked4) {
            points = points + 1;
        }
        CheckBox check5 = (CheckBox) findViewById(R.id.resp4_1);
        boolean isChecked5 = check5.isChecked();
        CheckBox check6 = (CheckBox) findViewById(R.id.resp4_2);
        boolean isChecked6 = check6.isChecked();
        if (isChecked5 && isChecked6) {
            points = points + 1;
        }
        EditText text1 = (EditText) findViewById(R.id.resp5_1);
        String resposta1 = text1.getText().toString();
        if (resposta1.equals("Division")) {
            points = points + 1;
        }
        EditText text2 = (EditText) findViewById(R.id.resp6_1);
        String resposta2 = text2.getText().toString();
        if (resposta2.equals("Multiplication")) {
            points = points + 1;
        }
        return points;
    }
}