public class MyTestingClass {
    private int id;
    private String name;
    private int age;
    
    public MyTestingClass(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int hashCode() {
        int result = 31 * id;
        result = 37 * result + (name != null ? name.hashCode() : 0);
        result = 41 * result + age;
        result = result ^ (result >>> 16);
        return Math.abs(result);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyTestingClass that = (MyTestingClass) obj;
        return id == that.id && age == that.age && 
               (name != null ? name.equals(that.name) : that.name == null);
    }
}