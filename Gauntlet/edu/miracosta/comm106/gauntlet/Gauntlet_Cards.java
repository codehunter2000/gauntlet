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
	private static final String challengePath = "Cards/challenge.dat";
	private String catagory;
	private String question;
	private String answer;
	private File physicalQ, emotionalQ, mentalQ, challengeQ;
	private LinkedList<Gauntlet_Cards> physicalCards, emotionalCards, mentalCards, challengeCards;
	
	public Gauntlet_Cards()
	{
		catagory = null;
		question = null;
		answer = null;
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
	
	public void createCards()
	{
		final boolean checkFilesExist = checkIfFilesExist();
		final boolean checkIfEmpty = checkIfFilesAreEmpty();
		boolean eof = false;
		final JPanel panel = new JPanel();
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
						String type = element.catagory;
						type = type.trim();
						if (type.equalsIgnoreCase("physical"))
							physicalCards.add(element);
						if (type.equalsIgnoreCase("emotional"))
							emotionalCards.add(element);
						if (type.equalsIgnoreCase("mental"))
							mentalCards.add(element);
						if (type.equalsIgnoreCase("challenge"))
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
					
				}
			}
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
}
