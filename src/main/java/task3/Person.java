package task3;

public class Person {
    @Save
    private String name = "Dmytro";
    @Save
    private String lastName = "Beliasnyk";
    private int age = 21;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
