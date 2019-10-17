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

		add(new JLabel("학번"));
		tfId = new JTextField(14);
		add(tfId);
		
		//수정을 위한 검색 버튼 추가
		JButton btnSearch = new JButton("검색");
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
		

		add(new JLabel("이름"));
		tfName = new JTextField(20);
		add(tfName);
		
		add(new JLabel("학과"));
		tfMajor = new JTextField(20);
		add(tfMajor);
		
		add(new JLabel("주소"));
		tfAddress = new JTextField(20);
		add(tfAddress);

		//TEXTFIELD 대신 TABLE로!
		String colName[]={"학번","이름","학과","주소"};	//표에 출력할 COLUMN NAME
		model=new DefaultTableModel(colName,0);	//표에 들어갈 데이터
		table = new JTable(model);				//테이블에 모델(데이터) 바인딩
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

		JButton btnInsert = new JButton("등록");
		add(btnInsert);
		btnInsert.addActionListener(new ActionListener() {
			Statement stmt = null;
			ResultSet rs = null;
			
			@Override
			public void actionPerformed(ActionEvent e) {

				//등록 시 모든 정보를 입력하지 않으면 메세지 창 띄우기
				if(tfId.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(
							null,"모든 정보를 입력하세요!","알림",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(
							null,"모든 정보를 입력하세요!","알림",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfMajor.getText().equals(""))
				{
					JOptionPane.showMessageDialog(
							null,"모든 정보를 입력하세요!","알림",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(tfAddress.getText().equals(""))
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
						int rowcnt = stmt.executeUpdate("insert into student(id,name,dept,address) values('"+tfId.getText()+"','"+tfName.getText()+"','"+tfMajor.getText()+"','"+tfAddress.getText()+"')");
	
						JOptionPane.showMessageDialog(null, "등록되었습니다.");
	
						//등록한 정보까지 모두 보여주기!
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

		JButton btnList = new JButton("목록");
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
		JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			Statement stmt = null;
			ResultSet rs = null;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try 
				{
					stmt = conn.createStatement();
					int rowcnt = stmt.executeUpdate("UPDATE STUDENT SET NAME='"+tfName.getText()+"', DEPT = '"+tfMajor.getText()+"', ADDRESS = '"+tfAddress.getText()+"' WHERE ID = '"+tfId.getText()+"'");

					//수정 버튼을 누르면 바로 바뀐 정보가 뜨도록
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
		
		JButton btnDelete = new JButton("삭제");
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {

			Statement stmt = null;
			ResultSet rs = null;
			@Override
			public void actionPerformed(ActionEvent e) {

				//EXAMPLE 02. 삭제 버튼 클릭 시 확인 메시지
				if(JOptionPane.showConfirmDialog(null, 
						"삭제하시겠습니까?","삭제",
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

					JOptionPane.showMessageDialog(null, "삭제되었습니다.","알림",JOptionPane.ERROR_MESSAGE);
					
					//초기화하는 메소드 따로 만들어서 해보삼^^...

				}
			}
		});

		this.setSize(280,500);
		this.setVisible(true);

	}
	
	
	//EXAMPLE 01. 전체 목록을 출력하는 메소드
	public void showList(ResultSet rs)
	{
		try
	    {		    
		    //JTable 초기화
		    model.setNumRows(0);
		    
		    while(rs.next())
		    {
		    	String[] row=new String[4];		//컬럼의 갯수가 4
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