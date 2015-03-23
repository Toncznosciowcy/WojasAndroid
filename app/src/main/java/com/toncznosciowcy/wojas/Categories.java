package com.toncznosciowcy.wojas;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.toncznosciowcy.wojas.data.CategoryData;
import com.toncznosciowcy.wojas.data.DatabaseHelper;

import java.util.List;


public class Categories extends ActionBarActivity {

    private String categoryType;
    private Integer categoryId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Bundle b = getIntent().getExtras();
        categoryId = b.getInt("categoryId");
        reloadCategories();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kategorie, menu);
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

    private void reloadCategories () {
        LinearLayout container = (LinearLayout) this.findViewById(R.id.activityCategories_container);
        container.removeAllViews();
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        List<CategoryData> categories = dbHelper.getSubCategories(categoryId);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (CategoryData category : categories) {
            View categoryItem = inflater.inflate(R.layout.item_category, null);
            categoryItem.setId(category.getId());
            if (category.getImage() != null && !category.getImage().isEmpty()) {
                ImageButton categoryImageButton = (ImageButton) categoryItem.findViewById(R.id.itemCategory_image);
                int imageResource = getResources().getIdentifier(category.getImage(), null, getPackageName());
                categoryImageButton.setImageDrawable(getResources().getDrawable(imageResource));
            }
            TextView categoryText = (TextView) categoryItem.findViewById(R.id.itemCategory_textCategory);
            categoryText.setText(category.getName());
            category.setHasSubCategories(dbHelper.hasSubCategories(category.getId()));
            if (category.hasSubCategories()) {
                //TODO: add icon
            }
            categoryItem.setOnClickListener(onCategoryClick);
            container.addView(categoryItem);
        }
    }

    private View.OnClickListener onCategoryClick = new View.OnClickListener() {
        //TODO: transitions? maybe use another component, ex. collectionView
        @Override
        public void onClick (View view){
            setCategoryId(view.getId());
            reloadCategories();
        }
    };

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
