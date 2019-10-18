import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

class MyDialog extends JDialog {
	public JTextField tfID = new JTextField(10);
	public JTextField tfPW = new JTextField(10);

	public MyDialog()
	{
		
		this.setLayout(null);
		
		JLabel id = new JLabel("아이디");
		JLabel pw = new JLabel("비밀번호");
		add(id);
		id.setBounds(30, 20, 50, 20);
		add(tfID);
		tfID.setBounds(90, 20, 100, 20);
		add(pw);
		pw.setBounds(20, 60, 60, 20);
		add(tfPW);
		tfPW.setBounds(90, 60, 100, 20);
		
		JButton btnLogin = new JButton("로그인");
		JButton btnCancel = new JButton("취소");
		add(btnLogin);
		btnLogin.setBounds(20, 100, 75, 30);
		add(btnCancel);
		btnCancel.setBounds(110, 100, 75, 30);
		ImageIcon loginCat = new ImageIcon("../img/login_cat.png");
		JLabel catLabel = new JLabel(loginCat);
		add(catLabel);
		catLabel.setBounds(160, 40, 220, 110);
		this.setSize(350,180);
	}
	   
}