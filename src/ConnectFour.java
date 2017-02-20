

import java.util.*;

public class ConnectFour {

	public static final int NUM_PLAYERS = 2;
	public static final String ERROR_MESSAGE = "ERROR";
	public static final String SUCCESS_MESSAGE = "OK";
	public static final String WIN_MESSAGE = "WIN";
	public static final String DRAW_MESSAGE = "DRAW";
	public static final String PLACE_TOKEN_COMMAND = "PUT";
	public static final String GET_MOVES_COMMAND = "GET";
	public static final String VIEW_BOARD_COMMAND = "BOARD";
	public static final String EXIT_GAME_COMMAND = "EXIT";
	private static Board gameBoard;
	private static Scanner input;
	private static int currentPlayer;
	private static int winner;
	
	// Run the Connect Four game.
	public static void main (String []args) {
		// Begin a new game. Player 1 goes first. No one has won yet.
		// Begin listening for user input
		currentPlayer = 1;
		winner = -1;
		gameBoard = new Board();
		input = new Scanner(System.in);
		
		// Start the game and continue it until user cancels program by keyboard,
		// or by typing "EXIT"
		System.out.print(">"); 				// Mark line as an input line 
		while(input.hasNextLine()) {
			// Ensure number of arguments from user is expected
			String line = input.nextLine();
			String[] tokens = line.split(" ");
			if (tokens.length > 2 || tokens.length == 0) {
				printUsage();
			} 
			
			// Process input. Ensure it is valid, and if so,
			// execute given command.
			String command = tokens[0];
			switch(command) {
			case PLACE_TOKEN_COMMAND:
				// Check for invalid input, printing usage or error message if not valid.
				if (tokens.length != 2) {
					printUsage();
					break;
				}
				int column = parseTargetColumn(tokens[1], gameBoard.getNumColumns());
				
				// If input included a valid column, command as a whole is valid.
				// First check to see if the game is already over, with a player winning
				// or a draw. If so, report this to the user and do not try to place a tile.
				if (winner > 0 || gameBoard.boardFilled()) {
					System.out.print("Sorry, the game has already finished.");
					if (winner > 0) {
						System.out.print(" Player " + winner + " has won.");
					} else {
						System.out.print(" The game was a draw.");
					}
					System.out.println(" No more tiles may be placed.");
					break;
				}

				// If the game is still on, try to place a tile for the current player.
				if (column != -1) {
					boolean success = gameBoard.placeTile(column, currentPlayer);
					if (!success) {
						System.out.println(ERROR_MESSAGE);
					} else {
						// Successfully placed a tile. Check current game state
						// and report it to the user.
						if (gameBoard.isWinState(currentPlayer)) {
							winner = currentPlayer;
							System.out.println(WIN_MESSAGE);
						} else if (gameBoard.boardFilled()) {
							System.out.println(DRAW_MESSAGE);
						} else {
							System.out.println(SUCCESS_MESSAGE);
						}
						// It is the next player's turn since currentPlayer
						// successfully placed a tile.
						currentPlayer = advanceToNextPlayer(currentPlayer);
					}
				}
				break;
			case GET_MOVES_COMMAND:
				// Check for invalid input, printing usage or error message if not valid.
				if (tokens.length != 1) {
					printUsage();
					break;
				}
				
				// Print moves made until present in this game.
				printMoves(gameBoard.getMoves());
				break;
			case VIEW_BOARD_COMMAND:
				// Check for invalid input, printing usage or error message if not valid.
				if (tokens.length != 1) {
					printUsage();
					break;
				}
				
				// Print the current board layout
				System.out.println(gameBoard.toString());
				break;
			case EXIT_GAME_COMMAND:
				// Exit the game program
				input.close();
				System.exit(0);
			default:
				// Command invalid; no valid command word included
				printUsage();
				break;
			}

			// Mark the start of the next input line. This line has been processed.
			System.out.print(">");				
		}
	}
	
	// Extract target column from user's argument. Returns the
	// column number if present, or -1 if the input was invalid.
	// If user's input column was invalid, appropriate error message
	// is printed to the user.
	public static int parseTargetColumn(String arg, int totalColumns) {
		int targetColumn = -1;
		try {
			targetColumn = Integer.parseInt(arg);
		} catch (NumberFormatException e) {
			printUsage();
			return -1;
		}
		
		// Check to see if column exists on this board, printing an error message if not.
		if (targetColumn < 1 || targetColumn > totalColumns) {
			printColumnOutOfBounds(totalColumns);
			return -1;
		}
		
		// Return valid target column.
		return targetColumn;
	}
	
	
	// Prints usage if there is an error in user's command.
	public static void printUsage() {
		System.out.println("Unrecognized command. Please choose from the following:");
		System.out.println("\"PUT X\" - place a tile in column x of the board");
		System.out.println("\"GET\" - get a list of moves made so far");
		System.out.println("\"BOARD\" - display current board and locations of tiles");
		System.out.println("\"EXIT\" - exit the game");
	}
	
	// Prints an error message that the column was out of bounds for the
	// place tile command.
	public static void printColumnOutOfBounds(int totalColumns) {
		System.out.println("Column out of bounds. Please enter a value between 1 and "
												+ totalColumns + ".");
	}
	
	// Upon the completion of a turn, advance current player to the next
	// in the game.
	public static int advanceToNextPlayer(int currentPlayer) {
		int nextPlayer = currentPlayer + 1;
		// If we have cycled through all the players,
		// restart at the first.
		if (nextPlayer > NUM_PLAYERS) {
			nextPlayer = 1;
		}
		return nextPlayer;
	}
	
	// Prints the moves to present in this game, one per line.
	// Moves are columns where players put their tiles. 
	// Moves are printed in order.
	public static void printMoves(List<Integer> moves) {
		for (int i = 0; i < moves.size(); i++) {
			System.out.println(moves.get(i));
		}
	}
	
	// TODO:
	// Undefined, should players be able to keep playing after someone has won?
}
