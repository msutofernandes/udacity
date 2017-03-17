package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittracker.data.DbHelper;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

public class MainActivity extends AppCompatActivity {

    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new DbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_DESC,
                HabitEntry.COLUMN_CATEGORY};

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        TextView displayView = (TextView) findViewById(R.id.habits);

        try {
            displayView.setText("You have entered " + cursor.getCount() + " habits.\n\n");

            int habitColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DESC);
            int categoryColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_CATEGORY);

            while (cursor.moveToNext()) {
                String currentHabit = cursor.getString(habitColumnIndex);
                String currentCategory = cursor.getString(categoryColumnIndex);

                displayView.append(("\n" + currentHabit + " - " + currentCategory));
            }
        } finally {
            cursor.close();
        }
    }
}
