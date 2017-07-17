package com.ihht.mina.NIO;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterGather {

	public static void main(String[] args) throws Exception {
		sctter();//分散
		gather();//整合
	}
	
	public static void gather() throws Exception{

		RandomAccessFile raf = new RandomAccessFile("D:/config.ini.bak", "rw");
		FileChannel channel = raf.getChannel();
		
		ByteBuffer buff01 = ByteBuffer.allocate(8);
		ByteBuffer buff02 = ByteBuffer.allocate(16);
		
		ByteBuffer[] bufferArray = new ByteBuffer[]{buff01,buff02};
		long write = channel.write(bufferArray);
		while(write!=0){
			
		}
		raf.close();
	}
	
	
	public static void sctter() throws Exception{

		RandomAccessFile raf = new RandomAccessFile("D:/config.ini", "rw");
		FileChannel channel = raf.getChannel();
		
		ByteBuffer buff01 = ByteBuffer.allocate(8);
		ByteBuffer buff02 = ByteBuffer.allocate(16);
		
		ByteBuffer[] bufferArray = new ByteBuffer[]{buff01,buff02};
		long read = channel.read(bufferArray);
		while(read != -1){
			System.out.println(read);
			buff01.flip();
			buff02.flip();
			while(buff01.hasRemaining()){
				System.out.print((char)buff01.get());
			}
			System.out.println();
			System.out.println("---------");
			while(buff02.hasRemaining()){
				System.out.print((char)buff02.get());
			}
			
			buff01.clear();
			buff02.clear();
			read = channel.read(bufferArray);
		}
		raf.close();
	
	}
}
