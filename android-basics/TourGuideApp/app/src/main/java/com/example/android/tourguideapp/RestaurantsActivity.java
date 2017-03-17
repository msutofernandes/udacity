package com.example.android.tourguideapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Varanda Grill", "Churrascaria", "$$$$", R.drawable.eat1));
        items.add(new Item("Barbacoa Itaim", "Restaurante/Bar", "$$$", R.drawable.eat2));
        items.add(new Item("Ristorantino", "Comida italiana", "$$", R.drawable.eat3));
        items.add(new Item("La Tambouille", "Bistrô", "$$", R.drawable.eat4));
        items.add(new Item("Jam - Jardins", "Comida japonesa", "$$$", R.drawable.eat5));
        items.add(new Item("A Bela Sintra", "Comtemporânea", "$$", R.drawable.eat6));
        items.add(new Item("Jam - Itaim Bibi", "Comida japonesa", "$$$$", R.drawable.eat7));
        items.add(new Item("Restaurante Fasano", "Clássico", "$$$$", R.drawable.eat8));

        ItemAdapter itemAdapter = new ItemAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

    }
}
