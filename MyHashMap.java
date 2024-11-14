package myHashMap;

import java.util.*;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private Entry<K, V>[] table;

    private Integer size = null;

    static final int DEFAULT_INITIAL_CAPACITY = 16; // aka 16

    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Integer capacity = null;

    private static class Entry<K, V> implements MyMap.Entry<K, V> {
        public K key;
        public V value;
        Entry<K, V> next;

        Entry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        Entry(Entry<K, V> obj) {
            this.key = obj.key;
            this.value = obj.value;
            this.next = null;
        }


        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public String toString() {
            return key + "=" + value;
        }
    }
    @SuppressWarnings("unchecked")
    MyHashMap() {
        table = (Entry<K, V>[]) new Entry[DEFAULT_INITIAL_CAPACITY];
        capacity = DEFAULT_INITIAL_CAPACITY;
        size = 0;
    }

    static final int hash(Object key) {
        return (key == null) ? 0 : key.hashCode();
    }


    @Override
    public void put(K key, V value) {
        if(((size.doubleValue() + 1) / capacity.doubleValue()) >= DEFAULT_LOAD_FACTOR) {resize();}
        int index = hash(key) % capacity;

        if(table[index] == null) {
            table[index] = new Entry<>(key, value);
        }else {
            Entry<K, V> tmp = table[index];
            while (tmp != null) {
                if(tmp.key == key || tmp.key.equals(key)) {
                    tmp.value = value;
                    return;
                }
                tmp = tmp.next;
            }
            Entry<K, V> newObj = new  Entry<>(key, value);
            newObj.next = table[index];
            table[index] = newObj;
        }
        size++;
    }

    @Override
    public void remove(Object key) {
        int index = hash(key) % capacity;
        Entry<K, V> e = table[index];
        if(e == null) {return;}
        if(e.key == key || e.key.equals(key)) {
            table[index] = table[index].next;
            size--;
            return;
        }
        Entry<K, V> tmp = e;
        e = e.next;
        while (e != null) {
            if(e.key == key || e.key.equals(key)) {
                tmp.next = e.next;
                size--;
                return;
            }
            tmp = e;
            e = e.next;
        }
        return;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        table =  (Entry<K, V>[]) new Entry[capacity];
        size = 0;
    }

    @Override
    public int size() {return size;}

    @Override
    public boolean isEmpty() {return size == 0;}

    @Override
    public boolean containsKey(Object key) {return this.get(key) != null;}

    @Override
    public boolean containsValue(Object value) {
        for(int i = 0; i < capacity; ++i) {
            if(table[i] != null) {
                Entry<K, V> e = table[i];
                while (e != null) {
                    if(e.value == value || e.value.equals(value)) {
                        return true;
                    }
                    e = e.next;
                }
            }
        }
        return false;
    }


    @Override
    public V get(Object key) {
        int index = hash(key) % capacity;
        Entry<K, V> elem = table[index];
        while (elem != null) {
            if(elem.key == key || elem.key.equals(key)) {
                return elem.value;
            }
            elem = elem.next;
        }
        return null;
    }

    @Override
    public Set<MyMap.Entry<K, V>> Entrys() {
        Set<MyMap.Entry<K, V>> s  = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                Entry<K, V> e = table[i];
                while (e != null) {
                    s.add(e);
                    e = e.next;
                }
            }
        }
        return s;
    }


    @Override
    public void putAll(MyMap<? extends K, ? extends V> m) {
        for(MyMap.Entry<? extends K, ? extends V> e: m.Entrys()) {
            put(e.getKey(), e.getValue());
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        for(int i = 0; i < capacity; ++i) {
            if(table[i] != null) {
                Entry<K, V> e = table[i];
                while (e != null) {
                    s.add(e.key);
                    e = e.next;
                }
            }
        }
        return s;
    }

    @Override
    public Collection<V> values() {
        ArrayList<V> vs = new ArrayList<>();
        for(int i = 0; i < capacity; ++i) {
            if(table[i] != null) {
                Entry<K, V> e = table[i];
                while (e != null) {
                    vs.add(e.value);
                    e = e.next;
                }
            }
        }
        return vs;
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        Entry<K, V>[] newTab =  new Entry[capacity];

        for (int i = 0; i < capacity / 2; ++i) {
            Entry<K, V> e = table[i];
            while (e != null) {
                int index = hash(e.key) % capacity;
                if(newTab[index] == null) {
                    newTab[index] = new Entry<>(e);
                }else {
                    Entry<K, V> tmp = new Entry<>(e);
                    tmp.next = newTab[index];
                    newTab[index] = tmp;
                }
                e = e.next;
            }
        }
        table = newTab;
    }

    public static void main(String[] args) {
        MyHashMap<Integer, String> myMap = new MyHashMap<>();

        myMap.put(1, "one");
        myMap.put(2, "two");
        myMap.put(3, "three");
        System.out.println("Value for key 1: " + myMap.get(1));
        System.out.println("Value for key 2: " + myMap.get(2));
        System.out.println("Value for key 3: " + myMap.get(3));

        myMap.put(2, "TWO");
        System.out.println("Updated value for key 2: " + myMap.get(2));

        System.out.println("Contains key 2: " + myMap.containsKey(2));
        System.out.println("Contains key 4: " + myMap.containsKey(4));
        System.out.println("Contains value 'TWO': " + myMap.containsValue("TWO"));
        System.out.println("Contains value 'four': " + myMap.containsValue("four"));

        myMap.put(null, "NULL_KEY");
        System.out.println("Value for null key: " + myMap.get(null));
        myMap.put(null, "UPDATED_NULL_KEY");
        System.out.println("Updated value for null key: " + myMap.get(null));

        myMap.put(4, null);
        System.out.println("Value for key 4 with null value: " + myMap.get(4));
        System.out.println("Contains value null: " + myMap.containsValue(null));

        MyHashMap<String, String> collisionMap = new MyHashMap<>();
        collisionMap.put("Aa", "first");
        collisionMap.put("BB", "second");  // "Aa" and "BB" may collide in some hash implementations
        System.out.println("Value for key 'Aa': " + collisionMap.get("Aa"));
        System.out.println("Value for key 'BB': " + collisionMap.get("BB"));

        System.out.println("Size of myMap: " + myMap.size());
        System.out.println("Is myMap empty?: " + myMap.isEmpty());
        MyHashMap<Integer, String> emptyMap = new MyHashMap<>();
        System.out.println("Is emptyMap empty?: " + emptyMap.isEmpty());

        myMap.remove(1);
        System.out.println("Removed key 1. Contains key 1?: " + myMap.containsKey(1));
        System.out.println("Size after removing key 1: " + myMap.size());

        myMap.clear();
        System.out.println("Cleared myMap. Size: " + myMap.size());
        System.out.println("Is myMap empty after clearing?: " + myMap.isEmpty());

        for (int i = 0; i < 20; i++) {
            myMap.put(i, "value" + i);
        }
        System.out.println("Size after adding 20 elements: " + myMap.size());
        System.out.println("Value for key 15: " + myMap.get(15));
        System.out.println("Value for key 19: " + myMap.get(19));

    }

}
