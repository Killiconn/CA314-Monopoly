/**
 * 
 */



function showInput() {
	
	var messageToConfirm;
	var input;
	var playersEntered = "Nothing!";
	var timeEntered = "Nothing!";

	
    document.getElementById('display').innerHTML = document.getElementById("players").value;
    document.getElementById('display2').innerHTML = document.getElementById("time").value;
    
    if (document.getElementById("players").value != "" && document.getElementById("time").value != "")
    {
    	playersEntered = "This is how many players have been entered: ";
    	timeEntered = "This is the duration of the game (in minutes): ";
    	messageToConfirm = "Happy with your input? Press 'Submit Details' below and then 'Start Game' to begin the game";	
    	submitDetailsFunction();
   	}
    else
    {
    	messageToConfirm = "Please Enter a number!, Try again";	
    }
    document.getElementById("playerEntered").innerHTML = playersEntered;
    document.getElementById("timeEntered").innerHTML = timeEntered;
    document.getElementById("msg").innerHTML = messageToConfirm;
    
    
    input = "Your Input:";
    document.getElementById("input").innerHTML = input;

}

function submitDetailsFunction(){
    document.getElementById("buttonAppear").innerHTML = '<input class = "SubmitDetails" type = "submit" value = "Submit Details">';
}

function enterNames(arg){
	
	var num = arg.toString();
	var person; 
	var name;
	person = prompt("Please enter your name Player" + num, "Name here!");
	name = person;
	document.getElementById("name" + num).innerHTML = name;

}



