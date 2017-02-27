package com.example.vrs.spinnerdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnMarkerDragListener,
        GoogleMap.OnMapLongClickListener
      {

    //Our Map
    private GoogleMap mMap;

    //To store longitude and latitude from map
    private double longitude;
    private double latitude;
          private double toLatitudeone;
          private double toLongitudeone;
          //From -> the first coordinate from where we need to calculate the distance
    private double fromLongitude;
    private double fromLatitude;
          public final Handler handler = new Handler();

          double y;
          double x;
          public String Busno;
    //To -> the second coordinate to where we need to calculate the distance
    private double toLongitude;
    private double toLatitude;

    //Google ApiClient
    private GoogleApiClient googleApiClient;

    //Our buttons
   /* private Button buttonSetTo;
    private Button buttonSetFrom;
    private Button buttonCalcDistance;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Initializing googleapi client
        // ATTENTION: This "addApi(AppIndex.API)"was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(AppIndex.API).build();

       /* buttonSetTo = (Button) findViewById(R.id.buttonSetTo);
        buttonSetFrom = (Button) findViewById(R.id.buttonSetFrom);
        buttonCalcDistance = (Button) findViewById(R.id.buttonCalcDistance);*/

      /*  buttonSetTo.setOnClickListener(this);
        buttonSetFrom.setOnClickListener(this);
        buttonCalcDistance.setOnClickListener(this);*/

        Intent i = getIntent();
        String test = i.getStringExtra("com.sliet.jeevansingh.projectjeevankeytwo");
        String[] long_lat = test.split("__");
        Busno=long_lat[5];

        if(long_lat[0].equals("1")) {
            fromLatitude = Double.parseDouble(long_lat[1]);//20.0118;//Double.valueOf(long_lat[1]);
            fromLongitude = Double.parseDouble(long_lat[2]);
            toLatitude = Double.parseDouble(long_lat[3]);//20.0118;//Double.valueOf(long_lat[1]);
            toLongitude = Double.parseDouble(long_lat[4]);

            getCurrentLocation();
            getDirection();
        }else if (long_lat[0].equals("0")){
            fromLatitude = Double.parseDouble(long_lat[1]);//20.0118;//Double.valueOf(long_lat[1]);
            fromLongitude = Double.parseDouble(long_lat[2]);
            toLatitude = latitude; //19.948108;//Double.valueOf(long_lat[3]);
            toLongitude = longitude; //73.841472;//Double.valueOf(long_lat[2]);
           // Toast.makeText(this, "Location zakkas:" + fromLatitude + fromLongitude + toLatitude + toLongitude, Toast.LENGTH_LONG).show();
        }
        else {
            getBUS();
           // Toast.makeText(this, "Location is not received" + latitude + longitude, Toast.LENGTH_LONG).show();
        }
      //  getDirection();

     //   doTheAutoRefresh();
    }
          public void doTheAutoRefresh() {
              handler.postDelayed(new Runnable() {
                  @Override
                  public void run() {
                    //  mMap.clear();
                      getBUS(); // this is where you put your refresh code
                      doTheAutoRefresh();

                  }
              }, 5000);
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
        StringBuilder urlString = new StringBuilder();
        //urlString.append("http://flocus.16mb.com/data.php");
        //urlString.append("http://bus.ypwhich.com/api/v1/bus/"+sourcebusno+"/?format=json");
        if (sourcebusno.equals("8040")){
            urlString.append("http://flocus.16mb.com/junction8040.php");
        } else {
            urlString.append("http://flocus.16mb.com/junction8098.php");
        }
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
        String urlone = makeURLone(Busno);
        //Showing a dialog till we get the route
        final ProgressDialog loading = ProgressDialog.show(this, "Getting Route", "Please wait...", false, false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(urlone,
                new Response.Listener<String>() {
                    // @Override
                    public void onResponse(String response) {
                          loading.dismiss();
                        //Calling the method drawPath to draw the path
                        showBUS(response);
                    }
                },
                new Response.ErrorListener() {
                    // @Override
                    public void onErrorResponse(VolleyError error) {
                          loading.dismiss();
                      //  Toast.makeText(getApplicationContext(),"Error Timeout",Toast.LENGTH_LONG).show();
                    }
                });

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

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

            String lat = json.getJSONObject("latest_position").getString("latitude");
            String log = json.getJSONObject("latest_position").getString("longitude");
           // String time = json.getJSONObject("latest_position").getString("timestamp");
            //JSONObject routes = routeArray.get(0);
            //double = Double.valueOf(json.get("latitude"));
            /*String[] items=result.split(",");
            String[] text1=items[3].split(":");
            String[] text2=items[4].split(":");*/
            x = Double.parseDouble(lat);
            y = Double.parseDouble(log);
          //  Toast.makeText(this,"hello jeevan"+y+x+time,Toast.LENGTH_SHORT).show();
            LatLng bus = new LatLng(x, y);
          //  Toast.makeText(this,"hja"+x+y,Toast.LENGTH_SHORT).show();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(bus));
            mMap.addMarker(new MarkerOptions().position(bus).draggable(true).title("BUS").icon(BitmapDescriptorFactory.fromResource(R.mipmap.jjj)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(16));

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

        }
    }



    //Getting current location
   public void getCurrentLocation() {
       // mMap.clear();
        //Creating a location object
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
      //  Toast.makeText(this, "Location =" + location, Toast.LENGTH_LONG).show();
        if (location != null) {
            //Getting longitude and latitude
            longitude = location.getLongitude();
            latitude = location.getLatitude();
           // Toast.makeText(this, "Location is :" + longitude +" jjj "+latitude, Toast.LENGTH_LONG).show();
           // toLatitudeone =  location.getLatitude(); //19.948108;//Double.valueOf(long_lat[3]);
           // toLongitudeone =location.getLongitude(); //73.841472;//Double.valueOf(long_lat[2]);

            //moving the map to location
            moveMap();
        }
    }

    //Function to move the map
    private void moveMap() {
        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(latitude, longitude);

        //Adding marker to map
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(false) //Making the marker draggable
                .title("Current Location")); //Adding a title

        //Moving the camera


        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
    }

    public String makeURL (double sourcelat, double sourcelog, double destlat, double destlog ){
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
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
        urlString.append("&key=AIzaSyCYqL1ozZxVBevE6hix-ik8NauumA2mi14");
        return urlString.toString();
    }

    private void getDirection(){
        //Getting the URL
        String url = makeURL(toLatitude, toLongitude, fromLatitude, fromLongitude);

        //Showing a dialog till we get the route
        final ProgressDialog loading = ProgressDialog.show(this, "Getting Route", "Please wait...", false, false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        //Calling the method drawPath to draw the path
                        drawPath(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                    }
                });

        //Adding the request to request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    //The parameter is the server response
    public void drawPath(String  result) {
        //Getting both the coordinates
        LatLng from = new LatLng(fromLatitude,fromLongitude);
        LatLng to = new LatLng(toLatitude,toLongitude);

        //Calculating the distance in meters
        Double distance = SphericalUtil.computeDistanceBetween(from, to);

        //Displaying the distance
        Toast.makeText(this,String.valueOf(distance+" Meters"),Toast.LENGTH_SHORT).show();


        try {
            //Parsing json
         //   Toast.makeText(this,"hello jeevan"+result,Toast.LENGTH_SHORT).show();
            final JSONObject json = new JSONObject(result);
            JSONArray routeArray = json.getJSONArray("routes");
            JSONObject routes = routeArray.getJSONObject(0);
            JSONObject overviewPolylines = routes.getJSONObject("overview_polyline");
            String encodedString = overviewPolylines.getString("points");
            List<LatLng> list = decodePoly(encodedString);
            Polyline line = mMap.addPolyline(new PolylineOptions()
                    .addAll(list)
                    .width(2)
                    .color(Color.RED)
                    .geodesic(true)
            );


        }
        catch (JSONException e) {

        }
    }

    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng( (((double) lat / 1E5)),
                    (((double) lng / 1E5) ));
            poly.add(p);
        }

        return poly;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.zoomTo(20));
        LatLng stop33 = new LatLng(19.9481080, 73.8414720);
        mMap.addMarker(new MarkerOptions().position(stop33).title("Nasik road bus stop").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(stop33));
        LatLng stop32 = new LatLng(19.9513444, 73.8404572);
        mMap.addMarker(new MarkerOptions().position(stop32).title("Shivaji Co.Op,HSG,Society").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop31 = new LatLng(19.9551910, 73.8379020);
        mMap.addMarker(new MarkerOptions().position(stop31).title("Bytco point bus stop").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop30 = new LatLng(19.9582693, 73.8384486);
        mMap.addMarker(new MarkerOptions().position(stop30).title("ISP Bus stop").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop29 = new LatLng(19.9598753, 73.8387729);
        mMap.addMarker(new MarkerOptions().position(stop29).title("BP P.C School").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop28 = new LatLng(19.9631967, 73.8390133);
        mMap.addMarker(new MarkerOptions().position(stop28).title("Catholic Church").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop27 = new LatLng(19.9654990, 73.8392845);
        mMap.addMarker(new MarkerOptions().position(stop27).title("New ONP gate").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop26 = new LatLng(19.9697705, 73.8401426);
        mMap.addMarker(new MarkerOptions().position(stop26).title("BP Central Jail").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop25 = new LatLng(19.9700544, 73.8399768);
        mMap.addMarker(new MarkerOptions().position(stop25).title("Ingale Nagar/Taki").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop24 = new LatLng(19.9742650, 73.8408660);
        mMap.addMarker(new MarkerOptions().position(stop24).title("MSEB Stores,Shivaji Nagar").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop23 = new LatLng(19.9759130, 73.8415010);
        mMap.addMarker(new MarkerOptions().position(stop23).title("Datta Mandir/Model Hos.Nagar").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop22 = new LatLng(19.9782386, 73.8421502);
        mMap.addMarker(new MarkerOptions().position(stop22).title("Deshmukh Bhavan").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop21 = new LatLng(19.9809633, 73.8439822);
        mMap.addMarker(new MarkerOptions().position(stop21).title("Sant Dyaneshwar Nagar").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop20 = new LatLng(19.9828960, 73.8445050);
        mMap.addMarker(new MarkerOptions().position(stop20).title("Sadurg Karanja").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop19 = new LatLng(19.9834760, 73.8447750);
        mMap.addMarker(new MarkerOptions().position(stop19).title("BP  Citric/Panchal").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop18 = new LatLng(19.9859040, 73.8449150);
        mMap.addMarker(new MarkerOptions().position(stop18).title("Dasak Village").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop17 = new LatLng(19.9875190, 73.8454530);
        mMap.addMarker(new MarkerOptions().position(stop17).title("Godavari New Bridge/ mahalakshmi").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop16 = new LatLng(19.9923837, 73.8442286);
        mMap.addMarker(new MarkerOptions().position(stop16).title("Nandur Village").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop15 = new LatLng(19.9962941, 73.8437667);
        mMap.addMarker(new MarkerOptions().position(stop15).title("Nandur Naka").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop14 = new LatLng(19.9990098, 73.8367553);
        mMap.addMarker(new MarkerOptions().position(stop14).title("Nimashe Farm 7/600").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop13 = new LatLng(20.0022122, 73.8314997);
        mMap.addMarker(new MarkerOptions().position(stop13).title("Nandur Chari No.4").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop12 = new LatLng(20.0045159, 73.8264979);
        mMap.addMarker(new MarkerOptions().position(stop12).title("Kailas Nagar 5/800").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop11 = new LatLng(20.0057763, 73.8235459);
        mMap.addMarker(new MarkerOptions().position(stop11).title("Seva Housing Colony").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop10 = new LatLng(20.0082913, 73.8162861);
        mMap.addMarker(new MarkerOptions().position(stop10).title("Mahadev mandir/janardan Swami").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop9 = new LatLng(20.0103780, 73.8111300);
        mMap.addMarker(new MarkerOptions().position(stop9).title("BP Manur/Adgoan Naka").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop8 = new LatLng(20.0091867, 73.8066813);
        mMap.addMarker(new MarkerOptions().position(stop8).title("Cross Road Dwarka").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop7 = new LatLng(20.0092363, 73.8055855);
        mMap.addMarker(new MarkerOptions().position(stop7).title("Nasik D-Link Gate").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop6 = new LatLng(20.0086326, 73.8022293);
        mMap.addMarker(new MarkerOptions().position(stop6).title("Old Adgaon Naka/BPHirawadi").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop5 = new LatLng(20.0087504, 73.8010922);
        mMap.addMarker(new MarkerOptions().position(stop5).title("Walmic Nagar/Maruti mandir").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop4 = new LatLng(20.0094441, 73.7992205);
        mMap.addMarker(new MarkerOptions().position(stop4).title("Raghuwanshi Building").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop3 = new LatLng(20.0098903, 73.7985164);
        mMap.addMarker(new MarkerOptions().position(stop3).title("Panzara Pol").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop2 = new LatLng(20.0101652, 73.7983855);
        mMap.addMarker(new MarkerOptions().position(stop2).title("Seva Kunj").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));
        LatLng stop1 = new LatLng(20.0118000, 73.7968730);
        mMap.addMarker(new MarkerOptions().position(stop1).title("Nimani bus stop").icon(BitmapDescriptorFactory.fromResource(R.mipmap.bus_stop)));

    }



    @Override
    public void onConnected(Bundle bundle) {
        getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        //Clearing all the markers
       /* mMap.clear();
        //Adding a new marker to the current pressed position
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .draggable(true));

        latitude = latLng.latitude;
        longitude = latLng.longitude;*/
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        //Getting the coordinates
        latitude = marker.getPosition().latitude;
        longitude = marker.getPosition().longitude;

        //Moving the map
        moveMap();
    }

   /* @Override
    public void onClick(View v) {
        if(v == buttonSetFrom){
            fromLatitude = latitude;
            fromLongitude = longitude;
            Toast.makeText(this,"From set",Toast.LENGTH_SHORT).show();
        }

        if(v == buttonSetTo){
            toLatitude = latitude;
            toLongitude = longitude;
            Toast.makeText(this,"To set",Toast.LENGTH_SHORT).show();
        }

        if(v == buttonCalcDistance){
            getDirection();
        }
    }*/
}