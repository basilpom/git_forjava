import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;




public class MenuHaksa extends JFrame{
	JPanel panel = null;	//container ����
	Connection conn = null;
	
	public MenuHaksa()
	{

		/*
		//DB Connection
		try
		{
			
			//Oracle�� ����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle","ora_user","hong");
			
			//MySQL�� ����
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?useSSL=false", "hkd", "1234");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/	
				
		this.setTitle("�л����");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//MENU
		JMenuBar mb = new JMenuBar();
		JMenu student = new JMenu("�л�����");
		JMenuItem sItem1 = new JMenuItem("�л�����");
		
		sItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("�л�����");
				panel.removeAll();			// ��� ������Ʈ ����
				panel.revalidate();			// �ٽ� Ȱ��ȭ
				panel.repaint();			// �ٽ� �׸���
				panel.add(new Student());	// ȭ�� ����!!!!!!!!
				panel.setLayout(null);
				
				
			}});
		
		student.add(sItem1);	// 1. �޴��� �޴������� ����
		mb.add(student);		// 2. �޴��ٿ� �޴� ����
		
		JMenu book = new JMenu("��������");
		JMenuItem bItem1 = new JMenuItem("��������");
		
		bItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("��������");
				panel.removeAll();			// ��� ������Ʈ ����
				panel.revalidate();			// �ٽ� Ȱ��ȭ
				panel.repaint();			// �ٽ� �׸���
				panel.add(new BookRent());	// ȭ�� ����!!!!!!!!
				panel.setLayout(null);
				
				
			}});
		book.add(bItem1);
		
		JMenuItem bItem2 = new JMenuItem("������Ȳ");
		bItem2.addActionListener(new ActionListener() {

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
		book.add(bItem2);
		mb.add(book);
		
		
		this.setJMenuBar(mb);		// 3. �����ӿ� �޴��� ����
		
		panel = new JPanel();
		this.add(panel);	//�� �г� ���� �л�/���� �г� �ø���
		
		
		
		//DB CLOSE
		/*
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowClosed(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {
				try
				{
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}

			@Override
			public void windowDeactivated(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowOpened(WindowEvent e) {}
			
		});
		*/
		
		
		
		
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	
	public static void main(String[] args)
	{
		new MenuHaksa();
	}

}
