import java.io.*;
import java.util.*;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private int weight; // в процентах от 100 

    public Toy(int id, String name, int quantity, int weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getWeight() {
        return this.weight;
    }



    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}