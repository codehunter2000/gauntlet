package edu.miracosta.comm106.gauntlet;

import javax.swing.*;
import java.awt.event.*;

public class Gauntlet 
{
	private static final int HEIGHT = 300;
	private static final int WIDTH = 400;
	private JMenuBar menuBar;
	private JMenu close;
	private JMenuItem exit;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel imageLabel;
	private ImageIcon eye = new ImageIcon("images/The-Illuminati-Eye.png");
	
	public Gauntlet()
	{
		mainFrame = new JFrame("GAUNTLET");
		mainFrame.setSize(WIDTH, HEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();
		close = new JMenu("Exit");
		exit = new JMenuItem("Exit");
		exit.addActionListener(new exitButtonListener());
		buildMainPanel();
		close.add(exit);
		menuBar.add(close);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	
	public void buildMainPanel()
	{
		mainPanel = new JPanel();
		mainPanel.add(new JLabel(eye));
	}
	
	private class exitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
}
