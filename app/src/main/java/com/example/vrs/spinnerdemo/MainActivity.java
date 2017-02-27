package com.example.vrs.spinnerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String MESSAGE_KEY = "com.sliet.jeevansingh.projectjeevankey";

    // uicontrols
    Spinner spCountries;
    Spinner spBusinessType;
    Button btnsubmit;
    String fD, fDo;
    int fD1, fDo1;
    //class members
    ArrayAdapter<String> adapterBusinessType;

    // local members
    String sbusinesstype,scountry;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH");
        fD = df.format(c.getTime());
        SimpleDateFormat dg = new SimpleDateFormat("mm");
        fDo = dg.format(c.getTime());
        fD1=Integer.parseInt(fD);
        fDo1=Integer.parseInt(fDo);

// Now formattedDate have current date/time
      //  Toast.makeText(this, fD+"::::"+fDo, Toast.LENGTH_SHORT).show();

        spCountries = (Spinner) findViewById(R.id.spCountries);
        spBusinessType = (Spinner) findViewById(R.id.spBussinessType);

        btnsubmit=(Button)findViewById(R.id.submit);
        btnsubmit.setOnClickListener(this);



        // Initialize and set Adapter
      //  adapterBusinessType = new ArrayAdapter<String>(this,
      //          android.R.layout.simple_spinner_item, businessType);
      //  adapterBusinessType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  spBusinessType.setAdapter(adapterBusinessType);

        // Country Item Selected Listener
        spCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                 scountry = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
              //  Toast.makeText(getApplicationContext(),
               //         "Selected Source : " + scountry, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        // Business Type Item Selected Listener
        spBusinessType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {
                // On selecting a spinner item
                sbusinesstype = adapter.getItemAtPosition(position).toString();

                // Showing selected spinner item
              //  Toast.makeText(getApplicationContext(),
              //          "Selected Destination : " + sbusinesstype, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


    }


    public void onClick(View v) {
        //Toast.makeText(getApplicationContext(), "You have selected " + scountry + " and " + sbusinesstype,Toast.LENGTH_SHORT).show();
        int a= spCountries.getSelectedItemPosition();
        int b= spBusinessType.getSelectedItemPosition();
        if ((a < b)&&(a!=0)&&(b!=0)) {
          /*  if(fD1<6) {*/
                String CurrentString = "1" + "__" + a + "__" + b + "__" + "AP28AD1024";
           /* }else if (fD1==6){
                if (fDo1<)

            }else if (){

            }else if (){

            }else if (){

            }else if (){

            }*/
            Intent i = new Intent(MainActivity.this, ListViewd.class);
            i.putExtra(MESSAGE_KEY,CurrentString);
            startActivity(i);
        }
        else if ((a>b)&&(a!=0)&&(b!=0)){
            String CurrentString ="2"+"__"+a+"__"+b+"__"+"AP29AJ1049";
            Intent i = new Intent(MainActivity.this, ListViewd.class);
            i.putExtra(MESSAGE_KEY,CurrentString);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Selection Invalid",Toast.LENGTH_SHORT).show();
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
