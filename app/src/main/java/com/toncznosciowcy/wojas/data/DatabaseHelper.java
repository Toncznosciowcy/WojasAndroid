package com.toncznosciowcy.wojas.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.toncznosciowcy.wojas.data.model.CategoryModel;

/**
 * Created by Grzesiek on 2015-03-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "wojas.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private CategoryModel categoryModel = new CategoryModel();

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(categoryModel.getCreateSQL());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL(categoryModel.getDropSQL());

        // create new tables
        onCreate(db);
    }
}
