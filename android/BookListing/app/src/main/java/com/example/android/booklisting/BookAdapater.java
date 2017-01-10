package com.example.android.booklisting;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by msuto on 07/01/2017.
 */

public class BookAdapater extends ArrayAdapter<Book> {

    public BookAdapater(Activity context, ArrayList<Book> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Book currentItem = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.book_title);
        titleTextView.setText(currentItem.getTile());

        TextView publisherTextView = (TextView) listItemView.findViewById(R.id.book_publisher);
        publisherTextView.setText(currentItem.getPublisher());

        return listItemView;

    }
}
