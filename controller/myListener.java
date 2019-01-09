package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import UI.mainFrame;
import UI.myDialog;
import UI.tableButton;
import model.CorderDAO;
import model.CorderVO;
import model.CustomerDAO;
import model.MenuDAO;
import model.MenuVO;
import model.PriceDAO;
import model.PriceVO;
import model.appModel;

public class myListener implements ActionListener{
	mainFrame UI;
	appModel DAO;
	
	tableButton btn;

	ArrayList<CorderVO> orderdatas;
	MenuVO menudata;
	int pricedata;
	int selectedTable;
	
	myListener(mainFrame UI, appModel DAO){
		this.UI = UI;
		this.DAO = DAO;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		
		if(obj instanceof tableButton) {
			btn = (tableButton)obj;
			switch(btn.getTmode()) {
			case "x":
				new myDialog(btn);
				break;
			default:
				UI.myArea.txt.setText("메뉴\t수량\t가격\n\n");
				orderdatas = DAO.orderdao.selectCorder(btn.getTnum());
				pricedata = DAO.pricedao.selectPrice(btn.getTnum());
				if(orderdatas != null) {
					for(CorderVO c : orderdatas) {
						menudata = DAO.menudao.selectMenu(c.getProduct());
						StringBuffer sb = new StringBuffer();
						sb.append(c.getProduct() + "\t");
						sb.append(c.getAmount() + "\t");
						sb.append(menudata.getPrice() + "\n");
						UI.myArea.txt.append(sb.toString());
					}
					UI.myArea.txt.append("\ntotal :\t\t" + pricedata + "\n");
				}                                                                        
				break;
			}
		}
		else if(obj == UI.myArea.btn) {
			UI.myArea.txt.setText("");
			DAO.customerdao.initCustomer(selectedTable);
			DAO.pricedao.initPrice(selectedTable);
			DAO.orderdao.initCorder(selectedTable);
			btn.setInfo(0, "x");
			
		}
		
	}

}
