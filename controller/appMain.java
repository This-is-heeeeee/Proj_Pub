package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import UI.mainFrame;
import model.appModel;
import server.MutiChatServer;
import server.s1;
import server.s2;

public class appMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainFrame mf;
		appModel am;
		
		mf = new mainFrame();
		
		am = new appModel();
		
		myListener listener = new myListener(mf,am);
		mf.addButtonActionListener(listener);
		
		new s1(mf.myTable).start();
		new s2().start();
		new MutiChatServer().start();
	}

}
