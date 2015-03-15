package com.toncznosciowcy.wojas.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.toncznosciowcy.wojas.data.model.CategoryModel;

/**
 * Created by Grzesiek on 2015-03-15.
 */
public class CategoryData {
    private Long id;
    private String name; /*TODO: how to store multi language values?*/
    private boolean type;
    private byte[] image;

    private final String LOG_MESSAGE="CategoryData";

    public CategoryData () {}

    public CategoryData (Long id, Context context) {
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + CategoryModel.TABLE_CATEGORIES + " WHERE "
                + CategoryModel.KEY_CAT_ID + " = " + id;
        Log.e(LOG_MESSAGE, selectQuery);
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            c.moveToFirst();
        }
        this.setId(id);
        this.setName((c.getString(c.getColumnIndex(CategoryModel.KEY_NAME))));
    }

    private void SaveCategory (Context context) {
        //TODO: each time the passing context to methods does not make sense
        /*SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TODO, todo.getNote());
        values.put(KEY_STATUS, todo.getStatus());

        // updating row
        return db.update(TABLE_TODO, values, KEY_ID + " = ?",
                new String[] { String.valueOf(todo.getId()) });*/
    }

    public CategoryData(Long id, String name, boolean type, byte[] image) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
