package org.example.controllers.AnimalControllers;

import org.example.service.AnimalService;

public class DeleteAnimalController {

    private final AnimalService animalService;

    public DeleteAnimalController() {
        this.animalService = new AnimalService();
    }

    public boolean deleteAnimal(int animalId) {
        boolean success = animalService.deleteAnimal(animalId);
        if (success) {
            System.out.println("Animal successfully deleted! 🐻‍❄️");
            return true;
        } else {
            System.out.println("Error on deleting animal. 🦐");
        }
        return false;
    }
}
