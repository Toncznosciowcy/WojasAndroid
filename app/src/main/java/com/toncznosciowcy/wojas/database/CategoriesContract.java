package com.toncznosciowcy.wojas.database;

import android.provider.BaseColumns;

public final class CategoriesContract {
    /*For safety empty constructor - DO NOT use it as */
    public CategoriesContract () {}

    /* Inner class that defines the table contents */
    public static abstract class Category implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String ID = "category_id";
        public static final String TITLE = "title";
        public static final String SUBTITLE = "subtitle";
    }

    /* Inner class that defines the table contents */
    public static abstract class Product implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String ID = "product_id";
        public static final String TITLE = "title";
        public static final String SUBTITLE = "subtitle";
        public static final String DESCRIPTION = "description";
    }

}
