package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class infoPanel extends JPanel {
	tableButton tbtn[];
	
	public infoPanel() {
		setLayout(new GridLayout(4,3,5,5));
		tbtn = new tableButton[12];
		
		for(int i = 0; i < 12; i++) {
			tbtn[i] = new tableButton(i);
			add(tbtn[i]);
		}
	}
}
