package org.example.controllers.AnimalControllers;

import org.example.service.AnimalService;

public class UpdateAnimalController {

    private final AnimalService animalService;

    public UpdateAnimalController() {
        this.animalService = new AnimalService();
    }

    public void updateAnimal(int id, String name, String nickname, int age, String species, String bioClass, String bioOrder, String enclosureId) {
        boolean success = animalService.updateAnimal(id, name, nickname, age, species, bioClass, bioOrder, enclosureId);
        if (success) {
            System.out.println("Animal successfully updated! 🐻‍❄️");
        } else {
            System.out.println("Error on updating animal. 🦐");
        }
    }
}
