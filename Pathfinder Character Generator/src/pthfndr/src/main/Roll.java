package pthfndr.src.main;
import java.lang.Math;
//class for generating random numbers and stats//
public class Roll { 
	//the d method is for rolling dice of a specific size//
	public static int d(int size)
	{
		return (int) (Math.random() * size + 1);
	}
	// method for creating one stat roll for a player character//
	public static int PCstatRoll()
	{
		int stats[] = {d(6),d(6),d(6),d(6)};
		
		int sum = 0;
		for (int i = 0; i < 4; i ++)
		{
			sum += stats[i];
		}
		
		int least = 6; // holds the lowest value number
		for (int i = 0; i < 4; i ++)
		{
			if(stats[i] < least)
			{
				least = stats[i];
			}
		}
		
		return sum - least; //returns the highest three rolls
	}
	//method for creating a single Non Player Character stat roll//
	public static int NPCstatRoll()
	{
		return d(6) + d(6) + d(6);
	}
	
	public static int minnimum(int value, int min)
	{
		if (value < min)
			return min;
		return value;
	}
	public static int numDsize(int num, int size)
	{
		int sum = 0;
		if ( num > 0)
		{
			for(int i = 0; i < num; i++)
			{
				sum += d(size);
			}
		}
		return sum;
	}
	
	public static String random (String[] hold)
	{
		return hold[d(hold.length)-1];
	}

	public static void main(String[] args) {
		for( int i = 0; i <= 100; i++)
		System.out.println(Roll.NPCstatRoll());
	}

}
