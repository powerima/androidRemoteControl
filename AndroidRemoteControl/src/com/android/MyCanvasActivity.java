package com.android;

import java.io.*;
import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.content.res.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.view.inputmethod.*;
import android.widget.*;

public class MyCanvasActivity extends Activity{
	RemoteCanvasView canvas;	//�̹����� ���̱����� ��
	boolean bShowKey;		//Ű���庸�̱� ����
	
	
	InputMethodManager imm;	//Ű���尡 �������� ���� ��ü
	
	
	
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);     
    	
    	//ȭ�� Ű���带 ǥ���ϱ� ���� InputMethodmanagerr ��ü ��������
		imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		
    	
 		Window win = getWindow(); 			//Ÿ��Ʋ�� ���߱� 		
		win.requestFeature(Window.FEATURE_NO_TITLE);
		win.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		

		try{
			canvas = new RemoteCanvasView(this);
			//canvas.setFocusable(true);	//Ű���� ��� ��ȯ
			//canvas.setFocusableInTouchMode(true);	//��ġ ��� �� Ű���� ��� ��� ����	
			new Thread(canvas).start();
			
			setContentView(canvas);
			
		}catch(IOException ex){
			Log.e("err", "socket connected fail" );
			ex.printStackTrace();
		}finally{
			//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
    }

    //ȭ�� ���� ���� ��ȯ �ݹ� �޼���
    public void onConfigurationChanged(Configuration newConfig){
    	super.onConfigurationChanged(newConfig);
    	
    	WindowManager wm = getWindowManager();
    	if(wm == null) return;
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);	//ȭ�� ������ȯ
    	canvas.setRect();		
    }	
	
	
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		super.onKeyDown(KeyCode, event);
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (KeyCode) {			
			case KeyEvent.KEYCODE_MENU:		//Ű���� ��������
			    Log.d("msg","show keyboard");
			    
			    //imm.showSoftInput(canvas, 0);
			    imm.showSoftInput(canvas, InputMethodManager.SHOW_FORCED);  
	       		break;
			
			case KeyEvent.KEYCODE_BACK:
				new AlertDialog.Builder(MyCanvasActivity.this)
				.setTitle("�˸�")
				.setMessage("�������� ������ ����Ǿ����ϴ�.")
				.setPositiveButton("����", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						finish();
					}
				})
				.show();		
				canvas.setCloseBLoop();
				
				imm.hideSoftInputFromWindow(canvas.getWindowToken(), 0); 
				finish();
				
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				break;
			}
		}
		return false;
	}

	public void onBackPressed(){

	}
}
