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

        db.execSQL("CREATE TABLE categories (\n" +
                "    cat_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    cat_name TEXT NOT NULL,\n" +
                "    cat_picture BLOB NOT NULL,\n" +
                "    cat_type TEXT NOT NULL\n" +
                ");\n" +
                "CREATE TABLE categories_list (\n" +
                "    cli_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    cli_fk_cat_id_from INTEGER NOT NULL,\n" +
                "    cli_fk_cat_id_to  INTEGER NOT NULL,\n" +
                "    FOREIGN KEY(cli_fk_cat_id_from) REFERENCES categories(cat_id),\n" +
                "    FOREIGN KEY(cli_fk_cat_id_to) REFERENCES categories(cat_id)\n" +
                ");\n" +
                "CREATE TABLE colors (\n" +
                "    col_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    col_name TEXT NOT NULL\n" +
                ");\n" +
                "CREATE TABLE pictures (\n" +
                "    pic_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    pic_blob BLOB NOT NULL\n" +
                ");\n" +
                "CREATE TABLE sizes (\n" +
                "    siz_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    siz_name TEXT NOT NULL\n" +
                ");\n" +
                "CREATE TABLE colors_pictures (\n" +
                "    cop_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    cop_fk_pic_id INTEGER NOT NULL,\n" +
                "    cop_fk_col_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY(cop_fk_pic_id) REFERENCES pictures(pic_id),\n" +
                "    FOREIGN KEY(cop_fk_col_id) REFERENCES colors(col_id)\n" +
                ");\n" +
                "CREATE TABLE products (\n" +
                "    prd_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    prd_fk_cat_id INTEGER NOT NULL,\n" +
                "    prd_fk_size_id  INTEGER NOT NULL,\n" +
                "    prd_name TEXT NOT NULL,\n" +
                "    prd_value INTEGER NOT NULL,\n" +
                "    FOREIGN KEY(prd_fk_cat_id) REFERENCES categories(cat_id),\n" +
                "    FOREIGN KEY(prd_fk_size_id) REFERENCES sizes(siz_id)\n" +
                ");\n" +
                "CREATE TABLE products_colors (\n" +
                "    prc_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                "    prc_fk_prd_id INTEGER NOT NULL,\n" +
                "    prc_fk_col_id INTEGER NOT NULL,\n" +
                "    FOREIGN KEY(prc_fk_prd_id) REFERENCES products(prd_id),\n" +
                "    FOREIGN KEY(prc_fk_col_id) REFERENCES colors(col_id)\n" +
                ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL(categoryModel.getDropSQL());

        // create new tables
        onCreate(db);
    }
}
