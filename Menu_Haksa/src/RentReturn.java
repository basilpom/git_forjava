
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class RentReturn extends JPanel{
	Connection conn = null;
	
	public RentReturn()
	{
		DBManager db = new DBManager();
		conn = db.getConnection();
		
		this.setLayout(new FlowLayout());
		//���� ���� �г�
		JPanel rentPanel = new JPanel();
		this.add(rentPanel);
		rentPanel.setBackground(Color.gray);
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
		// btn listener ���̱�
		btnBookSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				try
				{
					stmt = conn.createStatement();
					rs = stmt.executeQuery("SELECT * FROM BOOKS WHERE TITLE LIKE '%"+tfBookSearch.getText()+"%'");
					
					
					
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				
			}});
		
		rentPanel.add(new JLabel("���� ������ å ���"));
		String bookColName[] = {"å��ȣ", "������", "����"};	
		DefaultTableModel bookModel = new DefaultTableModel(bookColName,0);
		JTable bookTable = new JTable(bookModel);
		bookTable.setPreferredScrollableViewportSize(new Dimension(255,100));
		rentPanel.add(new JScrollPane(bookTable));
		bookTable.addMouseListener(new MouseListener() {
 
			@Override
			public void mouseClicked(MouseEvent e) {
				//����ٰ� ���޿���
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
		// btn listener ���̱�
		
		String rentStudentColName[] = {"�й�", "�̸�", "�а�"};	
		DefaultTableModel rentStudentModel = new DefaultTableModel(rentStudentColName,0);
		JTable rentStudentTable = new JTable(rentStudentModel);
		rentStudentTable.setPreferredScrollableViewportSize(new Dimension(255,100));
		rentPanel.add(new JScrollPane(rentStudentTable));
		rentStudentTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//����ٰ� ���޿���
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
		JButton rentCancelButton = new JButton("���");
		rentPanel.add(rentCancelButton);
		rentPanel.setSize(280, 600);
		rentPanel.setPreferredSize(new Dimension(280,600));

		//���� �ݳ� �г�
		JPanel returnPanel = new JPanel();
		this.add(returnPanel);
		returnPanel.setBackground(Color.GRAY);
		
		ImageIcon returnImg = new ImageIcon("img/returnBook.png");
		JLabel returnLabel = new JLabel("�����ݳ�", returnImg, SwingConstants.CENTER);
		returnLabel.setFont(new Font("���� ���", Font.BOLD, 45));
		returnPanel.add(returnLabel);
		
		returnPanel.add(new JLabel("�й�"));
		JTextField tfStudentSearch = new JTextField(12);
		returnPanel.add(tfStudentSearch);
		JButton btnStudentSearch = new JButton("�˻�");
		returnPanel.add(btnStudentSearch);
		// btn listener ���̱�
		
		//�л��˻�
		String returnStudentColName[] = {"�й�", "�̸�", "�а�", "������"};	
		DefaultTableModel returnStudentModel = new DefaultTableModel(returnStudentColName,0);
		JTable returnStudentTable = new JTable(returnStudentModel);
		returnStudentTable.setPreferredScrollableViewportSize(new Dimension(255,285));
		returnPanel.add(new JScrollPane(returnStudentTable));
		returnStudentTable.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//����ٰ� ���޿���
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
		
		JButton returnButton = new JButton("�ݳ�");
		returnPanel.add(returnButton);
		JButton returnCancelButton = new JButton("���");
		returnPanel.add(returnCancelButton);
		
		returnPanel.setSize(280, 600);
		returnPanel.setPreferredSize(new Dimension(280,600));
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	
}
