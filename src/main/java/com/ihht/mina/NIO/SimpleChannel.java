package com.ihht.mina.NIO;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class SimpleChannel {

	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("D:/config.ini", "rw");
		FileChannel fileChannel = raf.getChannel();
		ByteBuffer buff = ByteBuffer.allocate(1024);
		int read = fileChannel.read(buff);
		
		while(read != -1){
			System.out.println(read);
			buff.flip();
			buff.mark();
			while(buff.hasRemaining()){
				System.out.print((char)buff.get());
			}
			buff.reset();
			while(buff.hasRemaining()){
				System.out.print((char)buff.get());
			}
			System.out.println("current Position:"+fileChannel.position());
			buff.rewind();
			System.out.println("current Position:"+fileChannel.position());
			while(buff.hasRemaining()){
				System.out.print((char)buff.get());
			}
			buff.clear();
			read = fileChannel.read(buff);
		}
		raf.close();
	}
}
