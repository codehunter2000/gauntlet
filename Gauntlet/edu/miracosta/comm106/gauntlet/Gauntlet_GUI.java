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
	private Gauntlet_Cards launch, card;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem exit, addCards, showCards, addPlayer, showPoints;
	private JFrame mainFrame, questionFrame;
	private JPanel mainPanel, questionPanel;
	private JLabel illuminatiImage;
	private JButton spin, solved, answer, fail;
	private JTextArea questionText;
	private File illuminati;
	private BufferedImage eye;
	private int playerIndex;
	private String[] twoOptions = {"Completed! Give me the points!", "Commence the walk of shame"};

	
	public Gauntlet_GUI()
	{
		illuminati = new File("Gauntlet/edu/miracosta/comm106/gauntlet/images/The-Illuminati-Eye.png");
		players = new ArrayList<>();
		playerIndex = 0;
		
		if (!illuminati.exists())
			System.out.println("Illuminati DNE");
		
		try 
		{
			launch = new Gauntlet_Cards();
			launch.startUp();
			eye = ImageIO.read(illuminati);
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
			showPoints = new JMenuItem("Show players");
			spin = new JButton("GO!");
			addCards.addActionListener(new addCardsButtonListener());
			showCards.addActionListener(new showCardsButtonListener());
			addPlayer.addActionListener(new addPlayerButtonListener());
			showPoints.addActionListener(new showPointsButtonListener());
			spin.addActionListener(new spinButtonListener());
			exit.addActionListener(new exitButtonListener());
			menu.add(addCards);
			menu.add(addPlayer);
			menu.add(showCards);
			menu.add(showPoints);
			menu.add(exit);
			menuBar.add(menu);
			mainFrame.setJMenuBar(menuBar);
			
			illuminatiImage = new JLabel(new ImageIcon(eye));
			mainPanel.add(illuminatiImage, BorderLayout.CENTER);
			mainPanel.add(spin, BorderLayout.SOUTH);
			mainFrame.add(mainPanel);
			mainFrame.setVisible(true);
		} 
		
		catch (IOException e) 
		{
			JOptionPane.showMessageDialog(null, "Image not found!");
		}		
	}
	
	public void buildQestionWindow()
	{
		questionFrame = new JFrame("Mental");
		questionPanel = new JPanel();
		questionText = new JTextArea();
		solved = new JButton("Completed! Give me the points!");
		fail = new JButton("Commence the walk of shame");
		answer = new JButton("Show answer");
		questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		questionText.setText(card.getQuestion() + "\n" + card.getPoints() + " points");
		solved.addActionListener(new solvedButtonListener());
		fail.addActionListener(new failButtonListener());
		answer.addActionListener(new answerButtonListener());
		questionPanel.add(questionText);
		questionPanel.add(solved);
		questionPanel.add(fail);
		questionPanel.add(answer);
		questionFrame.add(questionPanel);
		questionFrame.pack();
		questionFrame.setVisible(true);
	}
	
	public void getCard()
	{
		card = launch.getCard();
		
		if (card.getCatagory().equals("Mental"))
		{
			buildQestionWindow();
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
			if (players.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No players!");
			}
			if (!players.isEmpty()) 
			{
				StringBuilder scores = new StringBuilder();
				for (Player player : players) {
					scores.append(player.toString());
				}
				String finalScores = scores.toString();
				JOptionPane.showMessageDialog(null, finalScores);
			}
		}
	}
	
	private class solvedButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Player thePlayer = players.get(playerIndex);
			int points = thePlayer.getPoints();
			points = points + card.getPoints();
			thePlayer.setPoints(points);
			players.set(playerIndex, thePlayer);
			questionFrame.dispose();
			if ((playerIndex + 1) == players.size())
				playerIndex = 0;
			else 
				playerIndex++;
		}
	}
	
	private class failButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			questionFrame.dispose();
			if ((playerIndex + 1) == players.size())
				playerIndex = 0;
			else 
				playerIndex++;
		}
	}
	
	private class answerButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JOptionPane.showMessageDialog(null, card.getAnswer());
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
