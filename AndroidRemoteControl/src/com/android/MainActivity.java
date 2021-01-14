/*
 * 
 * 	MainActivity 클래스
 *  
 *  실행시 가장 먼저 보이는 액티비티 설정
 * 
 */
package com.android;

import java.io.*;
import java.net.*;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.*;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	byte[] buf;
    Bitmap image;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);               
        setContentView(R.layout.activity_main);          
  	/*
		new Thread(new Net()).run();		
		setContentView(new MyView(this));
		
		
		
 		Window win = getWindow();
		win.requestFeature(Window.FEATURE_NO_TITLE);
		win.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		try{
			RemoteCanvas canvas = new RemoteCanvas(this);
			new Thread(canvas).start();
			
			setContentView(canvas);
		}catch(IOException ex){
			ex.printStackTrace();
		}

		*/
		
    
    }    
    
    
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Intent intent = new Intent(this, ListTabActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
    
    //test socket thread
    public class Net implements Runnable{
    	Socket sock;
    	DataInputStream dis;
    	PrintWriter pw;
    	
    	Net(){
    		try{
			InetAddress serverAddr = InetAddress.getByName("192.168.0.3");
			sock = new Socket(serverAddr, 9000);
			
			dis = new DataInputStream(sock.getInputStream());
			pw = new PrintWriter(sock.getOutputStream());
			
    		}catch(Exception ex){
    			ex.printStackTrace();
    		}
    	}
	    @SuppressLint("ParserError")
		public void run(){
	    	
	    	try{
				int retval;   				
				
				retval = ByteOrder.ntohl(dis.readInt());
				Log.d("msg", "recieved retval = " + retval);
				
				buf = new byte[retval];
				
				dis.readFully(buf, 0, retval);
						
				image = BitmapFactory.decodeByteArray(buf, 0, retval);
				
				dis.close();				
				pw.close();						
			
				Log.d("msg", "close server connection");
			}catch(Exception ex){
				ex.printStackTrace();
				Log.d("msg", "failed server connection");
			}
	    }
    }
    
    
    protected class MyView extends View{
    	public MyView(Context context){
    		super(context);
    	}
    	public void onDraw(Canvas canvas){    
    		DisplayMetrics dm = getResources().getDisplayMetrics();		
    		
    		
    		Paint Pnt = new Paint();
    	
			for (int y = 50;y < 1028;y += 5) {    			
    			Pnt.setARGB(255, (255 - y/ 5) , (255 - y / 5), 255);
    			canvas.drawRect(0, dm.heightPixels-y, 1028, dm.heightPixels - y+5, Pnt);
    			Log.w("msg", "x = " + dm.widthPixels + " y = " +dm.heightPixels);
			}
    		    		
    	}
    }
 
    
    
}
