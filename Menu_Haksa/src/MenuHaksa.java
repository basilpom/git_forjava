import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


class LoginDialog extends JDialog
{
	private JTextField tfID = new JTextField(10);
	private JPasswordField pfPW = new JPasswordField(10);
	private JButton loginBtn = new JButton("�α���");
	private JButton cancelBtn = new JButton("���");
	
	public LoginDialog(JFrame frame, String title)
	{
		super(frame, title, true);
		this.setLayout(new FlowLayout());
		ImageIcon loginImg = new ImageIcon("img/login.png");
		JLabel imgLabel = new JLabel(loginImg);
		JLabel titleLabel = new JLabel("������ �α���");
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 30));
		add(imgLabel);
		add(titleLabel);
		add(new JLabel("    ���̵� : "));
		add(tfID);
		add(new JLabel("��й�ȣ : "));
		add(pfPW);
		
		
		
		loginBtn.addActionListener(new ActionListener() {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String password;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//DB���� �α��� ������ ��ġ�ϸ� menuitem Ȱ��ȭ
				DBManager db = new DBManager();
				conn = db.getConnection();
				
				String query = "SELECT ADM_PW "
							 + " FROM ADMINISTRATOR "
							 + " WHERE ADM_ID = '"+tfID.getText()+"'";
				
				try 
				{
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query);
					System.out.println(query);
					while(rs.next())
					{
						password = rs.getString("ADM_PW");
						System.out.println(password);
					}
					
					if(password == String.valueOf(pfPW.getPassword()))
					{
						//�α��μ���!
					}
					else
					{
						//ID, PW�� Ȯ���ϼ���!
					}
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}});
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}});
		
		add(loginBtn);
		add(cancelBtn);
		
		this.setBackground(Color.GRAY);
		this.setSize(250, 320);
	}
}

public class MenuHaksa extends JFrame{
	JPanel panel = null;	//container ����
	Connection conn = null;
	Student StudentObject = null;
	JFrame frame = new JFrame();
	private LoginDialog dialog;
	public MenuHaksa()
	{	
		this.setTitle("�л����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//MENU
		JMenuBar mb = new JMenuBar();
		JMenu student = new JMenu("�л�����");
		JMenuItem sItem1 = new JMenuItem("�л�����");
		
		
		sItem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				StudentObject = new Student();
				panel.add(StudentObject);
				panel.setLayout(null);
				panel.setSize(280,600);
			}});
		
		student.add(sItem1);	// 1. �޴��� �޴������� ����
		mb.add(student);		// 2. �޴��ٿ� �޴� ����
		
		JMenu book = new JMenu("��������");
		
		JMenuItem bItemRentAndReturn = new JMenuItem("����/�ݳ�");
		bItemRentAndReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.revalidate();
				panel.repaint();
				panel.add(new RentReturn());
				panel.setLayout(null);
			}
		});
		book.add(bItemRentAndReturn);
		
		JMenuItem bItemInfo = new JMenuItem("��������");
		bItemInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("��������");
				panel.removeAll();			// ��� ������Ʈ ����
				panel.revalidate();			// �ٽ� Ȱ��ȭ
				panel.repaint();			// �ٽ� �׸���
				panel.add(new BookRentInfo());	// ȭ�� ����!!!!!!!!
				panel.setLayout(null);
			}});
		book.add(bItemInfo);
		
		JMenuItem bItemChart = new JMenuItem("������Ȳ");
		bItemChart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("������Ȳ");
				panel.removeAll();			// ��� ������Ʈ ����
				panel.revalidate();			// �ٽ� Ȱ��ȭ
				panel.repaint();			// �ٽ� �׸���
				panel.add(new PieChart());	// ȭ�� ����!!!!!!!!
				panel.setLayout(null);
				//�� ������ ����... ����...
			}});
		book.add(bItemChart);	
		mb.add(book);
		
		
		this.setJMenuBar(mb);		// 3. �����ӿ� �޴��� ����
		//MenuItem.Enable() ����ϱ�! (�α���)
		panel = new JPanel();
		this.add(panel);	//�� �г� ���� �л�/���� �г� �ø���
		//�α��� �� : ��� �޴� ������ ��Ȱ��ȭ
		/*
		sItem1.setEnabled(false);
		bItemRentAndReturn.setEnabled(false);
		bItemInfo.setEnabled(false);
		bItemChart.setEnabled(false);
		*/
		//Welcome Page
		JLabel welcomeText = new JLabel("    �л�/���� ���� ���α׷�    ");
		welcomeText.setFont(new Font("���� ���", Font.BOLD, 40));
		panel.add(welcomeText);
		JButton btnStart = new JButton("�����ϱ�");
		panel.add(btnStart);
		//Login Dialog 
		dialog = new LoginDialog(this,"�����ڷ� �α����ϱ�");
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Login ���� �� menu item Ȱ��ȭ
				dialog.setVisible(true);
				
			}});
		ImageIcon catIcon = new ImageIcon("img/newcat.png");
		JLabel welcome = new JLabel(catIcon);
		panel.add(welcome);
		
		this.setSize(600, 500);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		new MenuHaksa();
	}

}
