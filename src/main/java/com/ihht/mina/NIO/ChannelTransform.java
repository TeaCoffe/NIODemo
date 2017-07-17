package com.ihht.mina.NIO;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class ChannelTransform {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		RandomAccessFile fromFile = new RandomAccessFile("D:/config.ini", "rw");
		FileChannel fromChannel = fromFile.getChannel();
		
		RandomAccessFile toFile = new RandomAccessFile("D:/config.ini.bak", "rw");
		FileChannel toChannel = toFile.getChannel();
		
		long l = toChannel.transferFrom(fromChannel, 0,fromChannel.size());
		System.out.println(l);
		System.out.println("End!");
		
		fromChannel.transferFrom(toChannel, 0, fromChannel.size());
	}
}
