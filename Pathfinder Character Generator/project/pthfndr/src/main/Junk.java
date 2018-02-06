package pthfndr.src.main;

public class Junk {
	class meh // class made for testing ideas
	{
		int x;
		meh(int x)
		{
			this.value(x);
		}
		public void value(int x)
		{
			this.x = x;
		}
	}
	public meh[] test = {new meh(1),new meh(2),new meh(3),new meh(4)};
	public void testShorten(meh[] x)
	{
		int countNotNull = 0;
		for(int i = 0; i < x.length; i++)
		{
			if (x[i] != null)
			{
				countNotNull ++;
			}
		}
		meh[] hold = new meh[countNotNull];
		int indexHold = 0;
		for(int i = 0; i < x.length; i++)
		{
			if (x[i] != null)
			{
				hold[indexHold] = x[i];
				indexHold++;
				
			}
		}
		 hold;
	}
	
	public static int factoral(int x)
	{
		int sum = 0;
		for (int i = 0; i < x; i++)
		{
			sum += x - i;
		}
		return sum;
	}
	public static void error()
	{
		System.out.println("If you can read this somthing went wrong");
	}
	public static void tally()
	{
	int tally12 = 0, tally2d6 = 0, maxRoll12 = 0, maxRoll2d6 = 0;
		
		for (int j = 0; j < 100; j ++)
		{
			int sum12 = 0, sum2d6=0;
			for(int i = 0; i < 100; i ++)
			{
				int roll12 = Roll.numDsize(1, 12);
				int roll2d6 = Roll.numDsize(2, 6);
				sum12 += roll12;
				sum2d6 += roll2d6;
				if(roll12 == 12)
				{
					maxRoll12++;
				}
				if(roll2d6 == 12)
				{
					maxRoll2d6++;
				}
			}
			System.out.println("sum12 = " + sum12);
			System.out.println("sum2d6 = " + sum2d6);
			if (sum12 > sum2d6)
			{
				tally12 ++;
			}
			else
			{
				tally2d6 ++;
			}
		}
		System.out.println("Tally 12 = " + tally12 + " MAX ROLL TIMES = " + maxRoll12);
		System.out.println("Tally 2d6 = " + tally2d6 + " MAX ROLL TIMES = " + maxRoll2d6);
	}

	{
	int [] hold = Race.humanBonus(Stat.INT);
	for (int i = 0; i < hold.length; i++)
	{
		System.out.print(hold[i] + " ");
	}
	}
	public static void main(String[] args)
	{
		
	}

}
		



