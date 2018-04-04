import java.lang.Math;
public class HashTable {
	private Integer size;
	private String table[];
	
	public HashTable(int size) {
		this.setSize(nextPrime(size));
		table = new String[size];
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	public String getAt(int index) {
		return table[index];
	}
	/**
	 * The insertion function. Uses the hashfunction and normalizer and linearprob to attempt to insert a string
	 * @param line
	 */
	public void insert(String line) {
		int position = HashFunction(line); 		//Hash the string
		//position = Normalizer(position);		//Normalize the index number
		while(getAt(position) == null) { 	//Find an empty spot in hash table to put your string in
			position = LinearProb(position);
		}
		table[position] = line;
		//System.out.print(position + " ");
	}
	public void PrintHashTable() {
		for(int i = 0; i < getSize() - 1;i++)
			System.out.print(getAt(i)+ " ");
	}
	/**
	 * The hash function for this hash table class. Calculates a index in the table from a string.
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
