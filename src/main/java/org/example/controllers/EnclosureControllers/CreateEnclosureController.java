package org.example.controllers.EnclosureControllers;

import org.example.service.EnclosureService;
import java.util.List;

public class CreateEnclosureController {

    private final EnclosureService enclosureService;

    public CreateEnclosureController() {
        this.enclosureService = new EnclosureService();
    }

    public boolean createEnclosure(int currentOccupancy, int capacity, String name, List<String>orders) {
        boolean success = enclosureService.createEnclosure(currentOccupancy, capacity, name, orders);
        if (success) {
            System.out.println("Enclosure successfully created! 🐻‍❄️");
            return true;
        } else {
            System.out.println("Error on creating enclosure. 🦐");
        }
        return false;
    }
}
