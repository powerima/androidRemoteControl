package com.android;

public class ByteOrder {
	//����Ʈ ������ �ٲٴ� �޼ҵ�
    public static int ntohl(int value) { 
            byte[] bytes=new byte[4];            
            bytes[0]=(byte)((value>>24)&0xFF);
            bytes[1]=(byte)((value>>16)&0xFF);
            bytes[2]=(byte)((value>>8)&0xFF);
            bytes[3]=(byte)(value&0xFF);

            int newValue = 0;
            newValue |= (((int)bytes[3])<<24)&0xFF000000;
            newValue |= (((int)bytes[2])<<16)&0xFF0000;
            newValue |= (((int)bytes[1])<<8)&0xFF00;
            newValue |= (((int)bytes[0]))&0xFF;         
            
            return newValue;
    }  
    
    
    
    public static byte[] getByte(int value){
    	byte[] bytes = new byte[4];
    	
    	for(int i=0; i<bytes.length; i++){
    		bytes[i] = (byte)(value>>8*(bytes.length-i+1)&0xff);
    	}
    	
    	System.out.print("����Ʈ�� ���:");
    	for(int i=0; i<bytes.length; i++){
    		System.out.print(bytes[i]);    		
    	}
    	System.out.println();
    	
    	
    	return bytes;
    }
    
    public static byte[] getByte(long value){
    	byte[] bytes = new byte[8];
    	
    	for(int i=0; i<bytes.length; i++){
    		bytes[i] = (byte)(value>>8*(bytes.length-i+1)&0xff);
    	}
    	
    	System.out.print("����Ʈ�� ���:");
    	for(int i=0; i<bytes.length; i++){
    		System.out.print(bytes[i]);    		
    	}
    	System.out.println();
    	return bytes;   
    }
}
