package edu.louisiana.cacs;



import java.io.IOException;


import edu.louisiana.cacs.CSCE450Gproject.Parser;

/**
 * 
 */

/**
 * @author Sharmila
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Hello World from Main");
        Parser myParser = new Parser("sample.txt");
        myParser.printHeader();
        myParser.parse();

	}

}

