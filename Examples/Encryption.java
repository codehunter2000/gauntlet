import java.util.Date;

public class Encryption 
{
	private static final String TEST = "Hello World";
	private static int devisor;
	private static Date dt = new Date();
	
	
	public static void main(String[] args)
	{
		System.out.println(TEST);
	}
	
	public static String encrypt(String stuff)
	{
		String encrypted = stuff;
		byte[] nums = encrypted.getBytes();
		devisor = nums.length;
		
		return encrypted;
	}
	
	public static byte shiftValue(byte value, int index)
	{
		byte toReturn = value;
		
		if (index == 0)
		{
			
		}
		
		if (index == 1)
		{
			
		}
		if (index == 2)
		{
			
		}
		if (index == 3)
		{
			
		}
		if (index == 4)
		{
			
		}
		if (index == 5)
		{
			
		}
		if (index == 6)
		{
			
		}
		if (index == 7)
		{
			
		}
		if (index == 8)
		{
			
		}
		if (index == 9)
		{
			
		}
		
		
		
		return toReturn;
	}
}
