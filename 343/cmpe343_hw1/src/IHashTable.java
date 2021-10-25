//-----------------------------------------------------
// Title: Hash Table Interface
// Author: Baturalp KIZILTAN
// ID: 4456996054
// Section: 1
// Assignment: 1
// Description: This class provides the most basic structure and common methods
//				for any hash table implementation.
//-----------------------------------------------------
public interface IHashTable<K, V> {
	
	public int getM();
	
	public void put(K key, V value);
	
	public V get(K key);
	
	public void remove(K key);
	
	public int size();
	
}
