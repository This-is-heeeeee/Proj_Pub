package UI;

import java.awt.Container;

import javax.swing.JFrame;

import model.CustomerDAO;
import server.MutiChatServer;
import server.s1;
import server.s2;

public class mainFrame extends JFrame{
	titlePanel myTitle;
	static infoPanel myTable;
	printPanel myArea;
	
	mainFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("P.O.S");
		Container c = getContentPane();
		c.setLayout(null);
		
		setSize(800, 500);
		
		myTitle = new titlePanel();
		myTable = new infoPanel();
		myArea = new printPanel();
		
		myTitle.setSize(100,35);
		myTitle.setLocation(this.getSize().width*7/16, this.getSize().height*3/50);
		myTitle.setString("세종포차"); // 타이틀 제목 설정
		
		myTable.setSize(300,300);
		myTable.setLocation(this.getSize().width*1/10, this.getSize().height*1/5);
		
		myArea.setSize(300,300);
		myArea.setLocation(this.getSize().width*43/80,this.getSize().height*1/5);
		
		for(int i = 0; i < 12; i++) {
			myTable.tbtn[i].addActionListener(new myListener(myTable.tbtn[i], myArea));
		}
		
		c.add(myTitle);
		c.add(myTable);
		c.add(myArea);
		

		setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new mainFrame();
		new s1(myTable).start();
		new s2().start();
		new MutiChatServer().start();
	}

}
