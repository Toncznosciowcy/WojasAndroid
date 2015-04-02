package com.toncznosciowcy.wojas.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.toncznosciowcy.wojas.Categories;
import com.toncznosciowcy.wojas.R;

import java.util.List;

//TODO: implement Categories class
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private ContextThemeWrapper contextThemeWrapper;
    private List<CategoryData> categoriesList;
    private CategoryClickListener categoryClickListener;

    public interface CategoryClickListener {
        public void categoryClicked(Integer catId);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "Cat_Adapter ViewHolder";
        public TextView categoryName;
        public ImageButton image;
        private CategoriesAdapter adapter;

        public ViewHolder(LinearLayout container, TextView categoryName, ImageButton image, CategoriesAdapter adapter) {
            super(container);
            this.categoryName = categoryName;
            this.image = image;
            this.adapter = adapter;
            container.setOnClickListener(this);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick " + getPosition() + " " + categoryName.getText());
            if (categoryClickListener != null) {
                categoryClickListener.categoryClicked(categoriesList.get(getPosition()).getId());
            }
        }
    }

    public CategoriesAdapter (List<CategoryData> categoriesList, ContextThemeWrapper contextThemeWrapper) {
        this.categoriesList = categoriesList;
        this.contextThemeWrapper = contextThemeWrapper;
    }

    @Override
    public CategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        LinearLayout container = (LinearLayout)v.findViewById(R.id.itemCategory_container);
        TextView categoryText = (TextView) v.findViewById(R.id.itemCategory_textCategory);
        ImageButton categoryImage = (ImageButton) v.findViewById(R.id.itemCategory_image);
        return new ViewHolder(container, categoryText, categoryImage, this);
    }

    @Override
    public void onBindViewHolder(CategoriesAdapter.ViewHolder holder, int position) {
        CategoryData category = categoriesList.get(position);
        holder.categoryName.setText(category.getName());
        if (category.getImage() != null) {
            int imageResource = contextThemeWrapper.getResources().getIdentifier(category.getImage(), null, contextThemeWrapper.getPackageName());
            holder.image.setImageDrawable(contextThemeWrapper.getResources().getDrawable(imageResource));
        }
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public void setCategoryClickListener(CategoryClickListener categoryClickListener) {
        this.categoryClickListener = categoryClickListener;
    }

    public List<CategoryData> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(List<CategoryData> categoriesList) {
        this.categoriesList = categoriesList;
    }
}
