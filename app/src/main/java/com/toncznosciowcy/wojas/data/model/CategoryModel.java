package com.toncznosciowcy.wojas.data.model;

public class CategoryModel extends AbstractModel {
    // Table Names
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_CATEGORIES_LIST = "categories_list";

    // Common column names
    public static final String KEY_ID = "id";


    // CATEGORY Table - column names
    public static final String KEY_CAT_ID = "cat_id";
    public static final String KEY_CAT_NAME = "cat_name";
    public static final String KEY_CAT_PICTURE = "cat_picture";

    // CATEGORIES_LIST Table - column names
    public static final String KEY_CAT_LIST_ID = "cli_id";
    public static final String KEY_CAT_LIST_FROM_ID = "cli_fk_cat_id_from";
    public static final String KEY_CAT_LIST_TO_ID = "cli_fk_cat_id_to";

    // Table Create Statements
    // CATEGORIES table create statement
    public static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES
            + "(" + KEY_CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_CAT_NAME + " TEXT NOT NULL," + KEY_CAT_PICTURE + " BLOB" + ")";

    // CATEGORIES table create statement
    public static final String CREATE_TABLE_CATEGORIES_LIST = "CREATE TABLE " + TABLE_CATEGORIES_LIST
            + "(" + KEY_CAT_LIST_ID + " INTEGER PRIMARY KEY NOT NULL," + KEY_CAT_LIST_FROM_ID + " INTEGER NOT NULL," + KEY_CAT_LIST_TO_ID + " INTEGER NOT NULL," +
            "FOREIGN KEY(cli_fk_cat_id_from) REFERENCES categories(cat_id)," +
            "FOREIGN KEY(cli_fk_cat_id_to) REFERENCES categories(cat_id)" + ");";

    @Override
    public String getCreateSQL() {
        return CREATE_TABLE_CATEGORIES + " " + CREATE_TABLE_CATEGORIES_LIST;
    }

    @Override
    public String getDropSQL() {
        return "DROP TABLE IF EXISTS " + TABLE_CATEGORIES + ", " + TABLE_CATEGORIES_LIST;
    }

    @Override
    public String getDataSQL() {
        //TODO: move inserts from DatabaseHelper class
        return null;
    }

}
