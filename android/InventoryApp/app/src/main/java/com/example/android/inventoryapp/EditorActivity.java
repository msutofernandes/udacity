package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.ProductContract.ProductEntry;

import java.io.ByteArrayOutputStream;

/**
 * Created by msuto on 29/01/2017.
 */

public class EditorActivity extends AppCompatActivity {

    private static final int FILE_SELECT_CODE = 2;
    private Uri mCurrentProductUri;

    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mQuantityEditText;
    private Button mImageButton;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mNameEditText = (EditText) findViewById(R.id.edit_product);
        mPriceEditText = (EditText) findViewById(R.id.edit_price);
        mQuantityEditText = (EditText) findViewById(R.id.edit_quantity);
        mImageButton = (Button) findViewById(R.id.btn_choose_picture);
        mImageView = (ImageView) findViewById(R.id.image_view);

        mImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonImageClick();
            }
        });

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();

        if(mCurrentProductUri == null) {
            setTitle(R.string.title_new_product);
        }
    }

    private void buttonImageClick() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select a picture"), FILE_SELECT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == FILE_SELECT_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                    mImageView = (ImageView) findViewById(R.id.image_view);
                    mImageView.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveProduct() {
        String nameString = mNameEditText.getText().toString().trim();
        String quantityString = mQuantityEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();

        if (TextUtils.isEmpty(nameString) || TextUtils.isEmpty(quantityString) || TextUtils.isEmpty(priceString)) {
            Toast.makeText(this,"Please complete all the fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.valueOf(quantityString);
        double price = Double.valueOf(priceString);

        if(quantity < 0) {
            Toast.makeText(this,"Insert a real number for the quantity field.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(price < 0.0) {
            Toast.makeText(this,"Insert a real price.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(mImageView.getDrawable() == null) {
            Toast.makeText(this,"Please upload a picture.", Toast.LENGTH_SHORT).show();
            return;
        }

        Bitmap imageBitMap = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        imageBitMap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] imageByteArray = bos.toByteArray();

        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, quantityString);
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, priceString);
        values.put(ProductEntry.COLUMN_PRODUCT_PICTURE, imageByteArray);

        Uri newUri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);

        if (newUri == null) {
            Toast.makeText(this, getString(R.string.editor_insert_product_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.editor_insert_product_successful), Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveProduct();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
