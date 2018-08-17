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

public class MainActivity extends AppCompatActivity {




    public static  List<Flower> ListOfFlowers;//list of flowers
    static RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //todo detect System Language


        recyclerView = findViewById(R.id.RecyclerView);
        final getData getDataa = new getData(this);
        getDataa.execute();












        }





        //functions



    public static void initRecyclerView(Context context){
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(context,ListOfFlowers);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));


    }








}



