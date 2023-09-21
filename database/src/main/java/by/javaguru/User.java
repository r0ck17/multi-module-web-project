package by.javaguru;

public class User {
    private static long counter;
    private long id;
    private String name;

    public User() {

    }

    public User(String name) {
        this.id = ++counter;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setCounter(long counter) {
        User.counter = counter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
