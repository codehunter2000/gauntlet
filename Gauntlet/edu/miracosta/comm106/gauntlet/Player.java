package edu.miracosta.comm106.gauntlet;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Player implements Serializable
{
	private static final long serialVersionUID = 7794664838111510611L;
	private String userName;
	private int points;
	
	public Player()
	{
		userName = null;
		points = 0;
	}
	
	public Player(String name, int points)
	{
		userName = name;
		this.points = points;
	}
	
	public Player addNewPlayer()
	{
		String name = JOptionPane.showInputDialog("Enter a name to identify yourself");
		JOptionPane.showMessageDialog(null, "Welcome to the game " + name + " !");
		Player newPlayer = new Player(name, 0);
		return newPlayer;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public int getPoints()
	{
		return points;
	}
	
}
