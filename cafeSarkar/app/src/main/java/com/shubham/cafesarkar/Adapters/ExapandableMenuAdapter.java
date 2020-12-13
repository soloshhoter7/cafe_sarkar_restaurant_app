package com.shubham.cafesarkar.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.shubham.cafesarkar.Models.FoodItem;
import com.shubham.cafesarkar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExapandableMenuAdapter extends BaseExpandableListAdapter {

    Context context;
    private List<String> foodGroups;
    private List<String> originalFoodGroups;
    HashMap<String,List<FoodItem>> foodItems;

    public ExapandableMenuAdapter(Context context,List<String> listGroup,HashMap<String,List<FoodItem>> listItem){
        this.context= context;
        this.foodGroups = listGroup;
        this.originalFoodGroups = listGroup ;
        Log.d("original food groups size",String.valueOf(originalFoodGroups.size()));
        this.foodItems = listItem;
    }

    @Override
    public int getGroupCount() {
        return foodGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.foodItems.get(this.foodGroups.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.foodGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.foodItems.get(this.foodGroups.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group = (String) getGroup((groupPosition));
        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.food_list_group,null);
        }
        TextView groupNameTextView = convertView.findViewById(R.id.food_list_parent);
        groupNameTextView.setText(group);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        FoodItem child = (FoodItem) getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater layoutInflater =(LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.food_menu_item,null);
        }
        TextView foodItemName = convertView.findViewById(R.id.menu_item_name);
        TextView foodCategoryName = convertView.findViewById(R.id.menu_item_category);
        TextView foodItemPrice = convertView.findViewById(R.id.menu_item_price);

        foodItemName.setText(child.getName());
        foodCategoryName.setText(child.getCategory());
        Log.d("details ->",child.toString());
        foodItemPrice.setText("₹ "+String.valueOf(child.getSellingPrice()));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void filterData(String query){
        List<String> originalFoodGroups2 = originalFoodGroups;
        Log.d("original food groups size",String.valueOf(originalFoodGroups.size()));
        query = query.toLowerCase();
        Log.d("Query->",query);
        Log.v("Mylistadapter",String.valueOf(foodGroups.size()));

        originalFoodGroups = originalFoodGroups2;
        Log.d("original food groups size",String.valueOf(originalFoodGroups.size()));
        if(query.isEmpty()){
            Log.d("query is empty","query is empty");
            foodGroups.addAll(originalFoodGroups);
            Log.v("Mylistadapter",String.valueOf(foodGroups.size()));
        }else{
            Log.d("else case",String.valueOf(originalFoodGroups.size()));

            for(String foodGroup:originalFoodGroups){
                Log.d("food group",String.valueOf(originalFoodGroups.indexOf(foodGroup)));
                List<FoodItem> foodItemsList = foodItems.get(originalFoodGroups.indexOf(foodGroup));
//                Log.d("food item List size",String.valueOf(foodItemsList.size()));
                List<FoodItem> newFoodItemList = new ArrayList<FoodItem>();
                for(FoodItem foodItem:foodItemsList){
                    if(foodItem.getName().toLowerCase().contains(query)){
                        Log.d("item found","item addded");
                        newFoodItemList.add(foodItem);
                    }
                }
                if(newFoodItemList.size()>0){
                    foodGroups.clear();
                    String nGroup = foodGroup;
                    Log.d("food group found",foodGroup);
                    foodGroups.add(nGroup);
                }
            }
        }
        Log.v("MyListAdapter",String.valueOf(foodGroups.size()));
        notifyDataSetChanged();
    }
}
