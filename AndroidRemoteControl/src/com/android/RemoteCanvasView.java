package com.android;


import java.io.*;

import android.annotation.SuppressLint;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;

public class RemoteCanvasView extends View implements Runnable{
	private boolean bLoop;		//������ �����ϰ� �����带 ��ĥ������ 
	private ClientSocket cs;	//������ ����� ���� ����
	private Rect rect;			//�̹����� ����ϱ� ���� ����  
	private int right;
	private int bottom;
	
	
	public RemoteCanvasView(Context context) throws IOException{
		super(context);				
		
		cs = new ClientSocket();		
		cs.setupNetwork();
	
		
		bLoop = true;
		setRect();		
	}
	
	public RemoteCanvasView(Context context, String server_ip) throws IOException{
		super(context);				
	
		cs = new ClientSocket(server_ip);
		cs.setupNetwork();	
	
		bLoop = true;
		setRect();		
	}
	
	public void setCloseBLoop(){
		cs.closeSocket();		
		this.bLoop = bLoop;		
		Log.d("msg", "socket close, loop end");
	}
	
	public void onDraw(Canvas canvas){		
		Bitmap image = cs.getBitmap();
		
		if(image != null) {
			try{
				canvas.drawBitmap(image, null, rect , null);
				image.recycle();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}		
	}		
		
	
	public void run(){
		Log.d("msg", "RemoteCanvas Thread start!");
		
		while(bLoop){				
			postInvalidate();		
		}
		
		
	}

	//����� ȭ�� ũ�� ������ �� Rect ��ü�� ����
	public void setRect(){
		DisplayMetrics dm = getResources().getDisplayMetrics();		
		right = dm.widthPixels;
		bottom = dm.heightPixels;
		
		Log.d("msg", "right = " + right + ", bottom = " + bottom);
		rect = new Rect(0, 0, right, bottom);
	}
	
	
	 public boolean onTouchEvent(MotionEvent event) {
    	if(event.getAction() == MotionEvent.ACTION_DOWN) {
    		Log.d("msg","action down : " + event.getX() +"," + event.getY());
    		
    	}
    	if(event.getAction() == MotionEvent.ACTION_UP){
    		
    	}
    	if(event.getAction() == MotionEvent.ACTION_CANCEL){
    		
    	}
    	if(event.getAction() == MotionEvent.ACTION_MOVE) {
    	
    	}	 
    	
    	short data1 = (short)event.getX();
    	short data2 = (short)event.getY();
    	cs.touchEventHandler(event.getAction(), data1, data2);
    	return false;
    }
    
}
