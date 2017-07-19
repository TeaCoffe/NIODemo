package com.ihht.mina.DemoMina;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class TimeServer extends IoHandlerAdapter {

	@Override  
    public void exceptionCaught(IoSession session, Throwable cause)  
                       throws Exception {  
              cause.printStackTrace();  
    }  

    @Override  
    public void messageReceived(IoSession session, Object message) throws Exception {
              String str = message.toString();  
              if(str.trim().equalsIgnoreCase("quit")){
                       session.close(true);  
                       return;  
              }
              Calendar time = Calendar.getInstance();  
              SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
              session.write(df.format(time.getTime()));  
              System.out.println("TimeMessage written..."); 
              //是否关闭连接，是= 短连接 否 = 长连接
              session.close();
    }  

    @Override  
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
              System.out.println("IDLE"+session.getIdleCount(status));  
    }  
}
