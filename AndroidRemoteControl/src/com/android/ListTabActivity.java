package com.android;

import java.util.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ListTabActivity extends TabActivity {
	ArrayList<MyItem> arItem;
	MyItem mi;
	
	private Button deleteButton;
	private Button addButton;
    //TabHost mTab;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      
        TabHost mTab = getTabHost();
        arItem = new ArrayList<MyItem>();
        
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.listtabactivity, mTab.getTabContentView(), true);
        
        mTab.addTab(mTab.newTabSpec("tag").setIndicator("서버 리스트").setContent(R.id.server_list));
        mTab.addTab(mTab.newTabSpec("tag").setIndicator("서버 추가").setContent(R.id.server_add));        
        
        
        //서버 등록       arItem = new ArrayList<MyItem>();
                
      
        mi = new MyItem(R.drawable.phone_icon, "개인용 서버", "127.0.0.1");
        arItem.add(mi);
        mi = new MyItem(R.drawable.notebook_icon, "테스트용 서버", "10.0.2.2");
        arItem.add(mi);
        mi = new MyItem(R.drawable.desktop_icon, "공유기 서버", "192.168.0.3");
        arItem.add(mi);
   
        MyListAdapter myAdapter = new MyListAdapter(this, R.layout.icontext, arItem);
        
        ListView myList;
        myList = (ListView)findViewById(R.id.list);
        myList.setAdapter(myAdapter);
        

        
        //addButton 리스너 생성 및 등록
        addButton = (Button)findViewById(R.id.buttonAdd);       
        addButton.setOnClickListener(new View.OnClickListener() {			
			public void onClick(View v) {
				EditText edAddr = (EditText)findViewById(R.id.textAdress);
				EditText edName = (EditText)findViewById(R.id.textName);
			
				mi = new MyItem(R.drawable.desktop_icon, edName.getText().toString(), edAddr.getText().toString());
				arItem.add(mi);					
			}
		});

    }



}
