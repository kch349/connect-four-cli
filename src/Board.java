import java.util.*;

// A board is used in a Connect-Four-Cli game. This board is set
// to be a 4x4 board, and be playable by 2 players.
// It keeps track of the current tiles on the board, the plays
// made by players to this point, and can tell whether a player has won,
// if the game is a draw, or if plays are valid or invalid.
public class Board {
	
	public static final int NUM_COLUMNS = 4;
	public static final int NUM_ROWS = 4;
	public static final int NUM_PLAYERS = 2;
	
	// Current state of the board
	// A flattened array of the board state for faster lookup.
	// The board 
	// 14, 24, 34, 44
	// 13, 23, 33, 43
	// 12, 22, 32, 42
	// 11, 21, 31, 41
	// where 14 = column 1, row 4.
	// becomes flattened into
	// [11, 12, 13, 14, 21, 22, 23, 24, 31, 32, 33, 34, 41, 42, 43, 44]
	// or [column 1, column 2, column 3, column 4]
	int[] positions;							
	List<Integer> actions;				// List of actions made on this board
	int currentPlayer;						// Player whose turn it currently is (between 1 and num players)
	
	// Creates a new board of the set number of columns and rows, with the
	// set number of players, and an empty actions.
	// Sets Player 1 to be the one with the first turn.
	public Board() {
		positions = new int[NUM_COLUMNS * NUM_ROWS];
		System.out.println(Arrays.toString(positions));
		actions = new ArrayList<Integer>();
		currentPlayer = 1;
	}
	
	// Place a tile on the board at the specified column, by the
	// current player.
	// Returns a string representing the status of the game after this action.
	// Options include:
	// "OK" - The play was valid and completed.
	// "WIN" - The play was valid, completed, and caused the current player to win the game.
	// "ERROR" - The play was invalid, and not completed.
	// "DRAW" - The play was valid, completed, and filled the last space in the board without
	// 					any player having one. Therefore the game ended in a draw.
	public String placeTile(int column) {
		String result = findPositionAndPlaceTile(column);
		if (result.equals("ERROR"));
		advanceToNextPlayer();
		
		// Check if this caused a win or draw. If so return that.
		// Otherwise return OK.
		return getGameStatus();
}
	
	// Upon the completion of a turn, advance current player to the next
	// in the game.
	public void advanceToNextPlayer() {
		currentPlayer++;
		// If we have cycled through all the players,
		// restart at the first.
		if (currentPlayer > NUM_PLAYERS) {
			currentPlayer = 1;
		}
	}
	
	// Report game status, as in whether it is in an OK state,
	// a WIN state, or a DRAW state. Returns the corresponding string.
	public String getGameStatus() {
		
		return null;
	}
	@Override
	public String toString() {
		
		return null;
	}
	
	public List<Integer> getActions() {
		return null;
	}
	
	
	// Search for an empty spot at the top of the stack of tiles
	// in the given column. If there is one, place a tile for the current
	// player and return the index at which it was placed.
	// If there was not space in this row, a tile is not placed,
	// and -1 is returned as an error status.
	private String findPositionAndPlaceTile(int column) {
		// If column is not a column on this board, return -1 as error.
		if (column > NUM_COLUMNS || column < 0) {
			return -1;
		}
		
		// Try to find an empty position in the column.
		int emptyPositionInColumn = -1;
		int firstIndex = 0 + (NUM_ROWS * (column - 1));				// First position in the given column.
		for (int i = firstIndex; i < (firstIndex + NUM_ROWS); i++) {
			if (positions[i] == 0) {
				emptyPositionInColumn = i;
			}
		}
		return emptyPositionInColumn; 	// still -1 if no empty spot found.
	}
	
	
	
	
	

}
