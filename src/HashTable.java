
public class HashTable {
	private Integer size;
	private Integer table[];
	
	public HashTable(int size) {
		this.setSize(size);
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
	 * Returns the next prime number starting from n.
	 * @param n
	 * @return
	 */
	public int nextPrimeNum(int n) {
		return 0;
	}
	
	/**
	 * Finds the next open position in a hash table
	 */
	public void LinearProb() {
		
	}
}
