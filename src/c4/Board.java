package c4;
import java.util.*;

/**
 *  Board for the Connect-Four-Cli game
 * @author kch349
 * 
 * A Board is used in a Connect-Four-Cli game. This board is set
 * to be a 4x4 board. It keeps track of the current tiles on the board, the plays
 * made by players to this point, and can tell whether a player has won,
 * if the game is a draw, or if plays are valid or invalid.
 */
public class Board {
	
	public static final int NUM_COLUMNS = 4;
	public static final int NUM_ROWS = 4;
	
	int[][] positions;			// positions in the board, represented [bottom ... top]
													// and where the coordinates are [column][row] for easy access
	
	List<Integer> moves;		// list of actions made on this board
	
	/**
	 *  Creates a new board of the set number of columns and rows
	 *  with no moves yet.
	 */
	public Board() {
		positions = new int[NUM_COLUMNS][NUM_ROWS];
		moves = new ArrayList<Integer>();
	}
	
	/**
	 * Reports the number of columns in this board.
	 * @return number of columns
	 */
	public int getNumColumns() {
		return NUM_COLUMNS;
	}
	
	/**
	 * Reports whether this board is completely full of tiles,
	 * meaning no empty positions remain. (In this state, the
	 * game is a draw if no player has already won).
	 * @return true if board full, false otherwise
	 */
	public boolean boardFilled() {
		return moves.size() == (NUM_COLUMNS * NUM_ROWS);
	}
	
	/**
	 *  Place a tile for the given player on the board at the
	 *  given column. Reports whether the tile was successfully placed.
	 *  on the board at the specified column, by the
	 *  
	// current player. Records move if successful.
	// Returns true if tile successfully placed, false otherwise.
	 * @param column integer between 1 and the number of columns in this board
	 * @param player integer representing the current player (player 1, 2, etc).
	 * @return true if tile placed successfully, false if not 
	 * 				 (if the column is full, or if the column is not within the board).
	 */
	public boolean placeTile(int column, int player) {
		return findPositionAndPlaceTile(column, player);
	}
	
	/**
	 *  Checks whether the game is currently at a win state for the player.
	 * @param player integer representing whether this is player 1, 2, etc.
	 * @return true if the player has won, false if not
	 */
	public boolean isWinState(int player) {
		boolean wonHorizontally = checkHorizontals(player);
		boolean wonVertically = checkVerticals(player);
		boolean wonDiagonally = checkDiagonals(player);
		return wonHorizontally || wonVertically || wonDiagonally;
	}
	
	/**
	 * Provides a representation of the current board state. This follows the format:
	 * | 0 0 0 0
	 * | 0 0 0 0
	 * | 0 0 0 0
	 * | 1 2 1 2
	 * +--------
	 *   1 2 3 4 
	 * where columns are marked as column 1, 2, 3 or 4 in the case of a 4 column board,
	 * and players tiles are represented by their number (here shown for two players).
	 * @return a String of the specified format representing the current board
	 */
	@Override
	public String toString() {
		return null;
	}
	
	/**
	 * Gets a list of all moves made by all players to this point.
	 * Does not include invalid move attempts.
	 * @return a list of integers representing columns where a token was
	 * 				 placed, in chronological order.
	 */
	public List<Integer> getMoves() {
		return null;
	}
	
	// Search for an empty spot at the top of the stack of tiles
	// in the given column. If there is one, place a tile for the current
	// player and return true for success.
	// If there was not space in this column, a tile is not placed,
	// and returns false.
	private boolean findPositionAndPlaceTile(int column, int currentPlayer) {
		// If column is not a column on this board, return -1 as error.
		if (column > NUM_COLUMNS || column < 1) {
			return false;
		}
		
		int columnIndex = column - 1; // for zero based indexing.
		// Try to find an empty position in the column.
		int[] columnData = positions[columnIndex];
		for (int i = 0; i < columnData.length; i++) {
			if (columnData[i] == 0) {
				// Found an empty spot in this column. 
				// Add tile of the current player here,
				// and report success
				positions[columnIndex][i] = currentPlayer;
				return true;
			}
		}
		return false; 	// column was full and tile not placed
	}

	// Check all horizontal rows of board for possible wins
	// for the given player. Returns true if a row filled
	// with player's tiles is found, false if not.
	private boolean checkHorizontals(int player) {
		return false;
	}
	
	// Check all vertical columns of board for possible wins
	// for the given player. Returns true if a column filled
	// with player's tiles is found, false if not.
	private boolean checkVerticals(int player) {
		return false;
	}
	
	// Check both diagonals of board for possible wins for the
	// given player. If a full diagonal of player's tiles
	// is found, returns true, false if not.
	private boolean checkDiagonals(int player) {
		return false;
	}
}
