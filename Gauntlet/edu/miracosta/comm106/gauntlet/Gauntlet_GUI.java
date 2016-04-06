package edu.miracosta.comm106.gauntlet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Gauntlet_GUI
{
	private static final int HEIGHT = 300;
	private static final int WIDTH = 400;
	private ArrayList<Player> players;
	private Gauntlet_Cards launch;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem exit, addCards, showCards, addPlayer, showPlayers, showPoints;
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JLabel illuminatiImage,	brainImage, heartImage, muscleImage;
	private JButton spin;
	private File illuminati, brain, heart, muscle;
	private BufferedImage eye, mental, emotional, physical;
	private int playerIndex;
	private String[] threeOptions = {"Show answer", "Completed! Give me the points!", 
			"Commence the walk of shame"};
	private String[] twoOptions = {"Completed! Give me the points!", "Commence the walk of shame"};

	
	public Gauntlet_GUI()
	{
		illuminati = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/The-Illuminati-Eye.png");
		brain = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/activebrain.jpg");
		heart = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/Red-Heart.png");
		muscle = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/muscle.jpg");
		
		players = new ArrayList<>();
		playerIndex = 0;
		
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
			launch = new Gauntlet_Cards();
			launch.startUp();
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
			showCards = new JMenuItem("Show cards");
			addPlayer = new JMenuItem("Add player");
			showPlayers = new JMenuItem("Show players");
			showPoints = new JMenuItem("Show players");
			spin = new JButton("GO!");
			addCards.addActionListener(new addCardsButtonListener());
			showCards.addActionListener(new showCardsButtonListener());
			addPlayer.addActionListener(new addPlayerButtonListener());
			showPlayers.addActionListener(new showPlayersButtonListener());
			showPoints.addActionListener(new showPointsButtonListener());
			spin.addActionListener(new spinButtonListener());
			exit.addActionListener(new exitButtonListener());
			menu.add(addCards);
			menu.add(addPlayer);
			menu.add(showCards);
			menu.add(showPlayers);
			menu.add(showPoints);
			//menu.add(saveCards);
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
	
	public void getCard()
	{
		Gauntlet_Cards card = launch.getCard();
		System.out.println(card.getCatagory());
		String message;
		if (card.getCatagory().equals("Mental"))
		{
			System.out.println("Executing mental case:");
			message = card.getQuestion() + "\n" + card.getPoints() + " points";
			System.out.println(message);
			int choice = JOptionPane.showOptionDialog(new JFrame(), message, "Mental", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, threeOptions, threeOptions[2]);
			
			if (choice == JOptionPane.YES_OPTION)
			{
				JOptionPane.showMessageDialog(null, card.getAnswer());
			}
			
			else if (choice == JOptionPane.YES_OPTION)
			{
				Player thePlayer = players.get(playerIndex);
				int points = thePlayer.getPoints();
				points = points + card.getPoints();
				thePlayer.setPoints(points);
				players.set(playerIndex, thePlayer);
			}
		}
		else 
		{
			int choice = JOptionPane.showOptionDialog(new JFrame(), card.getChallenge(), "Challenge", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, twoOptions, twoOptions[1]);
			if (choice == JOptionPane.YES_OPTION)
			{
				Player thePlayer = players.get(playerIndex);
				int points = thePlayer.getPoints();
				points = points + card.getPoints();
				thePlayer.setPoints(points);
				players.set(playerIndex, thePlayer);
			}
		}
		
		if ((playerIndex + 1) == players.size())
			playerIndex = 0;
		else 
			playerIndex++;
	}
	
	
	private class showPointsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
	
	private class showPlayersButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (players.isEmpty())
				System.out.println("No players!");
			else
				for (Player player : players) 
				{
					System.out.println(player);
				}
		}
	}
	
	private class addPlayerButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Player thePlayer = new Player();
			Player newPlayer = thePlayer.addNewPlayer();
			players.add(newPlayer);
		}
	}
	
	private class showCardsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			launch.showAllCards();
		}
	}
	
	
	private class spinButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			getCard();
		}
	}
	
	private class addCardsButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			launch.createCards();
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
