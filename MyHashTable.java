import java.util.LinkedList;

public class MyHashTable<K, V> {
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;
        
        public HashNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;
    
    public MyHashTable() {
        this(11);
    }
    
    @SuppressWarnings("unchecked")
    public MyHashTable(int M) {
        this.M = M;
        this.size = 0;
        chainArray = (HashNode<K, V>[]) new HashNode[M];
    }
    
    private int hash(K key) {
        return Math.abs(key.hashCode()) % M;
    }
    
    public void put(K key, V value) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        
        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
        size++;
    }
    
    public V get(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    
    public boolean contains(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }
    
    public K getKey(V value) {
        for (int i = 0; i < M; i++) {
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if (current.value.equals(value)) {
                    return current.key;
                }
                current = current.next;
            }
        }
        return null;
    }
    
    public int getSize() {
        return size;
    }
    
    public int getBucketSize(int index) {
        int count = 0;
        HashNode<K, V> current = chainArray[index];
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    public int getNumberOfBuckets() {
        return M;
    }
}