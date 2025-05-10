package org.example.model;

public class Animal {
    private int id;
    private int age;
    private String name;
    private String nickname;
    private String species;
    private String bioClass;
    private String bioOrder;
    private String enclosureId;

    public Animal(int age, String name, String nickname, String species, String bioClass, String bioOrder, String enclosureId) {
        this.age = age;
        this.name = name;
        this.nickname = nickname;
        this.species = species;
        this.bioClass = bioClass;
        this.bioOrder = bioOrder;
        this.enclosureId = enclosureId;
    }

    public Animal() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBioClass() {
        return bioClass;
    }

    public void setBioClass(String bioClass) {
        this.bioClass = bioClass;
    }

    public String getBioOrder() {
        return bioOrder;
    }

    public void setBioOrder(String bioOrder) {
        this.bioOrder = bioOrder;
    }

    public String getEnclosureId() {
        return enclosureId;
    }

    public void setEnclosureId(String enclosureId) {
        this.enclosureId = enclosureId;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", species='" + species + '\'' +
                ", bioClass='" + bioClass + '\'' +
                ", bioOrder='" + bioOrder + '\'' +
                ", enclosureId='" + enclosureId + '\'' +
                '}';
    }
}