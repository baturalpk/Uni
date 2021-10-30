//-----------------------------------------------------
// Title: Separate Chaining Hash Table
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class contains all required functionalities, methods and member
//				variables to be able to used as a Separate Chaining Hash Table.
//-----------------------------------------------------
public class HashTableSC<K, V> implements IHashTable<K, V> {
	
	public static final int INITIAL_CAP = 16;
	private int M;
	private int N;
	private Node[] st;
	
    public static class Node {
        final Object key;
        Object val;
        Node next;

        public Node(Object key, Object val, Node next)  {    		
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    public HashTableSC() {
		//--------------------------------------------------------
		// Summary: Overloaded constructor with no parameters. Initializes
		// the hash table with default initial capacity which is 16.
		// Precondition: -
		// Postcondition: Hash table is initialized.
		//--------------------------------------------------------
		
		this(INITIAL_CAP);
	}
	
	public HashTableSC(int initialCapacity) {
		//--------------------------------------------------------
		// Summary: Initializes member variables of the hash table 
		// with default and given values.
		// Precondition: initialCapacity is an integer.
		// Postcondition: M, N, and st variables are initialized.
		//--------------------------------------------------------
		
		this.M = initialCapacity;
		this.N = 0;
		st = new Node[M];
	}

	public void resize(int chains) {
		//--------------------------------------------------------
		// Summary: Creates a new separate chaining hash table instance with 
		// the given size. Reinserts existing key/value pairs. Sets 
		// member variables of newly created hash table to existing one.
		// Precondition: chains is integer.
		// Postcondition: Current hash table is resized.
		//--------------------------------------------------------
		
		HashTableSC<K, V> newHT = new HashTableSC<>(chains);
		
		for (int i = 0; i < M; ++i) {
			for (Node x = st[i]; x != null; x = x.next) {
				newHT.put((K) x.key, (V) x.val);
			}
		}
		
		this.M = newHT.M;
		this.N = newHT.N;
		this.st = newHT.st;
	}

	public Node getChain(int chainIndex) {
		//--------------------------------------------------------
		// Summary:  Returns head of the chain (linked-list) at the certain 
		// index of hash table. 
		// Precondition: chainIndex is integer.
		// Postcondition: Returns node.
		//--------------------------------------------------------
		
		return st[chainIndex];
	}
	
	@Override
	public int getM() {
		//--------------------------------------------------------
		// Summary: Getter for table size (M).
		// Precondition: -
		// Postcondition: Returns M
		//--------------------------------------------------------
		
		return this.M;
	}

	@Override
	public int size() {
		//--------------------------------------------------------
		// Summary: Getter for total item count (N).
		// Precondition: -
		// Postcondition: Returns N.
		//--------------------------------------------------------
		
		return this.N;
	}

	public boolean isEmpty() {
		//--------------------------------------------------------
		// Summary: Checks for emptiness of hash table.
		// Precondition: -
		// Postcondition: Returns true if it is empty, otherwise false.
		//--------------------------------------------------------
		
		return this.N == 0;
	}
	
	public int hash(K key) {
		//--------------------------------------------------------
		// Summary: Hash function implementation for the hash table.
		// Precondition: key is a K (abbreviation for Key)
		// Postcondition: Calculates and return hash for the given key.
		//--------------------------------------------------------
		
		return (key.hashCode() & 0x7fffffff) % M;
	}

	@Override
	public V get(K key) {
		//--------------------------------------------------------
		// Summary: Gets value of given key if it exists, otherwise returns null.
		// Precondition: key is a K
		// Postcondition: Return V or null
		//--------------------------------------------------------
		
		int i = this.hash(key);
		
		for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key))
                return (V) x.val;
        }
				
		return null;
	}

	@Override
	public void put(K key, V val) {
		//--------------------------------------------------------
		// Summary: Resizes hash table if it's necessary. 
		// If first node of the table at this index empty, insert there.
		// If the given key's already exists in the table, overwrite.
		// If the given key does not exist in the current chain, insert to the tail.
		// Precondition: key is K, val is V.
		// Postcondition: Inserts or updates given key/value pair into hash table.
		//--------------------------------------------------------
		
		if (N >= 8 * M) 
			resize(2*M);
		
		int i = this.hash(key);
		Node head = st[i];
		
		if (head == null) {
			st[i] = new Node(key, val, null);
			++N;
			return;
		}
		
		for (Node x = head; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
			
			if (!key.equals(x.key) && x.next == null) {
				x.next = new Node(key, val, null);
				++N;
				return;
			}
		}
	}

	@Override
	public void delete(K key) {
		//--------------------------------------------------------
		// Summary: Removes the key if the hash table contains it.
		// Uses internal removeNodes method for deletion. Then resizes if it is necessary.
		// Precondition: key is K.
		// Postcondition: Removes desired key/value pair or it is idempotent.
		//--------------------------------------------------------
		
		int i = this.hash(key);
		this.st[i] = this.removeNodes(st[i], key);
		
		if (M > INITIAL_CAP && N <= 2*M) 
			resize(M/2);
	}
	
	private Node removeNodes(Node x, K key) {
		//--------------------------------------------------------
		// Summary: Recursive removal method for internal chain (linked-list)
		// implementation using nodes.
		// Precondition: x is Node, key is K
		// Postcondition: Removes the node with the given key.
		//--------------------------------------------------------
		
		if (x == null) 
			return null;
		
		if (key.equals(x.key)) {
            --N;
            return x.next;
        }
		
        x.next = removeNodes(x.next, key);
        return x;
	}
	
}
