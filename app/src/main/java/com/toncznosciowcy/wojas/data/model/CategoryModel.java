package com.toncznosciowcy.wojas.data.model;

/**
 * Created by Grzesiek on 2015-03-15.
 */
public class CategoryModel extends AbstractModel {
    // Table Names
    public static final String TABLE_CATEGORIES = "categories";
    public static final String TABLE_CATEGORIES_LIST = "categories_list";

    // Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";

    // CATEGORY Table - column names
    public static final String KEY_CAT_ID = "cat_id";
    public static final String KEY_PICTURE = "picture";
    public static final String KEY_TYPE = "type";

    // CATEGORIES_LIST Table - column names
    public static final String KEY_CAT_LIST_ID = "cat_list_id";
    public static final String KEY_CAT_LIST_FROM_ID = "cat_id_from";
    public static final String KEY_CAT_LIST_TO_ID = "cat_id_to";

    // Table Create Statements
    // CATEGORIES table create statement
    public static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE " + TABLE_CATEGORIES
            + "(" + KEY_CAT_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PICTURE + " BLOB," + ")";

    // CATEGORIES table create statement
    public static final String CREATE_TABLE_CATEGORIES_LIST = "CREATE TABLE " + TABLE_CATEGORIES_LIST
            + "(" + KEY_CAT_LIST_ID + " INTEGER PRIMARY KEY," + KEY_CAT_LIST_FROM_ID + " INTEGER ," + KEY_CAT_LIST_TO_ID + " INTEGER ," + ")";

    @Override
    public String getCreateSQL() {
        return CREATE_TABLE_CATEGORIES + CREATE_TABLE_CATEGORIES_LIST;
    }

    @Override
    public String getDropSQL() {
        return "DROP TABLE IF EXISTS " + TABLE_CATEGORIES + ", " + TABLE_CATEGORIES_LIST;
    }
}
