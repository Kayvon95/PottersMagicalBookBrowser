package com.rahimi.kayvon.pottersmagicalbookbrowser;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView xlImageView;
    private TextView titleTextView, specsTagTextView, summaryTextView, longDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent i = getIntent();

        Product selectedProduct = (Product) i.getSerializableExtra("Product");

        //view -> cast as - > findByXMLid
        xlImageView = (ImageView) findViewById(R.id.productImageView);
        titleTextView = (TextView) findViewById(R.id.productTitleView);
        specsTagTextView = (TextView) findViewById(R.id.productSpecsTagView);
        summaryTextView = (TextView) findViewById(R.id.productSummaryView);
        longDescriptionTextView = (TextView) findViewById(R.id.productLongDescriptionView);
        //Getters
        Picasso.with(this).load(selectedProduct.getLargeImageUrl()).into(xlImageView);
        titleTextView.setText(selectedProduct.getTitle());
        specsTagTextView.setText(selectedProduct.getSpecsTag());
        summaryTextView.setText(selectedProduct.getSummary());
        longDescriptionTextView.setText(Html.fromHtml(selectedProduct.getLongDescription()));
    }
}
