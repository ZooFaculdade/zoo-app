package org.example.controllers.EnclosureControllers;

import org.example.model.Enclosure;
import org.example.service.EnclosureService;

import java.util.List;

public class GetEnclosuresController {

    private final EnclosureService enclosureService;

    public GetEnclosuresController() {
        this.enclosureService = new EnclosureService();
    }

    public List<Enclosure> getEnclosures() {
        List<Enclosure> enclosures = enclosureService.getEnclosures();

        if (enclosures.isEmpty()) {
            System.out.println("No castrated enclosures 🪶");
            return enclosures;
        } else {
            for (Enclosure enclosure : enclosures) {
                System.out.println(enclosures);
            }
            // System.out.println(enclosures);
        }
        return enclosures;
    }
}
