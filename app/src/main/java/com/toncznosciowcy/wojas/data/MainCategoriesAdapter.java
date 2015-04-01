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
import android.widget.TableRow;
import android.widget.TextView;

import com.toncznosciowcy.wojas.Categories;
import com.toncznosciowcy.wojas.R;

import java.util.List;

public class MainCategoriesAdapter extends RecyclerView.Adapter<MainCategoriesAdapter.ViewHolder> {
    private ContextThemeWrapper contextThemeWrapper;
    private List<CategoryData> categoriesList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "MainCategory ViewHolder";
        public TextView categoryName;
        public TextView prefixCategoryName;
        public ImageButton image;
        public Integer id;

        public ViewHolder(TableRow container, TextView categoryName, TextView prefixCategoryName, ImageButton image) {
            super(container);
            this.categoryName = categoryName;
            this.prefixCategoryName = prefixCategoryName;
            this.image = image;
            container.setOnClickListener(this);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick " + getPosition() + " " + categoryName);
            Context context = itemView.getContext();
            Intent intent = new Intent(context, Categories.class);
            Bundle b = new Bundle();
            b.putInt("categoryId", id);
            intent.putExtras(b);
            context.startActivity(intent);
        }
    }

    public MainCategoriesAdapter(List<CategoryData> categoriesList, ContextThemeWrapper contextThemeWrapper) {
        this.categoriesList = categoriesList;
        this.contextThemeWrapper = contextThemeWrapper;
    }

    @Override
    public MainCategoriesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_category, parent, false);
        TextView mainCategoryText = (TextView) v.findViewById(R.id.mainCategory_text);
        TextView mainCategoryTextPrefix = (TextView) v.findViewById(R.id.mainCategory_textPrefix);
        ImageButton mainCategoryImage = (ImageButton) v.findViewById(R.id.mainCategory_imageBtn);
        TableRow tableRow = (TableRow)v.findViewById(R.id.mainCategory_row);
        return new ViewHolder(tableRow, mainCategoryText, mainCategoryTextPrefix, mainCategoryImage);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CategoryData category = categoriesList.get(position);
        holder.categoryName.setText(category.getName());
        holder.id = category.getId();
        if (category.getPrefixName() != null && !category.getPrefixName().isEmpty()) {
            holder.prefixCategoryName.setText(category.getPrefixName());
        }
        int imageResource = contextThemeWrapper.getResources().getIdentifier(category.getImage(), null, contextThemeWrapper.getPackageName());
        holder.image.setImageDrawable(contextThemeWrapper.getResources().getDrawable(imageResource));
    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }
}
