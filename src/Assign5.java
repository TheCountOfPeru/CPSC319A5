import java.io.File;
import java.io.FileNotFoundException;
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
		Scanner scanner;
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
		table = new HashTable(20);		
		//Parse the input text file and add entries into a hash table
		table.PrintHashTable();
		System.out.println();
		try {
			scanner = new Scanner(new File(args[0]));
			for(int i = 0; i<20; i++) {
				String temp = scanner.nextLine();
				table.insert(temp);
				System.out.println(temp);
			}
				
			/*while(scanner.hasNextLine()) {
				
			}*/
		} catch (FileNotFoundException e) {
			System.out.println("Error occured opening the input. Quitting...");
			System.exit(-1);
		}
		table.PrintHashTable();
		
		

	}

}
