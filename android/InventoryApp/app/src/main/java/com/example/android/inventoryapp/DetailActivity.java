package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;

/**
 * Created by msuto on 30/01/2017.
 */

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

        private static final int PRODUCT_LOADER = 1;
        private Uri mCurrentProductUri;

        Button mIncreaseButton;
        Button mDecreaseButton;
        Button mOrderButton;

        TextView mNameTextView;
        TextView mQuantityTextView;
        TextView mPriceTextView;

        ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mNameTextView = (TextView) findViewById(R.id.text_view_product);
        mPriceTextView = (TextView) findViewById(R.id.text_view_price);
        mQuantityTextView = (TextView) findViewById(R.id.text_view_quantity);
        mIncreaseButton = (Button) findViewById(R.id.increase_btn);
        mDecreaseButton = (Button) findViewById(R.id.decrease_btn);
        mOrderButton = (Button) findViewById(R.id.order_btn);
        mImageView = (ImageView) findViewById(R.id.image_view);

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        if(mCurrentProductUri != null) {
            setTitle(R.string.title_edit_product);
            getSupportLoaderManager().initLoader(PRODUCT_LOADER, null, this);
        }
    }

    private int deleteProduct(Uri itemUri) {
        return getContentResolver().delete(itemUri, null, null);
    }

    private int adjustQuantity(Uri itemUri, int newQuantity) {

        if(newQuantity < 0) {
            return 0;
        }

        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, newQuantity );
        int numRowsUpdated = getContentResolver().update(itemUri, values, null, null);
        return numRowsUpdated;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, mCurrentProductUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PICTURE);

            final String productName = cursor.getString(nameColumnIndex);
            double productPrice = cursor.getDouble(priceColumnIndex);
            final int productQuantity = cursor.getInt(quantityColumnIndex);
            byte[] imageByteArray = cursor.getBlob(imageColumnIndex);

            mNameTextView.setText(productName);
            mPriceTextView.setText("$" + String.format("%.2f", productPrice));
            mQuantityTextView.setText(Integer.toString(productQuantity));
            Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            mImageView.setImageBitmap(bmp);

            mIncreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adjustQuantity(mCurrentProductUri,productQuantity+1);
                }
            });

            mDecreaseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adjustQuantity(mCurrentProductUri,productQuantity-1);
                }
            });

            mOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    composeEmail(new String[]{"makeorders@gmail.com"}, "Supply Order for " + productName);
                }
            });

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mCurrentProductUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case R.id.action_save:
                NavUtils.navigateUpFromSameTask(DetailActivity.this);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(DetailActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProduct(mCurrentProductUri);
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}