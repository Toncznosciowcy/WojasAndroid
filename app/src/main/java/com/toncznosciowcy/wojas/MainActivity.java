package com.toncznosciowcy.wojas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import com.toncznosciowcy.wojas.data.CategoryData;
import com.toncznosciowcy.wojas.data.DatabaseHelper;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    LayoutInflater inflater = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_main);
        List<CategoryData> categories = dbHelper.getRootCategories();
        TableLayout container = (TableLayout) this.findViewById(R.id.mainActivity_tableLayout);
        inflater= (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (CategoryData category : categories) {
            View categoryItem = inflater.inflate(R.layout.item_main_category, null);
            ImageButton categoryImageButton = (ImageButton) categoryItem.findViewById(R.id.mainCategory_imageBtn);
            int imageResource = getResources().getIdentifier(category.getImage(), null, getPackageName());
            categoryImageButton.setImageDrawable(getResources().getDrawable(imageResource));
            TextView categoryText = (TextView) categoryItem.findViewById(R.id.mainCategory_text);
            categoryText.setText(category.getName());
            container.addView(categoryItem);
        }
        Bundle parameters = new Bundle();
        findViewById(R.id.mainCategory_row).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Categories.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
