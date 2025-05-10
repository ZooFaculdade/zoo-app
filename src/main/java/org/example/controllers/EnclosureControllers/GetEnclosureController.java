package org.example.controllers.EnclosureControllers;

import org.example.model.Enclosure;
import org.example.service.EnclosureService;

public class GetEnclosureController {

    private final EnclosureService enclosureService;

    public GetEnclosureController() {
        this.enclosureService = new EnclosureService();
    }

    public void getEnclosure(int enclosureId) {
        Enclosure enclosure = enclosureService.getEnclosure(enclosureId);

        if (enclosure == null) {
            System.out.println("Enclosure doesn't exists 😶‍🌫️");
        } else {
            System.out.println(enclosure);
        }
    }
}
