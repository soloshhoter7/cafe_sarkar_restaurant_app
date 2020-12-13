package com.shubham.cafesarkar.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.shubham.cafesarkar.Models.FoodItem;
import com.shubham.cafesarkar.R;

import java.util.ArrayList;

public class FoodMenuAdapter extends BaseAdapter {

    private ArrayList<FoodItem> foodItems;
    private LayoutInflater mInflater;

    public FoodMenuAdapter(@NonNull Context c, ArrayList<FoodItem> foodItems) {
        this.foodItems = foodItems;
        mInflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount () {
        return foodItems.size();
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public Object getItem (int position) {
        return foodItems.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate (R.layout.food_menu_item, parent, false);
        }

        TextView foodItemName = convertView.findViewById(R.id.menu_item_name);
        TextView foodCategoryName = convertView.findViewById(R.id.menu_item_category);
        TextView foodItemPrice = convertView.findViewById(R.id.menu_item_price);

        foodItemName.setText(foodItems.get(position).getName());
        foodCategoryName.setText(foodItems.get(position).getCategory());
        Log.d("details ->",String.valueOf(position)+"->"+foodItems.get(position).getName());
        foodItemPrice.setText("₹ "+String.valueOf(foodItems.get(position).getSellingPrice()));

        return convertView;
    }
//    private ArrayList<FoodItem> foodItems;
//
//    public FoodMenuAdapter(@NonNull Context context, int resource, ArrayList<FoodItem> foodItems) {
//        super(context, resource);
//        this.foodItems = foodItems;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        int phraseIndex = position;
//        if(convertView == null){
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_menu_item,parent,false);
//        }
//        TextView foodItemName = convertView.findViewById(R.id.menu_item_name);
//        TextView foodCategoryName = convertView.findViewById(R.id.menu_item_category);
//        TextView foodItemPrice = convertView.findViewById(R.id.menu_item_price);
//
//        foodItemName.setText(foodItems.get(position).getName());
//        foodCategoryName.setText(foodItems.get(position).getCategory());
//        foodItemPrice.setText(foodItems.get(position).getSellingPrice());
//        Log.d("details ->",String.valueOf(position)+"->"+foodItems.get(position).getName());
//        return super.getView(position, convertView, parent);
//    }

}
