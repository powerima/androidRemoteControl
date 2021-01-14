package com.android;

public class MessageDefine {
	public final static int IMAGE_QUALITY = 01;
	public final static int IMAGE_SIZE = 02;
	public final static int IMAGE_FRAME = 03;
	public final static int MOUSE_MOVE = 10;
	public final static int MOUSE_LEFT_PRESSED = 11;
	public final static int MOUSE_LEFT_RELEASED = 12;
	public final static int MOUSE_LEFT_CLICKED = 13;
	public final static int MOUSE_RIGHT_PRESSED = 21;
	public final static int MOUSE_RIGHT_RELEASED = 22;	
	public final static int MOUSE_RIGHT_CLICKED = 23;
	public final static int MOUSE_ENTERED = 31;
	public final static int MOUSE_EXITED = 30;	
	public final static int KEY_PRESSED = 41;
	public final static int KEY_RELEASED = 42;
	
	
	
	//4byte 정수형 메시지 설정 - 데이터 1개짜리
	public static int getMessage(int msgType, short data){
		int message =0;
		
		message |= msgType<<24;
		message |= data;
		
		return message;
	}

	//4byte 정수형 메시지 설정 - 데이터 2개짜리
	public static int getMessage(int msgType, short data1, short data2){
		int message =0;
		
		message |= msgType<<24;
		message |= (data1<<12) & 0x00fff000; 
		message |= data2 & 0x00000fff;
		
		return message;
	}	
	
}
