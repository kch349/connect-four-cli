import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * This class provides integration test functionality for the ConnectFour program. 
 * It uses two test files, one that tests win functionality, the other draw.
 * testFileWin.txt tests:
 *   - prints usage when invalid command
 *   - prints usage when column for PUT is not a number
 *   - prints column warning when column not on board in PUT command
 *   - PUT:
 *   		- the OK message
 *   		- the cannot place more tiles after winning message
 *   		- the ERROR message when no more room in a column
 *   		- the WIN message when a player wins
 *   - GET:
 *   		- display when nothing there
 *   		- display when some there
 *   - BOARD:
 *   		- display board
 *   - EXIT:
 *   		- close program (tested implicitly)
 *   
 * testFileDraw.txt tests:
 * 	 - PUT:
 * 			- the OK message
 * 			- the cannot place more tiles after a draw
 * 			- the DRAW message when a draw is reached
 * 	 - BOARD:
 * 			- display board
 * 	 - EXIT:
 * 			- closes the program, tested implicitly.
 * @param input file name - must exist in the connect-four-cli repository
 * @param output file name
 */
public class ConnectFourTest {
  public static void main(String[] args) {
  	 // Not testing parsing. Developer must know what to pass in.
  	 File in = new File(args[0]);
  	 File out= new File(args[1]);
  	 try {
  		 runTests(in, out);
  	 } catch (FileNotFoundException e) {
  		 System.out.println("Input file not found. Please try again.");
  	 }
  }
  /**
   * @requires in != null && out != null
   *
   * @effects Creates a new ConnectFourTestDriver and runs the main
   * program with System.in and System.out redirected to the provided files.
   **/
  
  public static void runTests(File in, File out) throws FileNotFoundException {
      // Store system I/O streams
      InputStream stdin = System.in;
      PrintStream stdout = System.out;
      PrintStream stderr = System.err;
      
      // Redirect I/O to files
      System.setIn(new FileInputStream(in));
      System.setOut(new PrintStream(out));
      
      // Call main method of Connect Four, reading from the input file
      // and sending output to the output file.
      ConnectFour.main(new String[0]);

      // Restore I/O streams
      System.setIn(stdin);
      System.setOut(stdout);
      System.setErr(stderr);
  }
}
