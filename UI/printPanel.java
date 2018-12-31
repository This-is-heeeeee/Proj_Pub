package UI;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class printPanel extends JPanel{

	public JTextArea txt = new JTextArea();
	
	public printPanel(){
		this.setLayout(new BorderLayout());
		add(txt,BorderLayout.CENTER);
	}
}