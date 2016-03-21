package edu.miracosta.comm106.gauntlet;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gauntlet_GUI
{
	private static final int HEIGHT = 300;
	private static final int WIDTH = 400;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem exit, addCards;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel illuminatiImage,	brainImage, heartImage, muscleImage;
	private JButton spin;
	private File illuminati, brain, heart, muscle;
	private BufferedImage eye, mental, emotional, physical;

	
	public Gauntlet_GUI()
	{
		illuminati = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/The-Illuminati-Eye.png");
		brain = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/activebrain.jpg");
		heart = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/Red-Heart.png");
		muscle = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/muscle.jpg");
		
		if (!illuminati.exists())
			System.out.println("Illuminati DNE");
		if (!brain.exists())
			System.out.println("If I only had a brain!");
		if (!heart.exists())
			System.out.println("You sin in the name of rock 'n roll.");
		if (!muscle.exists())
			System.out.println("Weakling");
		
		
		try 
		{
			eye = ImageIO.read(illuminati);
			mental = ImageIO.read(brain);
			physical = ImageIO.read(muscle);
			emotional = ImageIO.read(heart);
			mainFrame = new JFrame("GAUNTLET");
			mainFrame.setSize(WIDTH, HEIGHT);
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLayout(new BorderLayout());
			mainPanel = new JPanel();
			menuBar = new JMenuBar();
			menu = new JMenu("Menu");
			exit = new JMenuItem("Exit");
			addCards = new JMenuItem("Add cards");
			spin = new JButton("GO!");
			addCards.addActionListener(new addCardsButtonListener());
			spin.addActionListener(new spinButtonListener());
			exit.addActionListener(new exitButtonListener());
			menu.add(addCards);
			menu.add(exit);
			menuBar.add(menu);
			mainFrame.setJMenuBar(menuBar);
			
			illuminatiImage = new JLabel(new ImageIcon(eye));
			brainImage = new JLabel(new ImageIcon(mental));
			heartImage = new JLabel(new ImageIcon(emotional));
			muscleImage = new JLabel(new ImageIcon(physical));
			mainPanel.add(illuminatiImage, BorderLayout.CENTER);
//			mainPanel.add(brainImage, BorderLayout.NORTH);
//			mainPanel.add(heartImage, BorderLayout.EAST);
//			mainPanel.add(muscleImage, BorderLayout.WEST);
			mainPanel.add(spin, BorderLayout.SOUTH);
			mainFrame.add(mainPanel);
			mainFrame.setVisible(true);
		} 
		
		catch (IOException e) 
		{
			System.out.println("Image Not Found.");
		}		
	}

	
	private class spinButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Gauntlet_Cards test = new Gauntlet_Cards();
			
		}
	}
	
	private class addCardsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Gauntlet_Cards newCards = new Gauntlet_Cards();
			newCards.createCards();
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
