package com.app.daniel.hippotectask_daniel_cohen;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final getData getDataa = new getData();
        getDataa.execute();


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(getDataa.getStatus() != AsyncTask.Status.FINISHED){
                    System.out.println("Daniel Therade Still Running");//BackTheread watin for data
                }
                //send the Array to RecyclerView HERE
                System.out.println("Daniel Therade STOPED");




            }
        });

        thread.start();





        }





        //functions

    public void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this,ListOfFlowers);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }





}
