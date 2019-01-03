package server;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import model.*;


public class s2 extends Thread{
	CustomerDAO datas;
	ArrayList<CustomerVO> customerlist;
	ServerSocket sc;
	public s2() {
		try {
			sc  = new ServerSocket(5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		while(true) {
		try {
			
			System.out.println("table을 보냅니다.");
			Socket s = sc.accept();
			
			datas = new CustomerDAO();
			customerlist = datas.selectAll();
			
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(customerlist);
			System.out.println(os);
			
			os.close();
			oos.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	}

}
