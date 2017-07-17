package com.ihht.mina.NIO.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class BlockServerSocketChannelDemo {

	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 8953));
		boolean acceptFlag = true;
		while(acceptFlag){
			SocketChannel socketChannel = serverSocketChannel.accept();
			System.out.println("Connected!");
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			int read = socketChannel.read(buffer);
			while(read != 0){
				buffer.flip();
				while(buffer.hasRemaining()){
					System.out.print((char)buffer.get());
				}
				read = socketChannel.read(buffer);
			}
		}
		
		serverSocketChannel.close();
	}
}
