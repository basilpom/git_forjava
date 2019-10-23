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

		
	    setLayout(null);//레이아웃설정. 레이아웃 사용 안함.
	   
	    JLabel l_dept=new JLabel("학과");
	    l_dept.setBounds(10, 10, 30, 20);
	    add(l_dept);
	   
	    String[] dept={"전체","컴퓨터시스템","멀티미디어","컴퓨터공학"};
	    JComboBox cb_dept=new JComboBox(dept);
	    cb_dept.setBounds(45, 10, 100, 20);
	    add(cb_dept);
	    
	    cb_dept.addActionListener(new ActionListener() {
	    	//COMBOBOX에서 FILTERING
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				//COMBOBOX에서 잘 선택 되는지 확인
				//System.out.println(cb.getSelectedIndex());
				
				int si = cb.getSelectedIndex();
				query = "SELECT BOOKRENT.NO, STUDENT.NAME, BOOKS.TITLE, BOOKRENT.RDATE" + 
						"  FROM STUDENT, BOOKS, BOOKRENT" + 
						" WHERE STUDENT.ID = BOOKRENT.ID" + 
						"   AND BOOKS.NO = BOOKRENT.BOOKNO";
				
				//동적 쿼리 형태
				if(si == 0)			//전체
				{
					query += " ORDER BY BOOKRENT.NO ";
				}
				else if(si == 1)	//컴퓨터시스템
				{
					query += " AND STUDENT.DEPT = '컴퓨터시스템' "
						   + " ORDER BY BOOKRENT.NO ";
				}
				else if(si == 2)	//멀티미디어
				{
					query += " AND STUDENT.DEPT = '멀티미디어' "
						   + " ORDER BY BOOKRENT.NO ";
				}
				else if(si == 3)	//컴퓨터공학
				{
					query += " AND STUDENT.DEPT = '컴퓨터공학' "
						   + " ORDER BY BOOKRENT.NO";
				}
				//검색목록
				list();
			}});
	    
	    //TABLE
	    String colName[]={"대출번호","이름","도서명","대출일"};
	    model=new DefaultTableModel(colName,0);
	    table = new JTable(model);
	    table.setPreferredScrollableViewportSize(new Dimension(470,200));
	    add(table);
	    JScrollPane sp=new JScrollPane(table);
	    sp.setBounds(10, 40, 460, 250);
	    add(sp); 
	    
	    this.setSize(490, 400);
	    this.setVisible(true);
	    list();	//전체 목록
	}
	
	   public void list(){
	    try
	    {
	    	stmt = conn.createStatement();
		    // Select문 실행     
		    ResultSet rs = stmt.executeQuery(query);
		    
		    //JTable 초기화
		    model.setNumRows(0);
		    
		    while(rs.next())
		    {
		    	String[] row=new String[4];//컬럼의 갯수가 4
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
