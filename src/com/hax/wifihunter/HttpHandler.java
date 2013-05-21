package com.hax.wifihunter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class HttpHandler {
	HttpClient httpClient;
	public HttpHandler(){
		httpClient = new DefaultHttpClient();
	}
	public void postData(WifiData data, String username, String serverUrl){
		HttpPost httpPost = new HttpPost(serverUrl);
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(4);
		nameValuePair.add(new BasicNameValuePair("essid", data.essid));
		nameValuePair.add(new BasicNameValuePair("bssid", data.bssid));
		nameValuePair.add(new BasicNameValuePair("capabilities", data.capabilities));
		nameValuePair.add(new BasicNameValuePair("username", username));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		try {
			HttpResponse response = httpClient.execute(httpPost);
			// writing response to log
			Log.d("Http Response:", response.toString());
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
				
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}
	}
}
