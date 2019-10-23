import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;




public class BookRentInfo extends JPanel{
	DefaultTableModel model = null;
	JTable table = null;
	//Connection conn = MenuHaksa.conn;
	Statement stmt;
	String query;
	Connection conn = null;
	
	public BookRentInfo()
	{
		query = "SELECT BOOKRENT.NO, STUDENT.NAME, BOOKS.TITLE, BOOKRENT.RDATE" + 
				"  FROM STUDENT, BOOKS, BOOKRENT" + 
				" WHERE STUDENT.ID = BOOKRENT.ID" + 
				"   AND BOOKS.NO = BOOKRENT.BOOKNO";
		
		DBManager db = new DBManager();
		conn = db.getConnection();

		
	    setLayout(null);//���̾ƿ�����. ���̾ƿ� ��� ����.
	   
	    JLabel l_dept=new JLabel("�а�");
	    l_dept.setBounds(10, 10, 30, 20);
	    add(l_dept);
	   
	    String[] dept={"��ü","��ǻ�ͽý���","��Ƽ�̵��","��ǻ�Ͱ���"};
	    JComboBox cb_dept=new JComboBox(dept);
	    cb_dept.setBounds(45, 10, 100, 20);
	    add(cb_dept);
	    
	    cb_dept.addActionListener(new ActionListener() {
	    	//COMBOBOX���� FILTERING
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				//COMBOBOX���� �� ���� �Ǵ��� Ȯ��
				//System.out.println(cb.getSelectedIndex());
				
				int si = cb.getSelectedIndex();
				query = "SELECT BOOKRENT.NO, STUDENT.NAME, BOOKS.TITLE, BOOKRENT.RDATE" + 
						"  FROM STUDENT, BOOKS, BOOKRENT" + 
						" WHERE STUDENT.ID = BOOKRENT.ID" + 
						"   AND BOOKS.NO = BOOKRENT.BOOKNO";
				
				//���� ���� ����
				if(si == 0)			//��ü
				{
					query += " ORDER BY BOOKRENT.NO ";
				}
				else if(si == 1)	//��ǻ�ͽý���
				{
					query += " AND STUDENT.DEPT = '��ǻ�ͽý���' "
						   + " ORDER BY BOOKRENT.NO ";
				}
				else if(si == 2)	//��Ƽ�̵��
				{
					query += " AND STUDENT.DEPT = '��Ƽ�̵��' "
						   + " ORDER BY BOOKRENT.NO ";
				}
				else if(si == 3)	//��ǻ�Ͱ���
				{
					query += " AND STUDENT.DEPT = '��ǻ�Ͱ���' "
						   + " ORDER BY BOOKRENT.NO";
				}
				//�˻����
				list();
			}});
	    
	    //TABLE
	    String colName[]={"�����ȣ","�̸�","������","������"};
	    model=new DefaultTableModel(colName,0);
	    table = new JTable(model);
	    table.setPreferredScrollableViewportSize(new Dimension(470,200));
	    add(table);
	    JScrollPane sp=new JScrollPane(table);
	    sp.setBounds(10, 40, 460, 250);
	    add(sp); 
	    
	    this.setSize(490, 400);
	    this.setVisible(true);
	    list();	//��ü ���
	}
	
	   public void list(){
	    try
	    {
	    	stmt = conn.createStatement();
		    // Select�� ����     
		    ResultSet rs = stmt.executeQuery(query);
		    
		    //JTable �ʱ�ȭ
		    model.setNumRows(0);
		    
		    while(rs.next())
		    {
		    	String[] row=new String[4];//�÷��� ������ 4
			    row[0]=rs.getString("NO");
			    row[1]=rs.getString("NAME");
			    row[2]=rs.getString("TITLE");
			    row[3]=rs.getString("RDATE");
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
