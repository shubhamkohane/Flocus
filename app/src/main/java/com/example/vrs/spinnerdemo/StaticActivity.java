package com.example.vrs.spinnerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class StaticActivity extends AppCompatActivity {
    public final static String KEY3 = "com.sliet.jeevansingh.projectjeevankeyone3";


    // uicontrols
    Spinner staticone;
    Spinner statictwo;
    ImageButton btnsubmit;
    ImageButton btnlsubmit;

    //class members
    ArrayAdapter<String> adapterBusinessType;

    // local members
    String sbusinesstype,scountry;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);

        staticone = (Spinner) findViewById(R.id.spCountriesone);
        statictwo = (Spinner) findViewById(R.id.spBussinessTypeone);




        // Initialize and set Adapter
        //  adapterBusinessType = new ArrayAdapter<String>(this,
        //          android.R.layout.simple_spinner_item, businessType);
        //  adapterBusinessType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //  spBusinessType.setAdapter(adapterBusinessType);

        // Country Item Selected Listener
        staticone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                scountry = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
              //  Toast.makeText(getApplicationContext(),
                   //     "Selected bus stop is : " + scountry, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        // Business Type Item Selected Listener
        statictwo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                sbusinesstype = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
               // Toast.makeText(getApplicationContext(),
               //         "Selected Bus No. is : " + sbusinesstype, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


    }


    public void openNewone (View v) {
        //Toast.makeText(getApplicationContext(), "You have selected " + scountry + " and " + sbusinesstype,Toast.LENGTH_SHORT).show();
        int a= staticone.getSelectedItemPosition();

        if (a ==1) {
            String CurrentString ="1"+"__"+a+"__";
            Intent i = new Intent(this, static_busno.class);
            i.putExtra(KEY3,CurrentString);
            startActivity(i);
        }
        else if (a==2){
            String CurrentString ="2"+"__"+a+"__";
            Intent i = new Intent(this, static_busno.class);
            i.putExtra(KEY3,CurrentString);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "First Select Bus Station",Toast.LENGTH_SHORT).show();
        }
    }

    public void openNew(View v) {
        //Toast.makeText(getApplicationContext(), "You have selected " + scountry + " and " + sbusinesstype,Toast.LENGTH_SHORT).show();
        int b= statictwo.getSelectedItemPosition();
        if (b == 1) {
            String CurrentString ="8098"+"__"+b;
            Intent i = new Intent(this, static_busno.class);
            i.putExtra(KEY3,CurrentString);
            startActivity(i);
        }
        else if (b==2){
            String CurrentString ="8040"+"__"+b;
            Intent i = new Intent(this, static_busno.class);
            i.putExtra(KEY3,CurrentString);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "First Select Bus Number " ,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
