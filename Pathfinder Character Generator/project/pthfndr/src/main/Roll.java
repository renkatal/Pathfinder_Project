package pthfndr.src.main;

public class Roll {

	public static String random (String[] hold)
	{
		return hold[d(hold.length)-1];
	}
	public static int d(int x)
	{
		return (int) (Math.random()*x + 1);
	}
	
	public static int statRoll()
	{
		int stat;
		int sum = 0;
		int min = 6;
		int[] roll ={d(6),d(6),d(6),d(6)};
		for (int i= 0;i < 4; i ++)
		{
			if (roll[i] < min)
			{
				min = roll[i];
			}
			sum += roll[i]; 
			//System.out.print(roll[i] + " : "); // check roll
		}
		stat = sum - min;
		//System.out.print(stat + "\n"); // check stat
		return stat;
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
	
	public static int minnimum(int value, int min)
	{
		if (value < min)
			return min;
		return value;
	}

	
	public static void main(String[]args)
	{
		for (int i = 0; i < 10; i ++)
		System.out.println(d(20));
		statRoll();
		statRoll();
		
	}
}
