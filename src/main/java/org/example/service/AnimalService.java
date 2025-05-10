package org.example.service;

import org.example.db.AnimalDAO;
import org.example.db.EnclosureDAO;
import org.example.model.Animal;
import java.util.List;

public class AnimalService {

    private final AnimalDAO animalDAO;
    private final EnclosureDAO enclosureDAO;

    public AnimalService() {
        this.animalDAO = new AnimalDAO();
        this.enclosureDAO = new EnclosureDAO();
    }

    public boolean createAnimal(String name, String nickname, int age, String species, String bioClass, String bioOrder, String enclosureIdStr) {
        int enclosureId;

        try {
            enclosureId = Integer.parseInt(enclosureIdStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid enclosure's id! 🐍");
            return false;
        }

        if (!enclosureDAO.existsById(enclosureId) || !enclosureDAO.isAppropriate(enclosureId, bioOrder) || enclosureDAO.isAlreadyFull(enclosureId)) {
            System.out.println("Enclosure is not available! 🐿️");
            return false;
        }

        Animal animal = new Animal(age, name, nickname, species, bioClass, bioOrder, enclosureIdStr);
        animalDAO.addAnimal(animal);
        enclosureDAO.incrementOccupancy(enclosureId);

        return true;
    }

    public boolean deleteAnimal(int animalId) {
        Animal animal = animalDAO.findById(animalId);

        if (animal == null) {
            System.out.println("Animal doesn't exists! 😶‍🌫️");
            return false;
        }

        boolean deleted = animalDAO.deleteAnimal(animalId);
        if (deleted) {
            try {
                int enclosureId = Integer.parseInt(animal.getEnclosureId());
                enclosureDAO.decrementOccupancy(enclosureId);
            } catch (NumberFormatException e) {
                System.out.println("Invalid enclosure's id! 🐍");
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean updateAnimal(int animalId, String name, String nickname, int age, String species, String bioClass, String bioOrder, String enclosureIdStr) {
        int enclosureId;

        Animal existingAnimal = animalDAO.findById(animalId);

        if (existingAnimal == null) {
            System.out.println("Animal doesn't exist! 😶‍🌫️");
            return false;
        }

        try {
            enclosureId = Integer.parseInt(enclosureIdStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid enclosure's id! 🐍");
            return false;
        }

        boolean isEnclosureDiff = !existingAnimal.getEnclosureId().equals(enclosureIdStr);

        if (isEnclosureDiff) {
            if (!enclosureDAO.existsById(enclosureId) || !enclosureDAO.isAppropriate(enclosureId, bioOrder) || enclosureDAO.isAlreadyFull(enclosureId)) {
                System.out.println("Enclosure is not available! 🐿️");
                return false;
            }

            enclosureDAO.decrementOccupancy(Integer.parseInt(existingAnimal.getEnclosureId()));
            enclosureDAO.incrementOccupancy(enclosureId);
        }

        return animalDAO.updateAnimal(animalId, name, nickname, age, species, bioClass, bioOrder, enclosureIdStr);
    }

    public List<Animal> getAnimals() {
        return animalDAO.getAnimals();
    }

    public Animal getAnimal(int animalId) {
        return animalDAO.findById(animalId);
    }
}
