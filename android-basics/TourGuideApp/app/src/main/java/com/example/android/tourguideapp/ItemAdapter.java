package com.example.android.tourguideapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {

    private static final String LOG_TAG = ItemAdapter.class.getSimpleName();

    public ItemAdapter(Activity context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Item currentItem = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.item_name);
        nameTextView.setText(currentItem.getItemName());

        TextView descTextView = (TextView) listItemView.findViewById(R.id.item_desc);
        descTextView.setText(currentItem.getItemDescription());

        TextView priceTextView = (TextView) listItemView.findViewById(R.id.price);
        priceTextView.setText(currentItem.getPrice());

        ImageView iconView = (ImageView) listItemView.findViewById(R.id.icon);
        iconView.setImageResource(currentItem.getImageResourceId());

        return listItemView;




    }


}
