import java.util.Random;

public class Dice
{
	int outcome1;
	int outcome2;
	Random rand;

	public Dice()
	{
		this.rand = new Random();
	}

	public int roll()
	{
		this.outcome1 = this.rand.nextInt(6) + 1;
		this.outcome2 = this.rand.nextInt(6) + 1;
		return this.outcome1 + this.outcome2;
	}


}