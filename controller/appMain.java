package controller;

import UI.mainFrame;
import server.MutiChatServer;
import server.s1;
import server.s2;

public class appMain {
	static mainFrame mf;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mf = new mainFrame();
		new s1(mf.myTable).start();
		new s2().start();
		new MutiChatServer().start();
	}

}
