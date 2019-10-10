
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PieChart extends JPanel {

	InputPanel inputPanel = new InputPanel();
	ChartPanel chartPanel = new ChartPanel();
	
	static int firstValue = 0;
	static int secondValue = 0;
	static int thirdValue = 0;
	static int fourthValue = 0;
	
	public PieChart()
	{


		setLayout(new BorderLayout());
		
		add(inputPanel, BorderLayout.NORTH);
		
		add(chartPanel, BorderLayout.CENTER);
		

		this.setSize(600, 500);
		this.setVisible(true);
		
	}
	
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

	class ChartPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			
			super.paintComponent(g);
			try {
			int sum = firstValue + secondValue + thirdValue + fourthValue;
			int pFirst = 360*firstValue/sum;
			int pSecond = 360*secondValue/sum;
			int pThird = 360*thirdValue/sum;
			int pFourth = 360*fourthValue/sum;
			
			g.drawString("1/4분기", 380, 140);
			g.drawString("2/4분기", 380, 160);
			g.drawString("3/4분기", 380, 180);
			g.drawString("4/4분기", 380, 200);
			
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
			}
			catch(Exception e) {}
			
		}
	}
	

}
