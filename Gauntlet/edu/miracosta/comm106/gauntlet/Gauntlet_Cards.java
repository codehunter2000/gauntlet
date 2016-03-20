package edu.miracosta.comm106.gauntlet;

import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Gauntlet_Cards implements Serializable
{
	
	private static final long serialVersionUID = -1252346999228244761L;
	private static final String physicalPath = "cards/physical.dat";
	private static final String emotionalPath = "cards/emotion.dat";
	private static final String mentalPath = "cards/mental.dat";
	private static final String challengePath = "cards/challenge.dat";
	private String catagory;
	private String question;
	private String answer;
	private String challenge;
	private File physicalQ, emotionalQ, mentalQ, challengeQ;
	private JPanel panel = new JPanel();
	private LinkedList<Gauntlet_Cards> physicalCards, emotionalCards, mentalCards, challengeCards;
	
	public Gauntlet_Cards()
	{
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
	
	public Gauntlet_Cards(String cat, String q, String answ)
	{
		catagory = cat;
		question = q;
		answer = answ;
	}
	
	public Gauntlet_Cards(String chall)
	{
		challenge = chall;
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
			if (!checkP.hasNextLine())
				total++;
			if (!checkE.hasNextLine())
				total++;
			if (!checkM.hasNextLine())
				total++;
			if (!checkC.hasNextLine())
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
			JOptionPane.showMessageDialog(panel, "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
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
		String question, answer, challenge;
	    String input = (String) JOptionPane.showInputDialog(null, "Please select a category",
	        "Create Card", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	    if (input == p || input == e || input == m)
	    {
	    	question = JOptionPane.showInputDialog("Please enter the question");
	    	answer = JOptionPane.showInputDialog("Please enter the answer");
	    	Gauntlet_Cards newCard = new Gauntlet_Cards(input, question, answer);
	    	if (input == p)
	    		physicalCards.add(newCard);
	    	if (input == e)
	    		emotionalCards.add(newCard);
	    	if (input == m)
	    		mentalCards.add(newCard);
	    }
	    
	    if (input == c)
	    {
	    	challenge = JOptionPane.showInputDialog("Please enter the challenge");
	    	Gauntlet_Cards newCard = new Gauntlet_Cards(challenge);
	    	challengeCards.add(newCard);
	    }
	}
	
	public void saveCards()
	{
		boolean exists = checkIfFilesExist();
		boolean physicalEmpty = checkIfEmpty(physicalQ);
		boolean emotionalEmpty = checkIfEmpty(emotionalQ);
		boolean mentalEmpty = checkIfEmpty(mentalQ);
		boolean challengeEmpty = checkIfEmpty(challengeQ);
		
		if (exists == false)
			createDataFiles();
		if (physicalQ.isFile() == true && physicalEmpty == false)
		{
			
		}
		
		
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


