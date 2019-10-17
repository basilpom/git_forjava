import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;


public class Student extends JPanel{
	JTextArea taList = null;
	JTextField tfId = null;
	JTextField tfName = null;
	JTextField tfMajor = null;
	JTextField tfAddress = null;
	DefaultTableModel model = null;
	JTable table = null;
	//Connection conn = MenuHaksa.conn;
	Connection conn = null;

	public Student() {
		
		DBManager db = new DBManager();
		conn = db.getConnection();
		
		//DB Connection
		/*
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle","ora_user","hong");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		*/
		
		setLayout(new FlowLayout());

		add(new JLabel("�й�"));
		tfId = new JTextField(14);
		add(tfId);
		
		//������ ���� �˻� ��ư �߰�
		JButton btnSearch = new JButton("�˻�");
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from student where id = '"+tfId.getText()+"'");

					showList(rs);

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
			}});
		
		add(btnSearch);
		

		add(new JLabel("�̸�"));
		tfName = new JTextField(20);
		add(tfName);
		
		add(new JLabel("�а�"));
		tfMajor = new JTextField(20);
		add(tfMajor);
		
		add(new JLabel("�ּ�"));
		tfAddress = new JTextField(20);
		add(tfAddress);

		//TEXTFIELD ��� TABLE��!
		String colName[]={"�й�","�̸�","�а�","�ּ�"};	//ǥ�� ����� COLUMN NAME
		model=new DefaultTableModel(colName,0);	//ǥ�� �� ������
		table = new JTable(model);				//���̺� ��(������) ���ε�
		table.setPreferredScrollableViewportSize(new Dimension(255,255));
		add(new JScrollPane(table));
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				table = (JTable)e.getComponent();
				model = (DefaultTableModel)table.getModel();
				
				String id = (String)model.getValueAt(table.getSelectedRow(), 0);
				tfId.setText(id);
				String name = (String)model.getValueAt(table.getSelectedRow(), 1);
				tfName.setText(name);
				String dept = (String)model.getValueAt(table.getSelectedRow(), 2);
				tfMajor.setText(dept);
				String address = (String)model.getValueAt(table.getSelectedRow(), 3);
				tfAddress.setText(address);
				
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

		JButton btnInsert = new JButton("���");
		add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {
			Statement stmt = null;
			ResultSet rs = null;
			
			@Override
			public void actionPerformed(ActionEvent e) {

				//��� �� ��� ������ �Է����� ������ �޼��� â ����
				if(tfId.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(
							null,"��� ������ �Է��ϼ���!","�˸�",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(
							null,"��� ������ �Է��ϼ���!","�˸�",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfMajor.getText().equals(""))
				{
					JOptionPane.showMessageDialog(
							null,"��� ������ �Է��ϼ���!","�˸�",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfAddress.getText().equals(""))
				{
					JOptionPane.showMessageDialog(
							null,"��� ������ �Է��ϼ���!","�˸�",
							JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{
						stmt = conn.createStatement();
		
						//INSERT
						int rowcnt = stmt.executeUpdate("insert into student(id,name,dept,address) values('"+tfId.getText()+"','"+tfName.getText()+"','"+tfMajor.getText()+"','"+tfAddress.getText()+"')");
	
						JOptionPane.showMessageDialog(null, "��ϵǾ����ϴ�.");
	
						//����� �������� ��� �����ֱ�!
						rs = stmt.executeQuery("select * from student");
						showList(rs);
						
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

		JButton btnList = new JButton("���");
		btnList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				ResultSet rs = null;
				try 
				{
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select * from student");

					showList(rs);

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

			}});

		add(btnList);
		JButton btnUpdate = new JButton("����");
		btnUpdate.addActionListener(new ActionListener() {
			Statement stmt = null;
			ResultSet rs = null;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					stmt = conn.createStatement();
					int rowcnt = stmt.executeUpdate("UPDATE STUDENT SET NAME='"+tfName.getText()+"', DEPT = '"+tfMajor.getText()+"', ADDRESS = '"+tfAddress.getText()+"' WHERE ID = '"+tfId.getText()+"'");

					//���� ��ư�� ������ �ٷ� �ٲ� ������ �ߵ���
					rs = stmt.executeQuery("select * from student");
					showList(rs);
					
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
				});
		add(btnUpdate);
		
		JButton btnDelete = new JButton("����");
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			Statement stmt = null;
			ResultSet rs = null;
			@Override
			public void actionPerformed(ActionEvent e) {

				//EXAMPLE 02. ���� ��ư Ŭ�� �� Ȯ�� �޽���
				if(JOptionPane.showConfirmDialog(null, 
						"�����Ͻðڽ��ϱ�?","����",
						JOptionPane.YES_NO_OPTION)
						==JOptionPane.YES_OPTION) 
				{
					try 
					{
						stmt = conn.createStatement();
						
						int rowcnt = stmt.executeUpdate("DELETE FROM STUDENT WHERE ID = '"+tfId.getText()+"'");
						rs = stmt.executeQuery("select * from student");
						showList(rs);
						
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

					JOptionPane.showMessageDialog(null, "�����Ǿ����ϴ�.","�˸�",JOptionPane.ERROR_MESSAGE);
					
					//�ʱ�ȭ�ϴ� �޼ҵ� ���� ���� �غ���^^...

				}
			}
		});

		this.setSize(280,500);
		this.setVisible(true);

	}
	
	
	//EXAMPLE 01. ��ü ����� ����ϴ� �޼ҵ�
	public void showList(ResultSet rs)
	{
		try
	    {		    
		    //JTable �ʱ�ȭ
		    model.setNumRows(0);
		    
		    while(rs.next())
		    {
		    	String[] row=new String[4];		//�÷��� ������ 4
			    row[0]=rs.getString("ID");
			    row[1]=rs.getString("NAME");
			    row[2]=rs.getString("DEPT");
			    row[3]=rs.getString("ADDRESS");
			    model.addRow(row);
		     }
		     rs.close();
		    
	    }
	    catch(Exception e1)
	    {
	    	System.out.println(e1.getMessage());
	    }
		
	}
	


}