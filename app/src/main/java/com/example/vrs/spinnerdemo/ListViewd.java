package com.example.vrs.spinnerdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
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
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.vrs.spinnerdemo.R.layout;

public class ListViewd extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public final static String MESSAGE_KEY = "com.sliet.jeevansingh.projectjeevankey";
    public final static String KEY1 = "com.sliet.jeevansingh.projectjeevankeyone";
    public String[] items;
    LocationManager locationManager;
    float lat1;
    float lng1;
    String lat;
    String log;
    String fD, fDo;
    int fD1, fDo1;
    float a[] = new float[35];
    float b[] = new float[35];
    float a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21, a22, a23, a24, a25, a26, a27, a28, a29, a30, a31, a32, a33;
    int t = 0, w = 0;
    private float longitude;
    private float latitude;
    private float toLatitudeone;
    private float toLongitudeone;
    //From -> the first coordinate from where we need to calculate the distance
    private float fromLongitude;
    private float fromLatitude;
    public final Handler handler = new Handler();

    float y;
    float x;
    public String Busno;
    //To -> the second coordinate to where we need to calculate the distance
    private float toLongitude;
    private float toLatitude;
    //To -> the second coordinate to where we need to calculate the distance
    private GoogleApiClient googleApiClient;

    //Google ApiClient

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.view_list);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time =&gt; "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("HH");
        fD = df.format(c.getTime());
        SimpleDateFormat dg = new SimpleDateFormat("mm");
        fDo = dg.format(c.getTime());
        fD1=Integer.parseInt(fD);
        fDo1=Integer.parseInt(fDo);
        ListView listview = (ListView) findViewById(R.id.listv);
        Intent intent = getIntent();
        String jas = intent.getStringExtra(MESSAGE_KEY);
        String[] a = jas.split("__");
        //double x = Double.parseDouble(a);
        googleApiClient = new com.google.android.gms.common.api.GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(AppIndex.API).build();

        // Inflate header view
      //  ViewGroup headerView = (ViewGroup) getLayoutInflater().inflate(R.layout.header, listview, false);
        // Add header view to the ListView
     //   listview.addHeaderView(headerView);
        // Get the string array defined in strings.xml file
        if (a[0].equals("1")) {

            items = getResources().getStringArray(R.array.list_items);

        } else {
            items = getResources().getStringArray(R.array.list_itom);

        }

        // Create an adapter to bind data to the ListView

        LstViewAdapter adapter = new LstViewAdapter(this, R.layout.rowlayout, R.id.txtname, items);
        // Bind data to the ListView
        listview.setAdapter(adapter);
      //  getBUS();
        // Toast.makeText(this,"ye mera hai a",Toast.LENGTH_SHORT).show();

    }
    public void doTheAutoRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //  mMap.clear();
               // getBUS(); // this is where you put your refresh code
              //  doTheAutoRefresh();
                extrainto();
            }
        }, 10000);

    }

    public void doTheAutoRefreshone() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //  mMap.clear();
                // getBUS(); // this is where you put your refresh code
                //  doTheAutoRefresh();
                extraintoone();
            }
        }, 10000);

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


    public String makeURLone(String sourcebusno) {
        StringBuilder urlString = new StringBuilder();
        //urlString.append("http://flocus.16mb.com/data.php");
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

    public void getBUS() {
        //Getting the URL
        String urlone = makeURLone(Busno);
        //Showing a dialog till we get the route
            final ProgressDialog loading = ProgressDialog.show(this, "Getting Bus", "Please wait...", false, false);

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
                     @Override
                    public void onErrorResponse(VolleyError error) {
                          loading.dismiss();
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

    public void showBUS(String result) {
        //Getting both the coordinates

        try {
            //Parsing json
            //  Toast.makeText(this,"hello jeevan"+result,Toast.LENGTH_SHORT).show();
            final JSONObject json = new JSONObject(result);
            if (Busno.equals("8040")) {
                JSONObject reg = json.getJSONObject("registration");
                if (reg.equals("APZ0022")) {
                    lat = json.getJSONObject("latest_position").getString("latitude");
                    log = json.getJSONObject("latest_position").getString("longitude");
                }}
                else{
                    lat = json.getJSONObject("latest_position").getString("latitude");
                    log = json.getJSONObject("latest_position").getString("longitude");
                }

           // String time = json.getJSONObject("latest_position").getString("timestamp");
            //JSONObject routes = routeArray.get(0);
            //double = Double.valueOf(json.get("latitude"));
            /*String[] items=result.split(",");
            String[] text1=items[3].split(":");
            String[] text2=items[4].split(":");*/
            x = Float.parseFloat(lat);
            y = Float.parseFloat(log);
            //  Toast.makeText(this,"hello jeevan"+y+x+time,Toast.LENGTH_SHORT).show();
            LatLng bus = new LatLng(x, y);
            //  Toast.makeText(this,"hja"+x+y,Toast.LENGTH_SHORT).show();
            w = moveMap(x, y);
            //  mMap.animateCamera(CameraUpdateFactory.zoomTo(20));

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


        } catch (JSONException e) {

        }
    }


    public int moveMap(float lat1, float lng1) {

        a[0] = (float) 19980.102;
        a[1] = distFrom(lat1, lng1, 19.948108, 73.841472);
        a1=a[1];
        a[2] = distFrom(lat1, lng1, 19.95134442, 73.84045717);
        a2=a[2];
        a[3] = distFrom(lat1, lng1, 19.955191, 73.837902);
        a3=a[3];
        a[4] = distFrom(lat1, lng1, 19.95826929, 73.83844856);
        a4=a[4];
        a[5] = distFrom(lat1, lng1, 19.95987529, 73.83877291);
        a5=a[5];
        a[6] = distFrom(lat1, lng1, 19.9631967, 73.83901328);
        a6=a[6];
        a[7] = distFrom(lat1, lng1, 19.965499, 73.83928454);
        a7=a[7];
        a[8] = distFrom(lat1, lng1, 19.96977049, 73.84014258);
        a8=a[8];
        a[9] = distFrom(lat1, lng1, 19.97005443, 73.83997679);
        a9=a[9];
        a[10] =  distFrom(lat1, lng1, 19.974265, 73.840866);
        a10=a[10];
        a[11] = distFrom(lat1, lng1, 19.975913, 73.841501);
        a11=a[11];
        a[12] = distFrom(lat1, lng1, 19.97823858, 73.84215023);
        a12=a[12];
        a[13] = distFrom(lat1, lng1, 19.98096327, 73.84398215);
        a13=a[13];
        a[14] = distFrom(lat1, lng1, 19.982896, 73.844505);
        a14=a[14];
        a[15] = distFrom(lat1, lng1, 19.983476, 73.844775);
        a15=a[15];
        a[16] = distFrom(lat1, lng1, 19.985904, 73.844915);
        a16=a[16];
        a[17] = distFrom(lat1, lng1, 19.987519, 73.845453);
        a17=a[17];
        a[18] = distFrom(lat1, lng1, 19.99238367, 73.84422861);
        a18=a[18];
        a[19] = distFrom(lat1, lng1, 19.99629413, 73.84376668);
        a19=a[19];
        a[20] = distFrom(lat1, lng1, 19.9990098, 73.8367553);
        a20=a[20];
        a[21] = distFrom(lat1, lng1, 20.00221215, 73.83149966);
        a21=a[21];
        a[22] = distFrom(lat1, lng1, 20.00451587, 73.82649793);
        a22=a[22];
        a[23] = distFrom(lat1, lng1, 20.00577632, 73.82354592);
        a23=a[23];
        a[24] = distFrom(lat1, lng1, 20.00829127, 73.8162861);
        a24=a[24];
        a[25] = distFrom(lat1, lng1, 20.010378, 73.81113);
        a25=a[25];
        a[26] = distFrom(lat1, lng1, 20.00918672, 73.80668126);
        a26=a[26];
        a[27] = distFrom(lat1, lng1, 20.00923632, 73.80558551);
        a27=a[27];
        a[28] = distFrom(lat1, lng1, 20.00863258, 73.80222932);
        a28=a[28];
        a[29] = distFrom(lat1, lng1, 20.00875035, 73.80109223);
        a29=a[29];
        a[30] = distFrom(lat1, lng1, 20.00944412, 73.79922054);
        a30=a[30];
        a[31] = distFrom(lat1, lng1, 20.00989033, 73.79851644);
        a31=a[31];
        a[32] = distFrom(lat1, lng1, 20.01016517, 73.79838553);
        a32=a[32];
        a[33] = distFrom(lat1, lng1, 20.0118, 73.796873);
        a33=a[33];
       System.arraycopy(a, 0, b, 0, a.length);
        // Toast.makeText(this, "ye b[0]" + b[0], Toast.LENGTH_SHORT).show();
      //  Arrays.sort(a);
     /*   float min_a = a[0];
               String j = String.valueOf(min_a);
              //  Toast.makeText(this, "tictak" + j, Toast.LENGTH_LONG).show();
              //  Toast.makeText(this, "ye before b[0]" + b[0], Toast.LENGTH_SHORT).show();
                for (t = 1; t < 34; t++) {
                    if (b[t] == min_a) {
                      //  Toast.makeText(this, "b ki value " + b[t], Toast.LENGTH_SHORT).show();
                        w = t;
                      //  Toast.makeText(this, t + "jack" + w, Toast.LENGTH_SHORT).show();
                    }

                }
        // }*/
        if ((a1<a2)&&(a1<a3)&&(a1<a4)&&(a1<a5)&&(a1<a6)&&(a1<a7)&&(a1<a8)&&(a1<a9)&&(a1<a10)&&(a1<a11)&&(a1<a12)&&(a1<a13)&&(a1<a14)&&(a1<a15)&&(a1<a16)&&(a1<a17)&&(a1<a18)&&(a1<a19)&&(a1<a20)&&(a1<a21)&&(a1<a22)&&(a1<a23)&&(a1<a24)&&(a1<a25)&&(a1<a26)&&(a1<a27)&&(a1<a28)&&(a1<a29)&&(a1<a30)&&(a1<a31)&&(a1<a32)&&(a1<a33)){
            w=1;

        }else if ((a2<a1)&&(a2<a3)&&(a2<a4)&&(a2<a5)&&(a2<a6)&&(a2<a7)&&(a2<a8)&&(a2<a9)&&(a2<a10)&&(a2<a11)&&(a2<a12)&&(a2<a13)&&(a2<a14)&&(a2<a15)&&(a2<a16)&&(a2<a17)&&(a2<a18)&&(a2<a19)&&(a2<a20)&&(a2<a21)&&(a2<a22)&&(a2<a23)&&(a2<a24)&&(a2<a25)&&(a2<a26)&&(a2<a27)&&(a2<a28)&&(a2<a29)&&(a2<a30)&&(a2<a31)&&(a2<a32)&&(a2<a33)){
            w=2;

        }else if ((a3<a1)&&(a1<a2)&&(a3<a4)&&(a3<a5)&&(a3<a6)&&(a3<a7)&&(a3<a8)&&(a3<a9)&&(a3<a10)&&(a3<a11)&&(a3<a12)&&(a3<a13)&&(a3<a14)&&(a3<a15)&&(a3<a16)&&(a3<a17)&&(a3<a18)&&(a3<a19)&&(a3<a20)&&(a3<a21)&&(a3<a22)&&(a3<a23)&&(a3<a24)&&(a3<a25)&&(a3<a26)&&(a3<a27)&&(a3<a28)&&(a3<a29)&&(a3<a30)&&(a3<a31)&&(a3<a32)&&(a3<a33)){
            w=3;

        }else if ((a4<a1)&&(a4<a2)&&(a4<a3)&&(a4<a5)&&(a4<a6)&&(a4<a7)&&(a4<a8)&&(a4<a9)&&(a4<a10)&&(a4<a11)&&(a4<a12)&&(a4<a13)&&(a4<a14)&&(a4<a15)&&(a4<a16)&&(a4<a17)&&(a4<a18)&&(a4<a19)&&(a4<a20)&&(a4<a21)&&(a4<a22)&&(a4<a23)&&(a4<a24)&&(a4<a25)&&(a4<a26)&&(a4<a27)&&(a4<a28)&&(a4<a29)&&(a4<a30)&&(a4<a31)&&(a4<a32)&&(a4<a33)){
            w=4;

        }else if ((a5<a2)&&(a5<a3)&&(a5<a4)&&(a5<a1)&&(a5<a6)&&(a5<a7)&&(a5<a8)&&(a5<a9)&&(a5<a10)&&(a5<a11)&&(a5<a12)&&(a5<a13)&&(a5<a14)&&(a5<a15)&&(a5<a16)&&(a5<a17)&&(a5<a18)&&(a5<a19)&&(a5<a20)&&(a5<a21)&&(a5<a22)&&(a5<a23)&&(a5<a24)&&(a5<a25)&&(a5<a26)&&(a5<a27)&&(a5<a28)&&(a5<a29)&&(a5<a30)&&(a5<a31)&&(a5<a32)&&(a5<a33)){
            w=5;

        }else if ((a6<a2)&&(a6<a3)&&(a6<a4)&&(a6<a5)&&(a6<a1)&&(a6<a7)&&(a6<a8)&&(a6<a9)&&(a6<a10)&&(a6<a11)&&(a6<a12)&&(a6<a13)&&(a6<a14)&&(a6<a15)&&(a6<a16)&&(a6<a17)&&(a6<a18)&&(a6<a19)&&(a6<a20)&&(a6<a21)&&(a6<a22)&&(a6<a23)&&(a6<a24)&&(a6<a25)&&(a6<a26)&&(a6<a27)&&(a6<a28)&&(a6<a29)&&(a6<a30)&&(a6<a31)&&(a6<a32)&&(a6<a33)){
            w=6;

        }else if ((a7<a2)&&(a7<a3)&&(a7<a4)&&(a7<a5)&&(a7<a6)&&(a7<a1)&&(a7<a8)&&(a7<a9)&&(a7<a10)&&(a7<a11)&&(a7<a12)&&(a7<a13)&&(a7<a14)&&(a7<a15)&&(a7<a16)&&(a7<a17)&&(a7<a18)&&(a7<a19)&&(a7<a20)&&(a7<a21)&&(a7<a22)&&(a7<a23)&&(a7<a24)&&(a7<a25)&&(a7<a26)&&(a7<a27)&&(a7<a28)&&(a7<a29)&&(a7<a30)&&(a7<a31)&&(a7<a32)&&(a7<a33)){
            w=7;

        }else if ((a8<a2)&&(a8<a3)&&(a8<a4)&&(a8<a5)&&(a8<a6)&&(a8<a7)&&(a8<a1)&&(a8<a9)&&(a8<a10)&&(a8<a11)&&(a8<a12)&&(a8<a13)&&(a8<a14)&&(a8<a15)&&(a8<a16)&&(a8<a17)&&(a8<a18)&&(a8<a19)&&(a8<a20)&&(a8<a21)&&(a8<a22)&&(a8<a23)&&(a8<a24)&&(a8<a25)&&(a8<a26)&&(a8<a27)&&(a8<a28)&&(a8<a29)&&(a8<a30)&&(a8<a31)&&(a8<a32)&&(a8<a33)){
            w=8;

        }else if ((a9<a2)&&(a9<a3)&&(a9<a4)&&(a9<a5)&&(a9<a6)&&(a9<a7)&&(a9<a8)&&(a9<a1)&&(a9<a10)&&(a9<a11)&&(a9<a12)&&(a9<a13)&&(a9<a14)&&(a9<a15)&&(a9<a16)&&(a9<a17)&&(a9<a18)&&(a9<a19)&&(a9<a20)&&(a9<a21)&&(a9<a22)&&(a9<a23)&&(a9<a24)&&(a9<a25)&&(a9<a26)&&(a9<a27)&&(a9<a28)&&(a9<a29)&&(a9<a30)&&(a9<a31)&&(a9<a32)&&(a9<a33)){
            w=9;

        }else if ((a10<a2)&&(a10<a3)&&(a10<a4)&&(a10<a5)&&(a10<a6)&&(a10<a7)&&(a10<a8)&&(a10<a9)&&(a10<a1)&&(a10<a11)&&(a10<a12)&&(a10<a13)&&(a10<a14)&&(a10<a15)&&(a10<a16)&&(a10<a17)&&(a10<a18)&&(a10<a19)&&(a10<a20)&&(a10<a21)&&(a10<a22)&&(a10<a23)&&(a10<a24)&&(a10<a25)&&(a10<a26)&&(a10<a27)&&(a10<a28)&&(a10<a29)&&(a10<a30)&&(a10<a31)&&(a10<a32)&&(a10<a33)){
            w=10;

        }else if ((a11<a2)&&(a11<a3)&&(a11<a4)&&(a11<a5)&&(a11<a6)&&(a11<a7)&&(a11<a8)&&(a11<a9)&&(a11<a10)&&(a11<a1)&&(a11<a12)&&(a11<a13)&&(a11<a14)&&(a11<a15)&&(a11<a16)&&(a11<a17)&&(a11<a18)&&(a11<a19)&&(a11<a20)&&(a11<a21)&&(a11<a22)&&(a11<a23)&&(a11<a24)&&(a11<a25)&&(a11<a26)&&(a11<a27)&&(a11<a28)&&(a11<a29)&&(a11<a30)&&(a11<a31)&&(a11<a32)&&(a11<a33)){
            w=11;

        }else if ((a12<a2)&&(a12<a3)&&(a12<a4)&&(a12<a5)&&(a12<a6)&&(a12<a7)&&(a12<a8)&&(a12<a9)&&(a12<a10)&&(a12<a11)&&(a12<a1)&&(a12<a13)&&(a12<a14)&&(a12<a15)&&(a12<a16)&&(a12<a17)&&(a12<a18)&&(a12<a19)&&(a12<a20)&&(a12<a21)&&(a12<a22)&&(a12<a23)&&(a12<a24)&&(a12<a25)&&(a12<a26)&&(a12<a27)&&(a12<a28)&&(a12<a29)&&(a12<a30)&&(a12<a31)&&(a12<a32)&&(a12<a33)){
            w=12;

        }else if ((a13<a2)&&(a13<a3)&&(a13<a4)&&(a13<a5)&&(a13<a6)&&(a13<a7)&&(a13<a8)&&(a13<a9)&&(a13<a10)&&(a13<a11)&&(a13<a12)&&(a13<a1)&&(a13<a14)&&(a13<a15)&&(a13<a16)&&(a13<a17)&&(a13<a18)&&(a13<a19)&&(a13<a20)&&(a13<a21)&&(a13<a22)&&(a13<a23)&&(a13<a24)&&(a13<a25)&&(a13<a26)&&(a13<a27)&&(a13<a28)&&(a13<a29)&&(a13<a30)&&(a13<a31)&&(a13<a32)&&(a13<a33)){
            w=13;

        }else if ((a14<a2)&&(a14<a3)&&(a14<a4)&&(a14<a5)&&(a14<a6)&&(a14<a7)&&(a14<a8)&&(a14<a9)&&(a14<a10)&&(a14<a11)&&(a14<a12)&&(a14<a13)&&(a14<a1)&&(a14<a15)&&(a14<a16)&&(a14<a17)&&(a14<a18)&&(a14<a19)&&(a14<a20)&&(a14<a21)&&(a14<a22)&&(a14<a23)&&(a14<a24)&&(a14<a25)&&(a14<a26)&&(a14<a27)&&(a14<a28)&&(a14<a29)&&(a14<a30)&&(a14<a31)&&(a14<a32)&&(a14<a33)){
            w=14;

        }else if ((a15<a2)&&(a15<a3)&&(a15<a4)&&(a15<a5)&&(a15<a6)&&(a15<a7)&&(a15<a8)&&(a15<a9)&&(a15<a10)&&(a15<a11)&&(a15<a12)&&(a15<a13)&&(a15<a14)&&(a15<a1)&&(a15<a16)&&(a15<a17)&&(a15<a18)&&(a15<a19)&&(a15<a20)&&(a15<a21)&&(a15<a22)&&(a15<a23)&&(a15<a24)&&(a15<a25)&&(a15<a26)&&(a15<a27)&&(a15<a28)&&(a15<a29)&&(a15<a30)&&(a15<a31)&&(a15<a32)&&(a15<a33)){
            w=15;

        }else if ((a16<a2)&&(a16<a3)&&(a16<a4)&&(a16<a5)&&(a16<a6)&&(a16<a7)&&(a16<a8)&&(a16<a9)&&(a16<a10)&&(a16<a11)&&(a16<a12)&&(a16<a13)&&(a16<a14)&&(a16<a15)&&(a16<a1)&&(a16<a17)&&(a16<a18)&&(a16<a19)&&(a16<a20)&&(a16<a21)&&(a16<a22)&&(a16<a23)&&(a16<a24)&&(a16<a25)&&(a16<a26)&&(a16<a27)&&(a16<a28)&&(a16<a29)&&(a16<a30)&&(a16<a31)&&(a16<a32)&&(a16<a33)){
            w=16;

        }else if ((a17<a2)&&(a17<a3)&&(a17<a4)&&(a17<a5)&&(a17<a6)&&(a17<a7)&&(a17<a8)&&(a17<a9)&&(a17<a10)&&(a17<a11)&&(a17<a12)&&(a17<a13)&&(a17<a14)&&(a17<a15)&&(a17<a16)&&(a17<a1)&&(a17<a18)&&(a17<a19)&&(a17<a20)&&(a17<a21)&&(a17<a22)&&(a17<a23)&&(a17<a24)&&(a17<a25)&&(a17<a26)&&(a17<a27)&&(a17<a28)&&(a17<a29)&&(a17<a30)&&(a17<a31)&&(a17<a32)&&(a17<a33)){
            w=17;

        }else if ((a18<a2)&&(a18<a3)&&(a18<a4)&&(a18<a5)&&(a18<a6)&&(a18<a7)&&(a18<a8)&&(a18<a9)&&(a18<a10)&&(a18<a11)&&(a18<a12)&&(a18<a13)&&(a18<a14)&&(a18<a15)&&(a18<a16)&&(a18<a17)&&(a18<a1)&&(a18<a19)&&(a18<a20)&&(a18<a21)&&(a18<a22)&&(a18<a23)&&(a18<a24)&&(a18<a25)&&(a18<a26)&&(a18<a27)&&(a18<a28)&&(a18<a29)&&(a18<a30)&&(a18<a31)&&(a18<a32)&&(a18<a33)){
            w=18;

        }else if ((a19<a2)&&(a19<a3)&&(a19<a4)&&(a19<a5)&&(a19<a6)&&(a19<a7)&&(a19<a8)&&(a19<a9)&&(a19<a10)&&(a19<a11)&&(a19<a12)&&(a19<a13)&&(a19<a14)&&(a19<a15)&&(a19<a16)&&(a19<a17)&&(a19<a18)&&(a19<a1)&&(a19<a20)&&(a19<a21)&&(a19<a22)&&(a19<a23)&&(a19<a24)&&(a19<a25)&&(a19<a26)&&(a19<a27)&&(a19<a28)&&(a19<a29)&&(a19<a30)&&(a19<a31)&&(a19<a32)&&(a19<a33)){
            w=19;

        }else if ((a20<a2)&&(a20<a3)&&(a20<a4)&&(a20<a5)&&(a20<a6)&&(a20<a7)&&(a20<a8)&&(a20<a9)&&(a20<a10)&&(a20<a11)&&(a20<a12)&&(a20<a13)&&(a20<a14)&&(a20<a15)&&(a20<a16)&&(a20<a17)&&(a20<a18)&&(a20<a19)&&(a20<a1)&&(a20<a21)&&(a20<a22)&&(a20<a23)&&(a20<a24)&&(a20<a25)&&(a20<a26)&&(a20<a27)&&(a20<a28)&&(a20<a29)&&(a20<a30)&&(a20<a31)&&(a20<a32)&&(a20<a33)){
            w=20;

        }else if ((a21<a2)&&(a21<a3)&&(a21<a4)&&(a21<a5)&&(a21<a6)&&(a21<a7)&&(a21<a8)&&(a21<a9)&&(a21<a10)&&(a21<a11)&&(a21<a12)&&(a21<a13)&&(a21<a14)&&(a21<a15)&&(a21<a16)&&(a21<a17)&&(a21<a18)&&(a21<a19)&&(a21<a20)&&(a21<a1)&&(a21<a22)&&(a21<a23)&&(a21<a24)&&(a21<a25)&&(a21<a26)&&(a21<a27)&&(a21<a28)&&(a21<a29)&&(a21<a30)&&(a21<a31)&&(a21<a32)&&(a21<a33)){
            w=21;

        }else if ((a22<a2)&&(a22<a3)&&(a22<a4)&&(a22<a5)&&(a22<a6)&&(a22<a7)&&(a22<a8)&&(a22<a9)&&(a22<a10)&&(a22<a11)&&(a22<a12)&&(a22<a13)&&(a22<a14)&&(a22<a15)&&(a22<a16)&&(a22<a17)&&(a22<a18)&&(a22<a19)&&(a22<a20)&&(a22<a21)&&(a22<a1)&&(a22<a23)&&(a22<a24)&&(a22<a25)&&(a22<a26)&&(a22<a27)&&(a22<a28)&&(a22<a29)&&(a22<a30)&&(a22<a31)&&(a22<a32)&&(a22<a33)){
            w=22;

        }else if ((a23<a2)&&(a23<a3)&&(a23<a4)&&(a23<a5)&&(a23<a6)&&(a23<a7)&&(a23<a8)&&(a23<a9)&&(a23<a10)&&(a23<a11)&&(a23<a12)&&(a23<a13)&&(a23<a14)&&(a23<a15)&&(a23<a16)&&(a23<a17)&&(a23<a18)&&(a23<a19)&&(a23<a20)&&(a23<a21)&&(a23<a22)&&(a23<a1)&&(a23<a24)&&(a23<a25)&&(a23<a26)&&(a23<a27)&&(a23<a28)&&(a23<a29)&&(a23<a30)&&(a23<a31)&&(a23<a32)&&(a23<a33)){
            w=23;

        }else if ((a24<a2)&&(a24<a3)&&(a24<a4)&&(a24<a5)&&(a24<a6)&&(a24<a7)&&(a24<a8)&&(a24<a9)&&(a24<a10)&&(a24<a11)&&(a24<a12)&&(a24<a13)&&(a24<a14)&&(a24<a15)&&(a24<a16)&&(a24<a17)&&(a24<a18)&&(a24<a19)&&(a24<a20)&&(a24<a21)&&(a24<a22)&&(a24<a23)&&(a24<a1)&&(a24<a25)&&(a24<a26)&&(a24<a27)&&(a24<a28)&&(a24<a29)&&(a24<a30)&&(a24<a31)&&(a24<a32)&&(a24<a33)){
            w=24;

        }else if ((a25<a2)&&(a25<a3)&&(a25<a4)&&(a25<a5)&&(a25<a6)&&(a25<a7)&&(a25<a8)&&(a25<a9)&&(a25<a10)&&(a25<a11)&&(a25<a12)&&(a25<a13)&&(a25<a14)&&(a25<a15)&&(a25<a16)&&(a25<a17)&&(a25<a18)&&(a25<a19)&&(a25<a20)&&(a25<a21)&&(a25<a22)&&(a25<a23)&&(a25<a24)&&(a25<a1)&&(a25<a26)&&(a25<a27)&&(a25<a28)&&(a25<a29)&&(a25<a30)&&(a25<a31)&&(a25<a32)&&(a25<a33)){
            w=25;

        }else if ((a26<a2)&&(a26<a3)&&(a26<a4)&&(a26<a5)&&(a26<a6)&&(a26<a7)&&(a26<a8)&&(a26<a9)&&(a26<a10)&&(a26<a11)&&(a26<a12)&&(a26<a13)&&(a26<a14)&&(a26<a15)&&(a26<a16)&&(a26<a17)&&(a26<a18)&&(a26<a19)&&(a26<a20)&&(a26<a21)&&(a26<a22)&&(a26<a23)&&(a26<a24)&&(a26<a25)&&(a26<a1)&&(a26<a27)&&(a26<a28)&&(a26<a29)&&(a26<a30)&&(a26<a31)&&(a26<a32)&&(a26<a33)){
            w=26;

        }else if ((a27<a2)&&(a27<a3)&&(a27<a4)&&(a27<a5)&&(a27<a6)&&(a27<a7)&&(a27<a8)&&(a27<a9)&&(a27<a10)&&(a27<a11)&&(a27<a12)&&(a27<a13)&&(a27<a14)&&(a27<a15)&&(a27<a16)&&(a27<a17)&&(a27<a18)&&(a27<a19)&&(a27<a20)&&(a27<a21)&&(a27<a22)&&(a27<a23)&&(a27<a24)&&(a27<a25)&&(a27<a26)&&(a27<a1)&&(a27<a28)&&(a27<a29)&&(a27<a30)&&(a27<a31)&&(a27<a32)&&(a27<a33)){
            w=27;
        }else if ((a28<a2)&&(a28<a3)&&(a28<a4)&&(a28<a5)&&(a28<a6)&&(a28<a7)&&(a28<a8)&&(a28<a9)&&(a28<a10)&&(a28<a11)&&(a28<a12)&&(a28<a13)&&(a28<a14)&&(a28<a15)&&(a28<a16)&&(a28<a17)&&(a28<a18)&&(a28<a19)&&(a28<a20)&&(a28<a21)&&(a28<a22)&&(a28<a23)&&(a28<a24)&&(a28<a25)&&(a28<a26)&&(a28<a27)&&(a28<a1)&&(a28<a29)&&(a28<a30)&&(a28<a31)&&(a28<a32)&&(a28<a33)){w=29;
            w=28;
        }else if ((a29<a2)&&(a29<a3)&&(a29<a4)&&(a29<a5)&&(a29<a6)&&(a29<a7)&&(a29<a8)&&(a29<a9)&&(a29<a10)&&(a29<a11)&&(a29<a12)&&(a29<a13)&&(a29<a14)&&(a29<a15)&&(a29<a16)&&(a29<a17)&&(a29<a18)&&(a29<a19)&&(a29<a20)&&(a29<a21)&&(a29<a22)&&(a29<a23)&&(a29<a24)&&(a29<a25)&&(a29<a26)&&(a29<a27)&&(a29<a28)&&(a29<a1)&&(a29<a30)&&(a29<a31)&&(a29<a32)&&(a29<a33)){
            w=29;
        }else if ((a30<a2)&&(a30<a3)&&(a30<a4)&&(a30<a5)&&(a30<a6)&&(a30<a7)&&(a30<a8)&&(a30<a9)&&(a30<a10)&&(a30<a11)&&(a30<a12)&&(a30<a13)&&(a30<a14)&&(a30<a15)&&(a30<a16)&&(a30<a17)&&(a30<a18)&&(a30<a19)&&(a30<a20)&&(a30<a21)&&(a30<a22)&&(a30<a23)&&(a30<a24)&&(a30<a25)&&(a30<a26)&&(a30<a27)&&(a30<a28)&&(a30<a29)&&(a30<a1)&&(a30<a31)&&(a30<a32)&&(a30<a33)){
            w=30;
        }else if ((a31<a2)&&(a31<a3)&&(a31<a4)&&(a31<a5)&&(a31<a6)&&(a31<a7)&&(a31<a8)&&(a31<a9)&&(a31<a10)&&(a31<a11)&&(a31<a12)&&(a31<a13)&&(a31<a14)&&(a31<a15)&&(a31<a16)&&(a31<a17)&&(a31<a18)&&(a31<a19)&&(a31<a20)&&(a31<a21)&&(a31<a22)&&(a31<a23)&&(a31<a24)&&(a31<a25)&&(a31<a26)&&(a31<a27)&&(a31<a28)&&(a31<a29)&&(a31<a30)&&(a31<a1)&&(a31<a32)&&(a31<a33)){
            w=31;
        }else if ((a32<a2)&&(a32<a3)&&(a32<a4)&&(a32<a5)&&(a32<a6)&&(a32<a7)&&(a32<a8)&&(a32<a9)&&(a32<a10)&&(a32<a11)&&(a32<a12)&&(a32<a13)&&(a32<a14)&&(a32<a15)&&(a32<a16)&&(a32<a17)&&(a32<a18)&&(a32<a19)&&(a32<a20)&&(a32<a21)&&(a32<a22)&&(a32<a23)&&(a32<a24)&&(a32<a25)&&(a32<a26)&&(a32<a27)&&(a32<a28)&&(a32<a29)&&(a32<a30)&&(a32<a31)&&(a32<a1)&&(a32<a33)){
            w=32;
        }else if ((a33<a2)&&(a33<a3)&&(a33<a4)&&(a33<a5)&&(a33<a6)&&(a33<a7)&&(a33<a8)&&(a33<a9)&&(a33<a10)&&(a33<a11)&&(a33<a12)&&(a33<a13)&&(a33<a14)&&(a33<a15)&&(a33<a16)&&(a33<a17)&&(a33<a18)&&(a33<a19)&&(a33<a20)&&(a33<a21)&&(a33<a22)&&(a33<a23)&&(a33<a24)&&(a33<a25)&&(a33<a26)&&(a33<a27)&&(a33<a28)&&(a33<a29)&&(a33<a30)&&(a33<a31)&&(a33<a32)&&(a33<a1)){
        w=33;
        }else{
            Toast.makeText(this,"Try double now", Toast.LENGTH_SHORT).show();
        }
  /*
  /*
  /*      if (a1 == min_a) {
            w = 1;
        } else if (a2 == min_a) {
            w = 2;
        } else if (a3 == min_a) {
            w = 3;
        } else if (a4 == min_a) {
            w = 4;
        } else if (a5 == min_a) {
            w = 5;
        } else if (a6 == min_a) {
            w = 6;
        } else if (a7 == min_a) {
            w = 7;
        } else if (a8 == min_a) {
            w = 8;
        } else if (a9 == min_a) {
            w = 9;
        } else if (a10 == min_a) {
            w = 10;
        } else if (a11 == min_a) {
            w = 11;
        } else if (a12 == min_a) {
            w = 12;
        } else if (a13 == min_a) {
            w = 13;
        } else if (a14 == min_a) {
            w = 14;
        } else if (a15 == min_a) {
            w = 15;
        } else if (a16 == min_a) {
            w = 16;
        } else if (a17 == min_a) {
            w = 17;
        } else if (a18 == min_a) {
            w = 18;
        } else if (a19 == min_a) {
            w = 19;
        } else if (a20 == min_a) {
            w = 20;
        } else if (a21 == min_a) {
            w = 21;
        } else if (a22 == min_a) {
            w = 22;
        } else if (a23 == min_a) {
            w = 23;
        } else if (a24 == min_a) {
            w = 24;
        } else if (a25 == min_a) {
            w = 25;
        } else if (a26 == min_a) {
            w = 26;
        } else if (a27 == min_a) {
            w = 27;
        } else if (a28 == min_a) {
            w = 28;
        } else if (a29 == min_a) {
            w = 29;
        } else if (a30 == min_a) {
            w = 30;
        } else if (a31 == min_a) {
            w = 31;
        } else if (a32 == min_a) {
            w = 32;
        } else if (a33 == min_a){
            w = 33;
        }else{
            w=0;
        }
        double q= b[33];
       double g= b[17];*/

        return w;


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
    public static float distFrom(float lat1, float lng1, double lat2, double lng2) {
        float earthRadius = 6371000; //meters
        float dLat = (float) Math.toRadians(lat2-lat1);
        float dLng = (float) Math.toRadians(lng2-lng1);
        float a = (float) (Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                                Math.sin(dLng/2) * Math.sin(dLng/2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = (float) (earthRadius * c);
      return (float) dist;

    }

    public void myClick(View v) {

        //  Toast.makeText(this,"I phone 6132121321",Toast.LENGTH_SHORT).show();

        //get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout) v.getParent();
        TableRow child0 = (TableRow) vwParentRow.getChildAt(0);
        TextView tv = (TextView) child0.getChildAt(2);
       // TextView tv1 = (TextView) child0.getChildAt(3);

      //  String label = tv.getText().toString();
        String label1 = tv.getText().toString();
        {
           // if (label.equals("1")) {
                if (label1.equals("04:55")) {
                    if (((fD1 == 5) && (fDo1 <= 30)) || ((fD1 == 4) && (fDo1 <= 55))) {
                        Busno = "8040";
                        getBUS();
                       // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("06:15")) {
                    if (((fD1 == 6) && (fDo1 >= 15)) || ((fD1 == 6) && (fDo1 <= 55))) {
                        Busno = "8040";
                        getBUS();
                       // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("07:00")) {
                    if (((fD1 == 7) && (fDo1 <= 40)) || ((fD1 == 7) && (fDo1 >= 00))) {
                        Busno = "8098";
                        getBUS();
                       // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("07:30")) {
                    if (((fD1 == 8) && (fDo1 <= 15)) || ((fD1 == 7) && (fDo1 >= 30))) {
                        Busno = "8040";
                        getBUS();
                        //extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("08:20")) {
                    if (((fD1 == 9) && (fDo1 == 00)) || ((fD1 == 8) && (fDo1 >= 20))) {
                        Busno = "8098";
                        getBUS();
                       // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("08:59")) {
                    if (((fD1 == 8) && (fDo1 >= 59)) || ((fD1 == 9) && (fDo1 <= 30))) {
                        Busno = "8040";
                        getBUS();
                      //  extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("09:40")) {
                    if (((fD1 == 9) && (fDo1 >= 40)) || ((fD1 == 10) && (fDo1 <= 15))) {
                        Busno = "8098";
                        getBUS();
                       // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("10:14")) {
                    if (((fD1 == 11) && (fDo1 <= 00)) || ((fD1 == 10) && (fDo1 >= 14))) {
                        Busno = "8040";
                        getBUS();
                      //  extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("11:00")) {
                    if (((fD1 == 11) && (fDo1 >= 00)) || ((fD1 == 11) && (fDo1 <= 40))) {
                        Busno = "8098";
                        getBUS();
                       // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("17:00")) {
                    if (((fD1 == 17) && (fDo1 >= 00)) || ((fD1 == 17) && (fDo1 <= 40))) {
                        Busno = "8098";
                        getBUS();
                        // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("12:20")) {
                    if (((fD1 == 12) && (fDo1 >= 20)) || ((fD1 == 13) && (fDo1 == 0))) {
                        Busno = "8098";
                        getBUS();
                       //
                        // extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                }

                //  Toast.makeText(this,label,Toast.LENGTH_SHORT).show();
                //  if( label.equals("1")){

                //  Toast.makeText(this,"I phone 6",Toast.LENGTH_SHORT).show();
                // extrainto();

          //  } else {
                if (label1.equals("05:35")) {
                    if (((fD1 == 6) && (fDo1 <= 15)) || ((fD1 == 5) && (fDo1 >= 35))) {
                        Busno = "8040";
                        getBUS();
                      //  extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("06:20")) {
                    if (((fD1 == 6) && (fDo1 >= 20)) || ((fD1 == 7) && (fDo1 <= 00))) {
                        Busno = "8098";
                        getBUS();
                      //  extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("06:55")) {
                    if (((fD1 == 7) && (fDo1 <= 30)) || ((fD1 == 6) && (fDo1 >= 55))) {
                        Busno = "8040";
                        getBUS();
                       // extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("07:40")) {
                    if (((fD1 == 8) && (fDo1 <= 20)) || ((fD1 == 7) && (fDo1 >= 40))) {
                        Busno = "8098";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("08:15")) {
                    if (((fD1 == 9) && (fDo1 == 00)) || ((fD1 == 8) && (fDo1 >= 15))) {
                        Busno = "8040";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("09:00")) {
                    if (((fD1 == 10) && (fDo1 <= 15)) || ((fD1 == 9) && (fDo1 >= 00))) {
                        Busno = "8098";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("09:30")) {
                    if (((fD1 == 9) && (fDo1 >= 30)) || ((fD1 == 10) && (fDo1 <= 15))) {
                        Busno = "8040";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("10:15")) {
                    if (((fD1 == 11) && (fDo1 <= 00)) || ((fD1 == 10) && (fDo1 >= 15))) {
                        Busno = "8098";
                        getBUS();
                      //  extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("11:40")) {
                    if (((fD1 == 11) && (fDo1 >= 40)) || ((fD1 == 12) && (fDo1 <= 20))) {
                        Busno = "8098";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("14:20")) {
                    if (((fD1 == 15) && (fDo1 <= 00)) || ((fD1 == 14) && (fDo1 >= 20))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                     //   extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }

                } else if (label1.equals("13:00")) {
                    if (((fD1 == 13) && (fDo1 <= 40)) || ((fD1 == 13) && (fDo1 >= 0))) {
                  //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                       // extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                }else if (label1.equals("15:40")) {
                    if (((fD1 == 16) && (fDo1 <= 20)) || ((fD1 == 15) && (fDo1 >= 40))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                      //  extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                }  else if (label1.equals("15:00")) {
                    if (((fD1 == 15) && (fDo1 <= 40)) || ((fD1 == 15) && (fDo1 >= 00))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("16:20")) {
                    if (((fD1 == 17) && (fDo1 <= 00)) || ((fD1 == 16) && (fDo1 >= 20))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                } else if (label1.equals("17:00")) {
                    if (((fD1 == 17) && (fDo1 <= 40)) || ((fD1 == 17) && (fDo1 >= 00))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                     //   extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                }else if (label1.equals("17:40")) {
                    if (((fD1 == 18) && (fDo1 <= 30)) || ((fD1 == 17) && (fDo1 >= 40))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                      //  extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                }else if (label1.equals("18:30")) {
                    if (((fD1 == 19) && (fDo1 <= 00)) || ((fD1 == 18) && (fDo1 >= 30))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                     //   extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                }else if (label1.equals("19:00")) {
                    if (((fD1 == 20) && (fDo1 <= 05)) || ((fD1 == 19) && (fDo1 >= 00))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                     //   extrainto();
                        doTheAutoRefresh();

                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    } }else if (label1.equals("20:05")) {
                    if (((fD1 == 20) && (fDo1 <= 40)) || ((fD1 == 20) && (fDo1 >= 05))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                      //  extraintoone();
                        doTheAutoRefreshone();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    } }else if (label1.equals("20:45")) {
                    if (((fD1 == 21) && (fDo1 <= 20)) ||  ((fD1 == 20) && (fDo1 >= 45))) {
                        //  if(fD1!=0){
                        Busno = "8098";
                        getBUS();
                      //  extrainto();
                        doTheAutoRefresh();
                    } else {
                        Toast.makeText(this, "Not Running", Toast.LENGTH_SHORT).show();
                    }
                //  Toast.makeText(this,label,Toast.LENGTH_SHORT).show();
                //  if( label.equals("1")){

                //  Toast.makeText(this,"I phone 6",Toast.LENGTH_SHORT).show();
                // extrainto();

            }
            //  Toast.makeText(this,"iPad Air",Toast.LENGTH_SHORT).show();
            // extraintoone();
        }
    }
//}


            //Button btnChild = (Button)vwParentRow.getChildAt(2);

            //btnChild.setText(child.getText());
            //btnChild.setText("I've been clicked!");
    private void extraintoone() {
    //    final ProgressDialog loading = ProgressDialog.show(this, "Getting Bus", "Please wait...", false, false);
        String CurrentString = "b__"+String.valueOf(w)+"__"+Busno;
        Intent intent = new Intent(ListViewd.this, Main2Activity.class);
        intent.putExtra(KEY1,CurrentString);
        startActivity(intent);
    }

    public void extrainto() {
     //   final ProgressDialog loading = ProgressDialog.show(this, "Getting Bus", "Please wait...", false, false);
        String CurrentString = "a__"+String.valueOf(w)+"__"+Busno;;
        Intent intent = new Intent(ListViewd.this, Main2Activity.class);
        intent.putExtra(KEY1,CurrentString);
        startActivity(intent);
    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    public void onLocationChanged(Location location) {

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}
