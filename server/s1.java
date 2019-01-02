package server;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import UI.infoPanel;
import model.*;


public class s1 extends Thread{
	
	CorderDAO orderdao;
	MenuDAO menudao;
	PriceDAO pricedao;
	ArrayList<CorderVO> orderlist;
	ServerSocket sc;
	int onum;
	int total;
	int tnum;
	
	public s1(infoPanel table) {
		orderdao = new CorderDAO();
		menudao = new MenuDAO();
		pricedao = new PriceDAO();
		try {
			sc = new ServerSocket(5001);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("order정보를 받아옵니다.");
		onum = 0;////////////마지막 번호 다음번호로 고치기
	}
	
	public void run() {
		while(true) {
		try {
			Socket s = sc.accept();
			
			
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			
			orderlist = (ArrayList<CorderVO>)ois.readObject();
			
			total = 0;
			
			for(CorderVO o : orderlist) {
				o.setoNum(onum);
				orderdao.insertCorder(o);
				onum++;
				MenuVO menu;
				menu = menudao.selectMenu(o.getProduct());
				total += menu.getPrice() * o.getAmount();
				tnum = o.gettNum();
			}
			
			pricedao.updatePrice(tnum, total);
			
			s.close();
			is.close();
			ois.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

}
