package UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import model.CorderDAO;
import model.CorderVO;
import model.MenuDAO;
import model.MenuVO;
import model.PriceDAO;
import model.PriceVO;

public class myListener implements ActionListener{
	tableButton btn;
	printPanel myArea;
	
	CorderDAO orderdao;
	MenuDAO menudao;
	PriceDAO pricedao;
	ArrayList<CorderVO> orderdatas;
	MenuVO menudata;
	PriceVO pricedata;
	
	myListener(tableButton tbtn, printPanel txtArea){
		btn = tbtn;
		myArea = txtArea;
		
		orderdao = new CorderDAO();
		pricedao = new PriceDAO();
		menudao = new MenuDAO();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj == btn) {
			switch(btn.tmode) {
			case "x":
				new myDialog(btn);
				break;
			default:
				myArea.txt.setText("메뉴\t수량\t가격\n\n");
				orderdatas = orderdao.selectCorder(btn.tnum);
				pricedata = pricedao.selectPrice(btn.tnum);
				if(orderdatas != null) {
					for(CorderVO c : orderdatas) {
						menudata = menudao.selectMenu(c.getProduct());
						StringBuffer sb = new StringBuffer();
						sb.append(c.getProduct() + "\t");
						sb.append(c.getAmount() + "\t");
						sb.append(menudata.getPrice() + "\n");
						myArea.txt.append(sb.toString());
					}
					myArea.txt.append("\ntotal :\t\t" + pricedata.getTotal() + "\n");
				}
				break;
			}
		}
		
	}

}
