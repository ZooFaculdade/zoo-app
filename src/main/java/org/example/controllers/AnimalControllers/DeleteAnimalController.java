package org.example.controllers.AnimalControllers;

import org.example.service.AnimalService;

public class DeleteAnimalController {

    private final AnimalService animalService;

    public DeleteAnimalController() {
        this.animalService = new AnimalService();
    }

    public void deleteAnimal(int animalId) {
        boolean success = animalService.deleteAnimal(animalId);
        if (success) {
            System.out.println("Animal successfully deleted! 🐻‍❄️");
        } else {
            System.out.println("Error on deleting animal. 🦐");
        }
    }
}
