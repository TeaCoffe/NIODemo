package com.ihht.mina.NIO.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NonBlockSocketChannelDemo {

	public static void main(String[] args) throws Exception {
		SocketChannel socketChannel = SocketChannel.open();
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress("127.0.0.1", 8954));
		Selector selector = Selector.open();
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		
		boolean listenFlag = true;
		while(listenFlag){
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = iterator.next();
				iterator.remove();
				if(key.isConnectable()){
					SocketChannel client = (SocketChannel) key.channel();
					if(client.isConnectionPending()){
						client.finishConnect();
					}
					client.configureBlocking(false);
					client.write(ByteBuffer.wrap(new String("I'm a client!").getBytes()));
					
					client.register(selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					int read = channel.read(buffer);
					while(read != 0){
						buffer.flip();
						while(buffer.hasRemaining()){
							System.out.print((char)buffer.get());
						}
						read = channel.read(buffer);
					}
				}
			}
		}
		
	}
}
