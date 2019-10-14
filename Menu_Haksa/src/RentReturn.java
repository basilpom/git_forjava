
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RentReturn extends JPanel{
	
	public RentReturn()
	{
		// DB Connection �߰�
		
		this.setLayout(new BorderLayout());
		//���� ���� �г�
		JPanel rentPanel = new JPanel();
		this.add(rentPanel, BorderLayout.WEST);
		rentPanel.setBackground(Color.gray);
		rentPanel.setLayout(new FlowLayout());
		
		//������ �˻�
		rentPanel.add(new JLabel("������"));
		JTextField tfBookSearch = new JTextField(12);
		rentPanel.add(tfBookSearch);
		JButton btnBookSearch = new JButton("�˻�");
		rentPanel.add(btnBookSearch);
		// btn listener ���̱�
		
		rentPanel.add(new JLabel("���� ������ å ���"));
		String bookColName[] = {"å��ȣ", "������", "����"};	
		DefaultTableModel bookModel = new DefaultTableModel(bookColName,0);
		JTable bookTable = new JTable(bookModel);
		bookTable.setPreferredScrollableViewportSize(new Dimension(255,100));
		add(new JScrollPane(bookTable));
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
		JTextField tfStudentSearch = new JTextField(15);
		//rentPanel.add(tfStudentSearch);
		JButton btnStudentSearch = new JButton("�˻�");
		//rentPanel.add(btnStudentSearch);
		// btn listener ���̱�
		
		String studentColName[] = {"�й�", "�̸�", "�а�"};	
		DefaultTableModel studentModel = new DefaultTableModel(studentColName,0);
		JTable studentTable = new JTable(studentModel);
		studentTable.setPreferredScrollableViewportSize(new Dimension(255,100));
		//add(new JScrollPane(studentTable));
		studentTable.addMouseListener(new MouseListener() {

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
		
		rentPanel.setSize(280, 600);
		
		//���� �ݳ� �г�
		JPanel returnPanel = new JPanel();
		this.add(returnPanel, BorderLayout.CENTER);
		returnPanel.setBackground(Color.BLUE);
		
	
		this.setSize(280, 600);
		this.setVisible(true);
	}
	
}
