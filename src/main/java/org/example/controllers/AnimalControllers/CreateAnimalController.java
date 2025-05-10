package org.example.controllers.AnimalControllers;

import org.example.service.AnimalService;

public class CreateAnimalController {

    private final AnimalService animalService;

    public CreateAnimalController() {
        this.animalService = new AnimalService();
    }

    public CreateAnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    public void createAnimal(String name, String nickname, int age, String species, String bioClass, String bioOrder, String enclosureId) {
        boolean success = animalService.createAnimal(name, nickname, age, species, bioClass, bioOrder, enclosureId);
        if (success) {
            System.out.println("Animal successfully created! 🐻‍❄️");
        } else {
            System.out.println("Error on creating animal. 🦐");
        }
    }
}
