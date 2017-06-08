package com.rahimi.kayvon.pottersmagicalbookbrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BolApiConnector.BolListener, AdapterView.OnItemClickListener{
    //ArrayList with objects according to model
    private ArrayList<Product> productList;
    //ListView to connect xml with code
    private ListView productListView;
    //Searchbutton
    private Button searchBtn;
    //Search Textfield
    private EditText searchFld;
    //String to retrieve from field
    private String entry;
    //Inject fetched objects in list with adapter
    private ArrayAdapter myProductAdapter;

    //Logging tag
    private final String TAG = this.getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect to api when activity starts
        BolApiConnector bolApi = new BolApiConnector(this);
        bolApi.execute("https://api.bol.com/catalog/v4/search/?apikey=25C4742A92BF468EB2BD888FC8FBFF40&format=json&q=potter");

        //Initiate array
        productList = new ArrayList<>();

        //Connect MainActivity with activity.xml element
        productListView = (ListView) findViewById(R.id.productListView);

        //Search textfield + button
        searchFld = (EditText) findViewById(R.id.searchInput);
        searchBtn = (Button) findViewById(R.id.searchButton);
            //ActionListener for button, get text from field
            searchBtn.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 productList.clear();
                                                 entry = searchFld.getText().toString();
                                                 entry.replaceAll("", " + ");
                                                 getProduct();
                                             }
                                         });




        //Create adapter
        myProductAdapter = new ProductAdapter(this, productList);
        //bind adapter to listView
        productListView.setAdapter(myProductAdapter);
        //ActionListener
        productListView.setOnItemClickListener(this);

//        //Notify when data has changed
//        myProductAdapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.i(TAG, "onItemClick()");
        //Select Product
        Product selectedProduct = (Product) productList.get(position);

       //Get new intent, open ProductDetailActivity
        Intent i = new Intent(getApplicationContext(), ProductDetailActivity.class);
        i.putExtra("Product", selectedProduct);
        startActivity(i);
    }

    //Method to execute bol api search with entry from textfield.
    public void getProduct() {
        BolApiConnector task = new BolApiConnector(this);
        String[] urls = new String[]{"https://api.bol.com/catalog/v4/search/?apikey=25C4742A92BF468EB2BD888FC8FBFF40&format=json&q="+ entry};
        task.execute(urls);
    }

    @Override
    public void onProductAvailable(Product product) {
        // Toevoegen array
        productList.add(product);
        Log.i(TAG, "Product fetched (" + product.toString() + ")");
        myProductAdapter.notifyDataSetChanged();
    }
}
