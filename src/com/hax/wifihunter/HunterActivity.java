package com.hax.wifihunter;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HunterActivity extends Activity implements OnClickListener{
	String startString="Start hunting";
	String stopString="Stop hunting";
	Button bStartStop;
	TextView output;
	 WifiManager wifi;
	 BroadcastReceiver receiver=null;;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hunter);
		bStartStop=(Button) findViewById(R.id.button1);
		bStartStop.setText(startString);
		bStartStop.setOnClickListener(this);
		output=(TextView) findViewById(R.id.outputID);
		output.setText("Good hunt!");
		String connectivity_context = Context.WIFI_SERVICE;
		wifi = (WifiManager) getSystemService(connectivity_context); 
	}
	private void stopReciever(){
		if(receiver!=null){
			this.unregisterReceiver(receiver);
		}
	}
	private void setupReciever() {
		IntentFilter i = new IntentFilter();
		i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		receiver = new BroadcastReceiver() {
		    @Override
		    public void onReceive(Context context, Intent i) {
		        // TODO Auto-generated method stub
		        //ScanWiFiActivity a = ScanWiFiActivity.instance();
		        WifiManager w = (WifiManager) context
		                .getSystemService(Context.WIFI_SERVICE);
		        List<ScanResult> l = w.getScanResults();
		        //a.Clear();
		        for (ScanResult r : l) {
		                              //use r.SSID or r.BSSID to identify your home network and take action
		            output.setText(output.getText()+r.SSID + "" + r.level + r.capabilities +r.BSSID+ "\r\n");
		        }
		    }
		};
		this.registerReceiver(receiver, i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hunter, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		if(arg0 instanceof Button){
			String text= (String) ((Button) arg0).getText();
			if(text.equals(startString)){ // is stopped
				if(startHunting()){
					bStartStop.setText(stopString);
					setupReciever();
				}else{
					output.setText("Please turn on wifi");
				}
			}else if(text.equals(stopString)){ //is started
				bStartStop.setText(startString);
			}
		}
	}
	public boolean startHunting(){	
		if (wifi.isWifiEnabled()) {
                    wifi.startScan();
                    return true;
        }
		return false;
	}
}
