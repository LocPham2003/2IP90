package com.company.Week5;

public class MyZoo extends ZooResource implements Zoo{

    public MyZoo() {
        configureZooResources();
    }

    @Override
    public boolean accomodateAnimal(String animalSpecies, String animalNickname, String homeType, int homeNumber) {
        return accommodateCage(animalSpecies, animalNickname, homeType, homeNumber);
    }

    @Override
    public boolean feedAnimals(String homeType, int homeNumber, int amountOfFood, String animalFoodType) {
        return checkFeeding(homeType, homeNumber, amountOfFood, animalFoodType);
    }

    @Override
    public boolean buyFood(String animalFoodType, int amount) {
        return purchaseFood(animalFoodType, amount);
    }

    @Override
    public boolean relocateAnimal(String animalNickname, String homeType, int homeNumber) {
        return relocateSpecies(animalNickname, homeType, homeNumber);
    }

    @Override
    public boolean removeAnimal(String animalNickname) {
        return false;
    }

    @Override
    public void listOfTenants() {

    }

    public static void main(String[] args) {
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
        System.out.println(myZoo.accomodateAnimal("Leopard", "Bagheera", "Enclosure", 1));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Sid", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Zebra", "Vera", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Leopard", "Bagheera", "Enclosure", 1));
        System.out.println(myZoo.accomodateAnimal("Tiger", "Karl", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Tiger", "Karl", "Cage",5));
        System.out.println(myZoo.accomodateAnimal("Wildebeest", "Vega", "Enclosure",1));
        System.out.println(myZoo.accomodateAnimal("Giraffe", "Donald", "Enclosure", 4));
        System.out.println(myZoo.accomodateAnimal("Lion", "King", "Enclosure",0));
        System.out.println(myZoo.accomodateAnimal("Lion", "Kid", "Enclosure",0));
        System.out.println(myZoo.feedAnimals("Enclosure", 0, 100, "Carrot"));
        System.out.println(myZoo.feedAnimals("Enclosure", 0, 200, "Meat"));
        System.out.println(myZoo.relocateAnimal("Bagheera", "Enclosure", 1));
        System.out.println(myZoo.relocateAnimal("Bagheera", "Enclosure", 2));
    }
}
