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
    String JsonData2 = "";
    String testString = "";
    String fname;
    String best_season;
    String image_link;
    List<Flower> FlowersList = new ArrayList<Flower>(); //new list of flowers
    Context context;
    String System_Language;

    //details_Strings
    String hello_txt = "";
    String wellcome_txt  = "";
    String flower_guide_txt = "";


    public getData(Context context,String System_Language) {
        this.context = context;
        this.System_Language = System_Language;
    }

    @Override
    protected Void doInBackground(Void... voids) { //this will do the background task
        try {

            //get Flower Data
            URL url = new URL("https://api.myjson.com/bins/hsjik"); //Url of the Json
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); //opening Connection to URL
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String DataLine = "";
            while(DataLine != null){
                DataLine = bufferedReader.readLine();
                JsonData =  JsonData+DataLine;
            }

            //get Translate Data
            URL url2 = new URL("https://api.myjson.com/bins/1g0mgc"); //Url of the Json
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection(); //opening Connection to URL
            InputStream inputStream2 = httpURLConnection2.getInputStream();
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream2));

            String DataLine2 = "";
            while(DataLine2 != null){
                DataLine2 = bufferedReader2.readLine();
                JsonData2 =  JsonData2+DataLine2;
            }



            //parse Translate Data
            //Translates_JSONS
            JSONObject JO_Translate = new JSONObject(JsonData2);
            JSONObject JO_he = (JSONObject) JO_Translate.getJSONObject("he");
            JSONObject JO_en = (JSONObject) JO_Translate.getJSONObject("en");
            JSONObject JO_ru = (JSONObject) JO_Translate.getJSONObject("ru");

            JSONArray JA = new JSONArray(JsonData); //Flowers_JSON_Array






            switch (System_Language){
                case "heb":
                    GetData_Translated(JO_he,JA);
                    break;

                case "eng":
                    hello_txt = (String) JO_en.getString("hello");
                    wellcome_txt = (String) JO_en.getString("welcome");
                    flower_guide_txt = (String) JO_en.getString("flower guide");
                    for(int i=0;i<JA.length();i++) {
                        JSONObject JO = (JSONObject) JA.get(i);
                        fname = (String) JO.get("name");
                        best_season = (String) JO.get("best season");
                        image_link = (String) JO.get("image link");

                        FlowersList.add(new Flower(fname, best_season, image_link));
                    }
                        break;

                case "rus":
                    GetData_Translated(JO_ru,JA);
                    break;

                    default:
                        //English for default
                        hello_txt = (String) JO_en.getString("hello");
                        wellcome_txt = (String) JO_en.getString("welcome");
                        flower_guide_txt = (String) JO_en.getString("flower guide");
                        for(int i=0;i<JA.length();i++) {
                            JSONObject JO = (JSONObject) JA.get(i);
                            fname = (String) JO.get("name");
                            best_season = (String) JO.get("best season");
                            image_link = (String) JO.get("image link");

                            FlowersList.add(new Flower(fname, best_season, image_link));
                        }
                        break;

                    //Daniel's Note: i had to use Switch case because when i tried to detect_System Language hebrew got "heb" and in the JSON FILE its wrote "he" so i had to fix it


            }





            } catch (IOException e1) {
            e1.printStackTrace();
        } catch (JSONException e1) {
            e1.printStackTrace();
        }





        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.ListOfFlowers = new ArrayList<Flower>(FlowersList);
        MainActivity.initRecyclerView(context);

        //MainActivity.JSON_DATA_Translates.setText(JsonData2);


        /*Texts_Translated*/
        MainActivity.HelloTxt.setText(hello_txt);
        MainActivity.WelcomeTxt.setText(wellcome_txt);
        MainActivity.FlowerGuidTxt.setText(flower_guide_txt);






    }





    public void GetData_Translated( JSONObject Select_LANG, JSONArray Json_Array_Data) throws JSONException {
        //Title_txts
        hello_txt = (String) Select_LANG.getString("hello");
        wellcome_txt = (String) Select_LANG.getString("welcome");
        flower_guide_txt = (String) Select_LANG.getString("flower guide");






        for(int i=0;i<Json_Array_Data.length();i++) {
            JSONObject JO = (JSONObject) Json_Array_Data.get(i);


            fname =  Select_LANG.getString((String) JO.get("name"));
            if(Select_LANG.has((String) JO.get("best season"))){ //must Translate the Words that Contains two seasons
                best_season = (String) Select_LANG.getString((String) JO.get("best season"));

            }else {
                //Hebrew Translate for Complicated String
                best_season = (String) JO.get("best season");


                if ((best_season.contains("to"))&&(System_Language.contains("heb"))) {
                    best_season = best_season.replace("to", "ל-");
                    best_season = "בין " + best_season;
                }
                if ((best_season.contains("to"))&&(System_Language.contains("rus"))) {
                    best_season = best_season.replace("to", "в");
                }

                if (best_season.contains("summer")) {
                    best_season = best_season.replace("summer", (String) Select_LANG.getString("summer"));
                }
                if (best_season.contains("winter")) {
                    best_season = best_season.replace("winter", Select_LANG.getString("winter"));
                }
                if (best_season.contains("fall")) {
                    best_season = best_season.replace("fall", Select_LANG.getString("fall"));
                }
                if (best_season.contains("spring")) {
                    best_season = best_season.replace("spring", Select_LANG.getString("spring"));
                }

                //Russian Translate for Complicated String
            }



            image_link = (String) JO.get("image link");
            FlowersList.add(new Flower(fname,best_season,image_link));
        }



    }


}
