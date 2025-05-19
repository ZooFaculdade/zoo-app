package org.example.controllers.EnclosureControllers;

import org.example.service.EnclosureService;

public class DeleteEnclosureController {

    private final EnclosureService enclosureService;

    public DeleteEnclosureController() {
        this.enclosureService = new EnclosureService();
    }

    public boolean deleteEnclosure(int enclosureId) {
        boolean success = enclosureService.deleteEnclosure(enclosureId);
        if (success) {
            System.out.println("Enclosure successfully deleted! 🐻‍❄️");
            return true;
        } else {
            System.out.println("Error on deleting enclosure. 🦐");
        }
        return false;
    }
}
