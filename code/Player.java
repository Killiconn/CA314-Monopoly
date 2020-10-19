import java.util.ArrayList;

public class Player
{
	String name;
	int position;
	int bank;
	ArrayList<Property> ownedProp;
	Boolean jailStatus;

	public Player(String name)
	{
		this.name = name;
		this.position = 0;
		this.bank = 1500;
		this.ownedProp = new ArrayList<Property>();
		this.jailStatus = false;
	}

	public void changePosition(int diceResult)
	{
		//check if player is in jail
		if (this.getJailStatus() != true)
		{
			this.position = (this.position + diceResult) % 32;
		}
		else
		{	//skip one turn 
			this.setJailStatus(false);
		}
	}

	public void changeBank(int change)
	{
		//players balance does not increase while they're in jail
		if (this.getJailStatus() == false)
		{

			int proposedBalance = this.getBalance() + change;
			if (proposedBalance < 0)
			{
				//check if mortgaging is possible
				if ((this.ownedProp.size() > 0) && !(this.ownedProp.get(0).rent == 0))
				{
					//mortgage
					this.mortgageProps();

					this.bank = this.bank + change;
				}

				else
				{
					this.bank = this.bank + change;
					this.unmortgageProps();

				}

			}

			else
			{
				this.bank = this.bank + change;
			}
		}
	}

	public int getBalance()
	{
		return this.bank;
	}

	public void mortgageProps()
	{
		for (Property prop: ownedProp)
		{
			this.bank = this.bank + (prop.getPrice() / 2);
			//balance return int
			// call Property.mortgage()
			prop.mortgage();
		}
	}

	public void unmortgageProps()
	{
		for (Property prop : ownedProp)
		{
			prop.availableAgain();
		}
	}

	public void addProperty(Property prop)
	{
		this.ownedProp.add(prop);
	}

	public void setJailStatus(Boolean bool)
	{
		this.jailStatus = bool;
	}

	public Boolean getJailStatus()
	{
		return this.jailStatus;
	}

}