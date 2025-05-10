package org.example.model;
import java.util.List;

public class Enclosure {
    private int id;
    private int currentOccupancy;
    private int capacity;
    private String name;
    private List<String> orders;

    public Enclosure(int currentOccupancy, int capacity, String name, List<String> orders) {
        this.currentOccupancy = currentOccupancy;
        this.capacity = capacity;
        this.name = name;
        this.orders = orders;
    }

    public Enclosure() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Enclosure{" +
                "id=" + id +
                ", currentOccupancy=" + currentOccupancy +
                ", capacity=" + capacity +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
