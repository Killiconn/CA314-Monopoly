import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StartGameRunner
 */
//@WebServlet("/GameRunning")
public class StartGameRunner extends HttpServlet 
{
	private String htmlDoc;
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException
	{
	
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Board board = new Board();
		int time = Integer.parseInt(HomeScreen.timeLength);
		board.setTime(time);

		Player[] players;
		try 
		{
			players = board.begin(Integer.parseInt(HomeScreen.numPlayers), time);
		} 
		catch (InterruptedException e) 
		{
			players = new Player[] {new Player("Bob")};
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		response.setContentType("text/html");
	      // Writing message to the web page
	    PrintWriter out = response.getWriter();
		// TODO Auto-generated method stub
		htmlDoc = "<!DOCTYPE html>\n";
		htmlDoc += "<html>\n";
		htmlDoc += "\t<head>\n";
		htmlDoc += "\t\t<meta charset = 'UTF-8'>\n";
		htmlDoc += "\t\t<link rel = 'stylesheet' href = './webApp.css'>\n";
		htmlDoc += "\t\t<script type = 'text/javascript' src = './setup.js'></script>\n";
		htmlDoc += "\t\t<title>Monopoly</title>\n";
		htmlDoc += "\t</head>\n";
		htmlDoc += "\t<body class = 'GameBackground'>\n";
		for (int i = 1; i <= Integer.parseInt(HomeScreen.numPlayers); i++)
		{
			htmlDoc += "\t\t<section class = 'Player" + i + "'>\n";
			htmlDoc += "\t\t\t<span id = 'name" + i + "'></span>\n";
			htmlDoc += "\t\t\t<p class = 'money'>$" + players[i - 1].getBalance() + "</p>\n";
			htmlDoc += "\t\t\t<p class = 'OwnedProp'>" + players[i - 1].ownedProp + "</p>\n";
			htmlDoc += "\t\t\t<script>enterNames(" + i + ");</script>\n";
			htmlDoc += "\t\t</section>\n";
		}
		htmlDoc += "\t\t<div class = 'split left'>\n";
		htmlDoc += "\t\t\t<section class = 'mainButtons'>\n";
		htmlDoc += "\t\t\t\t<p>\n\t\t\t\t\t<button class = 'Neutral' type = 'button'>Roll Dice</button>\n\t\t\t</p>\n";
		htmlDoc += "\t\t\t\t<p>\n\t\t\t\t\t<button class = 'GreenButton' type = 'button'>Buy Property</button>\n\t\t\t\t</p>\n";
		htmlDoc += "\t\t\t\t<p>\n\t\t\t\t\t<button class = 'Redbutton' type = 'button'>Mortgage Property</button>\n\t\t\t\t</p>\n";
		htmlDoc += "\t\t\t</section>\n";
		htmlDoc += "\t\t</div>\n";

		try {
			board.startTimer();

			int j = 0;
			while (board.timerStopped())
			{
				for (int i = 1; i <= Integer.parseInt(HomeScreen.numPlayers); i++)
				{
					board.oneTurn();
					htmlDoc += "\t\t<section class = 'Player" + i + "'>\n";
					htmlDoc += "\t\t\t<span id = 'name" + i + "'>Player " + i + "</span>\n";
					htmlDoc += "\t\t\t<p class = 'money'>$" + players[i - 1].getBalance() + "</p>\n";
					htmlDoc += "\t\t\t<p class = 'OwnedProp'>" + players[i - 1].ownedProp.size() + " Properties Owned</p>\n";
					htmlDoc += "\t\t</section>\n";
				}
				j ++;
				board.endGame();
			}
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		htmlDoc += "\t</body>\n";
		htmlDoc += "</html>";
		
		

	    out.println(htmlDoc);
	    System.out.println(htmlDoc);
		//Board board = new Board();
		//board.begin(Integer.parseInt(HomeScreen.numPlayers), Integer.parseInt(HomeScreen.timeLength));
        //System.out.println(board.numTiles());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void destroy() 
	{
      /* leaving empty for now this can be
	       * used when we want to do something at the end
	       * of Servlet life cycle
	       */
	}

}
