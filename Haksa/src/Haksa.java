import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Haksa extends JFrame{

	public Haksa()
	{
		this.setTitle("�л���� ���α׷�");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		c.add(new JLabel("�̸�"));
		JTextField tfName = new JTextField(20);
		c.add(tfName);
		
		c.add(new JLabel("�а�"));
		JTextField tfMajor = new JTextField(20);
		c.add(tfMajor);
		
		c.add(new JLabel("�й�"));
		JTextField tfId = new JTextField(20);
		c.add(tfId);
		
		c.add(new JLabel("�ּ�"));
		
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
				String message = "��� ������ �Է����ּ���.";
				
				if(name.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "��� ����", JOptionPane.WARNING_MESSAGE);				}
				else if(major.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "��� ����", JOptionPane.WARNING_MESSAGE);
				}
				else if(id.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "��� ����", JOptionPane.WARNING_MESSAGE);
				}
				else if(address.length() == 0)
				{
					JOptionPane.showMessageDialog(null, message, "��� ����", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "���� ��� �Ϸ�!");
				}

			}});

		c.add(btnInsert);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "�����Ͻðڽ��ϱ�?",
						"���� Ȯ��", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.");
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