package com.ihht.mina.DemoMina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class Connector {

	static IoSession ioSession = null;

	public static void main(String[] args) {
		NioSocketConnector connector = new NioSocketConnector();

		connector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));

		connector.setHandler(new ClientHandler());

		try {
			ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 8099));
			future.awaitUninterruptibly();
			ioSession = future.getSession();
			ioSession.write("xxxxx");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		ioSession.getCloseFuture().awaitUninterruptibly();
		System.out.println("session is closed!");
		connector.dispose();
	}
}
