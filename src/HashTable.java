import java.io.PrintWriter;
import java.lang.Math;
import java.util.Scanner;
public class HashTable {
	private int size;
	private String table[];
	
	public HashTable(int init) {
		if(!isPrime(init))	
			setSize(nextPrime(init));
		else
			setSize(init);
		table = new String[getSize()];
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	public String getAt(int index) {
		return table[index];
	}
	/**
	 * The insertion function.
	 * @param line
	 */
	public void insert(String line) {
		int linearp = 0;
		int position = HashFunction(line); 		//Hash the string
		while(getAt(position + linearp) != null) { 	//Find an empty spot in hash table to put your string in, if necessary use linear probing to find an empty spot
			linearp++;
			if(position + linearp == table.length)
				position = linearp = 0;
		}
		table[position + linearp] = line;
	}
	public void PrintHashTable(PrintWriter pw) {
		for(int i = 0; i < getSize();i++)
			pw.println(getAt(i)+ " ");
	}
	public int search(String line) {
		int linearp = 0;
		int chain = 1;
		int position = HashFunction(line); 		//Hash the string
		//System.out.println(position);
		while(!getAt(position + linearp).equals(line)) { 	
			linearp++;
			chain++;
			if(position + linearp == table.length)
				position = linearp = 0;
		}
		return chain;
	}
	/**
	 * The hash function for this hash table class that also normalizes the hash results. It uses a folding algorithm.
	 * Adapted from https://research.cs.vt.edu/AVresearch/hashing/strings.php
	 * @param in
	 */
	public int HashFunction(String s) {
		int intLength = s.length() / 4;
	     long sum = 0;
	     for (int j = 0; j < intLength; j++) { //summing 4 chars at a time, with each index with a different weight
	       char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray(); //Put four chars into a temp char array for summing
	       long mult = 1;
	       for (int k = 0; k < c.length; k++) {						//For each char of the array multiply it by a variable number and sum them together
	    	   sum += c[k] * mult;									//Sum all the sums for each substring together
	    	   mult *= 1024;
	       }
	     }
	     
	     char c[] = s.substring(intLength * 4).toCharArray();		//Finally add to the previous sum all the characters of the string
	     long mult = 1;												//Each char of the string will have different weight
	     for (int k = 0; k < c.length; k++) {
	       sum += c[k] * mult;
	       mult *= 1024;
	     }
	     return (int) (Math.abs(sum) % getSize()); //Normalize the result and return it.
	}
	/**
	 * Calculates the load factor, 
	 * @param pw
	 * @param num_records
	 */
	public void MeasureHashFunc(PrintWriter pw, Scanner scanner, int num_records) {
		double loadfactor;
		int max_chain = 0;
		int chain;
		int total_chain = 0;
		//Calculate the load factor first. Load factor = # of records/Table size
		loadfactor = (num_records/(double)getSize())*100;
		//Parse through the input file again to calculate largest chain and total of all chains
		while(scanner.hasNextLine()) {
			String temp = scanner.nextLine();
			chain = search(temp);
			total_chain+=chain;
			if(chain > max_chain)
				max_chain = chain;			
		}
		//Print out the remaining calculations...
		pw.println("Average number of reads per record = " + (double)total_chain/num_records);
		pw.println("Load factor = " + num_records + "/"+getSize() +" = "+ loadfactor+"%");
		pw.println("Hash efficiency = "+ loadfactor/(total_chain/num_records)+"%");
		pw.println("The longest chain found = " + max_chain);
	}
	/**
	 * From https://gist.github.com/scottfrazer/2725727
	 * Checks if n is a prime number. 
	 * @param n
	 * @return
	 */
	public boolean isPrime(int n) {
        for(int i=2; i<n; i++) {
            if(n%i==0)return false;
        }
        return true;
    }
	/**
	 * From https://gist.github.com/scottfrazer/2725727
	 * Finds the next prime number from n
	 * @param n
	 * @return
	 */
    public int nextPrime(int n) {
        for(int i=n+1; true; i++) {
            if(isPrime(i)==true) return i;
        }
    }
	
}
