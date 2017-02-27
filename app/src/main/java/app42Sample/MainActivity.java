/**
 * -----------------------------------------------------------------------
 *     Copyright 2015 ShepHertz Technologies Pvt Ltd. All rights reserved.
 * -----------------------------------------------------------------------
 */
package app42Sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vrs.spinnerdemo.Home;
import com.example.vrs.spinnerdemo.R;
import com.shephertz.app42.paas.sdk.android.App42API;
import com.shephertz.app42.paas.sdk.android.App42Log;

import plugin.App42GCMController;
import plugin.App42GCMController.App42GCMListener;
import plugin.App42GCMService;

/**
 * @author Vishnu Garg
 *
 */
public class MainActivity extends Activity implements App42GCMListener {
	public final static String KEY5 = "com.sliet.jeevansingh.projectjeevankeyone5";
	private static final String GoogleProjectNo = "91060095651";
	private TextView responseTv;
	private EditText edUserName, edMessage;
	String jas;
	String[] a;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mai);
		responseTv = ((TextView) findViewById(R.id.response_msg1));
		//edUserName = ((EditText) findViewById(R.id.uname));
		//edMessage = ((EditText) findViewById(R.id.message));
		Intent intent= getIntent();
		jas = intent.getStringExtra(KEY5);
		//Toast.makeText(this,jas,Toast.LENGTH_SHORT).show();
//		a=jas.split("__");
			App42API.initialize(
					this,
					"1ec470d493321afe99295d1569e4b37f49377f204212cca25396ea923e4a3ba3",
					"8dbccea7dedeced80bdbcaacf1cd1dd60f14b1804b49e244319a8f3e09f1e3fd");
			App42Log.setDebug(true);
			App42API.setLoggedInUser(jas);
			}

	public void onStart() {
		super.onStart();
		if (App42GCMController.isPlayServiceAvailable(this)) {
			App42GCMController.getRegistrationId(MainActivity.this,
					GoogleProjectNo, this);
			String message = getIntent().getStringExtra(
					App42GCMService.ExtraMessage);
			if (message != null)
				Log.d("MainActivity-onResume", "Message Recieved :" + message);
			IntentFilter filter = new IntentFilter(
					App42GCMService.DisplayMessageAction);
			filter.setPriority(2);
			registerReceiver(mBroadcastReceiver, filter);
		} else {
			Log.i("App42PushNotification",
					"No valid Google Play Services APK found.");
		}
	}

	/*
	 * * This method is called when a Activty is stop disable all the events if
	 * occuring (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStop()
	 */
	public void onStop() {
		super.onStop();


	}

	/*
	 * This method is called when a Activty is finished or user press the back
	 * button (non-Javadoc)
	 * 
	 * @override method of superclass
	 * 
	 * @see android.app.Activity#onDestroy()
	 */
	public void onDestroy() {
		super.onDestroy();

	}

	/*
	 * called when this activity is restart again
	 * 
	 * @override method of superclass
	 */
	public void onReStart() {
		super.onRestart();

	}

	/*
	 * called when activity is paused
	 * 
	 * @override method of superclass (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	public void onPause() {
		super.onPause();
		//unregisterReceiver(mBroadcastReceiver);
	}

	/*
	 * called when activity is resume
	 * 
	 * @override method of superclass (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	public void onResume() {
		super.onResume();
		String message = getIntent().getStringExtra(
				App42GCMService.ExtraMessage);
		if (message != null)
			Log.d("MainActivity-onResume", "Message Recieved :" + message);
		IntentFilter filter = new IntentFilter(
				App42GCMService.DisplayMessageAction);
		filter.setPriority(2);
		registerReceiver(mBroadcastReceiver, filter);
	}

	final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String message = intent
					.getStringExtra(App42GCMService.ExtraMessage);
			Log.i("MainActivity-BroadcastReceiver", "Message Recieved " + " : "
					+ message);
			responseTv.setText(message);

		}
	};

	public void onSendPushClicked(View view) {
		responseTv.setText("Sending Push to User ");
		App42GCMController.sendPushToUser(edUserName.getText().toString(),
				edMessage.getText().toString(), this);
		

	}

	@Override
	public void onError(String errorMsg) {
		// TODO Auto-generated method stub
		responseTv.setText("Error");
	}

	@Override
	public void onGCMRegistrationId(String gcmRegId) {
		// TODO Auto-generated method stub
		if(jas!=null) {
			responseTv.setText("Welcome " + jas);
		}
		else{
			responseTv.setText("Welcome");
		}
		App42GCMController.storeRegistrationId(this, gcmRegId);
		if(!App42GCMController.isApp42Registerd(MainActivity.this))
		App42GCMController.registerOnApp42(App42API.getLoggedInUser(), gcmRegId, this);
	}

	@Override
	public void onApp42Response(final String responseMessage) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				responseTv.setText(jas);
			//	responseTv.setText(responseMessage);
			}
		});
	}

	@Override
	public void onRegisterApp42(final String responseMessage) {
		// TODO Auto-generated method stub
		runOnUiThread(new Runnable() {
			@Override
			public void run() {

				//responseTv.setText(responseMessage);
				App42GCMController.storeApp42Success(MainActivity.this);
			}
		});
	}
	public void skiptohome(View view){
		Intent i = new Intent(this,Home.class);
		startActivity(i);
	}

}
