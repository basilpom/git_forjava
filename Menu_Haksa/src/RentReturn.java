
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class RentReturn extends JPanel{
	Connection conn = null;
	DefaultTableModel bookModel = null;
	DefaultTableModel rentStudentModel = null;
	JTable bookTable = null;
	JTable rentStudentTable = null;
	
	public RentReturn()
	{
		DBManager db = new DBManager();
		conn = db.getConnection();
		
		this.setLayout(new FlowLayout());
		//도서 대출 패널
		JPanel rentPanel = new JPanel();
		this.add(rentPanel);
		rentPanel.setLayout(new FlowLayout());
		
		ImageIcon rentImg = new ImageIcon("img/rentBook.png");
		JLabel rentLabel = new JLabel("도서대출 ", rentImg, SwingConstants.CENTER);
		rentLabel.setFont(new Font("맑은 고딕", Font.BOLD, 45));
		rentPanel.add(rentLabel);
		//도서명 검색
		rentPanel.add(new JLabel("도서명"));
		JTextField tfBookSearch = new JTextField(12);
		rentPanel.add(tfBookSearch);
		JButton btnBookSearch = new JButton("검색");
		rentPanel.add(btnBookSearch);
		btnBookSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				try
				{
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM BOOKS WHERE TITLE LIKE '%"+tfBookSearch.getText()+"%'");
					bookModel.setNumRows(0);
					while(rs.next())
					{
						String[] row = new String[3];
						row[0] = rs.getString("NO");
						row[1] = rs.getString("TITLE");
						row[2] = rs.getString("AUTHOR");
						bookModel.addRow(row);
					}
					rs.close();					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}});
		
		rentPanel.add(new JLabel("대출 가능한 책 목록"));
		String bookColName[] = {"책번호", "도서명", "저자"};	
		bookModel = new DefaultTableModel(bookColName,0);
		bookTable = new JTable(bookModel);
		bookTable.setPreferredScrollableViewportSize(new Dimension(255,85));
		rentPanel.add(new JScrollPane(bookTable));
		//도서 검색 테이블에서 선택시 textfield에 반영
		bookTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bookTable = (JTable)e.getComponent();
				bookModel = (DefaultTableModel)bookTable.getModel();
				
				String title = (String)bookModel.getValueAt(bookTable.getSelectedRow(), 1);
				tfBookSearch.setText(title);
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			});
		
		//학번 검색
		rentPanel.add(new JLabel("학번"));
		JTextField tfRentStudentSearch = new JTextField(12);
		rentPanel.add(tfRentStudentSearch);
		JButton btnRentStudentSearch = new JButton("검색");
		rentPanel.add(btnRentStudentSearch);
		btnRentStudentSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				try
				{
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM STUDENT WHERE ID LIKE '%"+tfRentStudentSearch.getText()+"%'");
					rentStudentModel.setNumRows(0);
					while(rs.next())
					{
						String[] row = new String[3];
						row[0] = rs.getString("ID");
						row[1] = rs.getString("NAME");
						row[2] = rs.getString("DEPT");
						rentStudentModel.addRow(row);
					}
					rs.close();					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}});
		
		String rentStudentColName[] = {"학번", "이름", "학과"};	
		rentStudentModel = new DefaultTableModel(rentStudentColName,0);
		rentStudentTable = new JTable(rentStudentModel);
		rentStudentTable.setPreferredScrollableViewportSize(new Dimension(255,85));
		rentPanel.add(new JScrollPane(rentStudentTable));
		//학번 검색 테이블에서 선택시 textfield에 반영
		rentStudentTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rentStudentTable = (JTable)e.getComponent();
				rentStudentModel = (DefaultTableModel)rentStudentTable.getModel();
				
				String id = (String)rentStudentModel.getValueAt(rentStudentTable.getSelectedRow(), 0);
				tfRentStudentSearch.setText(id);
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			});
		
		JButton rentButton = new JButton("대출");
		rentPanel.add(rentButton);
		//등록 버튼 누르면 DB에 반영
		rentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				//등록 시 모든 정보를 입력하지 않으면 메세지 창 띄우기
				if(tfBookSearch.getText().equals("")) 
					
				{
					JOptionPane.showMessageDialog(
							null,"모든 정보를 입력하세요!","알림",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfRentStudentSearch.getText().equals(""))
				{
					JOptionPane.showMessageDialog(
							null,"모든 정보를 입력하세요!","알림",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{
						stmt = conn.createStatement();
						//INSERT
						//int rowcnt = stmt.executeUpdate("INSERT INTO VALUES");
						Calendar now = Calendar.getInstance();
						System.out.println();
						JOptionPane.showMessageDialog(null, "대출되었습니다.");					
					}
					
					catch(Exception e1) 
					{
						e1.printStackTrace();
					}
					finally 
					{ 
						try 
						{
							if(stmt != null) {stmt.close();}
							if(rs!=null) {rs.close();}
						}
						catch(Exception e2) 
						{
							e2.printStackTrace();
						}
					}
				}
				
			}});
		JButton rentCancelButton = new JButton("취소");
		rentPanel.add(rentCancelButton);
		//취소 버튼 누르면 도서명, 학번 textfield 값 지우기
		rentCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfBookSearch.setText(null);
				tfRentStudentSearch.setText(null);
				
			}});
		
		rentPanel.setSize(280, 600);
		rentPanel.setPreferredSize(new Dimension(280,600));

		//도서 반납 패널
		JPanel returnPanel = new JPanel();
		this.add(returnPanel);
		
		ImageIcon returnImg = new ImageIcon("img/returnBook.png");
		JLabel returnLabel = new JLabel("도서반납", returnImg, SwingConstants.CENTER);
		returnLabel.setFont(new Font("맑은 고딕", Font.BOLD, 45));
		returnPanel.add(returnLabel);
		
		returnPanel.add(new JLabel("학번"));
		JTextField tfStudentSearch = new JTextField(12);
		returnPanel.add(tfStudentSearch);
		JButton btnStudentSearch = new JButton("검색");
		returnPanel.add(btnStudentSearch);
		// btn listener 붙이기
		
		//학생검색
		String returnStudentColName[] = {"학번", "이름", "학과", "도서명"};	
		DefaultTableModel returnStudentModel = new DefaultTableModel(returnStudentColName,0);
		JTable returnStudentTable = new JTable(returnStudentModel);
		returnStudentTable.setPreferredScrollableViewportSize(new Dimension(255,255));
		returnPanel.add(new JScrollPane(returnStudentTable));
		returnStudentTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//여기다가 웅앵웅앵
			}

			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			});
		
		JButton returnButton = new JButton("반납");
		returnPanel.add(returnButton);
		JButton returnCancelButton = new JButton("취소");
		returnPanel.add(returnCancelButton);
		
		returnPanel.setSize(280, 600);
		returnPanel.setPreferredSize(new Dimension(280,600));
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	
}
