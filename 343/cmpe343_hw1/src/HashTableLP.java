//-----------------------------------------------------
// Title: Linear Probing Hash Table
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class contains all required functionalities, methods and member
//				variables to be able to used as a Linear Probing Hash Table.
//-----------------------------------------------------
import java.lang.reflect.Array;

public class HashTableLP<K, V> implements IHashTable<K, V> {

	public static final int INITIAL_CAP = 16;
	private int M;
	private int N;
	private K[] keys;
	private V[] values;
	
	public HashTableLP() {
		//--------------------------------------------------------
		// Summary: Overloaded constructor with no parameters. Initializes
		// the hash table with default initial capacity which is 16.
		// Precondition: -
		// Postcondition: Hash table is initialized.
		//--------------------------------------------------------
		
		this(INITIAL_CAP);
	}
	
	public HashTableLP(int initialCapacity) {
		//--------------------------------------------------------
		// Summary: Initializes member variables of the hash table 
		// with default and given values.
		// Precondition: initialCapacity is an integer.
		// Postcondition: M, N, keys, and values variables are initialized.
		//--------------------------------------------------------
		
		this.M = initialCapacity;
		this.N = 0;
		this.keys = (K[]) new Object[M];
		this.values = (V[]) new Object[M];
	}
	
	public void resize(int newCapacity) {
		//--------------------------------------------------------
		// Summary: Creates a new linear probing hash table instance with 
		// the given size. Reinserts existing key/value pairs. Sets 
		// member variables of newly created hash table to existing one.
		// Precondition: newCapacity is integer.
		// Postcondition: Current hash table is resized.
		//--------------------------------------------------------
		
		HashTableLP<K, V> newHT = new HashTableLP<>(newCapacity);
		
		for (int i = 0; i < this.M; ++i) {
			if (this.keys[i] == null) 
				continue;
			
			newHT.put(keys[i], values[i]);
		}
		
		this.M = newHT.M;
		this.keys = newHT.keys;
		this.values = newHT.values;
	}

	@SuppressWarnings("unchecked")
	public K[] getKeysAs(Class<K> _class) {
		//--------------------------------------------------------
		// Summary: Creates a new array instance with the given class type.
		// Copies existing keys array into newly created one returns it.
		// It ensures avoidance from generic array cast exceptions.
		// Precondition: _class is concrete type of class K
		// Postcondition: Returns hardcopy of this.keys
		//--------------------------------------------------------
		
		K[] castedKeys = (K[]) Array.newInstance(_class, this.keys.length);
		System.arraycopy(this.keys, 0, castedKeys, 0, this.keys.length);
		return castedKeys;
	}
	
	@SuppressWarnings("unchecked")
	public V[] getValuesAs(Class<V> _class) {
		//--------------------------------------------------------
		// Summary: Creates a new array instance with the given class type.
		// Copies existing values array into newly created one returns it.
		// It ensures avoidance from generic array cast exceptions.
		// Precondition: _class is concrete type of class V
		// Postcondition: Returns hardcopy of this.values
		//--------------------------------------------------------
		
		V[] castedValues = (V[]) Array.newInstance(_class, this.values.length);
		System.arraycopy(this.values, 0, castedValues, 0, this.values.length);
		return castedValues;
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
		
		int expectedHashIndex = this.hash(key);
		K currentKey;
		
		while ((currentKey = keys[expectedHashIndex]) != null) {
			if (currentKey.equals(key))
				return this.values[expectedHashIndex];
			
			expectedHashIndex = (expectedHashIndex + 1) % this.M;
		}
		
		return null;
	}

	@Override
	public void put(K key, V value) {
		//--------------------------------------------------------
		// Summary: Resizes hash table if it's necessary. Then searches for
		// the desired key by scanning possible indices (linear probing offsets)
		// If it founds the key, overwrites the value. Otherwise just inserts.
		// Precondition: key is a K, value is a V
		// Postcondition: Inserts or updates given key/value pair into hash table.
		//--------------------------------------------------------
		
        if (this.N >= this.M/2) 
        	resize(this.M * 2);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % this.M) {
            if (keys[i].equals(key)) {
                this.values[i] = value;
                return;
            }
        }
        
        this.keys[i] = key;
        this.values[i] = value;
        ++this.N;		
	}

	@Override
	public void delete(K key) {
		//--------------------------------------------------------
		// Summary: Removes the key if the hash table contains it.
		// Rehashes all keys to rearrange tables. Then resizes if it is necessary.
		// Precondition: key is K.
		// Postcondition: Removes desired key/value or it is idempotent.
		//--------------------------------------------------------
		
        if (this.get(key) == null) return;
        
        int i = hash(key);
        while (!key.equals(this.keys[i])) {
            i = (i + 1) % this.M;
        }

        this.keys[i] = null;
        this.values[i] = null;

        i = (i + 1) % this.M;
        while (keys[i] != null) {
            K keyToRehash = this.keys[i];
            V valToRehash = this.values[i];
            this.keys[i] = null;
            this.values[i] = null;
            --this.N;
            this.put(keyToRehash, valToRehash);
            i = (i + 1) % this.M;
        }

        --this.N;

        if (this.N > 0 && this.N <= this.M/8) resize(this.M/2);
	}
	
}
