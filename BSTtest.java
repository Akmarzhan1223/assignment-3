public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        
        bst.put(50, "Fifty");
        bst.put(30, "Thirty");
        bst.put(70, "Seventy");
        bst.put(20, "Twenty");
        bst.put(40, "Forty");
        bst.put(60, "Sixty");
        bst.put(80, "Eighty");
        
        System.out.println("Size: " + bst.size());
        
        for (BST.Pair<Integer, String> elem : bst.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
        
        bst.delete(20);
        bst.delete(30);
        bst.delete(50);
        
        System.out.println("\nAfter deletions:");
        for (BST.Pair<Integer, String> elem : bst.iterator()) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
        
        System.out.println("Final size: " + bst.size());
    }
}