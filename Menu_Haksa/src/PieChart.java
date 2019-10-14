import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//학과별 도서 대출 현황을 pie chart로 보여주기
public class PieChart extends JPanel {
	ChartPanel chartPanel = new ChartPanel();
	
	Connection conn = null;
	Statement stmt = null;
	
	public PieChart()
	{
		DBManager db = new DBManager();
		conn = db.getConnection();
		
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("학과별 도서 대출 현황");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));


		add(titleLabel, BorderLayout.NORTH);
		add(chartPanel, BorderLayout.CENTER);

		this.setSize(500, 500);
		this.setVisible(true);
		
	}
	
	
	class ChartPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			//각 과 별로 대출한 책 권수 구하기. 1열 : 학과이름, 2열 : 대출한 책 권 수
			String query = "SELECT STUDENT.DEPT, COUNT(BOOKRENT.NO) AS COUNT"
						 + " FROM STUDENT, BOOKRENT "
						 + " WHERE STUDENT.ID = BOOKRENT.ID "
						 + " GROUP BY STUDENT.DEPT";
			String[] dept = null;
			int[] bookCount = null;
			int rowCount = 0;
			
			try
			{
				stmt = conn.createStatement();
				int rowcnt = stmt.executeUpdate(query);
				ResultSet rs = stmt.executeQuery(query);
				rowCount = rowcnt;
				
				dept = new String[rowcnt];
				bookCount = new int[rowcnt];
				
				int i = 0;
				while(rs.next())
				{
					String[] row=new String[2];//컬럼의 갯수가 4
				    row[0]=rs.getString("DEPT");
				    row[1]=rs.getString("COUNT");
				    System.out.println(row[0]+row[1]);
				    
				    // table에서 select 한 값들을 string, int 배열에 저장
				    dept[i] = row[0];
				    bookCount[i] = Integer.parseInt(row[1]);
				    i++;
				    
				}
				rs.close();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			//color array로 하고싶은데...
			//Color[] chartColor = {new Color(100,20,30), new Color(10,200,30), new Color(10,20,300)};
			

			super.paintComponent(g);
			try 
			{
				int sum = 0;
				for (int i = 0; i < rowCount; i++)
				{
					sum += bookCount[i];
				}
				
				int angleSum = 0;
				for (int i = 0; i < rowCount; i++)
				{
					g.setColor(Color.black);
					g.drawString(dept[i], 380, 140 + 20*i);	//legend_name
					g.setColor(new Color(i*60, 100, 100));
					g.fillArc(30, 20, 300, 300, angleSum, 360*bookCount[i]/sum + 1);
					angleSum += 360*bookCount[i]/sum + 1;	//int 값 angle로 인한 오차 보정(+1)
					g.fillRect(360, 130+20*i, 12, 12);
				}
				
			}
			catch(Exception e) {}
			
		}
	}
	

}
