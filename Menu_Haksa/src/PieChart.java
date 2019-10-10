import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

//학과별 도서 대출 현황을 pie chart로 보여주기
public class PieChart extends JPanel {

	//InputPanel inputPanel = new InputPanel();
	ChartPanel chartPanel = new ChartPanel();
	
	static int firstValue = 0;
	static int secondValue = 0;
	static int thirdValue = 0;
	static int fourthValue = 0;
	
	Connection conn = null;
	Statement stmt = null;
	
	public PieChart()
	{
		DBManager db = new DBManager();
		conn = db.getConnection();
		
		setLayout(new BorderLayout());
		
		//add(inputPanel, BorderLayout.NORTH);
		
		add(chartPanel, BorderLayout.CENTER);
		

		this.setSize(600, 500);
		this.setVisible(true);
		
	}
/*
	class InputPanel extends JPanel
	{
		public InputPanel()
		{
			this.setBackground(Color.LIGHT_GRAY);
	
			JLabel firstL = new JLabel("1/4분기");
			this.add(firstL);
			JTextField firstT = new JTextField("0", 5);
			this.add(firstT);
			
			JLabel secondL = new JLabel("2/4분기");
			this.add(secondL);
			JTextField secondT = new JTextField("0", 5);
			this.add(secondT);
			
			JLabel thirdL = new JLabel("3/4분기");
			this.add(thirdL);
			JTextField thirdT = new JTextField("0", 5);
			this.add(thirdT);
			
			JLabel fourthL = new JLabel("4/4분기");
			this.add(fourthL);
			JTextField fourthT = new JTextField("0", 5);
			this.add(fourthT);
			
			JButton inputBtn = new JButton("Draw Pie Chart!");
			this.add(inputBtn);
			inputBtn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					
					firstValue = Integer.parseInt(firstT.getText());
					secondValue = Integer.parseInt(secondT.getText());
					thirdValue = Integer.parseInt(thirdT.getText());
					fourthValue = Integer.parseInt(fourthT.getText());
								
					chartPanel.repaint();

				}});
			
		}

	}
*/
	
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
				
				//System.out.println(rowcnt);
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
					

			/*
			for(int j = 0; j < rowCount; j++)
			{
				System.out.println(dept[j] + bookCount[j]);
			}
			*/	
					
					
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
					g.drawString(dept[i], 380, 140 + 20*i);	//legend_name
					g.setColor(new Color(i*30, 10, 10));
					
					g.fillArc(30, 20, 300, 300, angleSum, 360*bookCount[i]/sum);
					angleSum += 360*bookCount[i]/sum;
					g.fillRect(360, 130+20*i, 12, 12);
					System.out.println("start : " +angleSum+"angle :"+ 360*bookCount[i]/sum);
				}
				/*
				int pFirst = 360*firstValue/sum;
				int pSecond = 360*secondValue/sum;
				int pThird = 360*thirdValue/sum;
				int pFourth = 360*fourthValue/sum;
				*/
				
				/*
				g.drawString("1/4분기", 380, 140);
				g.drawString("2/4분기", 380, 160);
				g.drawString("3/4분기", 380, 180);
				g.drawString("4/4분기", 380, 200);
				*/
				/*
				g.setColor(new Color(192, 0, 0));
				g.fillArc(30, 20, 300, 300, 0, pFirst+1);
				g.fillRect(360, 130, 12, 12);
				g.setColor(new Color(255, 192, 0));
				g.fillArc(30, 20, 300, 300, pFirst+1, pSecond+1);
				g.fillRect(360, 150, 12, 12);
				g.setColor(new Color(19, 133, 53));
				g.fillArc(30, 20, 300, 300, pFirst+pSecond+2, pThird+1);
				g.fillRect(360, 170, 12, 12);
				g.setColor(new Color(0, 112, 192));
				g.fillArc(30, 20, 300, 300, pFirst+pSecond+pThird+3, pFourth);
				g.fillRect(360, 190, 12, 12);
				*/
			}
			catch(Exception e) {}
			
		}
	}
	

}
