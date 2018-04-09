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
		final int TABLE_SIZE = 16205;
		final int INPUT_SIZE = 11344;
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
		
		table = new HashTable(TABLE_SIZE);	//The input file from d2l has 11344 words	
		//Parse the input text file and add entries into a hash table
		//table.PrintHashTable();
		//System.out.println();
		
		System.out.println("Scanning " + args[0] + " and attempting to insert input words into a Hash Table....");
		try {
			scanner = new Scanner(new File(args[0]));				
			while(scanner.hasNextLine()) {
				String temp = scanner.nextLine();
				table.insert(temp);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error occured opening the input text file. Quitting...");
			System.exit(-1);
		}
		System.out.println("Hash Table created successfully.");
		System.out.println("Calculating hash statistics....");
		try {
			scanner = new Scanner(new File(args[0]));
			pw = new PrintWriter(args[1]);
			//table.PrintHashTable(pw);
			//System.out.println(INPUT_NUM);
			table.MeasureHashFunc(pw, scanner,INPUT_SIZE);
			pw.close();
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error occured opening the output or input text file. Quitting...");
			System.exit(-1);
		}
		
		System.out.println("Program successfully completed. Hash measurements printed to " + args[1]+ ". Quitting...");
	}

}
