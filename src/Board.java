

import java.util.*;

/**
 *  Board for the Connect-Four-Cli game
 * @author kch349
 * 
 * A Board is a 4x4 board used in a Connect-Four-Cli game.
 * It keeps track of the current tiles on the board, the plays
 * made by players to this point, and can tell whether a player has won,
 * if the game is a draw, or if plays are valid or invalid.
 */
public class Board {
	
	// Board dimensions: square 4x4 board. Changing these would also change the game
	// from Connect Four to Connect <NUM_COLUMNS> in this implementation.
	public static final int NUM_COLUMNS = 4;
	public static final int NUM_ROWS = 4;
	
	int[][] positions;			// positions in the board, represented [bottom ... top]
													// and where the coordinates are [column][row] for easy access
	
	List<Integer> moves;		// list of actions made on this board
	
	/**
	 *  Creates a new board of the set number of columns and rows
	 *  and all 0s. No moves made yet.
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
		boolean success = findPositionAndPlaceTile(column, player);
		// If successfully placed a token, add it to the list of moves
		if (success) {
			moves.add(column);
		}
		return success;
	}
	
	/**
	 *  Checks whether the game is currently at a win state for the player.
	 * @param player integer representing whether this is player 1, 2, etc.
	 * @return true if the player has won, false if not
	 */
	public boolean isWinState(int player) {
		boolean wonHorizontally = checkRowsForWin(player);
		boolean wonVertically = checkColumnsForWin(player);
		boolean wonDiagonally = checkDiagonalsForWin(player);
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
		// Small number Strings created along the way, so not using string builder
		String result = "";
		for (int j = NUM_ROWS - 1; j >= 0; j--) {
			result += "|";
			for (int i = 0; i < NUM_COLUMNS; i++) {
				result += " " + positions[i][j];
			}
			result +="\n";
		}
		result += "+--------\n";
		result += "  1 2 3 4";
		return result;
	}
	
	/**
	 * Gets a list of all moves made by all players to this point.
	 * Does not include invalid move attempts.
	 * @return a list of integers representing columns where a token was
	 * 				 placed, in chronological order.
	 */
	public List<Integer> getMoves() {
		return Collections.unmodifiableList(moves);
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
	private boolean checkRowsForWin(int player) {
		boolean allTokensOfPlayer;
		int nextTile;
		for (int j = 0; j < NUM_ROWS; j++) {
			allTokensOfPlayer = false;
			for(int i = 0; i < NUM_COLUMNS; i++) {
				nextTile = positions[i][j];
				allTokensOfPlayer = (nextTile == player);
				if (!allTokensOfPlayer) {
					break;
				}
			}
			// If found a full row of this player's token, player
			// has won and return true.
			if (allTokensOfPlayer) {
				return true;
			}
		}
		return false;
	}
	
	// Check all vertical columns of board for possible wins
	// for the given player. Returns true if a column filled
	// with player's tiles is found, false if not.
	private boolean checkColumnsForWin(int player) {
		boolean allTokensOfPlayer;
		int nextTile;
		for (int i = 0; i < NUM_COLUMNS; i++) {
			allTokensOfPlayer = false;
			for(int j = 0; j < NUM_ROWS; j++) {
				nextTile = positions[i][j];
				allTokensOfPlayer = (nextTile == player);
				if (!allTokensOfPlayer) {
					break;
				}
			}
			// If found a full row of this player's token, player
			// has won and return true.
			if (allTokensOfPlayer) {
				return true;
			}
		}
		return false;
	}
	
	// Check both diagonals of board for possible wins for the
	// given player. If a full diagonal of player's tiles
	// is found, returns true, false if not.
	private boolean checkDiagonalsForWin(int player) {
		// Check left to right diagonal
		boolean allTokensOfPlayer = false;
		int nextTile;
		for (int i = 0; i < NUM_COLUMNS; i++) {
			nextTile = positions[i][NUM_ROWS - 1 - i];
			allTokensOfPlayer = (nextTile == player);
			if (!allTokensOfPlayer) {
				break;
			}
		}
		// If found a full diagonal of this player's token from left
		// to right, player has won and return true.
		if (allTokensOfPlayer) {
			return true;
		}
		
		// Check right to left diagonal
		allTokensOfPlayer = false;
		for (int i = 0; i < NUM_COLUMNS; i++) {
			nextTile = positions[i][i];
			allTokensOfPlayer = (nextTile == player);
			if (!allTokensOfPlayer) {
				break;
			}
		}
		// If found a full diagonal of this player's token from
		// right to left, player has won and return true. Otherwise
		// player has not won by diagonal and return false.
		return allTokensOfPlayer;
	}
}
