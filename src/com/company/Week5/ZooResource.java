package com.company.Week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

abstract class ZooResource {
    private final String[] keys = {"Enclosure", "Cage", "Sharable"};
    private ArrayList<Container> listOfCages = new ArrayList<>();
    private ArrayList<Container> listOfEnclosures = new ArrayList<>();
    private ArrayList<String> animalNames = new ArrayList<>();
    private ArrayList<Container> selectedListOfContainer = new ArrayList<>();
    private HashMap<String, Integer> foodStorage = new HashMap<>();
    private HashMap<String, ArrayList<String>> diet = new HashMap<>();
    private HashMap<String, ArrayList<String>> containerType = new HashMap<>();

    private static class Container {
        private final ArrayList<Animal> listOfAnimals = new ArrayList<>();

        public int getNumberOfTenants() {
            return listOfAnimals.size();
        }

        public ArrayList<Animal> getCurrentAnimal() {
            ArrayList<Animal> availableAnimals = new ArrayList<>();

            for (Animal i : this.listOfAnimals) {
                if (!availableAnimals.contains(i)) {
                    availableAnimals.add(i);
                }
            }

            return availableAnimals;
        }

        public String outPutDietMessage() {
            StringBuilder stringBuilder = new StringBuilder();

            for (Animal i : listOfAnimals) {
                stringBuilder.append(i.getAnimalSpecies()).append(" ").append(i.getAnimalNickname()).append(", ");
            }

            return stringBuilder.toString();
        }

        public String outPutAnimals() {
            StringBuilder stringBuilder = new StringBuilder();

            for (Animal i : listOfAnimals) {
                stringBuilder.append(i.getAnimalSpecies()).append(" ");
            }

            return stringBuilder.toString();
        }

        public void addToContainer(String animalSpecies, String animalNickname) {
            Animal animal = new Animal(animalSpecies, animalNickname);
            this.listOfAnimals.add(animal);
        }
    }

    private static class Animal {
        private final String animalSpecies;
        private final String animalNickname;

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

    private ArrayList<Container> getSelectedContainer(String homeType) {
        ArrayList<Container> arr;
        if (homeType.equalsIgnoreCase("Enclosure")) {
            arr = listOfEnclosures;
        } else {
            arr = listOfCages;
        }

        return arr;
    }

    private void updateContainers(String homeType) {
        if (homeType.equalsIgnoreCase("Enclosure")) {
            listOfEnclosures = selectedListOfContainer;
        } else {
            listOfCages = selectedListOfContainer;
        }
        selectedListOfContainer = new ArrayList<>();

    }

    void configureZooResources() {
        String[] carnivores = {"Lion", "Tiger", "Bear", "Leopard"};
        String[] herbivores = {"Zebra", "Wildebeest", "Giraffe"};

        String[] enclosures = {"Zebra", "Wildebeest", "Giraffe", "Lion", "Tiger", "Bear", "Leopard"};
        String[] cages = {"Lion", "Tiger", "Bear", "Leopard"};

        String[] veggies = {"Carrot", "Hay", "Corn", "Grain"};
        String[] meat = {"Meat", "Chicken"};
        String[] sharableCage = {"Zebra", "Wildebeest", "Giraffe", "Lion", "Bear"};

        containerType.put(keys[0], new ArrayList<>(Arrays.asList(enclosures)));
        containerType.put(keys[1], new ArrayList<>(Arrays.asList(cages)));
        containerType.put(keys[2], new ArrayList<>(Arrays.asList(sharableCage)));

        for (String i : veggies) {
            foodStorage.put(i, 100);
        }

        for (String i : meat) {
            foodStorage.put(i, 100);
        }

        // Construct food diet of all animals
        for (String i : carnivores) {
            ArrayList<String> diet = new ArrayList<>(Arrays.asList(meat));

            if (i.equalsIgnoreCase("bear")) {
                diet.add(veggies[0]);
            }

            this.diet.put(i, diet);
        }


        for (String i : herbivores) {
            ArrayList<String> diet = new ArrayList<>(Arrays.asList(veggies));

            if (i.equalsIgnoreCase("wildebeest")) {
                diet.remove("carrots");
            }

            this.diet.put(i, diet);
        }

        for (int i = 0; i < 20; i++) {
            Container container  = new Container();
            listOfCages.add(container);

            if (i < 5) {
                Container container1 = new Container();
                listOfEnclosures.add(container1);
            }

        }

    }

