package com.example.android.tourguideapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class WhereToStayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item("Renaissance Sao Paulo Hotel", "Cerqueira César", "$$$", R.drawable.where1));
        items.add(new Item("Tivoli Mofarrej - São Paulo", "Avenida Paulista", "$$$$", R.drawable.where2));
        items.add(new Item("Quality Suites Long Stay Bela Cintra", "Bela Vista", "$$", R.drawable.where3));
        items.add(new Item("Marriott Executive", "Vila Nova Conceição", "$$$", R.drawable.where4));
        items.add(new Item("Lobo Urban Stay", "Cerqueira César", "$", R.drawable.where5));
        items.add(new Item("Staybridge Suítes São Paulo", "Itaim Bibi", "$$$", R.drawable.where6));
        items.add(new Item("TRYP by Wyndham São Paulo", "GRU Airport", "$$$", R.drawable.where7));
        items.add(new Item("Quality Suites Vila Olímpia", "Vila Olímpia", "$$", R.drawable.where8));

        ItemAdapter itemAdapter = new ItemAdapter(this, items);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);
    }
}
