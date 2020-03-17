package pl.coderslab.model;

public class UsersGroups {
    private int id;
    private String name;

    public UsersGroups(String name) {
        this.name = name;
    }
    public UsersGroups() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
