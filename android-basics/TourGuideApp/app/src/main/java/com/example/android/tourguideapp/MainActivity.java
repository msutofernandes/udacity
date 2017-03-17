package com.example.android.tourguideapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView whereToStay = (TextView) findViewById(R.id.where_to_stay);

        whereToStay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent whereIntent = new Intent(MainActivity.this, WhereToStayActivity.class);
                startActivity(whereIntent);
            }
        });

        TextView thingsToDo = (TextView) findViewById(R.id.things_to_do);

        thingsToDo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent thingsIntent = new Intent(MainActivity.this, ThingsToDoActivity.class);
                startActivity(thingsIntent);
            }
        });

        TextView restaurants = (TextView) findViewById(R.id.restaurants);

        restaurants.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent restaurantsIntent = new Intent(MainActivity.this, RestaurantsActivity.class);
                startActivity(restaurantsIntent);
            }
        });

        TextView parks = (TextView) findViewById(R.id.parks);

        parks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent parksIntent = new Intent(MainActivity.this, ParksActivity.class);
                startActivity(parksIntent);
            }
        });

    }
}
