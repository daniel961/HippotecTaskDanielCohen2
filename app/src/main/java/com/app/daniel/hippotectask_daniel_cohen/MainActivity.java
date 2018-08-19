package com.app.daniel.hippotectask_daniel_cohen;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {




    public static  List<Flower> ListOfFlowers;//list of flowers
    static RecyclerView recyclerView;
    String Device_Language = "";
    TextView LanguageTV;

    static TextView HelloTxt;
    static TextView WelcomeTxt;
    static TextView FlowerGuidTxt;

    static TextView JSON_DATA_Translates;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*Refs for Tvs*/
        HelloTxt = findViewById(R.id.HelloTxt);
        WelcomeTxt = findViewById(R.id.WelcomeTxt);
        FlowerGuidTxt = findViewById(R.id.FlowerGuidTxt);


        //todo detect System Language
        LanguageTV = findViewById(R.id.LanguageTV);
        Device_Language =  Locale.getDefault().getISO3Language();
        Toast.makeText(this, "Device Language Detected: " + Device_Language, Toast.LENGTH_SHORT).show();
        LanguageTV.setText( "Language: " + Device_Language);
        /*
        * heb for hebrew
        * eng for english
        * rus for russian
        * */




        recyclerView = findViewById(R.id.RecyclerView);
        final getData getDataa = new getData(this,Device_Language);
        getDataa.execute();












        }





        //functions



    public static void initRecyclerView(Context context){
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,ListOfFlowers);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


    }








}



