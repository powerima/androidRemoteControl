package com.android;

import java.io.*;
import java.net.*;

import android.annotation.SuppressLint;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.widget.*;

@SuppressLint("ParserError")
public class ClientSocket extends SocketInfo {
	private Socket sock; 			//통신을 위한 클라이언트 소켓
	private DataInputStream dis;	//데이터 수신을 위한 스트림
	private PrintWriter pw;			//데이터 송신을 위한 스트림	
	private Bitmap image;			//이미지를 저장하기 위한 객체
	private int msg;				//서버로 전송할 명령 패킷	
	private int w, h;
	
	ClientSocket(){
		super();	
	}	
	ClientSocket(String serverIp){
		super(serverIp);
	}
	ClientSocket(int serverPort){
		super(serverPort);
	}
	ClientSocket(String serverIp, int serverPort){
		super(serverIp, serverPort);
	}
	
	ClientSocket(int serverPort, String serverIp){
		super(serverIp, serverPort);
	}
	
	
	//init socket and stream for socket communication
	public void setupNetwork()throws IOException{
		
		Log.d("msg", "connecting.. IP = " + getServerIp() + " Port = " + getServerPort());
		
		sock = new Socket(getServerIp(), getServerPort());
		dis = new DataInputStream(sock.getInputStream());	
		pw = new PrintWriter(sock.getOutputStream());		
	
		Log.d("msg", "succeessed connection.");					
	
	}
	
	public void closeSocket(){		
		if(dis != null) try{ dis.close(); }catch(IOException ex){}
		pw.close(); 
		if(sock != null) try{ sock.close(); }catch(IOException ex){}			
	}
	
	//get image after recived image to server using socket stream
	public Bitmap getBitmap(){
		int retval;

		if(dis == null){
			Log.e("msg","set DIS stream!!");
			return null;
		}
		
		try{
			Log.d("msg", "start recieved image!");
			retval = ByteOrder.ntohl(dis.readInt());
			Log.d("msg", "recieved retval = " + retval);				
			
			Log.d("msg", "start " + retval +"byte image");
			byte[] buf = new byte[retval];
			
			dis.readFully(buf, 0, retval);		
			image = BitmapFactory.decodeByteArray(buf, 0, retval);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	
		return image;
	}
	
	//send message to server after occured keypad event
	public void keyEventHandler(KeyEvent e, String str, int stat){		
		int msgType=0;
		short data = (short) e.getKeyCode(); 
		
		switch(stat){
			case KeyEvent.ACTION_DOWN:
				msgType = MessageDefine.KEY_RELEASED;
				break;
				
			case KeyEvent.ACTION_UP:
				msgType = MessageDefine.KEY_PRESSED;
				break;
			default:
				return;						
		}
		
		System.out.print(e.getKeyCode() +" " + str);			
		msg = MessageDefine.getMessage(msgType, data);
		System.out.println(" msg = " +  msg);
		try{
			String s = msg + "\0";
			pw.write(s);
			pw.flush();
		}catch(Exception ex){}	
	}
	
	
	//터치이벤트를 공통 처리후 메시지를 서버로 송신하는 메서드
	public void touchEventHandler(int event, short data1, short data2){		
	
		switch(event){
			case MotionEvent.ACTION_DOWN:
				msg = MessageDefine.getMessage(MessageDefine.MOUSE_LEFT_PRESSED, data1, data2);
				System.out.println("msg = " + msg);				
				break;			
				
					
			case MotionEvent.ACTION_UP:
				msg = MessageDefine.getMessage(MessageDefine.MOUSE_LEFT_RELEASED, data1, data2);
				System.out.println("msg = " + msg);				
				break;
			
			case MotionEvent.ACTION_MOVE:
				msg = MessageDefine.getMessage(MessageDefine.MOUSE_MOVE, data1, data2);
				System.out.println("msg = " + msg);
				break;
		}
		
		try{ 
			String s = msg + "\0";
			pw.write(msg + "\0");
			pw.flush();
		}catch(Exception ex){}					
	}
	
	
	

}
