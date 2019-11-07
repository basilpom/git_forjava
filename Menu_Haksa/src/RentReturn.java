
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
	DefaultTableModel returnStudentModel = null;
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
					rs = stmt.executeQuery("SELECT * FROM BOOKS "
										 + " WHERE UPPER(TITLE) LIKE '%'||UPPER('"+tfBookSearch.getText()+"')||'%'");
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
					if(JOptionPane.showConfirmDialog(null, 
							"대출하시겠습니까?","대출",
							JOptionPane.YES_NO_OPTION)
							==JOptionPane.YES_OPTION)
					{
						try 
						{
							stmt = conn.createStatement();
							//INSERT
							stmt.executeUpdate("INSERT INTO BOOKRENT "
											 + " VALUES(CONCAT(TO_CHAR(SYSDATE, 'YYYYMMDD'), "
											 + "LPAD(SEQ_RENT_NO.NEXTVAL, 2, 0)), "
											 + "'"+tfRentStudentSearch.getText()+"', "
											 + "'"+(String)bookModel.getValueAt(bookTable.getSelectedRow(), 0)+"', "
											 + "TO_CHAR(SYSDATE, 'YYYYMMDD'))");
							//int rowcnt = stmt.executeUpdate("INSERT INTO VALUES");
							//(String)bookModel.getValueAt(bookTable.getSelectedRow(), 0);
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
		JTextField tfReturnStudentSearch = new JTextField(12);
		returnPanel.add(tfReturnStudentSearch);
		JButton btnReturnStudentSearch = new JButton("검색");
		returnPanel.add(btnReturnStudentSearch);
		// btn listener 붙이기
		btnReturnStudentSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				try
				{
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT STUDENT.ID, STUDENT.NAME, STUDENT.DEPT, BOOKS.TITLE "
										 + " FROM STUDENT, BOOKRENT, BOOKS "
										 + " WHERE STUDENT.ID = BOOKRENT.ID "
										 + " AND BOOKS.NO = BOOKRENT.BOOKNO "
										 + " AND STUDENT.ID LIKE '%"+tfReturnStudentSearch.getText()+"%' "
										 + " ORDER BY BOOKRENT.NO ");
					returnStudentModel.setNumRows(0);
					while(rs.next())
					{
						String[] row = new String[4];
						row[0] = rs.getString("ID");
						row[1] = rs.getString("NAME");
						row[2] = rs.getString("DEPT");
						row[3] = rs.getString("TITLE");
						returnStudentModel.addRow(row);
					}
					rs.close();					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}});
		
		String returnStudentColName[] = {"학번", "이름", "학과", "도서명"};	
		returnStudentModel = new DefaultTableModel(returnStudentColName,0);
		JTable returnStudentTable = new JTable(returnStudentModel);
		returnStudentTable.setPreferredScrollableViewportSize(new Dimension(255,255));
		returnPanel.add(new JScrollPane(returnStudentTable));
		returnStudentTable.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tfReturnStudentSearch.setText((String)returnStudentModel.getValueAt(returnStudentTable.getSelectedRow(), 0));
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
		
		JButton btnReturn = new JButton("반납");
		returnPanel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, 
						"반납하시겠습니까?","반납",
						JOptionPane.YES_NO_OPTION)
						==JOptionPane.YES_OPTION)
				{
					Statement stmt = null;
					ResultSet rs = null;
					try
					{
						stmt = conn.createStatement();
						stmt.executeQuery("DELETE FROM BOOKRENT "
											 + " WHERE ID = '"+tfReturnStudentSearch.getText()+"' "
											 + " AND BOOKNO = (SELECT NO FROM BOOKS WHERE TITLE= '"
											 +(String)returnStudentModel.getValueAt(returnStudentTable.getSelectedRow(), 3)+"')");
						rs = stmt.executeQuery("SELECT STUDENT.ID, STUDENT.NAME, STUDENT.DEPT, BOOKS.TITLE "
								 + " FROM STUDENT, BOOKRENT, BOOKS "
								 + " WHERE STUDENT.ID = BOOKRENT.ID "
								 + " AND BOOKS.NO = BOOKRENT.BOOKNO");
						returnStudentModel.setNumRows(0);
						while(rs.next())
						{
							String[] row = new String[4];
							row[0] = rs.getString("ID");
							row[1] = rs.getString("NAME");
							row[2] = rs.getString("DEPT");
							row[3] = rs.getString("TITLE");
							returnStudentModel.addRow(row);
						}
						rs.close();					
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
					tfReturnStudentSearch.setText(null);
				}
				
			}});
		JButton btnReturnCancel = new JButton("취소");
		returnPanel.add(btnReturnCancel);
		btnReturnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfReturnStudentSearch.setText(null);				
			}});
		
		returnPanel.setSize(280, 600);
		returnPanel.setPreferredSize(new Dimension(280,600));
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	
}
