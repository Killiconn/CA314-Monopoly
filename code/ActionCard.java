import java.io.*;
import java.util.*;

public class ActionCard implements Tile
{
	String tile_id;
	int position;
	File chance = new File("chance.txt");
	File communityChest = new File("community.txt");
	int lineinFile;
	String [] listofChance = new String[12]; //Make a list of all outcomes in the file
	String [] listofCommunity = new String[12];//List of all possible outcomes for community
	
	Random rand = new Random();

	public ActionCard(String tile_id, int position)
	{
		this.tile_id = tile_id;
		this.position = position;

		

		try
		{
			Scanner chanceScan = new Scanner(chance); 
			
			int counter = 0;
			while (chanceScan.hasNextLine())
			{
				listofChance[counter] = chanceScan.nextLine(); //add all to list because you cant just get a random line from a file
				counter ++;
			}
			chanceScan.close();


			Scanner communityScan = new Scanner(communityChest);

			counter = 0;
			while (communityScan.hasNextLine())
			{
				listofCommunity[counter] = communityScan.nextLine();
				counter ++;
			}
			communityScan.close();
		}
		catch (FileNotFoundException ex)
		{
			System.out.println("Unable to find the file.");
		}

	}

	public void landedOn(Player player) 
	{
		if(tile_id == "chanceCard")
			chanceOutcome(player);
		else
			communityOutcome(player);
	}

	public void chanceOutcome(Player player) 
	{		
		int lineinFile = rand.nextInt(11); //12 lines in each of the files
		String randomChance = listofChance[lineinFile]; //your outcome for the GUI

		System.out.println(randomChance);

		if(lineinFile == 0)//Advance to Go (Collect $200)
		{
			player.changeBank(200);
			player.changePosition(-this.position);
		}

		else if(lineinFile == 1)//Advance to GT —If you pass Go, collect $200
		{
			player.changePosition(-this.position+26); 
			if(position > 26)
				player.changeBank(200);
		}

		else if(lineinFile == 2)//Advance to Golf – If you pass Go, collect $200
		{
			player.changePosition(-this.position+18); 
			if(position > 18)
				player.changeBank(200);
		}

		else if(lineinFile == 3)//Bank pays you dividend of $50
		{
			player.changeBank(50);
		}

		else if(lineinFile == 4)//Road tax, pay $150
		{
			player.changeBank(-150);
		}

		else if(lineinFile == 5)//Go to Jail–Go directly to Jail–Do not pass Go, do not collect $200
		{
			player.changePosition(-this.position + 8);
		}

		else if(lineinFile == 6)//Make general repairs on all your property–For each house pay $25–For each hotel $100
		{
			player.changeBank((player.ownedProp.size() * 25));
		}

		else if(lineinFile == 7)//Tank is empty, pay $150
		{
			player.changeBank(-150);
		}

		else if(lineinFile == 8)//Take a trip to Micra–If you pass Go, collect $200
		{
			player.changePosition(-this.position + 1); 
			if(position > 1)
				player.changeBank(200);
		}

		else if(lineinFile == 9)//Your building and loan matures—Collect $150
		{
			player.changeBank(150);
		}
		
		else if(lineinFile == 10)//You have won a crossword competition—Collect $100
		{
			player.changeBank(100);
		}

		else if(lineinFile == 11)//You inherit $100
		{
			player.changeBank(100);
		}
	}


	public void communityOutcome(Player player)
	{
		int lineinFile = rand.nextInt(11); //12 lines in each of the files	
		String randomCommunity = listofCommunity[lineinFile];

		System.out.println(randomCommunity);

		if(lineinFile == 0)//Advance to Go (Collect $200)
		{
			player.changeBank(200);
			player.changePosition(-this.position);
		}
		
		else if(lineinFile == 1) //Bank error in your favor -> Collect $200
		{
			player.changeBank(200);
		}

		else if(lineinFile == 2)//Doctor's fee—Pay $50
		{
			player.changeBank(-50);
		}

		else if(lineinFile == 3)//From sale of stock you get $50
		{
			player.changeBank(50);
		}

		else if(lineinFile == 4)//Go to Jail–Go directly to jail–Do not pass Go–Do not collect $200
		{
			player.changePosition(-this.position + 8);
		}

		else if(lineinFile == 5)//Holiday Fund matures—Receive $50
		{
			player.changeBank(50);
		}

		else if(lineinFile == 6)//Income tax refund–Collect $20
		{
			player.changeBank(20);
		}

		else if(lineinFile == 7)//Life insurance matures–Collect $100
		{
			player.changeBank(100);
		}

		else if(lineinFile == 8)//Pay hospital fees of $100
		{
			player.changeBank(-100);
		}

		else if(lineinFile == 9)//Pay school fees of $150
		{
			player.changeBank(-150);
		}

		else if(lineinFile == 10)//You are assessed for street repairs–$40 per house
		{
			player.changeBank(- (player.ownedProp.size() * 100));
		}

		else if(lineinFile == 11)//You have won second prize in a beauty contest–Collect $10
		{
			player.changeBank(10);
		}
	}
}