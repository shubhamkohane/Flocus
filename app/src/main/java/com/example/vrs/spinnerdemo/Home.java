package com.example.vrs.spinnerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {
    public final static String MESSAGE_KEY = "com.sliet.jeevansingh.projectjeevankeykey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }
    public void openNew(View view){
        String s="track";
        Intent intent = new Intent(Home.this,MainActivity.class);
        intent.putExtra(MESSAGE_KEY, s);
        startActivity(intent);
    }
    public void openNew1(View view){

        Intent intent1 = new Intent(Home.this,StaticActivity.class);
        startActivity(intent1);
    }
    public void Alert(View view){
        Intent i = new Intent(Home.this,MyAndroidAppActivity.class);
        startActivity(i);
    }
    public void textkaro(View view){
        Intent i1 = new Intent(Home.this,aboutus.class);
        startActivity(i1);
    }
}
