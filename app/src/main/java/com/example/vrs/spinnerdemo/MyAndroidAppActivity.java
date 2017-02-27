package com.example.vrs.spinnerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class MyAndroidAppActivity extends Activity {

	private Spinner spinner1, spinner2 ,spinner3 ,spinner4;
	private Button btnSubmit;
	private EditText edittextMob;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		edittextMob = (EditText) findViewById(R.id.editText1);

		addItemsOnSpinner2();
		addItemsOnSpinner3();
		addItemsOnSpinner4();
		addListenerOnButton();
		addListenerOnSpinnerItemSelection();

	}
	
	public void insert(View view){
       
		
        String mob = edittextMob.getText().toString();
        //String sp1 = spinner1.getContext().toString();
        String sp1 = String.valueOf(spinner1.getSelectedItem());
        //String sp2 = spinner2.getContext().toString();
        //String sp3 = spinner3.getContext().toString();
        //String sp4 = spinner4.getContext().toString();
        String sp2 = String.valueOf(spinner2.getSelectedItem());
        String sp3 = String.valueOf(spinner3.getSelectedItem());
        String sp4 = String.valueOf(spinner4.getSelectedItem());
        //Toast.makeText(getApplicationContext(), mob + " " + sp1 + " " +sp2 , Toast.LENGTH_LONG).show();
        insertToDatabase(mob,sp1,sp2,sp3,sp4);

        
    }

	private void insertToDatabase(String mob, String sp1, String sp2,
								  String sp3, String sp4) {
		
		// Toast.makeText(MyAndroidAppActivity.this , mob + " " + sp1 + " " +sp2 , Toast.LENGTH_LONG).show();
	       
		// TODO Auto-generated method stub
	       class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
	            @Override
	            protected String doInBackground(String... params) {
	                String paramMob = params[0];
	                String paramSp1 = params[1];
	                String paramSp2 = params[2];
	                String paramSp3 = params[3];
	                String paramSp4 = params[4];
	       //         String url = "http://flocus.16mb.com/alert.php?";
	               String data = null;
	            		   //route=ng&at_location=dh&schedule_timing=1100&alarm_for=today&on_mobileno=412563";
	                
	
	               try {
		               data = "route=" + URLEncoder.encode(paramSp1, "UTF-8");
		               data+= "&at_location=" + URLEncoder.encode(paramSp2, "UTF-8");
		               data+= "&schedule_timing=" + URLEncoder.encode(paramSp3, "UTF-8");
		               data+= "&alarm_for="+ URLEncoder.encode(paramSp4, "UTF-8");
		               data+= "&on_mobileno="+ URLEncoder.encode(paramMob, "UTF-8");

	                } catch (UnsupportedEncodingException e) {
	                    e.printStackTrace();
	                }
	               final String url = "http://flocus.16mb.com/alert.php?"+data;

	               
	               
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

					/*Intent intent= new Intent(MyAndroidAppActivity.this, Home.class);
					startActivity(intent);*/
	                return "success";
	            }
	 
	            @Override
	            protected void onPostExecute(String result) {
	                super.onPostExecute(result);
	 
	                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
					Intent intent= new Intent(MyAndroidAppActivity.this, Home.class);
					startActivity(intent);
	                //TextView textViewResult = (TextView) findViewById(R.id.textViewResult);
	              //  textViewResult.setText("Inserted");
	            }
	        }
	        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
	        sendPostReqAsyncTask.execute(mob,sp1, sp2, sp3,sp4);
	    }
	 
	

	//add items into spinner dynamically
	public void addItemsOnSpinner2() {

		spinner2 = (Spinner) findViewById(R.id.spinner2);
		List<String> list = new ArrayList<String>();
		list.add("Nasik road Stop");
		list.add("Bytco Stop");
		list.add("Central Jail");
		list.add("Nandur naka");
		list.add("Old Adgaon Naka");
		list.add("Nimani Stop");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(dataAdapter);
	}
	
	public void addItemsOnSpinner3() {

		spinner3 = (Spinner) findViewById(R.id.spinner3);
		List<String> list = new ArrayList<String>();
		list.add("04 :55 AM to 05 :25 AM");
		list.add("06 :15 AM to 06 :45 AM");
		list.add("07 :00 AM to 07 :40 AM");
		list.add("07 :30 AM to 08 :05 AM");
		list.add("08 :20 AM to 09 :00 AM");
		list.add("09 :40 AM to 10 :15 AM");
		list.add("10 :15 AM to 10 :45 AM");
		list.add("11 :00 AM to 11 :30 AM");
		list.add("12 :20 PM to 12 :50 PM");
		list.add("14 :20 PM to 14 :50 PM");
		list.add("12 :20 PM to 12 :50 PM");
		list.add("14 :20 PM to 14 :50 PM");
		list.add("14 :40 PM to 15 :10 PM");
		list.add("15 :40 PM to 16 :10 PM");
		list.add("16 :00 PM to 16 :30 PM");
		list.add("17 :00 PM to 17 :30 PM");
		list.add("17 :25 PM to 17 :55 PM");
		list.add("18 :30 PM to 19 :05 PM");
		list.add("18 :50 PM to 19 :20 PM");
		list.add("19 :15 PM to 19 :45 PM");
		list.add("20 :05 PM to 20 :30 PM");
		list.add("20 :35 PM to 21 :05 PM");
	
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner3.setAdapter(dataAdapter);
	}
	
	public void addItemsOnSpinner4() {

		spinner4 = (Spinner) findViewById(R.id.spinner4);
		List<String> list = new ArrayList<String>();
		list.add("Today");
		list.add("Tommorrow");
		list.add("Everyday");
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner4.setAdapter(dataAdapter);
	}
	

	public void addListenerOnSpinnerItemSelection(){
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
	}
	
	//get the selected dropdown list value
	public void addListenerOnButton() {

		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		spinner4 = (Spinner) findViewById(R.id.spinner4);
		
		btnSubmit = (Button) findViewById(R.id.btnSubmit);

		btnSubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				/*Toast.makeText(MyAndroidAppActivity.this,
						"OnClickListener : " + 
						"\nSpinner 1 : " + String.valueOf(spinner1.getSelectedItem()) +
						"\nSpinner 2 : " + String.valueOf(spinner2.getSelectedItem()) +
						"\nSpinner 3 : " + String.valueOf(spinner3.getSelectedItem()) +
						"\nSpinner 4 : " + String.valueOf(spinner4.getSelectedItem()) +
						"\nedittextMob : " + String.valueOf(edittextMob.getText().toString()),
						Toast.LENGTH_SHORT).show();*/
				insert(v);
			}

		});

	}

	private class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {

		}
	}
	public void delete(View view){
		Intent i = new Intent(MyAndroidAppActivity.this,deletealert.class);
		startActivity(i);
	}
}