package org.example;

import org.example.controllers.AnimalControllers.CreateAnimalController;
import org.example.controllers.EnclosureControllers.CreateEnclosureController;
import org.example.controllers.EnclosureControllers.UpdateEnclosureController;

import java.util.Arrays;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        //CreateEnclosureController enclosureController = new CreateEnclosureController();
        //enclosureController.createEnclosure(0, 6, "Prisão", Arrays.asList("Fabio", "Bruno"));

        //UpdateEnclosureController updateEnclosureController = new UpdateEnclosureController();
        //updateEnclosureController.updateEnclosure(1, 15, "Nome sei la", Arrays.asList("Felinos", "Gatos"));

        //DeleteEnclosureController deleteEnclosureController = new DeleteEnclosureController();
        //deleteEnclosureController.deleteEnclosure(4);

        //GetEnclosuresController getEnclosuresController = new GetEnclosuresController();
        //getEnclosuresController.getEnclosures();

        //GetEnclosureController getEnclosureController = new GetEnclosureController();
        //getEnclosureController.getEnclosure(1);

        CreateAnimalController createAnimalController = new CreateAnimalController();
        createAnimalController.createAnimal("fabio", "Fabio", 21, "humano", "Mamífero", "Fabio", "5");

        //UpdateAnimalController updateAnimalController = new UpdateAnimalController();
        //updateAnimalController.updateAnimal(3, "Gato", "Tunico", 2, "Sphynx", "Mamífero", "Felinos", "3");

        //DeleteAnimalController deleteAnimalController = new DeleteAnimalController();
        //deleteAnimalController.deleteAnimal(6);

        //GetAnimalsController getAnimalsController = new GetAnimalsController();
        //getAnimalsController.getAnimals();

        //GetAnimalController getAnimalController = new GetAnimalController();
        //getAnimalController.getAnimal(2);
    }
}
