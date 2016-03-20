package edu.miracosta.comm106.gauntlet;

import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

public class Gauntlet_Cards implements Serializable
{
	
	private static final long serialVersionUID = -1252346999228244761L;
	private String catagory;
	private String question;
	private String answer;
	private File physicalQ, emotionalQ, mentalQ, challengeQ;
	private LinkedList<Gauntlet_Cards> physical, emotional, mental, challenge;
	
	public Gauntlet_Cards()
	{
		catagory = null;
		question = null;
		answer = null;
		physical = new LinkedList<>();
		emotional = new LinkedList<>();
		mental = new LinkedList<>();
		challenge = new LinkedList<>();
		physicalQ = new File("cards/physical.dat");
		emotionalQ = new File("cards/emotional.dat");
		mentalQ = new File("cards/mental.dat");
		challengeQ = new File("cards/challenge.dat");
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
		boolean empty = true;
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
