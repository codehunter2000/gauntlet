package edu.miracosta.comm106.gauntlet;

import java.io.Serializable;

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
