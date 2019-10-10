import java.sql.*;
import java.io.*;

public class JDBC01 {
	
	public static void main(String[] args)
	{
		Connection conn = null;
		Statement stmt = null;	
		try {
			// driver load
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
						"jdbc:oracle:thin:@localhost:1521:myoracle",
						"ora_user", "hong");
			System.out.println("Connected");
			
			//statement instance
			stmt = conn.createStatement();
			//INSERT
			//stmt.executeUpdate("insert into student(id, name, dept)"
			//		+ "			 values('1209375', '������', '���ڰ���')");
			//UPDATE
			stmt.executeUpdate("update student set dept='ö��' where id='1091011'");
			//DELETE
			String sql = "delete from student "
					+ " where id = '1792012'";
			stmt.executeUpdate(sql);

			//CURSOR ����
			ResultSet rs = stmt.executeQuery("select * from student");
			while(rs.next())	//EOF�� �Ǹ� rs.next()�� false
			{
				//by column name
				//System.out.println(rs.getString("id"));
				//System.out.println(rs.getString("name"));
				//System.out.println(rs.getString("dept"));
				
				//by column index
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getString(3));
			}
			rs.close();	//cursor close
			conn.close();

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	

}
