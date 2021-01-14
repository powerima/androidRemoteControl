package com.android;

/*
 * 서버 접속시 필요한 정보를 저장, 수정하는 클래스
 * 아이피주소, 포트번호
 */

public class SocketInfo {
	
	private static final String DEFAULT_IP = "10.0.2.2";
	private static final int DEFAULT_PORT = 9000;
	
	private String serverIp;
	private int serverPort;
	
	
	SocketInfo(){
		setServerIp(DEFAULT_IP);
		setServerPort(DEFAULT_PORT);
	}
	
	SocketInfo(String serverIp){
		this();
		setServerIp(serverIp);
	}
	SocketInfo(int port){
		this();
		setServerPort(port);
	}
	SocketInfo(String serverIp, int port){
		setServerIp(serverIp);
		setServerPort(port);
	}
	SocketInfo(int port, String serverIp){
		this(serverIp, port);
	}
	
	
	public void setServerIp(String serverIp){
		this.serverIp = serverIp;		
	}
	public void setServerPort(int serverPort){
		this.serverPort = serverPort;
	}
	public String getServerIp(){
		return serverIp;
	}
	public int getServerPort(){
		return serverPort;
	}
	
	
}
