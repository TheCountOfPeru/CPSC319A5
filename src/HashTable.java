
public class HashTable {
	private Integer size;
	private Integer table[];
	
	public HashTable(int size) {
		this.setSize(nextPrime(size));
		table = new Integer[size];
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
	/**
	 * The hash function for this hash table class. Calculates a index in the table from a string.
	 * @param in
	 */
	public int HashFunction(String in) {
		return 0;
	}
	/**
	 * The normalizer function for this hash table class. Makes sure HashFunction output is within the bounds of the table.
	 * @param in
	 */
	public void Normalizer(int in) {
		
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
	/**
	 * Finds the next open position in a hash table
	 */
	public void LinearProb() {
		
	}
}
