import java.util.*;
//weird
public class Property implements Tile
{
    String tile_id;
    private int price;
    PropertyGroup propertyGroup;
    Player owner;
    Boolean status = false;
    int position;
    int rent;

    public Property(String tile_id, PropertyGroup propertyGroup, int position)
    {
        this.tile_id = tile_id;
        this.propertyGroup = propertyGroup;
        this.price = this.propertyGroup.getGroupPrice();
        this.owner = owner;
        this.status = status;
        this.position = position;
        this.rent = price/5;
    }

    public void optionToBuy(Player currentPlayer)
    {
        // int balance = currentPlayer.getBalance();
        // int newBalance = balance - this.price;

        int negativeChange = (0 - this.price);
        currentPlayer.changeBank(negativeChange);

        this.status = true;
        currentPlayer.addProperty(this);
        this.owner = currentPlayer;
        checkMono();
    }

    public void getBought(Player currentPlayer)
    {
        //System.out.println("Would you like to buy this property? [Yes/No]");
        if(currentPlayer.getBalance() > this.price){optionToBuy(currentPlayer);}
        
        /*
        Scanner in = new Scanner(System.in);
        String answer = in.next();
        answer = answer.toLowerCase();
        String yes = "yes";
        String no = "no";

        while (true)
        {
            if (answer.equals(yes) && currentPlayer.getBalance() > this.price)
            {
                optionToBuy(currentPlayer);
                break;
            }
            else if (answer.equals(no))
            {
                break;
            }
            else if (!(answer.equals(yes) || answer.equals(no)))
            {
                System.out.println("Would you like to buy this property? [Yes/No] - Please enter Yes or No");
                answer = in.next();
                answer = answer.toLowerCase();
            }
            else
            {
                System.out.println("You do not have enough funds!");
                break;
            }
        }
        */

    }

    public int getPrice()
    {
        return this.price;
    }

    public void mortgage()
    {
        this.rent = 0;
    }

    public void unmortgage()
    {
        this.rent = this.price / 10;
    }

    public void landedOn(Player currentPlayer)
    {
        if (this.status == true) // true == bought, false == available
        {
            // Check if owner is in jail or not
            if(!(owner.getJailStatus()))
            {
                this.owner.changeBank(this.rent);
                
                //int otherNewBal = otherBal - this.rent;
                int negativeChange = (0 - this.rent);
                currentPlayer.changeBank(negativeChange);
            }
        }
        else
            getBought(currentPlayer);
    }

    public void checkMono()
    {
        int count = 0;
        for (Property prop : this.owner.ownedProp)
        {
            if (prop.propertyGroup == this.propertyGroup)
                count = count + 1;
        }

        if (count == 3)
            this.propertyGroup.checkMonopoly();
    }

    public void availableAgain()
    {
        this.status = false;
        this.owner = null;
        this.rent = this.price / 5;
    }
}
