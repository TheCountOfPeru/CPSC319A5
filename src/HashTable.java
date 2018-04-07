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
		System.out.println(getSize());
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
		while(!getAt(position + linearp).equals(line)) { 	
			linearp++;
			chain++;
			if(position + linearp == table.length)
				position = linearp = 0;
		}
		return chain;
	}
	/**
	 * The hash function for this hash table class that also normalizes the has results. Generates an index for a hash table.
	 * From https://research.cs.vt.edu/AVresearch/hashing/strings.php
	 * @param in
	 */
	public int HashFunction(String s) {
		int intLength = s.length() / 4;
	     long sum = 0;
	     for (int j = 0; j < intLength; j++) {
	       char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
	       long mult = 1;
	       for (int k = 0; k < c.length; k++) {
		 sum += c[k] * mult;
		 mult *= 256;
	       }
	     }

	     char c[] = s.substring(intLength * 4).toCharArray();
	     long mult = 1;
	     for (int k = 0; k < c.length; k++) {
	       sum += c[k] * mult;
	       mult *= 256;
	     }
	     return (int) (Math.abs(sum) % getSize()); //Normalize the result
	}
	/**
	 * The normalizer function for this hash table class. Makes sure HashFunction output is within the bounds of the table.
	 * @param in
	 */
	public int Normalizer(int k) {
		return k % getSize();
	}
	/**
	 * Finds the next open position in a hash table
	 */
	public int LinearProb(int position) {
		position++;
		if(position > getSize()-1)//if you reach the end of a table start over at the beginning
			position = 0;
		return position;
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
		pw.println("Hash efficiency = "+ (double)getSize()/total_chain*100+"%");
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
