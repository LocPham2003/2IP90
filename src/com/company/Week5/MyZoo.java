package com.company.Week5;

public class MyZoo implements Zoo{
    static ZooResource zooResource = new ZooResource();

    @Override
    public boolean accomodateAnimal(String animalSpecies, String animalNickname, String homeType, int homeNumber) {
        return zooResource.accommodateCage(animalSpecies, animalNickname, homeType, homeNumber);
    }

    @Override
    public boolean removeAnimal(String animalNickname) {
        return false;
    }

    @Override
    public boolean relocateAnimal(String animalNickname, String homeType, int homeNumber) {
        return false;
    }

    @Override
    public boolean feedAnimals(String homeType, int homeNumber, int amountOfFood, String animalFoodType) {


        return false;
    }

    @Override
    public boolean buyFood(String animalFoodType, int amount) {
        return false;
    }

    @Override
    public void listOfTenants() {

    }

    public static void main(String[] args) {
        zooResource.configureZooResources();
        MyZoo myZoo = new MyZoo();

        System.out.println(myZoo.accomodateAnimal("Leopard", "Bagheera", "Cage", 20));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom", "Cage",0));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom1", "Cage",0));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom2", "Cage",2));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom3", "Cage",2));
        System.out.println(myZoo.accomodateAnimal("Lion", "Tom4", "Cage",2));
        System.out.println(myZoo.accomodateAnimal("Tiger", "King", "Cage",6));
        System.out.println(myZoo.accomodateAnimal("Tiger", "Kid", "Cage",6));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Sid", "Cage",1));
    }
}
