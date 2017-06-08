package com.rahimi.kayvon.pottersmagicalbookbrowser;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Kayvon Rahimi on 14-3-2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    //Logging tag
    private final String TAG = this.getClass().getSimpleName();


    public ProductAdapter(Context mContext, ArrayList<Product> productList) {
       super(mContext, R.layout.product_listview_row , productList);
    }


    //View class to match java to xml
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        //Logging tag
        Log.i(TAG, "getView() position #" + position);
        //Create product from item in list
        Product selectedProduct = getItem(position);

        //create convertView if not exist
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View myView = mInflater.inflate(R.layout.product_listview_row, viewGroup, false);


        //Bind variables to xml elements
        ImageView thumbImg = (ImageView) myView.findViewById(R.id.productRowImageView);
        TextView title = (TextView) myView.findViewById(R.id.productRowTitle);
        TextView specsTag = (TextView) myView.findViewById(R.id.productRowSpecsTag);
        TextView summary = (TextView) myView.findViewById(R.id.productRowSummary);

        //Bind images to listview with Picasso, text to listview
        Picasso.with(getContext()).load(selectedProduct.getSmallImageUrl()).into(thumbImg);
        title.setText(selectedProduct.getTitle());
        specsTag.setText(selectedProduct.getSpecsTag());
        summary.setText(selectedProduct.getSummary());

        return myView;

    }
}

