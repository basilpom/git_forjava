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
	JPanel panel = null;	//container 역할
	Connection conn = null;
	
	public MenuHaksa()
	{

		/*
		//DB Connection
		try
		{
			
			//Oracle에 연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle","ora_user","hong");
			
			//MySQL에 연결
			//Class.forName("com.mysql.jdbc.Driver");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledb?useSSL=false", "hkd", "1234");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/	
				
		this.setTitle("학사관리");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//MENU
		JMenuBar mb = new JMenuBar();
		JMenu student = new JMenu("학생관리");
		JMenuItem sItem1 = new JMenuItem("학생정보");
		
		sItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("학생정보");
				panel.removeAll();			// 모든 컴포넌트 삭제
				panel.revalidate();			// 다시 활성화
				panel.repaint();			// 다시 그리기
				panel.add(new Student());	// 화면 생성!!!!!!!!
				panel.setLayout(null);
				
				
			}});
		
		student.add(sItem1);	// 1. 메뉴에 메뉴아이템 부착
		mb.add(student);		// 2. 메뉴바에 메뉴 부착
		
		JMenu book = new JMenu("도서관리");
		JMenuItem bItem1 = new JMenuItem("대출정보");
		
		bItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("대출정보");
				panel.removeAll();			// 모든 컴포넌트 삭제
				panel.revalidate();			// 다시 활성화
				panel.repaint();			// 다시 그리기
				panel.add(new BookRent());	// 화면 생성!!!!!!!!
				panel.setLayout(null);
				
				
			}});
		book.add(bItem1);
		
		JMenuItem bItem2 = new JMenuItem("대출현황");
		bItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("대출현황");
				panel.removeAll();			// 모든 컴포넌트 삭제
				panel.revalidate();			// 다시 활성화
				panel.repaint();			// 다시 그리기
				panel.add(new PieChart());	// 화면 생성!!!!!!!!
				panel.setLayout(null);
				//각 과별로 대출... 웅앵...
				
				
			}});
		book.add(bItem2);
		mb.add(book);
		
		
		this.setJMenuBar(mb);		// 3. 프레임에 메뉴바 부착
		
		panel = new JPanel();
		this.add(panel);	//이 패널 위에 학사/도서 패널 올리기
		
		
		
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
