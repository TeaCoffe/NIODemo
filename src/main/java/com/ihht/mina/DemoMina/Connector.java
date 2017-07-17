package com.ihht.mina.DemoMina;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Connector {

	static IoSession ioSession = null;
	public static void main(String[] args) {
		NioSocketConnector connector = new NioSocketConnector();
		
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
		
		connector.setHandler(null);
		boolean flag = true;
		while(flag){
			ConnectFuture connectFuture = connector.connect(new InetSocketAddress("", 8069));
			connectFuture.awaitUninterruptibly();
			ioSession = connectFuture.getSession();
			flag = false;
		}
	}
}
