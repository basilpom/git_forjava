import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Haksa extends JFrame{

	public Haksa()
	{
		this.setTitle("학사관리 프로그램");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("이름"));
		JTextField tfName = new JTextField(20);
		c.add(tfName);
		
		c.add(new JLabel("학과"));
		JTextField tfMajor = new JTextField(20);
		c.add(tfMajor);
		
		c.add(new JLabel("학번"));
		JTextField tfId = new JTextField(20);
		c.add(tfId);
		
		c.add(new JLabel("주소"));
		
		JTextField tfAddress = new JTextField(20);
		c.add(tfAddress);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String name = tfName.getText();
				String major = tfMajor.getText();
				String id = tfId.getText();
				String address = tfAddress.getText();
				String message = "모든 정보를 입력해주세요.";
				
				if(name.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "등록 실패", JOptionPane.WARNING_MESSAGE);				}
				else if(major.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "등록 실패", JOptionPane.WARNING_MESSAGE);
				}
				else if(id.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "등록 실패", JOptionPane.WARNING_MESSAGE);
				}
				else if(address.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "등록 실패", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "정보 등록 완료!");
				}

			}});

		c.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?",
						"삭제 확인", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null, "삭제되었습니다.");
				}

			}});
		
		
		c.add(btnDelete);

		this.setSize(305, 200);
		this.setVisible(true);
		
	}

	public static void main(String[] args)
	{
		new Haksa();
	}

}