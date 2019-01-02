package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import model.CustomerDAO;
import model.CustomerVO;

public class tableButton extends JButton{
	JLabel tLabel;
	JLabel pLabel;
	int tnum;
	int pnum;
	String tmode;
	
	CustomerDAO customerdao;
	CustomerVO customerdata;
	tableButton(int num){
		
		customerdao = new CustomerDAO();
		customerdata = new CustomerVO();
		
		tnum = num+1;
		
		customerdata = customerdao.selectCustomer(tnum);
		
		tmode = customerdata.getSex();
		pnum = customerdata.getNumofpeo();
		
		
		tLabel = new JLabel("좌석 번호 : "+Integer.toString(tnum));
		tLabel.setFont(new Font("",Font.PLAIN,10));
		
		pLabel = new JLabel(Integer.toString(pnum));
		pLabel.setFont(new Font("",Font.PLAIN,15));
		
		setLayout(new BorderLayout());
		
		add(tLabel, BorderLayout.NORTH);
		add(pLabel, BorderLayout.CENTER);
		
		switch(tmode) {
		case "x":
			setBackground(Color.gray);
			break;
		case "여":
			setBackground(Color.pink);
			break;
		case "남":
			setBackground(Color.blue);
			break;
		case "혼":
			setBackground(Color.GREEN);
			break;
		default :
			break;
		}
		setOpaque(true);
	}
	
	public void setInfo(int pnum, String tmode) {
		this.pnum = pnum;
		this.tmode = tmode;
		pLabel.setText(Integer.toString(pnum));
		switch(tmode) {
		case "x":
			setBackground(Color.gray);
			break;
		case "여":
			setBackground(Color.pink);
			break;
		case "남":
			setBackground(Color.blue);
			break;
		case "혼":
			setBackground(Color.GREEN);
			break;
		default :
			break;
		}
	}

}
