package org.example.service;

import org.example.db.AnimalDAO;
import org.example.db.EnclosureDAO;
import org.example.model.Animal;
import org.example.model.Enclosure;

import java.util.List;

public class EnclosureService {

    private final AnimalDAO animalDAO;
    private final EnclosureDAO enclosureDAO;

    public EnclosureService() {
        this.animalDAO = new AnimalDAO();
        this.enclosureDAO = new EnclosureDAO();
    }

    public boolean createEnclosure(int currentOccupancy, int capacity, String name, List<String> orders) {

        if (currentOccupancy > capacity) {
            System.out.println("Enclosure's current occupancy cannot be higher than it's capacity! 🐡");
            return false;
        }

        Enclosure enclosure = new Enclosure(currentOccupancy, capacity, name, orders);
        enclosureDAO.addEnclosure(enclosure);

        return true;
    }

    public boolean deleteEnclosure(int enclosureId) {
        if (enclosureDAO.existsById(enclosureId)) {
            if (enclosureDAO.hasAnimal(enclosureId)) {
                System.out.println("Enclosure need to be empty to be deleted! 🦧");
                return false;
            }
            enclosureDAO.deleteEnclosure(enclosureId);
            return true;
        } else {
            System.out.println("Enclosure doesn't exists! 😶‍🌫️");
            return false;
        }
    }

    public boolean updateEnclosure(int enclosureId, int capacity, String name, List<String> orders) {
        Enclosure existingEnclosure = enclosureDAO.findById(enclosureId);

        if (existingEnclosure == null) {
            System.out.println("Enclosure doesn't exist! 😶‍🌫️");
            return false;
        }

        if (existingEnclosure.getCurrentOccupancy() > capacity){
            System.out.println("Enclosure capacity cannot be less than current occupancy! 🐈‍⬛");
            return false;
        }

        return enclosureDAO.updateEnclosure(enclosureId, capacity, name, orders);
    }

    public List<Enclosure> getEnclosures() {
        return enclosureDAO.getEnclosures();
    }

    public Enclosure getEnclosure(int enclosureId) {
        return enclosureDAO.findById(enclosureId);
    }
}
