import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;
        
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
            this.size = 1;
        }
    }
    
    private Node root;
    
    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            return;
        }
        
        Node current = root;
        Node parent = null;
        int compareResult = 0;
        
        while (current != null) {
            parent = current;
            compareResult = key.compareTo(current.key);
            
            if (compareResult < 0) {
                current = current.left;
            } else if (compareResult > 0) {
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }
        
        if (compareResult < 0) {
            parent.left = new Node(key, val);
        } else {
            parent.right = new Node(key, val);
        }
        
        updateSizes(root);
    }
    
    public V get(K key) {
        Node current = root;
        while (current != null) {
            int compareResult = key.compareTo(current.key);
            if (compareResult < 0) {
                current = current.left;
            } else if (compareResult > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }
    
    public void delete(K key) {
        if (root == null) return;
        root = deleteNode(root, key);
    }
    
    private Node deleteNode(Node root, K key) {
        Node current = root;
        Node parent = null;
        
        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        
        if (current == null) return root;
        
        if (current.left == null && current.right == null) {
            if (parent == null) return null;
            if (parent.left == current) parent.left = null;
            else parent.right = null;
        }
        else if (current.left == null) {
            if (parent == null) return current.right;
            if (parent.left == current) parent.left = current.right;
            else parent.right = current.right;
        }
        else if (current.right == null) {
            if (parent == null) return current.left;
            if (parent.left == current) parent.left = current.left;
            else parent.right = current.left;
        }
        else {
            Node successor = current.right;
            Node successorParent = current;
            
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            
            current.key = successor.key;
            current.val = successor.val;
            
            if (successorParent.left == successor) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }
        }
        
        updateSizes(root);
        return root;
    }
    
    private void updateSizes(Node node) {
        if (node == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            if (current != null) {
                int leftSize = (current.left != null) ? current.left.size : 0;
                int rightSize = (current.right != null) ? current.right.size : 0;
                current.size = leftSize + rightSize + 1;
                stack.push(current.left);
                stack.push(current.right);
            }
        }
    }
    
    public int size() {
        return (root != null) ? root.size : 0;
    }
    
    public Iterable<Pair<K, V>> iterator() {
        return () -> new Iterator<Pair<K, V>>() {
            private Stack<Node> stack = new Stack<>();
            {
                pushAllLeft(root);
            }
            
            private void pushAllLeft(Node node) {
                Node current = node;
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
            
            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }
            
            @Override
            public Pair<K, V> next() {
                if (!hasNext()) throw new NoSuchElementException();
                Node current = stack.pop();
                if (current.right != null) pushAllLeft(current.right);
                return new Pair<>(current.key, current.val);
            }
        };
    }
    
    public static class Pair<K, V> {
        private K key;
        private V value;
        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
    }
}