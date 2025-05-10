package org.example.controllers.AnimalControllers;

import org.example.model.Animal;
import org.example.service.AnimalService;

import java.util.List;

public class GetAnimalsController {

    private final AnimalService animalService;

    public GetAnimalsController() {
        this.animalService = new AnimalService();
    }

    public void getAnimals() {
        List<Animal> animals = animalService.getAnimals();

        if (animals.isEmpty()) {
            System.out.println("No castrated animals 🪶");
        } else {
            for (Animal animal : animals) {
                System.out.println(animal);
            }
            // System.out.println(animals);
        }
    }
}
