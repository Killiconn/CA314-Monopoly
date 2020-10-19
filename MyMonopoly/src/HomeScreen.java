import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HomeScreen extends HttpServlet
{
	public static String timeLength;
	public static String numPlayers;
	//public static final String timeLength = null;
	String mainMessage = "Game setup here";


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {

        RequestDispatcher rd = request.getRequestDispatcher("setup.html");
        rd.forward(request, response);
        
        String noOfPlayers = request.getParameter("No.OfPlayers");
        String lengthOfTime = request.getParameter("LengthOfTime");
        
        System.out.println(noOfPlayers);
        System.out.println(lengthOfTime);


        //Board board = new Board();
        // args will contain two things, 1. How many players are playing 2. How many minutes the game will run for
        
        numPlayers = noOfPlayers;
        timeLength = lengthOfTime;
        //board.begin(Integer.parseInt(numPlayers), Integer.parseInt(timeLength));
        //System.out.println(board.numTiles());
        
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
//    {
//        // TODO Auto-generated method stub
//        doGet(request, response);
//        
//        RequestDispatcher rd = request.getRequestDispatcher("setup.html");
//       // rd.forward(request, response);
//        
//        String noOfPlayers = request.getParameter("No.OfPlayers");
//        String lengthOfTime = request.getParameter("LengthOfTime");
//        
//        System.out.println(noOfPlayers);
//        System.out.println(lengthOfTime);
//
//        //Board board = new Board();
//        // args will contain two things, 1. How many players are playing 2. How many minutes the game will run for
//        
//        
//        numPlayers = noOfPlayers;
//        timeLength = lengthOfTime;
//        //board.begin(Integer.parseInt(numPlayers), Integer.parseInt(timeLength));
//        //System.out.println(board.numTiles());
//        
//        rd = request.getRequestDispatcher("ChooseToken.html");
//    }
}
