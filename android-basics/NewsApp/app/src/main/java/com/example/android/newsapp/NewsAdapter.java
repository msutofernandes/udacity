package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by msuto on 18/01/2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> newslist) {
        super(context, 0, newslist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        News currentItem = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.news_title);
        titleTextView.setText(currentItem.getTitle());

        TextView authorTextView = (TextView) listItemView.findViewById(R.id.news_section);
        authorTextView.setText(currentItem.getSection());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.news_date);
        dateTextView.setText(currentItem.getDate());

        return listItemView;

    }
}

