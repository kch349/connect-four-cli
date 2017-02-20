
public class ConnectFour {

	public static final String ERROR_MESSAGE = "ERROR";
	public static final String SUCCESS_MESSAGE = "OK";
	public static final String WIN_MESSAGE = "WIN";
	public static final String DRAW_MESSAGE = "DRAW";
	
	// Run the Connect Four game.
	public static void main (String []args) {
		
		
	}
	
	// Handle case where wrong input. Prompt user to input one of following commands and give list and info
	// If there are other arguments, give warning that program does not take other arguments
	// Need to check if there are connections... just a check method?
	// Have a board object that keeps track of state. check to see how things are ran with main methods otherwise.
	// do pieces need to have an object? keep track of position and player.
	// or just have a board with a matrix of 0s 1s and 2s.
	
	// is it best to store as a matrix?
	// Better as a single list array, accessible by index? like a heap in an array.
	// Can look at ever 1st, every 2nd, every 3rd, every 4th for the columns. Every chunk of 4 for the rows.
	// And for diagonals, need 11, 22, 33, 44, and 14, 23, 32, 41.
	// board could have a conversion function from coordinates to flattened coordinates
	// board could have a check for horizontals
	// check for verticals
	// check for diagonals
	// if any, return win.
	// can easily check if a piece is already there with indices.
	
	// Undefined, should players be able to keep playing after someone has won?
	
	// unit tests for:
	// when piece already present
	// when added too many per column
  // when add one that makes player win horizontally
	// win vertically
	// win horizontally
	// same 3 for player 2 rather than player 3
	// make one of those arrangements but not all of the same player number
	// get list when no moves made
	// get list after a few
	// get list after several
	// get list after winning accomplished (should the game just end then? It should end when you win I think)
	// show board when no plays
	// show board when some
	// show board when someone wins?
	
}
