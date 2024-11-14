MyHashMap Implementation
This repository contains an implementation of a HashMap-like data structure in Java called MyHashMap. This implementation supports basic operations such as inserting, retrieving, updating, and removing key-value pairs, along with additional functionalities like resizing, handling collisions, and supporting null keys and values.

Features
Put (put): Inserts or updates a key-value pair.
Get (get): Retrieves the value associated with a key.
Remove (remove): Removes the key-value pair for a given key.
Contains Key (containsKey): Checks if a key exists in the map.
Contains Value (containsValue): Checks if a value exists in the map.
Clear (clear): Removes all entries from the map.
Size (size): Returns the number of entries in the map.
Is Empty (isEmpty): Checks if the map is empty.
Key Set (keySet): Returns a set of all keys in the map.
Values (values): Returns a collection of all values in the map.
Resize (resize): Doubles the capacity when the load factor threshold is reached.
Collision Handling: Uses separate chaining with linked lists to handle hash collisions.
Supports Null Keys and Values.
