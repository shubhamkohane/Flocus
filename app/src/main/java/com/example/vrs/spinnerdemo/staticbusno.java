package com.example.vrs.spinnerdemo;

import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TableRow;

public class staticbusno extends AppCompatActivity  {
    public final static String KEY1 = "com.sliet.jeevansingh.projectjeevankeyone";
    public final static String KEY2 = "com.sliet.jeevansingh.projectjeevankeytwo";
    public final static String KEY4 = "com.sliet.jeevansingh.projectjeevankeyone4";

    String[] itemsone;
    LocationManager locationManager;
    String mprovider;
    TableRow sendSMSBtn;
    String f;
    String CurrentString;
    String g;
    public double longitude;
    public double latitude;
    public double toLatitudeone;
    public double toLongitudeone;


    //From -> the first coordinate from where we need to calculate the distance


    public final Handler handler = new Handler();

    double y;
    double x;
    public String Busno;
    //To -> the second coordinate to where we need to calculate the distance


    //Google ApiClient
    private com.google.android.gms.common.api.GoogleApiClient googleApiClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.static_view_list_one);

        // See https://g.co/AppIndexing/AndroidStudio for more information.


        ListView listviewone = (ListView) findViewById(R.id.listvonestatic);
        Intent intent= getIntent();
        String jas = intent.getStringExtra(KEY4);
        String[] a=jas.split("__");
        //double x = Double.parseDouble(a);

        // Inflate header view
     //   ViewGroup headerViewone = (ViewGroup) getLayoutInflater().inflate(R.layout.headerone, listviewone, false);
        // Add header view to the ListView
     //   listviewone.addHeaderView(headerViewone);
        // Get the string array defined in strings.xml file
        if (jas.equals("a")) {
            itemsone = getResources().getStringArray(R.array.country_arrays_i);
        } else {
            //    items = getResources().getStringArray(R.array.list_itom);
            itemsone = getResources().getStringArray(R.array.country_arrays_u);
        }

        // Create an adapter to bind data to the ListView

        Listmapadaptor adapter = new Listmapadaptor(this, R.layout.static_row_layout, R.id.txtnameone, itemsone);
        // Bind data to the ListView
        listviewone.setAdapter(adapter);

    }




}


