import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;


public class DBManager extends JFrame {
	Connection conn = null;
	
	public Connection getConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:myoracle","ora_user","hong");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//DB CLOSE
		this.addWindowListener(new WindowListener() {

		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowClosed(WindowEvent e) {}

		@Override
		public void windowClosing(WindowEvent e) {
			try
			{
				if(conn != null)
				{
					System.out.println("Closed");
					conn.close();
				}
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
			}
		}

		@Override
		public void windowDeactivated(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowOpened(WindowEvent e) {}
					
		});
				
		return conn;
		
	}

}

