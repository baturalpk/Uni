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
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		this(INITIAL_CAP);
	}
	
	public HashTableLP(int initialCapacity) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		this.M = initialCapacity;
		this.N = 0;
		this.keys = (K[]) new Object[M];
		this.values = (V[]) new Object[M];
	}
	
	public void resize(int newCapacity) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
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
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		K[] castedKeys = (K[]) Array.newInstance(_class, this.keys.length);
		System.arraycopy(this.keys, 0, castedKeys, 0, this.keys.length);
		return castedKeys;
	}
	
	@SuppressWarnings("unchecked")
	public V[] getValuesAs(Class<V> _class) {
		//--------------------------------------------------------
		// Summary: 
		// Precondition: 
		// Postcondition: 
		//--------------------------------------------------------
		
		V[] castedValues = (V[]) Array.newInstance(_class, this.values.length);
		System.arraycopy(this.values, 0, castedValues, 0, this.values.length);
		return castedValues;
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
		// Summary: 
		// Precondition: 
		// Postcondition: 
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
		// Summary: 
		// Precondition: 
		// Postcondition: 
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
