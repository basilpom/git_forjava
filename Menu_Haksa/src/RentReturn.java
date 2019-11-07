
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
		//���� ���� �г�
		JPanel rentPanel = new JPanel();
		this.add(rentPanel);
		rentPanel.setLayout(new FlowLayout());
		
		ImageIcon rentImg = new ImageIcon("img/rentBook.png");
		JLabel rentLabel = new JLabel("�������� ", rentImg, SwingConstants.CENTER);
		rentLabel.setFont(new Font("���� ���", Font.BOLD, 45));
		rentPanel.add(rentLabel);
		//������ �˻�
		rentPanel.add(new JLabel("������"));
		JTextField tfBookSearch = new JTextField(12);
		rentPanel.add(tfBookSearch);
		JButton btnBookSearch = new JButton("�˻�");
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
		
		rentPanel.add(new JLabel("���� ������ å ���"));
		String bookColName[] = {"å��ȣ", "������", "����"};	
		bookModel = new DefaultTableModel(bookColName,0);
		bookTable = new JTable(bookModel);
		bookTable.setPreferredScrollableViewportSize(new Dimension(255,85));
		rentPanel.add(new JScrollPane(bookTable));
		//���� �˻� ���̺��� ���ý� textfield�� �ݿ�
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
		
		//�й� �˻�
		rentPanel.add(new JLabel("�й�"));
		JTextField tfRentStudentSearch = new JTextField(12);
		rentPanel.add(tfRentStudentSearch);
		JButton btnRentStudentSearch = new JButton("�˻�");
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
		
		String rentStudentColName[] = {"�й�", "�̸�", "�а�"};	
		rentStudentModel = new DefaultTableModel(rentStudentColName,0);
		rentStudentTable = new JTable(rentStudentModel);
		rentStudentTable.setPreferredScrollableViewportSize(new Dimension(255,85));
		rentPanel.add(new JScrollPane(rentStudentTable));
		//�й� �˻� ���̺��� ���ý� textfield�� �ݿ�
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
		
		JButton rentButton = new JButton("����");
		rentPanel.add(rentButton);
		//��� ��ư ������ DB�� �ݿ�
		rentButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				//��� �� ��� ������ �Է����� ������ �޼��� â ����
				if(tfBookSearch.getText().equals("")) 
					
				{
					JOptionPane.showMessageDialog(
							null,"��� ������ �Է��ϼ���!","�˸�",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfRentStudentSearch.getText().equals(""))
				{
					JOptionPane.showMessageDialog(
							null,"��� ������ �Է��ϼ���!","�˸�",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					if(JOptionPane.showConfirmDialog(null, 
							"�����Ͻðڽ��ϱ�?","����",
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
							JOptionPane.showMessageDialog(null, "����Ǿ����ϴ�.");
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
		JButton rentCancelButton = new JButton("���");
		rentPanel.add(rentCancelButton);
		//��� ��ư ������ ������, �й� textfield �� �����
		rentCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tfBookSearch.setText(null);
				tfRentStudentSearch.setText(null);
				
			}});
		
		rentPanel.setSize(280, 600);
		rentPanel.setPreferredSize(new Dimension(280,600));

		//���� �ݳ� �г�
		JPanel returnPanel = new JPanel();
		this.add(returnPanel);
		
		ImageIcon returnImg = new ImageIcon("img/returnBook.png");
		JLabel returnLabel = new JLabel("�����ݳ�", returnImg, SwingConstants.CENTER);
		returnLabel.setFont(new Font("���� ���", Font.BOLD, 45));
		returnPanel.add(returnLabel);
		
		returnPanel.add(new JLabel("�й�"));
		JTextField tfReturnStudentSearch = new JTextField(12);
		returnPanel.add(tfReturnStudentSearch);
		JButton btnReturnStudentSearch = new JButton("�˻�");
		returnPanel.add(btnReturnStudentSearch);
		// btn listener ���̱�
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
		
		String returnStudentColName[] = {"�й�", "�̸�", "�а�", "������"};	
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
		
		JButton btnReturn = new JButton("�ݳ�");
		returnPanel.add(btnReturn);
		btnReturn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(null, 
						"�ݳ��Ͻðڽ��ϱ�?","�ݳ�",
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
		JButton btnReturnCancel = new JButton("���");
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
