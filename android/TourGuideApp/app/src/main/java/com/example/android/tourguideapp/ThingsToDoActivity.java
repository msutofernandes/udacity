package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ThingsToDoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Parque do Ibirapuera", "Centro de cultura e área verde", "$$", R.drawable.things1));
        items.add(new Item("Museu de Arte", "Amplo acervo de artistas renomados", "$$", R.drawable.things2));
        items.add(new Item("Catavento", "Museu de Ciências e espaço lúdico", "$", R.drawable.things3));
        items.add(new Item("Catedral de São Paulo", "Templo neogótico e mobília italiana", "$$", R.drawable.things4));
        items.add(new Item("Sala São Paulo", "Clássica e imponente casa de concertos", "$$$", R.drawable.things5));
        items.add(new Item("Mercadão", "Hortifruti raros em edifícios histórico", "$$$", R.drawable.things6));
        items.add(new Item("Museu de Arte", "Amplo acervo de artistas renomados", "$", R.drawable.things7));
        items.add(new Item("Centro Cultura Banco do Brasil", "Resnascimento", "$", R.drawable.things8));

        ItemAdapter itemAdapter = new ItemAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

    }
}
