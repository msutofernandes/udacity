package com.example.android.tourguideapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ParksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Parque Ibirapuera", "Ibirapuera", "$$", R.drawable.park1));
        items.add(new Item("Jardim Botânico", "Parque do Estado", "$", R.drawable.park2));
        items.add(new Item("Zoológico de São Paulo", "Vila Agua Funda", "$$", R.drawable.park3));
        items.add(new Item("Parque Villa Lobos", "Pinheiros", "$$$", R.drawable.park4));
        items.add(new Item("Parque da Independência", "Ipiranga", "$", R.drawable.park5));
        items.add(new Item("Instituto Butantan", "Butantã", "$", R.drawable.park6));
        items.add(new Item("Horto Florestal", "Horto Florestal", "$$", R.drawable.park7));
        items.add(new Item("Parque Tenente Siqueira Campos", "Trianon", "$$", R.drawable.park8));

        ItemAdapter itemAdapter = new ItemAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);
    }
}
