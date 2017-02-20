package c4.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import c4.ConnectFour;

/**
 * This class provides integration test functionality for the ConnectFour program. It
 * redirects System.out and System.in to input and output files, then invokes
 * ConnectFour's main method. This allows the output of a set of commands
 * to be compared against the expected output programmatically.
 */
public class ConnectFourTestDriver {
	//Files for reading input and writing output in place of the console.
  private File in, out;

  /**
   * @requires in != null && out != null
   *
   * @effects Creates a new ConnectFourTestDriver and runs the main
   * program with System.in and System.out redirected to the provided files.
   **/
  public ConnectFourTestDriver(File in, File out) {
      this.in = in;
      this.out = out;
  }
  
  public void runTests() throws FileNotFoundException {
      // Store original values of I/O streams.
      InputStream stdin = System.in;
      PrintStream stdout = System.out;
      PrintStream stderr = System.err;
      
      // Redirect I/O to files.
      System.setIn(new FileInputStream(in));
      System.setOut(new PrintStream(out));
      
      // Call main method of textual maps program
      ConnectFour.main(new String[0]);

      // Restore standard I/O streams.
      System.setIn(stdin);
      System.setOut(stdout);
      System.setErr(stderr);
  }
  ///////////////////////////////////////////////////////////////////////////////////////
  ////	Give input that does not match a command
  ///////////////////////////////////////////////////////////////////////////////////////
	
	// If do not provide proper command PUT, GET, or BOARD, print usage and ask user to try again
	// If do not provide a number for PUT within columns in board, print out of column range, try again.
	// If provide "EXIT", quit.
}
