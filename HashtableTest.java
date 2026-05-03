import java.util.Random;

public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(100);
        Random random = new Random();
        
        for (int i = 0; i < 10000; i++) {
            int id = random.nextInt(100000);
            String name = "Student" + random.nextInt(10000);
            int age = 18 + random.nextInt(30);
            
            MyTestingClass key = new MyTestingClass(id, name, age);
            Student value = new Student(
                "FirstName" + random.nextInt(100),
                "LastName" + random.nextInt(100),
                "ID" + id,
                2.0 + random.nextDouble() * 2.0
            );
            table.put(key, value);
        }
        
        System.out.println("Bucket Distribution:");
        for (int i = 0; i < table.getNumberOfBuckets(); i++) {
            System.out.printf("Bucket %3d: %4d elements%n", i, table.getBucketSize(i));
        }
        
        System.out.printf("\nTotal elements: %d%n", table.getSize());
        System.out.printf("Number of buckets: %d%n", table.getNumberOfBuckets());
        System.out.printf("Average load: %.2f%n", (double) table.getSize() / table.getNumberOfBuckets());
    }
}