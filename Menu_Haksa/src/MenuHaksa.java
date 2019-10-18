import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;




public class MenuHaksa extends JFrame{
	JPanel panel = null;	//container 역할
	Connection conn = null;
	Student StudentObject = null;
	JFrame frame = new JFrame();
	public MenuHaksa()
	{	
		this.setTitle("학사관리");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//MENU
		JMenuBar mb = new JMenuBar();
		JMenu student = new JMenu("학생관리");
		JMenuItem sItem1 = new JMenuItem("학생정보");
		
		
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
		
		student.add(sItem1);	// 1. 메뉴에 메뉴아이템 부착
		mb.add(student);		// 2. 메뉴바에 메뉴 부착
		
		JMenu book = new JMenu("도서관리");
		
		JMenuItem bItemRentAndReturn = new JMenuItem("대출/반납");
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
		
		JMenuItem bItemInfo = new JMenuItem("대출정보");
		bItemInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("대출정보");
				panel.removeAll();			// 모든 컴포넌트 삭제
				panel.revalidate();			// 다시 활성화
				panel.repaint();			// 다시 그리기
				panel.add(new BookRentInfo());	// 화면 생성!!!!!!!!
				panel.setLayout(null);
			}});
		book.add(bItemInfo);
		
		JMenuItem bItemChart = new JMenuItem("대출현황");
		bItemChart.addActionListener(new ActionListener() {

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
		book.add(bItemChart);	
		mb.add(book);
		
		
		this.setJMenuBar(mb);		// 3. 프레임에 메뉴바 부착
		//MenuItem.Enable() 사용하기! (로그인)
		panel = new JPanel();
		this.add(panel);	//이 패널 위에 학사/도서 패널 올리기
		//로그인 전 : 모든 메뉴 아이템 비활성화
		sItem1.setEnabled(false);
		bItemRentAndReturn.setEnabled(false);
		bItemInfo.setEnabled(false);
		bItemChart.setEnabled(false);
		//Welcome Page
		JLabel welcomeText = new JLabel("    학사/도서 관리\n프로그램    ");
		welcomeText.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		panel.add(welcomeText);
		JButton btnStart = new JButton("시작하기");
		panel.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Login Dialog 
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
