package com.company.Week5;

import java.util.ArrayList;

public class Container {
    private ArrayList<Animal> listOfAnimals = new ArrayList<>();
    private String animalSpecies = "";
    public int getNumberOfTenants() {
        return listOfAnimals.size();
    }

    public String getCurrentAnimal() {
        return this.animalSpecies;
    }

    public void addToContainer(String animalSpecies, String animalNickname) {
        Animal animal = new Animal(animalSpecies, animalNickname);
        this.animalSpecies = animalSpecies;
        this.listOfAnimals.add(animal);
    }
}
