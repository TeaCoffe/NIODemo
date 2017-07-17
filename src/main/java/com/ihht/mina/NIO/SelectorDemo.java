package com.ihht.mina.NIO;

import java.io.RandomAccessFile;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {

	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws Exception {
		Selector selector = Selector.open();
		RandomAccessFile readF = new RandomAccessFile("D:/config.int", "rw");
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);//与Selector一起使用时，Channel必须处于非阻塞模式下。FileChannel不能设置为非阻塞模式
		SelectionKey key = channel.register(selector, SelectionKey.OP_WRITE);
		boolean flag = true;
		while(flag){
			int select = selector.select();
			if(select == 0){
				continue;
			}
//			key.interestOps();
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey skey = (SelectionKey) iterator.next();
				if(skey.isConnectable()){
					SelectableChannel selectableChannel = skey.channel();
				}
				if(skey.isAcceptable()){
					
				}
				if(skey.isReadable()){
					
				}
				if(skey.isWritable()){
					
				}
				iterator.remove();//Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。
			}
		}
	}
}
