package com.android;

/*
 * 서버를 추가할때 생기는 리스트 아이템
 * 
 */
public class MyItem {
	public static int[] iconArr = {R.drawable.desktop_icon, R.drawable.notebook_icon, R.drawable.phone_icon};
	
	private int icon;
	private String server_name;
	private String server_ip;	
	
	
	public MyItem(int icon, String server_name, String server_ip){
		this.icon = icon;
		this.server_name = server_name;
		this.server_ip = server_ip;
	}


	public int getIcon() {
		return icon;
	}


	public void setIcon(int icon) {
		this.icon = icon;
	}


	public String getServer_name() {
		return server_name;
	}


	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}


	public String getServer_ip() {
		return server_ip;
	}


	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}

	
}