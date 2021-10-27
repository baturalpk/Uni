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
    		//--------------------------------------------------------
    		// Summary: 
    		// Precondition: 
    		// Postcondition: 
    		//--------------------------------------------------------
    		
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }
    
    public HashTableSC() {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		this(INITIAL_CAP);
	}
	
	public HashTableSC(int initialCapacity) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		this.M = initialCapacity;
		this.N = 0;
		st = new Node[M];
	}

	public void resize(int chains) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
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
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		return st[chainIndex];
	}
	
	@Override
	public int getM() {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		return this.M;
	}

	@Override
	public int size() {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		return this.N;
	}

	public boolean isEmpty() {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		return this.N == 0;
	}
	
	public int hash(K key) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		return (key.hashCode() & 0x7fffffff) % M;
	}

	@Override
	public V get(K key) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
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
		// Summary: 
		// Precondition: 
		// Postcondition: 
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
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		int i = this.hash(key);
		this.st[i] = this.removeNodes(st[i], key);
		
		if (M > INITIAL_CAP && N <= 2*M) 
			resize(M/2);
	}
	
	private Node removeNodes(Node x, K key) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
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
