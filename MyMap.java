package myHashMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyMap <K, V>{
    void put(K key, V value);
    void remove(Object key);
    int size();
    boolean isEmpty();
    boolean containsKey(Object key);
    boolean containsValue(Object value);
    V get(Object key);
    void putAll(MyMap<? extends K, ? extends V> m);
    void clear();
    Set<K> keySet();
    Set<Entry<K, V>> Entrys();
    Collection<V> values();

    public interface Entry<K, V> {
       K getKey();
       V getValue();
    }
}
