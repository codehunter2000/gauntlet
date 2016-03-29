package edu.miracosta.comm106.gauntlet;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

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
	private static final String physicalPath = "Gauntlet/edu/miracosta/comm106/gauntlet/cards/physical.dat";
	private static final String emotionalPath = "Gauntlet/edu/miracosta/comm106/gauntlet/cards/emotion.dat";
	private static final String mentalPath = "Gauntlet/edu/miracosta/comm106/gauntlet/cards/mental.dat";
	private static final String challengePath = "Gauntlet/edu/miracosta/comm106/gauntlet/cards/challenge.dat";
	private int points;
	private String catagory;
	private String question;
	private String answer;
	private String challenge;
	private static File physicalQ, emotionalQ, mentalQ, challengeQ;
	private JPanel panel = new JPanel();
	private static LinkedList<Gauntlet_Cards> physicalCards, emotionalCards, mentalCards, challengeCards;
	
	public Gauntlet_Cards()
	{
		points = 0;
		catagory = null;
		question = null;
		answer = null;
		challenge = null;
		physicalCards = new LinkedList<>();
		emotionalCards = new LinkedList<>();
		mentalCards = new LinkedList<>();
		challengeCards = new LinkedList<>();
		physicalQ = new File(physicalPath);
		emotionalQ = new File(emotionalPath);
		mentalQ = new File(mentalPath);
		challengeQ = new File(challengePath);
	}
	
	public Gauntlet_Cards(String cat, String q, String answ, int points)
	{
		this.points = points;
		catagory = cat;
		question = q;
		answer = answ;
	}
	
	public Gauntlet_Cards(String chall, int points)
	{
		this.points = points;
		challenge = chall;
	}
	
	public void startUp()
	{
		boolean filesExist = checkIfFilesExist();
		if (filesExist == false)
			createDataFiles();
		generateCards();
		if (physicalCards.isEmpty())
			JOptionPane.showMessageDialog(panel, "No physical cards!", "Error", 
					JOptionPane.ERROR_MESSAGE);
		if (emotionalCards.isEmpty())
			JOptionPane.showMessageDialog(panel, "No emotional cards!", "Error", 
					JOptionPane.ERROR_MESSAGE);
		if (mentalCards.isEmpty())
			JOptionPane.showMessageDialog(panel, "No mental cards!", "Error", 
					JOptionPane.ERROR_MESSAGE);
		if (challengeCards.isEmpty())
			JOptionPane.showMessageDialog(panel, "No challenge cards!" , "Error", 
					JOptionPane.ERROR_MESSAGE);
		
	}
	
	public boolean checkIfFilesExist()
	{
		boolean exist = false;
		int total = 0;
		if (physicalQ.exists())
			total++;
		if (emotionalQ.exists())
			total++;
		if (mentalQ.exists())
			total++;
		if (challengeQ.exists())
			total++;
		if (total == 4)
			exist = true;
		
		return exist;
	}
	
	public boolean createDataFiles()
	{
		boolean exists = false;
		
		if (!physicalQ.exists())
		{
			try 
			{
				physicalQ.createNewFile();
			} 
			
			catch (IOException e) 
			{
				System.out.println("Problem creating file.");
			}
		}
		
		if (!emotionalQ.exists())
		{
			try
			{
				emotionalQ.createNewFile();
			}
			
			catch (IOException e)
			{
				System.out.println("Problem creating file.");
			}
		}
		
		if (!mentalQ.exists())
		{
			try 
			{
				mentalQ.createNewFile();
			}
			
			catch (IOException e)
			{
				System.out.println("Problem creating file.");
			}
		}
		
		if (!challengeQ.exists())
		{
			try
			{
				challengeQ.createNewFile();
			}
			
			catch (IOException e)
			{
				System.out.println("Problem creating file.");
			}
		}
		
		exists = checkIfFilesExist();
		
		return exists;
	}
	
	public boolean checkIfFilesAreEmpty()
	{
		boolean empty = false;
		int total = 0;
		
		try 
		{
			Scanner checkP = new Scanner(physicalQ);
			Scanner checkE = new Scanner(emotionalQ);
			Scanner checkM = new Scanner(mentalQ);
			Scanner checkC = new Scanner(challengeQ);
			if (!checkP.hasNext())
				total++;
			if (!checkE.hasNext())
				total++;
			if (!checkM.hasNext())
				total++;
			if (!checkC.hasNext())
				total++;
			if (total != 0)
				empty = true;
			checkP.close();
			checkE.close();
			checkM.close();
			checkC.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("File Not Found");
		}
		
		return empty;
	}
	
	public boolean checkIfEmpty(File dataFile)
	{
		boolean isEmpty = false;
		try 
		{
			Scanner check = new Scanner(dataFile);
			if (!check.hasNextLine())
				isEmpty = true;
			check.close();
		} 
		
		catch (FileNotFoundException e) 
		{
		
		}
		
		return isEmpty;
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
			ObjectInputStream physical = new ObjectInputStream(new FileInputStream(physicalPath));
			ObjectInputStream emotional = new ObjectInputStream(new FileInputStream(emotionalPath));
			ObjectInputStream mental = new ObjectInputStream(new FileInputStream(mentalPath));
			ObjectInputStream challenge = new ObjectInputStream(new FileInputStream(challengePath));

			try
			{
				while (!eof)
				{
					try 
					{
						Gauntlet_Cards element = (Gauntlet_Cards) physical.readObject();
						physicalCards.add(element);
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
				
				physical.close();
				eof = false;
				
				while (!eof)
				{
					try 
					{
						Gauntlet_Cards element = (Gauntlet_Cards) emotional.readObject();
						emotionalCards.add(element);
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
				
				emotional.close();
				eof = false;
				
				while (!eof)
				{
					try 
					{
						Gauntlet_Cards element = (Gauntlet_Cards) mental.readObject();
						mentalCards.add(element);
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
				
				mental.close();
				eof = false;
				
				while (!eof)
				{
					try 
					{
						Gauntlet_Cards element = (Gauntlet_Cards) challenge.readObject();
						challengeCards.add(element);
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
				challenge.close();
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
	    	points = JOptionPane.showInputDialog("Please enter the amount of points for this cards");
	    	try
	    	{
	    		totalPoints = Integer.parseInt(points);
	    		Gauntlet_Cards newCard = new Gauntlet_Cards(input, question, answer, totalPoints);
		    	mentalCards.add(newCard);
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
	    		Gauntlet_Cards newCard = new Gauntlet_Cards(challenge, totalPoints);
	    		if (input == c)
	    			challengeCards.add(newCard);
	    		else if (input == e)
	    			emotionalCards.add(newCard);
	    		
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
	    		Gauntlet_Cards newCard = new Gauntlet_Cards(strength, totalPoints);
	    		physicalCards.add(newCard);
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
			ObjectOutputStream physical = new ObjectOutputStream(new FileOutputStream(physicalQ));
			ObjectOutputStream emotional = new ObjectOutputStream(new FileOutputStream(emotionalQ));
			ObjectOutputStream mental = new ObjectOutputStream(new FileOutputStream(mentalQ));
			ObjectOutputStream challenge = new ObjectOutputStream(new FileOutputStream(challengeQ));
			
			if (physicalQ.isFile())
			{
				for (Gauntlet_Cards gauntlet_Cards : physicalCards) 
					{
						physical.writeObject(gauntlet_Cards);
					}
			}
			
			if (emotionalQ.isFile())
			{
				for (Gauntlet_Cards gauntlet_Cards : emotionalCards) 
					{
						emotional.writeObject(gauntlet_Cards);
					}
			}
			
			if (mentalQ.isFile())
			{
				for (Gauntlet_Cards gauntlet_Cards : mentalCards) 
					{
						mental.writeObject(gauntlet_Cards);
					}
			}
			
			if (challengeQ.isFile())
			{

				for (Gauntlet_Cards gauntlet_Cards : challengeCards) 
				{
					challenge.writeObject(gauntlet_Cards);
				}
			}
			
			physical.close();
			emotional.close();
			mental.close();
			challenge.close();
		}
		
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(panel, "Problem with file!", "Error", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public void showAllCards()
	{
		for (Gauntlet_Cards gauntlet_Cards : physicalCards) 
		{
			System.out.println("\n" + gauntlet_Cards.challenge + "\n"
					+ gauntlet_Cards.points + "\n");
		}
		for (Gauntlet_Cards gauntlet_Cards : emotionalCards) 
		{
			System.out.println("\n" + gauntlet_Cards.challenge + "\n"
					+
					gauntlet_Cards.points + "\n");			
		}
		for (Gauntlet_Cards gauntlet_Cards : mentalCards) {
			System.out.println("\n" + gauntlet_Cards.question + "\n"
					+ "\n" + gauntlet_Cards.answer + "\n" + 
					gauntlet_Cards.points);
		}
		for (Gauntlet_Cards gauntlet_Cards : challengeCards) {
			System.out.println("\n" + gauntlet_Cards.challenge
					+ "\n" + gauntlet_Cards.points);
		}
	}
	
	public Gauntlet_Cards getPhysicalCard()
	{
		Gauntlet_Cards card = null;
		Random randomCard = new Random();
		int index = randomCard.nextInt(physicalCards.size());
		card = physicalCards.get(index);		
		return card;
	}
	
	public Gauntlet_Cards getEmotionalCard()
	{
		Gauntlet_Cards card = null;
		Random randomCard = new Random();
		int index = randomCard.nextInt(emotionalCards.size());
		card = physicalCards.get(index);
		return card;
	}
	
	public Gauntlet_Cards getMentalCard()
	{
		Gauntlet_Cards card = null;
		Random randomCard = new Random();
		int index = randomCard.nextInt(mentalCards.size());
		card = mentalCards.get(index);
		return card;
	}
	
	public Gauntlet_Cards getChallengeCard()
	{
		Gauntlet_Cards card = null;
		Random randomCard = new Random();
		int index = randomCard.nextInt(challengeCards.size());
		card = challengeCards.get(index);
		return card;
	}
	
	
	public int getPoints()
	{
		return points;
	}
	
	public String getCatagory()
	{
		return catagory;
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
	
	public void setCatagory(String cat)
	{
		catagory = cat;
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


