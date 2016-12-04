package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class Pergunta1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta1);
    }

    public void mostraResultado(View view) {
        int pontuacao = somaPontos();
        if (pontuacao >= 6) {
            montaResultado("Parabéns!\nVocê é uma pessoa saudável, continue assim!");
        } else if (pontuacao <= 5) {
            montaResultado("Infelizmente você não é uma pessoa saudável.");
        }
    }

    public void montaResultado(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.resultado);
        orderSummaryTextView.setText(message);
    }

    public int somaPontos() {
        int points = 0;
        RadioButton refeicao1 = (RadioButton) findViewById(R.id.refeicao_3);
        boolean refChecked1 = refeicao1.isChecked();
        if (refChecked1) {
            points = points + 0;
        }
        RadioButton refeicao2 = (RadioButton) findViewById(R.id.refeicao_3);
        boolean refChecked2 = refeicao2.isChecked();
        if (refChecked2) {
            points = points + 1;
        }
        RadioButton refeicao3 = (RadioButton) findViewById(R.id.refeicao_3);
        boolean refChecked3 = refeicao3.isChecked();
        if (refChecked3) {
            points = points + 2;
        }
        RadioButton fuma1 = (RadioButton) findViewById(R.id.fuma_nao);
        boolean fumaChecked1 = fuma1.isChecked();
        if (fumaChecked1) {
            points = points + 2;
        }
        RadioButton fuma2 = (RadioButton) findViewById(R.id.fuma_sim);
        boolean fumaChecked2 = fuma2.isChecked();
        if (fumaChecked2) {
            points = points + 0;
        }
        RadioButton exer1 = (RadioButton) findViewById(R.id.exer_0);
        boolean exerChecked1 = exer1.isChecked();
        if (exerChecked1) {
            points = points + 0;
        }
        RadioButton exer2 = (RadioButton) findViewById(R.id.exer_3);
        boolean exerChecked2 = exer2.isChecked();
        if (exerChecked2) {
            points = points + 1;
        }
        RadioButton exer3 = (RadioButton) findViewById(R.id.exer_6);
        boolean exerChecked3 = exer3.isChecked();
        if (exerChecked3) {
            points = points + 2;
        }
        RadioButton sono1 = (RadioButton) findViewById(R.id.sono_menos_8);
        boolean sonoChecked1 = sono1.isChecked();
        if (sonoChecked1) {
            points = points + 0;
        }
        RadioButton sono2 = (RadioButton) findViewById(R.id.sono_mais_8);
        boolean sonoChecked2 = sono2.isChecked();
        if (sonoChecked2) {
            points = points + 1;
        }
        RadioButton agua1 = (RadioButton) findViewById(R.id.agua_2);
        boolean aguaChecked1 = agua1.isChecked();
        if (aguaChecked1) {
            points = points + 0;
        }
        RadioButton agua2 = (RadioButton) findViewById(R.id.agua_4);
        boolean aguaChecked2 = agua2.isChecked();
        if (aguaChecked2) {
            points = points + 1;
        }
        RadioButton agua3 = (RadioButton) findViewById(R.id.agua_8);
        boolean aguaChecked3 = agua3.isChecked();
        if (aguaChecked3) {
            points = points + 2;
        }
        RadioButton bebida1 = (RadioButton) findViewById(R.id.bebida_sim);
        boolean bebidaChecked1 = bebida1.isChecked();
        if (bebidaChecked1) {
            points = points + 0;
        }
        RadioButton bebida2 = (RadioButton) findViewById(R.id.bebida_nao);
        boolean bebidaChecked2 = bebida2.isChecked();
        if (bebidaChecked2) {
            points = points + 1;
        }
        return points;
    }
}