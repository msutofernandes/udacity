package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;

import static com.example.android.inventoryapp.R.id.price;

/**
 * Created by msuto on 30/01/2017.
 */

public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        TextView productTextView = (TextView) view.findViewById(R.id.product);
        TextView priceTextView = (TextView) view.findViewById(price);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);

        final int productId = cursor.getInt(cursor.getColumnIndex(ProductEntry._ID));
        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        final int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);

        String productName = cursor.getString(nameColumnIndex);
        Double productPrice = cursor.getDouble(priceColumnIndex);
        final int productQuantity = cursor.getInt(quantityColumnIndex);

        productTextView.setText(productName);
        priceTextView.setText("Price: $" + String.format("%.2f", productPrice));
        quantityTextView.setText("Quantity: " + String.valueOf(productQuantity));

        Button btnBuy = (Button) view.findViewById(R.id.buy_button);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri itemUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, productId);
                    saleProduct(context, itemUri, productQuantity);
            }
        });

    }

    private void saleProduct(Context context, Uri itemUri, int currentQuantity) {
        int newQuantity;
        if (currentQuantity >= 1) {
            newQuantity = currentQuantity - 1;
        } else {
            newQuantity = 0;
        }
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, newQuantity );
        context.getContentResolver().update(itemUri, values, null, null);

    }

}
