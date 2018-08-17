package com.app.daniel.hippotectask_daniel_cohen;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class getData extends AsyncTask<Void,Void,Void> {

    String JsonData = "";
    String testString = "";
    String fname;
    String best_season;
    String image_link;
    List<Flower> FlowersList = new ArrayList<Flower>(); //new list of flowers
    Context context;

    public getData(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) { //this will do the background task
        try {
            URL url = new URL("https://api.myjson.com/bins/hsjik"); //Url of the Json
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //opening Connection to URL
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String DataLine = "";
            while(DataLine != null){
                DataLine = bufferedReader.readLine();
                JsonData =  JsonData+DataLine;
            }

            JSONArray JA = new JSONArray(JsonData);
            for(int i=0;i<JA.length();i++){
            JSONObject JO = (JSONObject) JA.get(i);
                fname = (String) JO.get("name");
                best_season = (String) JO.get("best season");
                image_link = (String) JO.get("image link");

                testString  = testString + "\n"+  fname + "\n" +
                                best_season + "\n"+ image_link +"\n" ;


                //MainActivity.ListOfFlowers.add(new Flower(fname,best_season,image_link));
                FlowersList.add(new Flower(fname,best_season,image_link));

            }





        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.ListOfFlowers = new ArrayList<Flower>(FlowersList);

        MainActivity.initRecyclerView(context);//problem function is not static

        //MainActivity.Json_TV.setText(testString);  //send the data to String inside MainActivity


    }
}
