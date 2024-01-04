package test;

public class Dranks {
    private int id, cost;
    private String name, type;

    public Dranks(int id, String name, String type, int cost) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

   
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

   
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
