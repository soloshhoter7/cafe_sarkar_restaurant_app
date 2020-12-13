package com.shubham.cafesarkar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.shubham.cafesarkar.Adapters.ExapandableMenuAdapter;
import com.shubham.cafesarkar.Adapters.FoodMenuAdapter;
import com.shubham.cafesarkar.Models.FoodItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantMenu extends Fragment implements SearchView.OnQueryTextListener,SearchView.OnCloseListener,AddItemDialog.custom_dialogInterface {
    private SearchView search;
    ListView listView ;
    FloatingActionButton actionButton;
    ExapandableMenuAdapter adapter;
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<FoodItem>> listItems;
    public FoodItem foodItemAdded;
    private ArrayList<FoodItem> foodItems = new ArrayList<FoodItem>();
    private ArrayList<FoodItem> tikkaItems = new ArrayList<FoodItem>();
    private ArrayList<FoodItem> chineseItems = new ArrayList<FoodItem>();
    private ArrayList<FoodItem> beveragesItems = new ArrayList<FoodItem>();

    private Context mContext;
    public RestaurantMenu() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_restaurant_menu,container,false);
        expandableListView = (ExpandableListView)view.findViewById(R.id.expandable_menu);
        actionButton = (FloatingActionButton)view.findViewById(R.id.bt_float) ;
        SearchManager searchManager =(SearchManager)mContext.getSystemService(Context.SEARCH_SERVICE);
        search =(SearchView)view.findViewById(R.id.search_menu);
        search.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setOnCloseListener(this);
        search.setQueryHint("Search menu");

        listGroup = new ArrayList<>();
        listItems = new HashMap<>();
        initMenuData();


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("clicked","clicked");
                openDialog();
            }
        });
//        ListView listView = (ListView)view.findViewById(R.id.restaurant_menu_list);
//        createMenu();
//        FoodMenuAdapter adapter = new FoodMenuAdapter(mContext,foodItems);
//        listView.setAdapter(adapter);

//        ListView tikkaListView = (ListView)view.findViewById(R.id.restaurant_menu_tikka_list);
//        ListView chineseListView = (ListView)view.findViewById(R.id.restaurant_menu_chinese_list);
//        ListView beveragesListView = (ListView)view.findViewById(R.id.restaurant_menu_beverages_list);
//        //for initialising the menu
//        createMenu();
//        //for displaying tikka items
////        FoodMenuAdapter tikkaAdapter = new FoodMenuAdapter(mContext,tikkaItems);
////        tikkaListView.setAdapter(tikkaAdapter);
//        //for displaying chinese items
//        FoodMenuAdapter chineseAdapter = new FoodMenuAdapter(mContext,chineseItems);
//        chineseListView.setAdapter(chineseAdapter);
//        //for displaying beverage items
//        FoodMenuAdapter beverageAdapter = new FoodMenuAdapter(mContext,beveragesItems);
//        beveragesListView.setAdapter(beverageAdapter);

        displayList();
//        expandAll();
        return view;
    }
    public void openDialog(){
        AddItemDialog addItemDialog = new AddItemDialog();
        addItemDialog.show(getFragmentManager(),"add item");
    }
    private void expandAll(){
        int count = adapter.getGroupCount();
        for(int i=0;i<count;i++){
         expandableListView.expandGroup(i);
        }
    }

    private void displayList(){

        adapter = new ExapandableMenuAdapter(mContext,listGroup,listItems);
        expandableListView.setAdapter(adapter);
    }
    private void initMenuData(){
        createMenu();
        listGroup.add("Tikka");
        listGroup.add("Chinese");
        listGroup.add("Beverage");
        listItems.put(listGroup.get(0),tikkaItems);
        listItems.put(listGroup.get(1),chineseItems);
        listItems.put(listGroup.get(2),beveragesItems);

    }
    public void createMenu(){
        for(int i=0;i<6;i++) {
            FoodItem foodItem = new FoodItem();
            foodItem.setName("Malai chaap");
            foodItem.setCategory("tikka");
            foodItem.setSellingPrice(100);
            tikkaItems.add(foodItem);
        }
        for(int i=0;i<5;i++) {
            FoodItem foodItem = new FoodItem();
            foodItem.setName("Kurkure momos");
            foodItem.setCategory("chinese");
            foodItem.setSellingPrice(80);
            chineseItems.add(foodItem);
        }
        for(int i=0;i<3;i++) {
            FoodItem foodItem = new FoodItem();
            foodItem.setName("Soda");
            foodItem.setCategory("beverage");
            foodItem.setSellingPrice(20);
            beveragesItems.add(foodItem);
        }
    }

    @Override
    public boolean onClose() {
        adapter.filterData("");
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.filterData(query);
        expandAll();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filterData(newText);
        expandAll();
        return false;
    }

    @Override
    public void sendData(FoodItem item) {
        Log.d("food item ->",item.toString());
    }
}
