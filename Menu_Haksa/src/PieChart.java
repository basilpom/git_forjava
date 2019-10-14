import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//�а��� ���� ���� ��Ȳ�� pie chart�� �����ֱ�
public class PieChart extends JPanel {
	ChartPanel chartPanel = new ChartPanel();
	
	Connection conn = null;
	Statement stmt = null;
	
	public PieChart()
	{
		DBManager db = new DBManager();
		conn = db.getConnection();
		
		setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel("�а��� ���� ���� ��Ȳ");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("���� ���", Font.BOLD, 40));


		add(titleLabel, BorderLayout.NORTH);
		add(chartPanel, BorderLayout.CENTER);

		this.setSize(500, 500);
		this.setVisible(true);
		
	}
	
	
	class ChartPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			//�� �� ���� ������ å �Ǽ� ���ϱ�. 1�� : �а��̸�, 2�� : ������ å �� ��
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
					String[] row=new String[2];//�÷��� ������ 4
				    row[0]=rs.getString("DEPT");
				    row[1]=rs.getString("COUNT");
				    System.out.println(row[0]+row[1]);
				    
				    // table���� select �� ������ string, int �迭�� ����
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

			//color array�� �ϰ������...
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
					angleSum += 360*bookCount[i]/sum + 1;	//int �� angle�� ���� ���� ����(+1)
					g.fillRect(360, 130+20*i, 12, 12);
				}
				
			}
			catch(Exception e) {}
			
		}
	}
	

}
