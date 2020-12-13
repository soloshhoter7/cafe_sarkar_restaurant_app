package com.shubham.cafesarkar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.shubham.cafesarkar.Models.FoodItem;

import java.util.ArrayList;

public class AddItemDialog extends AppCompatDialogFragment{
//    public clickInterfaceHelper clickListener
    private EditText itemName;
    private EditText sellingPrice;
    private EditText costPrice;
    custom_dialogInterface dialogInterface;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
//        AppCompatDialogFragment myDialogFragment = getFragmentManager().getFragment();
//        myDialogFragment.setTargetFragment(this,0);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_item_dialog,null);
        final Spinner categorySpinner = (Spinner)view.findViewById(R.id.add_category_spinner);
        ArrayList<String> listGroup = new ArrayList<>();
        listGroup.add("Tikka");
        listGroup.add("Chinese");
        listGroup.add("Beverage");
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listGroup);

        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        itemName = view.findViewById(R.id.add_item_name);
        sellingPrice = view.findViewById(R.id.add_item_sp);
        costPrice = view.findViewById(R.id.add_item_cp);

        builder.setView(view).setTitle("Add Menu item").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FoodItem foodItem = new FoodItem();
                        foodItem.setName(itemName.getText().toString());
                        foodItem.setCostPrice(Integer.valueOf(costPrice.getText().toString()));
                        foodItem.setSellingPrice(Integer.valueOf(sellingPrice.getText().toString()));
                        foodItem.setCategory(categorySpinner.getSelectedItem().toString());
                        dialogInterface.sendData(foodItem);
                    }
                });


        return builder.create();
    }

//    @Override
//    public void onAttach(@NonNull Context context){
//        super.onAttach(getContext());
//        dialogInterface = (custom_dialogInterface)getTargetFragment();
//    }

    public interface custom_dialogInterface{
        void sendData(FoodItem item);
    }
}
