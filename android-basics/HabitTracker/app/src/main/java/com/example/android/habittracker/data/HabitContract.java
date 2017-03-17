package com.example.android.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by msuto on 23/01/2017.
 */

public class HabitContract {

    private HabitContract(){}

    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_HABIT_DESC ="habit_desc";
        public final static String COLUMN_CATEGORY = "category";
        public static final int CATEG_STUDY = 0;
        public static final int CATEG_EXER = 1;
        public static final int CATEG_EAT = 2;
        public static final int CATEG_BUDGET = 3;
        public static final int CATEG_SLEEP = 4;
    }
}
