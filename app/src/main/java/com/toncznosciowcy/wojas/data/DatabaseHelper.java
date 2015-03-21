package com.toncznosciowcy.wojas.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.toncznosciowcy.wojas.data.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grzesiek on 2015-03-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 8;

    // Database Name
    private static final String DATABASE_NAME = "wojas.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

        private CategoryModel categoryModel = new CategoryModel();

        @Override
        public void onCreate(SQLiteDatabase db) {
            // creating required tables
            db.execSQL(
            "CREATE TABLE categories (\n" +
                    "    cat_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                    "    cat_name TEXT NOT NULL,\n" +
                    "    cat_picture TEXT\n" +
                    ");" );
            db.execSQL("CREATE TABLE categories_list (\n" +
                            "    cli_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
                            "    cli_fk_cat_id_from INTEGER NOT NULL,\n" +
                            "    cli_fk_cat_id_to  INTEGER NOT NULL,\n" +
                            "    FOREIGN KEY(cli_fk_cat_id_from) REFERENCES categories(cat_id),\n" +
                            "    FOREIGN KEY(cli_fk_cat_id_to) REFERENCES categories(cat_id)\n" +
                            ");");
            db.execSQL(
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
            Log.i("DatabaseHelper", "All tables created SUCCESSFULLY");
            /*VALUES*/
            db.execSQL("INSERT INTO categories (cat_name, cat_id, cat_picture) VALUES " +
                    "('Ona', 1, 'drawable/ona'), ('On', 2, 'drawable/on');");
            db.execSQL("INSERT INTO categories (cat_name, cat_id) VALUES " +
                    /*"('Ona', 1 ), " +*/
                        "('Topy', 3 ), " +
                            "('Vests', 4 ), ( 'Krótki rękaw', 5 ), ( 'Długi rękaw', 6 ), ( 'Krótie topy', 7 ), " +
                        "('Spodnie', 8), " +
                            "('Rurki', 9), ('Luźne', 10), ('Spodnie chinos', 11), ('Eleganckie', 12), ('Legginsy', 13), ('Kombinezony', 14), ('Joggersy', 15), " +
                    /*ON*/
                    /*"('On', 2), " +*/
                        "('Koszule', 16), " +
                            "('W sytlu casual', 17), " +
                                            "('Krótki rękaw', 18), ('Długi rękaw', 19), " +
                            "('Eleganckie', 20), " +
                        "('Spodnie', 21), " +
                            "('W stylu causal', 22), ('Eleganckie', 23), ('Spodnie chinos', 24);");
            Log.i("DatabaseHelper", "Table Categories filled with data SUCCESSFULLY");
            db.execSQL("INSERT INTO categories_list (cli_fk_cat_id_from, cli_fk_cat_id_to) VALUES " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Ona'), (SELECT cat_id FROM categories WHERE cat_name='Topy')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Topy'), (SELECT cat_id FROM categories WHERE cat_name='Vests')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Topy'), (SELECT cat_id FROM categories WHERE cat_name='Krótki rękaw' AND cat_id=5)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Topy'), (SELECT cat_id FROM categories WHERE cat_name='Długi rękaw' AND cat_id=6)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Topy'), (SELECT cat_id FROM categories WHERE cat_name='Krótie topy')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Ona'), (SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8), (SELECT cat_id FROM categories WHERE cat_name='Rurki')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8), (SELECT cat_id FROM categories WHERE cat_name='Luźne')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8), (SELECT cat_id FROM categories WHERE cat_name='Spodnie chinos')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8), (SELECT cat_id FROM categories WHERE cat_name='Eleganckie')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8), (SELECT cat_id FROM categories WHERE cat_name='Legginsy')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8), (SELECT cat_id FROM categories WHERE cat_name='Kombinezony')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=8), (SELECT cat_id FROM categories WHERE cat_name='Joggersy')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='On'), (SELECT cat_id FROM categories WHERE cat_name='Koszule')), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Koszule'), (SELECT cat_id FROM categories WHERE cat_name='W sytlu casual' AND cat_id=17)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='W sytlu casual'), (SELECT cat_id FROM categories WHERE cat_name='Krótki rękaw' AND cat_id=18)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='W sytlu casual'), (SELECT cat_id FROM categories WHERE cat_name='Długi rękaw' AND cat_id=19)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Koszule'), (SELECT cat_id FROM categories WHERE cat_name='Eleganckie' AND cat_id=20)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='On'), (SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=21)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=21), (SELECT cat_id FROM categories WHERE cat_name='W stylu causal' AND cat_id=22)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=21), (SELECT cat_id FROM categories WHERE cat_name='Eleganckie' AND cat_id=23)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Spodnie' AND cat_id=21), (SELECT cat_id FROM categories WHERE cat_name='Spodnie chinos' AND cat_id=24)), " +
                    "((SELECT cat_id FROM categories WHERE cat_name='Topy'), (SELECT cat_id FROM categories WHERE cat_name='Topy'));");
            Log.i("DatabaseHelper", "Table categories_list filled with data SUCCESSFULLY");
    }

    public List<CategoryData> getRootCategories () {
        String selectQuery = "SELECT cat_id, cat_name, cat_picture FROM categories WHERE (SELECT COUNT (cli_fk_cat_id_to) FROM categories_list WHERE cli_fk_cat_id_to=cat_id ) = 0";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return getCategories(cursor);
    }

    public List<CategoryData> getSubCategories (Integer catId) {
        String selectQuery = "SELECT cat_id, cat_name, cat_picture FROM categories WHERE cat_id IN (SELECT cli_fk_cat_id_to FROM categories_list WHERE cli_fk_cat_id_from = " + catId + ")";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return getCategories(cursor);
    }

    public List<CategoryData> getAllCategories () {
        String selectQuery = "SELECT cat_id, cat_name, cat_picture FROM categories";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return getCategories(cursor);
    }

    private List<CategoryData> getCategories (Cursor cursor) {
        ArrayList<CategoryData> categories = new ArrayList<CategoryData>();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                CategoryData category = new CategoryData();
                category.setId(Integer.getInteger(cursor.getString(0)));
                category.setName(cursor.getString(1));
                category.setImage(cursor.getString(2));
                categories.add(category);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        Log.i("DatabaseHelper", "onUpgrade Starts");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS categories_list");
        db.execSQL("DROP TABLE IF EXISTS colors");
        db.execSQL("DROP TABLE IF EXISTS pictures");
        db.execSQL("DROP TABLE IF EXISTS sizes");
        db.execSQL("DROP TABLE IF EXISTS colors_pictures");
        db.execSQL("DROP TABLE IF EXISTS products");
        db.execSQL("DROP TABLE IF EXISTS products_colors");
        Log.i("DatabaseHelper", "All tables dropped");
        // create new tables
        onCreate(db);
    }
}
