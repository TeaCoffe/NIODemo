package com.ihht.mina.NIO.channel;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class BlockSocketChannelDemo {

	public static void main(String[] args) throws Exception {
		//open
		SocketChannel socketChannel = SocketChannel.open();
		//isBlock synchronize asynchronous
//		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8953));
		
		if(!socketChannel.isConnected()){
			Thread.sleep(3000);
		}
		
		RandomAccessFile raf = new RandomAccessFile("D:/config.ini", "rw");
		
		while(socketChannel.isConnected()){
			FileChannel fileChannel = raf.getChannel();
			//read
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int fileFlag = fileChannel.read(buffer);
			if(fileFlag != -1){
				buffer.flip();
				while(buffer.hasRemaining()){
					socketChannel.write(buffer);
				}
				fileFlag = fileChannel.read(buffer);
			}
		}
		
		//close
		raf.close();
		socketChannel.close();
	}
}
