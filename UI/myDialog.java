package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import model.CustomerDAO;
import model.CustomerVO;

public class myDialog{
	JDialog dialog;
	JLabel PopupText;
    JLabel PopupMode;
    JComboBox modeBox;
    JLabel PopupPeople;
    JComboBox peoBox;
    JButton PopupSave;
	
	String n[] = {"1","2","3","4"};
	String s[] = {"여", "남", "혼"};
	
	tableButton tbtn;
	CustomerVO customer;
	CustomerDAO cdao;
	
	public myDialog(tableButton btn){
		dialog = new JDialog();
		
        dialog.setLayout(new FlowLayout());
        dialog.setSize(300, 150);
        dialog.setLocation(100, 100);
        dialog.setVisible(true);
        // 팝업창 내용
        PopupText = new JLabel("정보를 입력해주세요.");
        PopupPeople = new JLabel("인 : ");
        peoBox = new JComboBox(n);
        PopupMode = new JLabel("성 : ");
        modeBox = new JComboBox(s);
        PopupSave = new JButton("입력");
        
        dialog.setLayout(null);
        
        PopupText.setSize(150,20);
        PopupText.setLocation(100,10);
        
        PopupMode.setSize(70,20);
        PopupMode.setLocation(90,40);
        modeBox.setSize(80,30);
        modeBox.setLocation(150,40);
        
        PopupPeople.setSize(70,20);
        PopupPeople.setLocation(90,70);
        peoBox.setSize(80,30);
        peoBox.setLocation(150,70);
        
        PopupSave.setSize(50,20);
        PopupSave.setLocation(125,100);
        
        dialog.add(PopupText);
        dialog.add(PopupMode);
        dialog.add(modeBox);
        dialog.add(PopupPeople);
        dialog.add(peoBox);
        dialog.add(PopupSave);
        // 새로만들기, 열기 눌렀을때 뜨는 팝업창 종료
        
        PopupSave.addActionListener(new DialogListener());
        
        tbtn = btn;
        
        cdao = new CustomerDAO();
	}

	class DialogListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object obj = e.getSource();
			
			if(obj == PopupSave) {
				tbtn.pnum = Integer.parseInt((String) peoBox.getSelectedItem());
				tbtn.pLabel.setText((String) peoBox.getSelectedItem());
				switch((String)modeBox.getSelectedItem()) {
				case "여":
					tbtn.setBackground(Color.pink);
					tbtn.tmode = "여";
					break;
				case "남":
					tbtn.setBackground(Color.blue);
					tbtn.tmode = "남";
					break;
				case "혼":
					tbtn.setBackground(Color.green);
					tbtn.tmode = "혼";
					break;
				default :
					break;
				}
				dialog.setVisible(false);
				
				customer = new CustomerVO();
				customer.settNum(tbtn.tnum);
				customer.setNumofpeo(tbtn.pnum);
				customer.setSex(tbtn.tmode);
				
				cdao.updateCustomer(customer);
			}
		}
		
	}
}
