package com.example.vrs.spinnerdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;



public class deletealert extends Activity {

    private Spinner spinner1;
    private Spinner spinner2;
    private Button btndelete;
    private EditText edittextMob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        edittextMob = (EditText) findViewById(R.id.editText1);

        addItemsOnSpinner1();
        addItemsOnSpinner2();
        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    public void delete(View view){


        String mob = edittextMob.getText().toString();
        //String sp1 = spinner1.getContext().toString();
        String sp1 = String.valueOf(spinner1.getSelectedItem());
        String sp2 = String.valueOf(spinner2.getSelectedItem());

        deleteToDatabase(mob,sp1,sp2);
        //  Toast.makeText(getApplicationContext(), "hello", RESULT_OK).show();

    }

    private void deleteToDatabase(String mob,String sp1,String sp2) {
        // TODO Auto-generated method stub
        // TODO Auto-generated method stub
        class DeleteAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String paramMob = params[0];
                String paramSp1 = params[1];
                String paramSp2 = params[2];
                // String url = "http://flocus.16mb.com/alert.php?";
                String data = null;
                //route=ng&at_location=dh&schedule_timing=1100&alarm_for=today&on_mobileno=412563";


                try {
                    data= "alarm_for="+ URLEncoder.encode(paramSp1, "UTF-8");
                    data+= "&on_mobileno="+URLEncoder.encode(paramMob, "UTF-8");
                    data+= "&at_location="+URLEncoder.encode(paramSp2, "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                final String url = "http://flocus.16mb.com/delete.php?"+data;



                InputStream inputStream = null;

                HttpURLConnection urlConnection;

                try {
	                        /* forming th java.net.URL object */
                    URL url2 = new URL(url);

                    urlConnection = (HttpURLConnection) url2.openConnection();

	                        /* for Get request */
                    urlConnection.setRequestMethod("GET");

                    int statusCode = urlConnection.getResponseCode();

	                        /* 200 represents HTTP OK */
                    if (statusCode == 200) {

                        BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = r.readLine()) != null) {
                            response.append(line);
                        }
                        return response.toString();

                    }

                } catch (ProtocolException e) {
                    e.printStackTrace();
                    return "Protocol Exception";
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "MalformedURLException";
                } catch (IOException e) {
                    e.printStackTrace();
                    return "IOException";
                }


                return "success";


            }


        }


        DeleteAsyncTask delete = new DeleteAsyncTask();
        delete.execute(mob,sp1,sp2);

    }
    public void addItemsOnSpinner2() {

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Nasik road Stop");
        list.add("Shivaji C.O.E");
        list.add("Bytco Stop");
        list.add("Nandurgao Stop");
        list.add("Nandur naka");
        list.add("Cross Road dwaraka");
        list.add("Link road");
        list.add("Panjar Pol Stop");
        list.add("Nimai Near");
        list.add("Nimani Stop");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);
    }

    private void addItemsOnSpinner1() {
        // TODO Auto-generated method stub
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        list.add("Today");
        list.add("Tommorrow");
        list.add("Everyday");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);

    }

    private void addListenerOnSpinnerItemSelection() {
        // TODO Auto-generated method stub
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }

    private void addListenerOnButton() {
        // TODO Auto-generated method stub
        spinner1 = (Spinner) findViewById(R.id.spinner1);

        btndelete = (Button) findViewById(R.id.button7);

        btndelete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                delete(v);
                Toast.makeText(getApplicationContext(), "Alert Deleted Successfully..", Toast.LENGTH_LONG).show();
            }

        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
