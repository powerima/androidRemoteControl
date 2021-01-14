package com.android;

import java.io.*;
import java.util.*;
import android.content.*;
import android.util.*;
import android.view.*;
import android.widget.*;


public class MyListAdapter extends BaseAdapter {
	ListTabActivity maincon;
	LayoutInflater inflater;
	ArrayList<MyItem> arSrc;
	int layout;
	
	public MyListAdapter(ListTabActivity context, int alayout, ArrayList<MyItem> aarSrc){
		maincon = context;
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		arSrc = aarSrc;
		layout = alayout;
	}
	
	public int getCount(){
		return arSrc.size();
	}

	public String getItem(int position) {		
		return arSrc.get(position).getServer_name();
	}

	public long getItemId(int position) {
		
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		if(convertView == null){
			convertView = inflater.inflate(layout, parent, false);
			
		}
		ImageView imgIcon = (ImageView)convertView.findViewById(R.id.img);
		imgIcon.setImageResource(arSrc.get(position).getIcon());
		
		TextView txtName = (TextView)convertView.findViewById(R.id.server_name);
		txtName.setText(arSrc.get(position).getServer_name());
		
		TextView txtIp = (TextView)convertView.findViewById(R.id.server_ip);
		txtIp.setText(arSrc.get(position).getServer_ip());
		
		
		
		Button btn = (Button)convertView.findViewById(R.id.btn);
		btn.setOnClickListener(new Button.OnClickListener(){
			public void onClick(View v){
				String str = arSrc.get(pos).getServer_name() + "로 접속합니다.";
				Toast.makeText(maincon, str, Toast.LENGTH_SHORT).show();	 	
								
				Intent intent = new Intent(maincon, MyCanvasActivity.class);
				maincon.startActivity(intent);					
		
			}
				
		});
		return convertView;
	}
	
	

}