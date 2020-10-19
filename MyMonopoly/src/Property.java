import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

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

    public void getBought(Player currentPlayer) throws IOException
    {
        //System.out.println("Would you like to buy this property? [Yes/No]");
        if (currentPlayer.getBalance() > this.price)
        {
        	optionToBuy(currentPlayer);
        }
    	
//        HttpServletResponse response = null;
//        String yes = "yes";
//        String no = "no";
//	    PrintWriter out = response.getWriter();
//
//        while (true)
//        {
//            if (out.equals(yes) && currentPlayer.getBalance() > this.price)
//            {
//                optionToBuy(currentPlayer);
//                break;
//            }
//            else if (out.equals(no))
//            {
//                break;
//            }
//            else if (!(out.equals(yes) || out.equals(no)))
//            {
//                out.println("Would you like to buy this property? [Yes/No] - Please enter Yes or No");
//            }
//            else
//            {
//                out.println("You do not have enough funds!");
//                break;
//            }
//        }
//        

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
        } else
			try {
				getBought(currentPlayer);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
