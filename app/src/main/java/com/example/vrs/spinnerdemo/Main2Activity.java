package com.example.vrs.spinnerdemo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final static String KEY1 = "com.sliet.jeevansingh.projectjeevankeyone";
    public final static String KEY2 = "com.sliet.jeevansingh.projectjeevankeytwo";
    public final static String KEY20 = "com.sliet.jeevansingh.projectjeevankeytwo0";

    String[] itemsone;
    LocationManager locationManager;
    String mprovider;
    TableRow sendSMSBtn;
    String f;
    String CurrentString;
    String lat;
    String log;
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

    //From -> the first coordinate from where we need to calculate the distance
    public double fromLongitude;
    public double fromLatitude;

    //To -> the second coordinate to where we need to calculate the distance
    public double toLongitude;
    public double toLatitude;
   int k,l,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list_one);
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // final ProgressDialog loading = ProgressDialog.show(this, "Getting Route", "Please wait...", false, false);

        googleApiClient = new com.google.android.gms.common.api.GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(AppIndex.API).build();

        ListView listviewone = (ListView) findViewById(R.id.listvone);
        Intent intent = getIntent();
        String a = intent.getStringExtra(KEY1);
        //double x = Double.parseDouble(a);
        String[] aco=a.split("__");
        // Inflate header view
         l= Integer.parseInt(aco[1]);

        //Toast.makeText(this,"value of k "+k,Toast.LENGTH_SHORT).show();

      //  ViewGroup headerViewone = (ViewGroup) getLayoutInflater().inflate(R.layout.headerone, listviewone, false);
        // Add header view to the ListView
     //   listviewone.addHeaderView(headerViewone);
        // Get the string array defined in strings.xml file
        Busno=aco[2];
        if (aco[0].equals("b")) {
            itemsone = getResources().getStringArray(R.array.Nimani_bus_stop);
            k = 34-l;
            c=1;
           // Busno = "8098";
            getBUS();
        } else  {
            //    items = getResources().getStringArray(R.array.list_itom);
            itemsone = getResources().getStringArray(R.array.Nasik_road_bus_stop);
            k = Integer.parseInt(aco[1]);
            c=2;
            //Busno="8040";
            getBUS();

        }
        // Create an adapter to bind data to the ListView

        LstViewAdapterone adapter = new LstViewAdapterone(this, R.layout.rowlayoutone, R.id.txtnameone, itemsone, k, c);
        // Bind data to the ListView
        listviewone.setAdapter(adapter);

        sendSMSBtn = (TableRow) findViewById(R.id.buttonone);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Not Getting GPS location", Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.vrs.spinnerdemo/http/host/path")
        );
        AppIndex.AppIndexApi.start(googleApiClient, viewAction);
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Maps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.vrs.spinnerdemo/http/host/path")
        );
        AppIndex.AppIndexApi.end(googleApiClient, viewAction);
    }


    public String makeURLone (String sourcebusno){
       // Toast.makeText(this,"ye mera hai",Toast.LENGTH_SHORT).show();
        StringBuilder urlString = new StringBuilder();
        //urlString.append("http://flocus.16mb.com/data.php");
       // urlString.append("http://bus.ypwhich.com/api/v1/bus/"+sourcebusno+"/?format=json");
        if (sourcebusno.equals("8040")){
            urlString.append("http://flocus.16mb.com/junction8040.php");
        } else {
            urlString.append("http://flocus.16mb.com/junction8098.php");
        }
      //  Toast.makeText(this,"ye mera hai",Toast.LENGTH_SHORT).show();

       /* urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString
                .append(Double.toString( sourcelog));
        urlString.append("&destination=");// to
        urlString
                .append(Double.toString( destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        urlString.append("&key=AIzaSyCYqL1ozZxVBevE6hix-ik8NauumA2mi14");*/
        return urlString.toString();
    }
    public void getBUS(){
        //Getting the URL
       // Toast.makeText(this,"ye mera hai",Toast.LENGTH_SHORT).show();

        String urlone = makeURLone(Busno);
        //Showing a dialog till we get the route
         //   final ProgressDialog loading = ProgressDialog.show(this, "Getting Route", "Please wait...", false, false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(urlone,
                new Response.Listener<String>() {
                    // @Override
                    public void onResponse(String response) {
                        //  loading.dismiss();
                        //Calling the method drawPath to draw the path
                        showBUS(response);
                    }
                },
                new Response.ErrorListener() {
                    // @Override
                    public void onErrorResponse(VolleyError error) {
                         // loading.dismiss();
                    }
                });

        //Adding the request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void showBUS(String  result) {
        //Getting both the coordinates

        try {
            //Parsing json
            //  Toast.makeText(this,"hello jeevan"+result,Toast.LENGTH_SHORT).show();
            final JSONObject json = new JSONObject(result);
         //   Toast.makeText(this,"ye mera hai",Toast.LENGTH_SHORT).show();

                lat = json.getJSONObject("latest_position").getString("latitude");
                log = json.getJSONObject("latest_position").getString("longitude");
           // String time = json.getJSONObject("latest_position").getString("timestamp");
            //JSONObject routes = routeArray.get(0);
            //double = Double.valueOf(json.get("latitude"));
            /*String[] items=result.split(",");
            String[] text1=items[3].split(":");
            String[] text2=items[4].split(":");*/
            x = Double.parseDouble(lat);
            y = Double.parseDouble(log);
           //   Toast.makeText(this,"ye mera hai"+y+x+time,Toast.LENGTH_SHORT).show();
            LatLng bus = new LatLng(x, y);
                //Getting longitude and latitude

                // Toast.makeText(this, "Location is :" + longitude +" jjj "+latitude, Toast.LENGTH_LONG).show();
                // toLatitudeone =  location.getLatitude(); //19.948108;//Double.valueOf(long_lat[3]);
                // toLongitudeone =location.getLongitude(); //73.841472;//Double.valueOf(long_lat[2]);

                //moving the map to location



            //  Toast.makeText(this,"hja"+x+y,Toast.LENGTH_SHORT).show();

           // if ((x=19.948108)) {

           // }
            // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            /*JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");*/
          /*  LatLng stop33 = new LatLng(19.9481080, 73.8414720);
            mMap.addMarker(new MarkerOptions().position(stop33).title("Nasik road bus stop"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(stop33));
            LatLng stop32 = new LatLng(19.9513444, 73.8404572);
            mMap.addMarker(new MarkerOptions().position(stop32).title("Shivaji Co.Op,HSG,Society"));
            LatLng stop31 = new LatLng(19.9551910, 73.8379020);
            mMap.addMarker(new MarkerOptions().position(stop31).title("Bytco point bus stop"));
            LatLng stop30 = new LatLng(19.9582693, 73.8384486);
            mMap.addMarker(new MarkerOptions().position(stop30).title("ISP Bus stop"));
            LatLng stop29 = new LatLng(19.9598753, 73.8387729);
            mMap.addMarker(new MarkerOptions().position(stop29).title("BP P.C School"));
            LatLng stop28 = new LatLng(19.9631967, 73.8390133);
            mMap.addMarker(new MarkerOptions().position(stop28).title("Catholic Church"));
            LatLng stop27 = new LatLng(19.9654990, 73.8392845);
            mMap.addMarker(new MarkerOptions().position(stop27).title("New ONP gate"));
            LatLng stop26 = new LatLng(19.9697705, 73.8401426);
            mMap.addMarker(new MarkerOptions().position(stop26).title("BP Central Jail"));
            LatLng stop25 = new LatLng(19.9700544, 73.8399768);
            mMap.addMarker(new MarkerOptions().position(stop25).title("Ingale Nagar/Taki"));
            LatLng stop24 = new LatLng(19.9742650, 73.8408660);
            mMap.addMarker(new MarkerOptions().position(stop24).title("MSEB Stores,Shivaji Nagar"));
            LatLng stop23 = new LatLng(19.9759130, 73.8415010);
            mMap.addMarker(new MarkerOptions().position(stop23).title("Datta Mandir/Model Hos.Nagar"));
            LatLng stop22 = new LatLng(19.9782386, 73.8421502);
            mMap.addMarker(new MarkerOptions().position(stop22).title("Deshmukh Bhavan"));
            LatLng stop21 = new LatLng(19.9809633, 73.8439822);
            mMap.addMarker(new MarkerOptions().position(stop21).title("Sant Dyaneshwar Nagar"));
            LatLng stop20 = new LatLng(19.9828960, 73.8445050);
            mMap.addMarker(new MarkerOptions().position(stop20).title("Sadurg Karanja"));
            LatLng stop19 = new LatLng(19.9834760, 73.8447750);
            mMap.addMarker(new MarkerOptions().position(stop19).title("BP  Citric/Panchal"));
            LatLng stop18 = new LatLng(19.9859040, 73.8449150);
            mMap.addMarker(new MarkerOptions().position(stop18).title("Dasak Village"));
            LatLng stop17 = new LatLng(19.9875190, 73.8454530);
            mMap.addMarker(new MarkerOptions().position(stop17).title("Godavari New Bridge/ mahalakshmi"));
            LatLng stop16 = new LatLng(19.9923837, 73.8442286);
            mMap.addMarker(new MarkerOptions().position(stop16).title("Nandur Village"));
            LatLng stop15 = new LatLng(19.9962941, 73.8437667);
            mMap.addMarker(new MarkerOptions().position(stop15).title("Nandur Naka"));
            LatLng stop14 = new LatLng(19.9990098, 73.8367553);
            mMap.addMarker(new MarkerOptions().position(stop14).title("Nimashe Farm 7/600"));
            LatLng stop13 = new LatLng(20.0022122, 73.8314997);
            mMap.addMarker(new MarkerOptions().position(stop13).title("Nandur Chari No.4"));
            LatLng stop12 = new LatLng(20.0045159, 73.8264979);
            mMap.addMarker(new MarkerOptions().position(stop12).title("Kailas Nagar 5/800"));
            LatLng stop11 = new LatLng(20.0057763, 73.8235459);
            mMap.addMarker(new MarkerOptions().position(stop11).title("Seva Housing Colony"));
            LatLng stop10 = new LatLng(20.0082913, 73.8162861);
            mMap.addMarker(new MarkerOptions().position(stop10).title("Mahadev mandir/janardan Swami"));
            LatLng stop9 = new LatLng(20.0103780, 73.8111300);
            mMap.addMarker(new MarkerOptions().position(stop9).title("BP Manur/Adgoan Naka"));
            LatLng stop8 = new LatLng(20.0091867, 73.8066813);
            mMap.addMarker(new MarkerOptions().position(stop8).title("Cross Road Dwarka"));
            LatLng stop7 = new LatLng(20.0092363, 73.8055855);
            mMap.addMarker(new MarkerOptions().position(stop7).title("Nasik D-Link Gate"));
            LatLng stop6 = new LatLng(20.0086326, 73.8022293);
            mMap.addMarker(new MarkerOptions().position(stop6).title("Old Adgaon Naka/BPHirawadi"));
            LatLng stop5 = new LatLng(20.0087504, 73.8010922);
            mMap.addMarker(new MarkerOptions().position(stop5).title("Walmic Nagar/Maruti mandir"));
            LatLng stop4 = new LatLng(20.0094441, 73.7992205);
            mMap.addMarker(new MarkerOptions().position(stop4).title("Raghuwanshi Building"));
            LatLng stop3 = new LatLng(20.0098903, 73.7985164);
            mMap.addMarker(new MarkerOptions().position(stop3).title("Panzara Pol"));
            LatLng stop2 = new LatLng(20.0101652, 73.7983855);
            mMap.addMarker(new MarkerOptions().position(stop2).title("Seva Kunj"));
            LatLng stop1 = new LatLng(20.0118000, 73.7968730);
            mMap.addMarker(new MarkerOptions().position(stop1).title("Nimani bus stop"));*/


        }
        catch (JSONException e) {
            e.printStackTrace();

        }
    }




    public void onLocationChanged(Location location) {

            CurrentString = location.getLatitude() + "__" + location.getLongitude()+"__"+Busno;

           /* View v = new View(this);
                LinearLayout vwParentRow = (LinearLayout)v.getParent();
                TextView child0 = (TextView)vwParentRow.getChildAt(2);
                f = child0.getText().toString();
                TextView child1 = (TextView)vwParentRow.getChildAt(3);
                g = child1.getText().toString();
                String one = CurrentString+"__"+f+"__"+g;
                //goToMap(v,one);
                //myClickone(v);*/
        getBUS();
    }
  /*  public void goToMap(View v,String one) {
        Intent intent = new Intent(Main2Activity.this, MapsActivity.class);
        intent.putExtra(KEY2,one);
        startActivity(intent);
    }*/

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public void myClickone(View v ) {

        // Toast.makeText(getApplicationContext(), "jeevan zakkas"+"__"+ CurrentString, Toast.LENGTH_SHORT).show();
        if (CurrentString!= null) {
             Toast.makeText(getApplicationContext(), "Connection Problem", Toast.LENGTH_SHORT).show();


            //get the row the clicked button is in
            LinearLayout vwParentRow = (LinearLayout) v.getParent();
            TableRow child0 = (TableRow) vwParentRow.getChildAt(0);
            TextView tv = (TextView) child0.getChildAt(1);
            // Toast.makeText(getApplicationContext(), child0.toString()+"  ", Toast.LENGTH_LONG).show();
            f = tv.getText().toString();


            if (f.equals("Nasik road bus stop")) {
                String z = "1__19.948108__73.841472" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Shivaji Co.Op,HSG,Society")) {
                String z = "1__19.95134442__73.84045717" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Bytco point bus stopPanzara Pol")) {
                String z = "1__19.955191__73.837902" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("ISP Bus stop")) {
                String z = "1__19.95826929__73.83844856" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("BP P.C School")) {
                String z = "1__19.95987529__73.83877291" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Catholic Church")) {
                String z = "1__19.9631967__73.83901328" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("New ONP gate")) {
                String z = "1__19.965499__73.83928454" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("BP Central Jail")) {
                String z = "1__19.96977049__73.84014258" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Ingale Nagar/Taki")) {
                String z = "1__19.97005443__73.83997679" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("MSEB Stores,Shivaji Nagar")) {
                String z = "1__19.974265__73.840866" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Datta Mandir/Model Hos.Nagar")) {
                String z = "1__19.975913__73.841501" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Deshmukh Bhavan")) {
                String z = "1__19.97823858__73.84215023" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Sant Dyaneshwar Nagar")) {
                String z = "1__19.98096327__73.84398215" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Sadurg Karanja")) {
                String z = "1__19.982896__73.844505" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("BP  Citric/Panchal")) {
                String z = "1__19.983476__73.844775" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Dasak Village")) {
                String z = "1__19.985904__73.844915" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Godavari New Bridge/ mahalakshmi")) {
                String z = "1__19.987519__73.845453" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Nandur Village")) {
                String z = "1__19.99238367__73.84422861" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Nandur Naka")) {
                String z = "1__19.99629413__73.84376668" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Nimashe Farm 7/600")) {
                String z = "1__19.9990098__73.8367553" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Nandur Chari No.4")) {
                String z = "1__20.00221215__73.83149966" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Kailas Nagar 5/800")) {
                String z = "1__20.00451587__73.82649793" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Seva Housing Colony")) {
                String z = "1__20.00577632__73.82354592" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Mahadev mandir/janardan Swami")) {
                String z = "1__20.00829127__73.8162861" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("BP Manur/Adgoan Naka")) {
                String z = "1__20.010378__73.81113" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
                //Button btnChild = (Button)vwParentRow.getChildAt(2);
            } else if (f.equals("Cross Road Dwarka")) {
                String z = "1__20.00918672__73.80668126" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Nasik D-Link Gate")) {
                String z = "1__20.00923632__73.80558551" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Old Adgaon Naka/BPHirawadi")) {
                String z = "1__20.00863258__73.80222932" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Walmic Nagar/Maruti mandir")) {
                String z = "1__20.00875035__73.80109223" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Raghuwanshi Building")) {
                String z = "1__20.00944412__73.79922054" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Panzara Pol")) {
                String z = "1__20.00989033__73.79851644" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Seva Kunj")) {
                String z = "1__20.01016517__73.79838553" + "__" + CurrentString;
                Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            } else if (f.equals("Nimani bus stop")) {
                String z = "1__20.0118__73.796873" + "__" + CurrentString;
                // Toast.makeText(getApplicationContext(), z, Toast.LENGTH_SHORT).show();
                Intent j = new Intent(Main2Activity.this, MapsActivity.class);
                j.putExtra(KEY2, z);
                startActivity(j);
            }
            }else {
            Toast.makeText(getBaseContext(), "Not getting GPS location", Toast.LENGTH_SHORT).show();
        }
        //btnChild.setText(child.getText());
        //btnChild.setText("I've been clicked!");
    }

    public void myClickz(View view) {

        //Toast.makeText(this,"It`s in developing Phase",Toast.LENGTH_SHORT).show();
        String CurrentString = "2__20.0118__73.796873__19.948108__73.841472"+"__"+Busno;
        Intent j = new Intent(Main2Activity.this, MapsActivity.class);
        j.putExtra(KEY2, CurrentString);
        startActivity(j);

        //Button btnChild = (Button)vwParentRow.getChildAt(2);

        //btnChild.setText(child.getText());
        //btnChild.setText("I've been clicked!");

    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


    private class GoogleApiClient {
    }
}


