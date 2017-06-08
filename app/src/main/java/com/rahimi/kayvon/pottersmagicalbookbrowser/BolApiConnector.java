package com.rahimi.kayvon.pottersmagicalbookbrowser;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Kayvon Rahimi on 14-3-2017.
 */

public class BolApiConnector extends AsyncTask<String, Void, String> {
    // Call back
    private BolListener listener = null;
    // Logging tag
    private final String TAG = this.getClass().getSimpleName();

    // Constructor, set listener
    public BolApiConnector(BolListener listener) {
        this.listener = listener;
    }
    @Override

    protected String doInBackground(String... params) {
        //
        InputStream inputStream = null;
        // url fetched from entry
        String productUrl = "";
        // Resulting response
        String response = "";
        BufferedReader reader = null;

        //Try-block to establish connection
        try {
            // Create productURL object
            URL url = new URL(params[0]);
            // Open connection
            URLConnection urlConnection = url.openConnection();
            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while  ((line = reader.readLine()) != null) {
                response += line;
            }

        //Exception catching and logging
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground MalformedURL " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG, "doInBackground IOException " + e.getLocalizedMessage());
            return null;
        }catch (Exception e) {
            Log.e(TAG, "doInBackground Exception " + e.getLocalizedMessage());
            return null;
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("doInBackGround IOExc", e.getLocalizedMessage());
                    return null;
                }
            }
        }
        // Het resultaat gaat naar de onPostExecute methode.
        return response;
    }

    //Handle doInBackground method results, retrieve from json, fit form of Product-class
    protected void onPostExecute(String response) {
        //Logging info
//        Log.i("onPostExecute ", response);

        //Parse JSON-results for requested results
        try {
            // Top level json object
            JSONObject jsonObject = new JSONObject(response);

            // Get all products, loop through them
            JSONArray products = jsonObject.getJSONArray("products");
            //The loop
            for(int i = 0; i < products.length(); i++) {

                // Get title, specsTag, summary, longDescription, smallImgUrl, largeImgUrl products:get json via index, convert index to String as variable
                String title = products.getJSONObject(i).optString("title");
                String specsTag = products.getJSONObject(i).optString("specsTag");
                String summary = products.getJSONObject(i).optString("summary");
                String longDescription = products.getJSONObject(i).optString("longDescription");
                //Get images: Get object according to JSONarray indexNr > appropriate category > convert to String
                String thumbImgUrl = products.getJSONObject(i).getJSONArray("images").getJSONObject(0).optString("url");
                String largeImgUrl = products.getJSONObject(i).getJSONArray("images").getJSONObject(3).optString("url");

                Product product = new Product(title, specsTag, summary, longDescription, thumbImgUrl, largeImgUrl);


                Log.i(TAG, "Got product " + title + " " + specsTag );



                // call back with new person data
                listener.onProductAvailable(product);

            }
        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }

    // Call back interface
    public interface BolListener {
        void onProductAvailable(Product product);
    }
}