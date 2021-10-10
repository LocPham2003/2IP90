package com.company.Week5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ZooResource {
    private final String[] keys = {"Enclosure", "Cage", "Sharable"};

    private ArrayList<Container> listOfCages = new ArrayList<>();
    private ArrayList<Container> listOfEnclosures = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> vegetables = new ArrayList<>();
    private ArrayList<String> meats = new ArrayList<>();
    private HashMap<String, ArrayList<String>> containerType = new HashMap<>();
    private HashMap<String, String> specialCase = new HashMap<>();



    public void configureZooResources() {
       String[] carnivores = {"Lion", "Tiger", "Bear", "Leopard"};
       String[] herbivores = {"Zebra", "Wildebeest", "Giraffe"};

       String[] vegetables = {"carrots", "hey", "corns", "grain"};
       String[] meats = {"meat", "chicken"};

       String[] sharableCage = {"Zebra", "Wildebeest", "Giraffe", "Lion"};

       containerType.put(keys[0], new ArrayList<>(Arrays.asList(herbivores)));
       containerType.put(keys[1], new ArrayList<>(Arrays.asList(carnivores)));
       containerType.put(keys[2], new ArrayList<>(Arrays.asList(sharableCage)));

       this.vegetables.addAll(List.of(vegetables));
       this.meats.addAll(List.of(meats));

       specialCase.put("Wildebeest", "carrots");

       for (int i = 0; i < 20; i++) {
           Container container = new Container();
           listOfCages.add(container);

           if (i < 5) {
               Container container1 = new Container();
               listOfEnclosures.add(container1);
           }
       }
     }

     public boolean accommodateCage(String animalSpecies, String animalNickname, String homeType, int homeNumber) {
        boolean successfulAccommodation = false;
        ArrayList<Container> selectedContainer = new ArrayList<>();
        // Check if the species is put in the right cage
         if (!this.names.contains(animalNickname)) {
             this.names.add(animalNickname);
             // Check if the upcoming animal is being put in the right container
             if (containerType.get(homeType).contains(animalSpecies)) {
                 // Check to see if we are putting animal in enclosure or cage
                 if (homeType.equalsIgnoreCase("Enclosure")) {
                     selectedContainer = listOfEnclosures;
                 } else {
                     selectedContainer = listOfCages;
                 }

                 // Check if the upcoming animal can be put in the selected container
                 try {
                     // Get the existing animal of the current container
                         String existingAnimal = selectedContainer.get(homeNumber).getCurrentAnimal();
                         boolean canBeShared = containerType.get(keys[2]).contains(existingAnimal) && containerType.get(keys[2]).contains(animalSpecies);
                         boolean isEmpty = existingAnimal.isEmpty();
                         // Check if the upcoming animal can share the same cage with the current animal
                         if (canBeShared || isEmpty) {
                             selectedContainer.get(homeNumber).addToContainer(animalSpecies, animalNickname);
                             successfulAccommodation = true;
                         } else {
                             System.out.println(animalSpecies + " can't live with " + existingAnimal);
                         }
                        
                 } catch (IndexOutOfBoundsException e) {
                     System.out.println("Our zoo does not have " + homeType + " number " + homeNumber);
                 }
             } else {
                 System.out.println(animalSpecies + " doesn't live in " + homeType);
             }
         } else {
             System.out.println(animalSpecies + " with nickname Bagheera already lives in our Zoo!");
         }

         // Update the list of containers
         if (homeType.equalsIgnoreCase("Enclosure")) {
             listOfEnclosures = selectedContainer;
         } else {
             listOfCages = selectedContainer;
         }

         return successfulAccommodation;
     }
}
