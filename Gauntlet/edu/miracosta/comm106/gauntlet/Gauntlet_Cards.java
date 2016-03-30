package edu.miracosta.comm106.gauntlet;

import java.util.LinkedList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Gauntlet_Cards implements Serializable
{
	private static final long serialVersionUID = -1252346999228244761L;
	private static final String cardsPath = "Gauntlet/edu/miracosta/comm106/gauntlet/cards/cards.dat";
	private int points;
	private String catagory;
	private String question;
	private String answer;
	private String challenge;
	private File cards;
	private JPanel panel = new JPanel();
	private LinkedList<Gauntlet_Cards> totalCards;
	
	public Gauntlet_Cards()
	{
		points = 0;
		question = null;
		answer = null;
		challenge = null;
		totalCards = new LinkedList<>();
		cards = new File(cardsPath);
	}
	
	public Gauntlet_Cards(String cat, String q, String answ, int points)
	{
		catagory = cat;
		this.points = points;
		question = q;
		answer = answ;
	}
	
	public Gauntlet_Cards(String cat, String chall, int points)
	{
		catagory = cat;
		this.points = points;
		challenge = chall;
	}
	
	public void startUp()
	{
		boolean filesExist = checkIfFilesExist();
		if (filesExist == false)
			createDataFiles();
		generateCards();
	}
	
	public boolean checkIfFilesExist()
	{
		boolean exist = false;
		if (cards.isFile())
			exist = true;
		
		return exist;
	}
	
	public boolean createDataFiles()
	{
		boolean exists = false;
		
		try
		{
			if (!cards.isFile())
				cards.createNewFile();
		}
		
		catch (IOException e) 
		{
			System.out.println("Problem creating file.");
		}
		
		exists = checkIfFilesExist();
		
		return exists;
	}
	
	public boolean checkIfFilesAreEmpty()
	{
		boolean empty = false;

		try 
		{
			if (cards.length() == 0)
				empty = true;
			else if (cards.length() != 0)
				empty = false;
		} 
		
		catch (SecurityException e)
		{
			JOptionPane.showMessageDialog(panel, "Problem with file!", "Error", JOptionPane.ERROR_MESSAGE);	
		}
		
		return empty;
	}
	
	
	public void generateCards()
	{
		final boolean checkFilesExist = checkIfFilesExist();
		final boolean checkIfEmpty = checkIfFilesAreEmpty();
		boolean eof = false;
		if (checkFilesExist == false)
			createDataFiles();
		if (checkIfEmpty == true)
			JOptionPane.showMessageDialog(panel, "Some of the files are empty! Please fill them."
					, "Error", JOptionPane.ERROR_MESSAGE);
		try
		{
			ObjectInputStream cardInput = new ObjectInputStream(new FileInputStream(cards));

			try
			{
				while (!eof)
				{
					try 
					{
						Gauntlet_Cards element = (Gauntlet_Cards) cardInput.readObject();
						totalCards.add(element);
					}
					catch (EOFException e)
					{
						eof = true;
					}
					catch (ClassNotFoundException e) 
					{
						JOptionPane.showMessageDialog(panel, "Problem reading file!", "Error", JOptionPane.ERROR_MESSAGE);

					}
					catch (FileNotFoundException e)
					{
						JOptionPane.showMessageDialog(panel, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);

					}
					catch (IOException e)
					{
						JOptionPane.showMessageDialog(panel, "Problem reading file!", "Error", JOptionPane.ERROR_MESSAGE);

					}
				}
				
				cardInput.close();
				
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(panel, "Problem with file!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		catch (FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(panel, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(panel, "Problem with file!", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void createCards()
	{
		final String p = "Physical";
		final String e = "Emotional";
		final String m = "Mental";
		final String c = "Challenge";
		final String[] choices = {p, e, m, c};
		String question, answer, challenge, points, strength;
		int totalPoints;
		
	    String input = (String) JOptionPane.showInputDialog(null, "Please select a category",
	        "Create Card", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	    if (input == m)
	    {
	    	question = JOptionPane.showInputDialog("Please enter the question");
	    	answer = JOptionPane.showInputDialog("Please enter the answer");
	    	try
	    	{
	    		points = JOptionPane.showInputDialog("Please enter the amount of points for this cards");
	    		totalPoints = Integer.parseInt(points);
	    		Gauntlet_Cards newCard = new Gauntlet_Cards(question, answer, totalPoints);
		    	totalCards.add(newCard);
	    	}
	    	catch (NumberFormatException x)
	    	{
	    		JOptionPane.showMessageDialog(panel, "Number must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	    
	    if (input == c || input == e)
	    {
	    	challenge = JOptionPane.showInputDialog("Please enter the challenge");
	    	
	    	try
	    	{
	    		points = JOptionPane.showInputDialog("Please enter the points for this card.");
	    		totalPoints = Integer.parseInt(points);
	    		Gauntlet_Cards newCard = new Gauntlet_Cards(input, challenge, totalPoints);
	    		totalCards.add(newCard);
	    		
	    	}
	    	catch (NumberFormatException y)
	    	{
	    		JOptionPane.showMessageDialog(panel, "Number must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	    
	    if (input == p)
	    {
	    	strength = JOptionPane.showInputDialog("Please enter the physical challenge");
	    	points = JOptionPane.showInputDialog("Please enter the number of points for this challenge");
	    	
	    	try 
	    	{
	    		totalPoints = Integer.parseInt(points);
	    		Gauntlet_Cards newCard = new Gauntlet_Cards(input, strength, totalPoints);
	    		totalCards.add(newCard);
	    	}
	    	
	    	catch (NumberFormatException steve)
	    	{
	    		JOptionPane.showMessageDialog(panel, "Number must be an integer!", "Error", JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	    JOptionPane.showMessageDialog(null, "The program must be restarted before "
	    		+ "the new cards can be used. Please restart now.");
	    	
	    
	    saveCards();
	}
	
	public void saveCards()
	{
		
		try
		{
			ObjectOutputStream cardOut = new ObjectOutputStream(new FileOutputStream(cards));
			
			if (cards.isFile())
			{
				for (Gauntlet_Cards theCards : totalCards) 
					{
						cardOut.writeObject(theCards);
					}
			}
			
			cardOut.close();
		}
		
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(panel, "Problem with file!", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public void showAllCards()
	{
		if (totalCards.isEmpty())
			JOptionPane.showMessageDialog(panel, "List empty!", "Error", JOptionPane.ERROR_MESSAGE);
		
		for (Gauntlet_Cards theCard : totalCards) 
		{
			if (theCard.challenge != null)
			{
				System.out.println("\n" + theCard.challenge + "\n" + theCard.points + "\n");
			}
			
			else if (theCard.challenge == null)
			{
				System.out.println("\n" + theCard.question + "\n" + theCard.answer + "\n" 
			+ theCard.points + "\n");
			}
		}
		
	}
	
	public Gauntlet_Cards getCard()
	{
		Gauntlet_Cards card = null;
		Random randomCard = new Random();
		int index = randomCard.nextInt(totalCards.size());
		card = totalCards.get(index);		
		return card;
	}
	
	
	public int getPoints()
	{
		return points;
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public String getAnswer()
	{
		return answer;
	}
	
	public String getChallenge()
	{
		return challenge;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public void setQuestion (String q)
	{
		question = q;
	}
	
	public void setAnswer(String answ)
	{
		answer = answ;
	}
	
	public void setChallenge(String chall)
	{
		challenge = chall;
	}
}


