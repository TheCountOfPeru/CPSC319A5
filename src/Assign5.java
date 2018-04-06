import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Assign5 {
	/**
	 * Adapted from https://stackoverflow.com/a/21974043
	 * @param aString - The name of a text file.
	 * @return The file extension if it exists, blank otherwise
	 */
	public static String getFileExtension(String aString) {
	    try {
	        return aString.substring(aString.lastIndexOf("."));
	    } catch (Exception e) {
	        return "";
	    }
	}
	public static void main(String[] args) {
		final int INPUT_NUM = 11344;
		Scanner scanner;
		PrintWriter pw;
		HashTable table;
		//Command line argument verification 
				/*
				if(args.length != 2) {
					System.out.println("Incorrect number of inputs. I need a input and output file name Quitting...");
					System.exit(-1);
				}
				if(!getFileExtension(args[0]).equals(".txt") || !getFileExtension(args[1]).equals(".txt")) {
					System.out.println("Unable to use files that are not text files. Check your file names. Quitting...");
					System.exit(-1);
				}*/
		
		table = new HashTable(INPUT_NUM);	//The input file from d2l has 11344 words	
		//Parse the input text file and add entries into a hash table
		//table.PrintHashTable();
		//System.out.println();
		
		try {
			scanner = new Scanner(new File(args[0]));				
			while(scanner.hasNextLine()) {
				String temp = scanner.nextLine();
				table.insert(temp);
				//System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error occured opening the input text file. Quitting...");
			System.exit(-1);
		}
		
		
		try {
			pw = new PrintWriter(args[1]);
			//table.PrintHashTable(pw);
			System.out.println(INPUT_NUM);
			table.MeasureHashFunc(pw, INPUT_NUM);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error occured opening the ouput text file. Quitting...");
			System.exit(-1);
		}
		

	}

}
