package org.example.controllers.EnclosureControllers;

import org.example.service.EnclosureService;
import java.util.List;

public class UpdateEnclosureController {

    private final EnclosureService enclosureService;

    public UpdateEnclosureController() {
        this.enclosureService = new EnclosureService();
    }

    public void updateEnclosure(int id, int capacity, String name, List<String>orders) {
        boolean success = enclosureService.updateEnclosure(id, capacity, name, orders);
        if (success) {
            System.out.println("Enclosure successfully updated! 🐻‍❄️");
        } else {
            System.out.println("Error on updating enclosure. 🦐");
        }
    }
}
