package com.hax.wifihunter;

public class WifiData {
	String bssid;
	String essid;
	String capabilities;
	public WifiData(String bssid, String essid, String capabilities){
		this.bssid=bssid;
		this.essid=essid;
		this.capabilities=capabilities;
	}
	public boolean equals(Object o){
		if (o instanceof WifiData){
			WifiData other= (WifiData) o;
			return (other.bssid.equals(bssid)&&other.essid.equals(essid)&&other.capabilities.equals(other.capabilities));
		}
		return false;
	}
}
