package com.company.Week5;

public class Animal {
    private String animalSpecies;
    private String animalNickname;

    public Animal(String animalSpecies, String animalNickname) {
        this.animalNickname = animalNickname;
        this.animalSpecies = animalSpecies;
    }

    public String getAnimalNickname() {
        return animalNickname;
    }

    public String getAnimalSpecies() {
        return animalSpecies;
    }
}
