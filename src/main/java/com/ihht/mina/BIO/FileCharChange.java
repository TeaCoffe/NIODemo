package com.ihht.mina.BIO;

import java.io.RandomAccessFile;

public class FileCharChange {

	public static void main(String[] args) throws Exception {
		RandomAccessFile rag = new RandomAccessFile("D:/temp.txt", "rw");
		int i = 0;
		String tsCode = "AuT+D";
//		System.out.println("----------");
		String lineStr = "";
		while((lineStr =rag.readLine())!=null){
			if(i%14 == 0&&i<28&&i>=14){
				tsCode = "AgT+D";
			}
			if(i%14 == 0 && i>=28){
				tsCode = "mAUTD";
			}
			//VALUES ('137'
			String replaceAll = lineStr.replaceAll("VALUES \\(\'[0-9]{3}\', \'[0-9]{3}\'", "select id");
			String string = replaceAll.replaceAll("\\);$", " FROM TRADE_SYMBOL WHERE TS_CODE = '"+tsCode+"';");
			i++;
			System.out.println(string);
		}
		rag.close();
	}
}
