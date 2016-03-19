package edu.miracosta.comm106.gauntlet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gauntlet_GUI
{
	private static final int HEIGHT = 300;
	private static final int WIDTH = 400;
	private JMenuBar menuBar;
	private JMenu close;
	private JMenuItem exit;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel imageLabel;
	private File illuminati;

	
	public Gauntlet_GUI()
	{
		
		BufferedImage eye;
			// Will need to be changed for windows filepath
		illuminati = new File("/home/gabriel/git/gauntlet/Gauntlet/edu/miracosta/comm106/gauntlet/images/The-Illuminati-Eye.png");
		
		if (illuminati.exists())
			System.out.println("File exists");
		if (!illuminati.exists())
			System.out.println("DNE");
		
		try 
		{
			eye = ImageIO.read(illuminati);
			mainFrame = new JFrame("GAUNTLET");
			mainFrame.setSize(WIDTH, HEIGHT);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			imageLabel = new JLabel(new ImageIcon(eye));
			mainFrame.add(imageLabel);
			mainFrame.setVisible(true);
		} 
		
		catch (IOException e) 
		{
			System.out.println("Image Not Found.");
		}
		
		
	}

	
	private class exitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
}
