package UI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class printPanel extends JPanel{

	public JTextArea txt;
	public JButton btn;
	
	public printPanel(){
		txt = new JTextArea();
		btn = new JButton("계산하기");
		
		this.setLayout(new BorderLayout());
		add(txt,BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
	}
}