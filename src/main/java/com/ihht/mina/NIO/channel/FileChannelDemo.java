package com.ihht.mina.NIO.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		RandomAccessFile fileStream = new RandomAccessFile("D:/config.ini", "rw");
		FileChannel channel = fileStream.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(128);
		int read;
		while((read = channel.read(buffer))!=-1){
			buffer.flip();
			while(buffer.hasRemaining()){
				System.out.print((char)buffer.get());
			}
			buffer.clear();
		}
		System.out.println();
		buffer.put((byte) 1);
		while(buffer.hasRemaining()){
			buffer.flip();
			System.out.println(buffer.get());
		}
		
		//position  读取position所在位置数据，读且仅读单个数据
		long position = channel.position();
		System.out.println(position);

		ByteBuffer buffer2 = ByteBuffer.allocate(1024);
		FileChannel channel2 = channel.position(3);
		int read2 = channel2.read(buffer2);
		while(read2 != -1){
			buffer2.flip();
			
			System.out.print((char)buffer2.get());
			
			read2 = channel2.read(buffer2);
		}
		System.out.println();
		System.out.println("---------------");
		
		//size: file size
		long size = channel.size();
		System.out.println(size);
		
		buffer2.clear();
//		channel.truncate(20);
		
		fileStream.close();
	}
	
}
