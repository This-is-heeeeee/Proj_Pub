package UI;

import java.awt.Container;

import javax.swing.JFrame;

public class mainFrame extends JFrame{
	titlePanel myTitle;
	infoPanel myTable;
	printPanel myArea;
	
	mainFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("프로그램");
		Container c = getContentPane();
		c.setLayout(null);
		
		setSize(800, 500);
		
		myTitle = new titlePanel();
		myTable = new infoPanel();
		myArea = new printPanel();
		
		myTitle.setSize(100,35);
		myTitle.setLocation(this.getSize().width*7/16, this.getSize().height*3/50);
		myTitle.setString("점주용"); // 타이틀 제목 설정
		
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
	}

}
