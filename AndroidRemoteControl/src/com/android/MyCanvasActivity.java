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
	RemoteCanvasView canvas;	//이미지를 보이기위한 뷰
	boolean bShowKey;		//키보드보이기 여부
	
	
	InputMethodManager imm;	//키보드가 보여지기 위한 객체
	
	
	
    public void onCreate(Bundle savedInstanceState){
    	super.onCreate(savedInstanceState);     
    	
    	//화면 키보드를 표시하기 위한 InputMethodmanagerr 객체 가져오기
		imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		
    	
 		Window win = getWindow(); 			//타이틀바 감추기 		
		win.requestFeature(Window.FEATURE_NO_TITLE);
		win.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		

		try{
			canvas = new RemoteCanvasView(this);
			//canvas.setFocusable(true);	//키보드 모드 변환
			//canvas.setFocusableInTouchMode(true);	//터치 모드 중 키보드 모드 사용 가능	
			new Thread(canvas).start();
			
			setContentView(canvas);
			
		}catch(IOException ex){
			Log.e("err", "socket connected fail" );
			ex.printStackTrace();
		}finally{
			//setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
    }

    //화면 가로 세로 전환 콜백 메서드
    public void onConfigurationChanged(Configuration newConfig){
    	super.onConfigurationChanged(newConfig);
    	
    	WindowManager wm = getWindowManager();
    	if(wm == null) return;
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);	//화면 가로전환
    	canvas.setRect();		
    }	
	
	
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		super.onKeyDown(KeyCode, event);
		if (event.getAction() == KeyEvent.ACTION_DOWN) {
			switch (KeyCode) {			
			case KeyEvent.KEYCODE_MENU:		//키보드 가져오기
			    Log.d("msg","show keyboard");
			    
			    //imm.showSoftInput(canvas, 0);
			    imm.showSoftInput(canvas, InputMethodManager.SHOW_FORCED);  
	       		break;
			
			case KeyEvent.KEYCODE_BACK:
				new AlertDialog.Builder(MyCanvasActivity.this)
				.setTitle("알림")
				.setMessage("서버와의 접속이 종료되었습니다.")
				.setPositiveButton("종료", new DialogInterface.OnClickListener() {
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
