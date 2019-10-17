import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;




public class MenuHaksa extends JFrame{
	JPanel panel = null;	//container ����
	Connection conn = null;
	
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
		
		JMenuItem bItemRentAndReturn = new JMenuItem("����/�ݳ�");
		bItemRentAndReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//����ݳ��־��~~~
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
		mb.setEnabled(false);
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
		
		//Welcome Page
		JLabel welcomeText = new JLabel("�л�/���� ����\n���α׷�");
		welcomeText.setFont(new Font("���� ���", Font.BOLD, 40));
		panel.add(welcomeText);
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
