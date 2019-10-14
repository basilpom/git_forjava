
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RentReturn extends JPanel{
	
	public RentReturn()
	{
		// DB Connection 추가
		
		this.setLayout(new BorderLayout());
		//도서 대출 패널
		JPanel rentPanel = new JPanel();
		this.add(rentPanel, BorderLayout.WEST);
		rentPanel.setBackground(Color.gray);
		rentPanel.setLayout(new FlowLayout());
		
		//도서명 검색
		rentPanel.add(new JLabel("도서명"));
		JTextField tfBookSearch = new JTextField(12);
		rentPanel.add(tfBookSearch);
		JButton btnBookSearch = new JButton("검색");
		rentPanel.add(btnBookSearch);
		// btn listener 붙이기
		
		rentPanel.add(new JLabel("대출 가능한 책 목록"));
		String bookColName[] = {"책번호", "도서명", "저자"};	
		DefaultTableModel bookModel = new DefaultTableModel(bookColName,0);
		JTable bookTable = new JTable(bookModel);
		bookTable.setPreferredScrollableViewportSize(new Dimension(255,100));
		add(new JScrollPane(bookTable));
		bookTable.addMouseListener(new MouseListener() {
 
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
		
		//학번 검색
		rentPanel.add(new JLabel("학번"));
		JTextField tfStudentSearch = new JTextField(15);
		//rentPanel.add(tfStudentSearch);
		JButton btnStudentSearch = new JButton("검색");
		//rentPanel.add(btnStudentSearch);
		// btn listener 붙이기
		
		String studentColName[] = {"학번", "이름", "학과"};	
		DefaultTableModel studentModel = new DefaultTableModel(studentColName,0);
		JTable studentTable = new JTable(studentModel);
		studentTable.setPreferredScrollableViewportSize(new Dimension(255,100));
		//add(new JScrollPane(studentTable));
		studentTable.addMouseListener(new MouseListener() {

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
		
		rentPanel.setSize(280, 600);
		
		//도서 반납 패널
		JPanel returnPanel = new JPanel();
		this.add(returnPanel, BorderLayout.CENTER);
		returnPanel.setBackground(Color.BLUE);
		
	
		this.setSize(280, 600);
		this.setVisible(true);
	}
	
}
