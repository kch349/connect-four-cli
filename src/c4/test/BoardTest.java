package c4.test;

import static org.junit.Assert.*;
import java.util.*;
import c4.Board;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	public Board testBoard;
	
	@Before
	public void setUp() throws Exception {
		testBoard = new Board();
	}

	@After
	public void tearDown() throws Exception {
		testBoard = null;
	}
	
  ///////////////////////////////////////////////////////////////////////////////////////
  ////	Test constructor
  ///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testConstructorAndGetNumColumns () {
		testBoard = new Board();
		assertEquals("should return 4 columns", 4, testBoard.getNumColumns());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Test place tile
	///////////////////////////////////////////////////////////////////////////////////////
	
	// Test that a player can place a tile, and it is reported in the correct place.
	@Test
	public void testPlaceTileSuccess() {
		assertTrue("should be able to successfully place a tile on when space available", testBoard.placeTile(1, 1));
	}
	
	// Test that player cannot place a tile if a column is full.
	@Test
	public void testPlaceTileColumnFullFailure() {
		// Set up board state to fill column 1.
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		assertFalse("should not be able to place a tile in a full column", testBoard.placeTile(1, 1));
	}
	
	// Test that player cannot place a tile if a column is full.
	@Test
	public void testPlaceTileNoColumnFailure() {
		// Set up board state to fill column 1.
		assertFalse("should not be able to place a tile in a non-existant column", testBoard.placeTile(5, 1));
	}
	
	// Test proper records of who played what (depends on the toString method, tested more thoroughly below).
	@Test
	public void testPlaceTileRecordPlayer() {
		String expected = "| 0 0 0 0\n"
										+ "| 0 0 0 0\n"
										+ "| 0 0 0 0\n"
										+ "| 1 2 1 0\n"
										+ "+--------\n"
										+ "  1 2 3 4";
		
		// Build up board state.
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		assertEquals("board should show alternating plays, marking different players placing tiles.", expected, testBoard.toString());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Test board to string
	////	Tests rely on placeTile(), tested above.
	///////////////////////////////////////////////////////////////////////////////////////

	// Test that toString() correctly displays an empty board.
	@Test
	public void testToStringEmpty () {
		String expected = "| 0 0 0 0\n"
									+ "| 0 0 0 0\n"
									+ "| 0 0 0 0\n"
									+ "| 0 0 0 0\n"
									+ "+--------\n"
									+ "  1 2 3 4";
		assertEquals("should print an empty board of given format", expected, testBoard.toString());
	}
	
	// Test that toString() correctly does not change board display after an error
	@Test
	public void testToStringAfterError () {
		String expected = "| 2 0 0 0\n"
										+ "| 1 0 0 0\n"
										+ "| 2 0 0 0\n"
										+ "| 1 0 0 0\n"
										+ "+--------\n"
										+ "  1 2 3 4";
		
		// Build up board state.
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2); // Board will be at state in expected
		assertEquals("board should show column 1 as full.", expected, testBoard.toString());
		testBoard.placeTile(1, 1); // Provoke error, no more room in the column
		assertEquals("board should not change after error", expected, testBoard.toString());
	}
	
	// Test that toString() correctly displays full board. Also tests that to string correctly
	// marks which player is playing.
	@Test
	public void testToStringEndState () {
		String expected = "| 2 2 1 2\n"
										+ "| 1 1 2 1\n"
										+ "| 2 2 2 1\n"
										+ "| 1 1 1 2\n"
										+ "+--------\n"
										+ "  1 2 3 4";
		
		// Build board state. Fill column 1
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		
		// Fill column 2
		testBoard.placeTile(2, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(2, 2);
		
		// Fill bottom right corner
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(3, 2);
		
		// Fill top left corner
		testBoard.placeTile(4, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		assertEquals("should print a full board after corresponding plays", expected, testBoard.toString());
	}


  ///////////////////////////////////////////////////////////////////////////////////////
  ////	Test win checking, player 1
  ///////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testWinHorizontalPlayer1() {
		/* Building | 2 0 0 0
								| 2 0 0 0
								| 2 0 0 0
								| 1 1 1 1
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(4, 1);
		assertTrue("should notice that player 1 has won horizontally", testBoard.isWinState(1));
	}
	
	@Test
	public void testWinVerticalPlayer1() {
		/* Building | 1 0 0 0
								| 1 0 0 0
								| 1 0 0 0
								| 1 2 2 2
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(1, 1);
		assertTrue("should notice that player 1 has won vertically", testBoard.isWinState(1));
	}
	
	@Test
	public void testWinDiagonal1Player1() {
		/* Building | 1 0 0 0
								| 2 1 0 0
								| 2 2 1 2
								| 1 1 2 1
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		assertTrue("should notice that player 1 has won diagonally left to right", testBoard.isWinState(1));
	}
	
	@Test
	public void testWinDiagonal2Player1() {
		/* Building | 0 0 0 1
								| 0 0 1 2
								| 2 1 2 2
								| 1 2 1 1
								+--------
								  1 2 3 4 */
		testBoard.placeTile(4, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(4, 1);
		assertTrue("should notice that player 1 has won diagonally right to left", testBoard.isWinState(1));
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Test win checking, player 2
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testWinHorizontalPlayer2() {
		/* Building | 1 0 0 0
								| 1 0 0 0
								| 2 2 2 2
								| 1 1 1 2
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(4, 2);
		assertTrue("should notice that player 2 has won horizontally", testBoard.isWinState(2));
	}
	
	@Test
	public void testWinVerticalPlayer2() {
		/* Building | 2 0 0 0
								| 2 0 0 0
								| 2 1 0 0
								| 2 1 1 1
								+--------
								  1 2 3 4 */
		testBoard.placeTile(2, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(1, 2);
		assertTrue("should notice that player 2 has won vertically", testBoard.isWinState(2));
	}
	
	@Test
	public void testWinDiagonal1Player2() {
		/* Building | 2 0 0 0
								| 1 2 0 1
								| 1 2 2 1
								| 1 2 1 2
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(2, 2);
		assertTrue("should notice that player 2 has won diagonally left to right", testBoard.isWinState(2));
	}
	
	@Test
	public void testWinDiagonal2Player2() {
		/* Building | 0 0 0 2
								| 1 0 2 2
								| 1 2 1 1
								| 2 1 2 1
								+--------
								  1 2 3 4 */
		testBoard.placeTile(4, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(4, 2);
		assertTrue("should notice that player 1 has won diagonally right to left", testBoard.isWinState(2));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Test does not find win when not all tiles from same player
	///////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testNotWinHorizontal() {
		/* Building | 0 0 0 0
								| 0 0 0 0
								| 0 0 0 0
								| 1 2 1 2
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		assertFalse("should not mistake that either player has won horizontally", testBoard.isWinState(2));
	}
	
	@Test
	public void testNotWinVertical() {
		/* Building | 2 0 0 0
								| 1 0 0 0
								| 2 0 0 0
								| 1 0 0 0
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		assertFalse("should not mistake that either player has won vertically", testBoard.isWinState(2));
	}
	
	@Test
	public void testNotWinDiagonal1() {
		/* Building | 1 0 0 0
								| 2 1 0 0
								| 1 2 1 2
								| 1 2 1 2
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(2, 1);
		assertFalse("should not mistake that either player has won diagonally left to right", testBoard.isWinState(1));
	}
	
	@Test
	public void testNotWinDiagonal2() {
		/* Building | 0 0 0 2
								| 0 0 2 1
								| 0 2 1 1
								| 1 2 1 2
								+--------
								  1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(4, 2);
		assertFalse("should not mistake that either player has won diagonally right to left", testBoard.isWinState(2));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Test draw checking (based on whether the board is filled
	///////////////////////////////////////////////////////////////////////////////////////
	
	// An empty board should not report that the board is not filled.
	@Test
	public void testNotFilled() {
		assertFalse("should not report board full when it is not", testBoard.boardFilled());
	}
	
	// If there the whole board is filled, should return true.
	// After checking wins, if the board is filled, this would indicate a draw.
	@Test
	public void testFilled() {
		// Fill board
		/* Building | 1 2 1 2
								| 1 2 1 2
								| 2 1 2 1
								| 1 2 1 2
								+--------
		  						1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		assertTrue("should report the board is full", testBoard.boardFilled());
	}
	
	// If the board is almost full (one space remaining) but the next play is
	// an error, board should not consider itself full.
	@Test
	public void testNotFilledWithError() {
		// Fill board
		/* Building | 1 2 1 0
								| 1 2 1 2
								| 2 1 2 1
								| 1 2 1 2
								+--------
		  						1 2 3 4 */
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(4, 1);
		testBoard.placeTile(3, 2);
		testBoard.placeTile(2, 1);
		testBoard.placeTile(1, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(4, 2);
		testBoard.placeTile(1, 1);
		testBoard.placeTile(2, 2);
		testBoard.placeTile(3, 1);
		testBoard.placeTile(1, 2);	// Provokes an error. Tile not placed.
		assertFalse("should report the board is full", testBoard.boardFilled());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	////	Test get actions
	///////////////////////////////////////////////////////////////////////////////////////

	// Proper when nothing there
	@Test
	public void testGetMovesOnEmptyBoard () {
		assertEquals("should return an empty list when no moves have been made yet", new ArrayList<Integer>(), testBoard.getMoves());
	}
	
	// Displays one move for every position in the board once the board is full (meaning
	// when the board is full, there will have been be 16 moves).
	// Also these moves correspond to moves made as the board was filled.
	@Test
	public void testGetMovesOnFilledBoard () {
		// Fill board
		/* Building | 1 2 1 2
								| 1 2 1 2
								| 2 1 2 1
								| 1 2 1 2
								+--------
		  						1 2 3 4 */
		List<Integer> expected = new ArrayList<Integer>();
		testBoard.placeTile(1, 1);
		expected.add(1);
		testBoard.placeTile(2, 2);
		expected.add(2);
		testBoard.placeTile(3, 1);
		expected.add(3);
		testBoard.placeTile(4, 2);
		expected.add(4);
		testBoard.placeTile(4, 1);
		expected.add(4);
		testBoard.placeTile(3, 2);
		expected.add(3);
		testBoard.placeTile(2, 1);
		expected.add(2);
		testBoard.placeTile(1, 2);
		expected.add(1);
		testBoard.placeTile(1, 1);
		expected.add(1);
		testBoard.placeTile(2, 2);
		expected.add(2);
		testBoard.placeTile(3, 1);
		expected.add(3);
		testBoard.placeTile(4, 2);
		expected.add(4);
		testBoard.placeTile(1, 1);
		expected.add(1);
		testBoard.placeTile(2, 2);
		expected.add(2);
		testBoard.placeTile(3, 1);
		expected.add(3);
		testBoard.placeTile(4, 2);
		expected.add(4);
		assertTrue("should report the board is full", testBoard.boardFilled());
		assertEquals("should return the correct list of moves", expected, testBoard.getMoves());
		assertEquals("length should equal the size of the board", 16, testBoard.getMoves().size());
	}
	
	// 
	// Does not add a move when it was an error and a tile was not played.
	@Test
	public void testGetMovesOnNearFilledBoardWithError () {
		// Fill board
		/* Building | 1 2 1 2
								| 1 2 1 2
								| 2 1 2 1
								| 1 2 1 2
								+--------
		  						1 2 3 4 */
		List<Integer> expected = new ArrayList<Integer>();
		testBoard.placeTile(1, 1);
		expected.add(1);
		testBoard.placeTile(2, 2);
		expected.add(2);
		testBoard.placeTile(3, 1);
		expected.add(3);
		testBoard.placeTile(4, 2);
		expected.add(4);
		testBoard.placeTile(4, 1);
		expected.add(4);
		testBoard.placeTile(3, 2);
		expected.add(3);
		testBoard.placeTile(2, 1);
		expected.add(2);
		testBoard.placeTile(1, 2);
		expected.add(1);
		testBoard.placeTile(1, 1);
		expected.add(1);
		testBoard.placeTile(2, 2);
		expected.add(2);
		testBoard.placeTile(3, 1);
		expected.add(3);
		testBoard.placeTile(4, 2);
		expected.add(4);
		testBoard.placeTile(1, 1);
		expected.add(1);
		testBoard.placeTile(2, 2);
		expected.add(2);
		testBoard.placeTile(3, 1);
		expected.add(3);
		testBoard.placeTile(1, 2); // Provokes an error, the column is full.
		assertEquals("should return the correct list of moves", expected, testBoard.getMoves());
		assertEquals("length should equal the size of the board - 1", 15, testBoard.getMoves().size());
	}
}
