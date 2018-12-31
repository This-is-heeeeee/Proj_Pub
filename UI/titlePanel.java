package UI;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class titlePanel extends JPanel{
		public JLabel la= new JLabel();

		public titlePanel() {
			
			la.setFont(new Font("",Font.PLAIN,24));
			add(la);

		}
		
		public void setString(String s) {
			la.setText(s);
		}
	

}