    private boolean doesNameExist(String name, String species) {
        boolean exist = false;
        for (Container enclosures : listOfEnclosures) {
            for (Animal a : enclosures.getCurrentAnimal()) {
                if (a.getAnimalNickname().equals(name) && a.getAnimalSpecies().equals(species)) {
                    exist = true;
                    break;
                }
            }
        }

        return exist;
    }

    boolean accommodateCage(String animalSpecies, String animalNickname, String homeType, int homeNumber) {
        boolean successfulAccommodation = false;

        selectedListOfContainer = getSelectedContainer(homeType);
        // Check if the species is put in the right cage
         if (!doesNameExist(animalNickname, animalSpecies)) {
             this.animalNames.add(animalNickname);
             // Check if the upcoming animal is being put in the right container
             if (containerType.get(homeType).contains(animalSpecies)) {

                // Check if the upcoming animal can be put in the selected container
                 try {
                     // Get the existing animal of the current container
                        int numberOfTenants = selectedListOfContainer.get(homeNumber).getNumberOfTenants();
                         if (numberOfTenants == 4 || numberOfTenants == 20) {
                             System.out.println(homeType + " " + homeNumber + " is already full");
                         } else {
                             ArrayList<Animal> existingAnimal = selectedListOfContainer.get(homeNumber).getCurrentAnimal();
                             boolean canBeShared = true;

                             for (Animal i : existingAnimal) {
                                 if (!(containerType.get(keys[2]).contains(i.getAnimalSpecies()) && containerType.get(keys[2]).contains(animalSpecies))) {
                                     canBeShared = false;
                                     break;
                                 }
                             }

                             boolean isEmpty = existingAnimal.isEmpty();
                             // Check if the upcoming animal can share the same cage with the current animal
                             if (canBeShared || isEmpty) {
                                 selectedListOfContainer.get(homeNumber).addToContainer(animalSpecies, animalNickname);
                                 successfulAccommodation = true;
                             } else {
                                 System.out.println(animalSpecies + " can't live with " + selectedListOfContainer.get(homeNumber).outPutAnimals());
                             }
                         }
                        
                 } catch (IndexOutOfBoundsException e) {
                     System.out.println("Our zoo does not have " + homeType + " number " + homeNumber);
                 }
             } else {
                 System.out.println(animalSpecies + " doesn't live in " + homeType);
             }
         } else {
             System.out.println(animalSpecies + " with nickname " + animalNickname + " already lives in our Zoo!");
         }

         updateContainers(homeType);

         return successfulAccommodation;
     }

    boolean checkFeeding(String homeType, int homeNumber, int amountOfFood, String animalFoodType) {
        boolean isFed = false;

        selectedListOfContainer = getSelectedContainer(homeType);
        try {
            Container container = selectedListOfContainer.get(homeNumber);

            if (foodStorage.get(animalFoodType) >= amountOfFood) {
                // Check if all animals in container can eat that food
                ArrayList<Animal> animalsInContainer = container.getCurrentAnimal();
                boolean allAreFed = true;
                for (Animal i : animalsInContainer) {
                    if (!diet.get(i.getAnimalSpecies()).contains(animalFoodType)) {
                        allAreFed = false;
                    }
                }

                if (allAreFed) {
                    isFed = true;
                } else {
                    System.out.println(container.outPutDietMessage() + "doesn't eat " + animalFoodType);
                }



            } else {
                System.out.println("Not enough " + animalFoodType + ", current amount " + foodStorage.get(animalFoodType));
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println(homeType + " " + homeNumber + " does not exist");
        }

        return isFed;
     }
}
