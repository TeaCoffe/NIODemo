package com.ihht.mina.NIO.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NonBlockServerSocketChannelDemo {

	public static void main(String[] args) throws Exception {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.socket().bind(new InetSocketAddress("127.0.0.1",8954));
		
		Selector selector = Selector.open();
		
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		boolean listenFlag = true;
		while(listenFlag){
			int select = selector.select();
			System.out.println(select);
			System.out.println();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				if(key.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel socketChannel = server.accept();
					if(socketChannel!=null){
						socketChannel.configureBlocking(false);
						socketChannel.write(ByteBuffer.wrap(new String("It's a test msg!").getBytes()));
						socketChannel.register(selector, SelectionKey.OP_READ);
					}
				}else if(key.isReadable()){
					SocketChannel socketChannel = (SocketChannel) key.channel();
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
			}
		}
	}
}
