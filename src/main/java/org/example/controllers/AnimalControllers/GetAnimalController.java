package org.example.controllers.AnimalControllers;

import org.example.model.Animal;
import org.example.service.AnimalService;

public class GetAnimalController {

    private final AnimalService animalService;

    public GetAnimalController() {
        this.animalService = new AnimalService();
    }

    public void getAnimal(int animalId) {
        Animal animal = animalService.getAnimal(animalId);

        if (animal == null) {
            System.out.println("Animal doesn't exists 😶‍🌫️");
        } else {
            System.out.println(animal);
        }
    }
}
