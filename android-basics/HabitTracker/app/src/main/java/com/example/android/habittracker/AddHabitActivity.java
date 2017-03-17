package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.habittracker.data.DbHelper;
import com.example.android.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by msuto on 23/01/2017.
 */

public class AddHabitActivity extends AppCompatActivity {

    private EditText mHabitEditText;
    private Spinner mCategorySpinner;
    private int mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        mHabitEditText = (EditText) findViewById(R.id.edit_habit);
        mCategorySpinner = (Spinner) findViewById(R.id.spinner_category);

        setupSpinner();
    }

    private void setupSpinner() {
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        mCategorySpinner.setAdapter(spinnerAdapter);

        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.cat_study))) {
                        mCategory = HabitEntry.CATEG_STUDY;
                    } else if (selection.equals(getString(R.string.cat_exercise))) {
                        mCategory = HabitEntry.CATEG_EXER;
                    } else if (selection.equals(getString(R.string.cat_eat))) {
                        mCategory = HabitEntry.CATEG_EAT;
                    } else if (selection.equals(getString(R.string.cat_budget)))
                        mCategory = HabitEntry.CATEG_BUDGET;
                } else {
                    mCategory = HabitEntry.CATEG_SLEEP;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void insertHabit() {

        DbHelper mDbHelper = new DbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String habitString =  mHabitEditText.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_DESC, habitString);
        values.put(HabitEntry.COLUMN_CATEGORY, mCategory);

        long rowId = db.insert(HabitEntry.TABLE_NAME, null, values);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertHabit();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
