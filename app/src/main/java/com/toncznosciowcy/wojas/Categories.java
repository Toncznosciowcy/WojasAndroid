package com.toncznosciowcy.wojas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.toncznosciowcy.wojas.data.CategoriesAdapter;
import com.toncznosciowcy.wojas.data.DatabaseHelper;


public class Categories extends ActionBarActivity implements CategoriesAdapter.CategoryClickListener {

    private String categoryType;
    private Integer categoryId;

    private RecyclerView recyclerView;
    private CategoriesAdapter categoriesAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Bundle b = getIntent().getExtras();
        categoryId = b.getInt("categoryId");
        dbHelper = new DatabaseHelper(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.category_activity_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        categoriesAdapter = new CategoriesAdapter(dbHelper.getSubCategories(categoryId), this);
        categoriesAdapter.setCategoryClickListener(this);
        recyclerView.setAdapter(categoriesAdapter);
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

    @Override
    public void categoryClicked(Integer catId) {
        //categoriesAdapter = new CategoriesAdapter(dbHelper.getSubCategories(catId), this);
        //recyclerView.swapAdapter(categoriesAdapter, false);
        categoriesAdapter.setCategoriesList(dbHelper.getSubCategories(catId));
        categoriesAdapter.notifyDataSetChanged();
    }
}
