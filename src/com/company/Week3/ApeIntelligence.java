package com.company.Week3;

import java.util.Objects;
import java.util.Scanner;

/**
 * ApeIntelligence.java
 * @author Loc Pham
 * @ID 1682512
 */

public class ApeIntelligence {
    static Scanner parsedEncodedCommand;
    public static int getUserInput(String i, int index, Parts chosenPart) {
        if (Objects.equals(i, "-1")) {
            System.out.println(chosenPart.getDecodedCommand());
            return -1;
        }

        chosenPart.getCommand(i, index);

        index++;

        return getUserInput(parsedEncodedCommand.next(), index, chosenPart);
    }
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        Parts selectedPart = new Parts();
        String selectedPartString = userInput.nextLine();
        String encodedCommand = userInput.nextLine();

        parsedEncodedCommand = new Scanner(encodedCommand);

        switch (selectedPartString) {
            case "Part 1":
                selectedPart = new Part1();
                break;
            case "Part 2":
                selectedPart = new Part2();
                break;
            case "Part 3":
                selectedPart = new Part3();
                break;
        }

        getUserInput(parsedEncodedCommand.next(), 0, selectedPart);

    }
}

class Part1 extends Parts {
    @Override
    // Command is what we need to translate to
    // Index is just the order of the command in the decrypted message
    public void getCommand(String command, int index) {
        if (index == 0) {
            commandType = command;
        }

        switch (commandType) {
            case "0":
                action = "Attack";
                this.attack(command, index);
                break;
            case "1":
                action = "Search";
                this.search(command, index);
                break;
            case "2":
                action = "Retreat to";
                this.retreat(command, index);
                break;
        }

        if (index == 0) {
            fullCommand.append(action);
        }
    }

    private void attack(String command, int commandIndex) {
        if (!isInvalidCommand) {
            switch (commandIndex) {
                case 1:
                    if (Objects.equals(command, "0")) {
                        quantity = " with all your ";
                    } else {
                        quantity = " " + command;
                    }
                    fullCommand.append(quantity);
                    break;
                case 2:
                    selectedObject = objects[Integer.parseInt(command) - 1];
                    fullCommand.append(selectedObject);
                    break;
            }
        }

    }

    private void search(String command, int index) {
            switch (index) {
                case 1:
                    if (command.equals("0")) {
                        isDirection = false;
                    } else {
                        isDirection = true;
                        selectedDirection = getValidCommand(Integer.parseInt(command) - 1, directions);
                        fullCommand.append(selectedDirection).append(" ");
                    }
                    break;
                case 2:
                    if (!isDirection) {
                        quantity = command + " ";
                    } else {
                        selectedHuman = getValidCommand(Integer.parseInt(command), humans);
                        fullCommand.append("and look for ").append(selectedHuman).append(" ");
                    }
                    break;
                case 3:
                    selectedSearchingAreas = getValidCommand(Integer.parseInt(command), searchingAreas);
                    fullCommand.append(quantity).append(selectedSearchingAreas).append(" ");
                    break;
                case 4:
                    selectedHuman = getValidCommand(Integer.parseInt(command), humans);
                    fullCommand.append("and look for ").append(selectedHuman);
                    break;
            }

    }

    private void retreat(String command, int index) {
            switch (index) {
                case 1:
                    selectedRetreatingLocation = getValidCommand(Integer.parseInt(command), retreatingLocations);
                    fullCommand.append(selectedRetreatingLocation).append(" and move ");
                    break;
                case 2:
                    if (command.equals("0")) {
                        quantity = " all ";
                    } else {
                        quantity = command;
                    }
                    fullCommand.append(quantity).append(" ");
                    break;
                case 3:
                    selectedObject = getValidCommand(Integer.parseInt(command), objects);
                    fullCommand.append(selectedObject).append(" to ");
                    break;
                case 4:
                    selectedDirection = getValidCommand(Integer.parseInt(command) - 1, directions);
                    fullCommand.append(selectedDirection);
                    break;
        }
    }

    @Override
    public String getDecodedCommand() {
        return fullCommand.toString();
    }

}

class Part2 extends Parts {
    Part2() {
        fullCommand.append("Pack ");
    }

    @Override
    public void getCommand(String command, int index) {
        if (command.equals("5")) {
            fullCommand.append(" containing ");
            isFirstObj = true;
            objectInContainer = true;
            isObjectCreated = false;
            consecutiveObject = false;
            numberOfObjects = 0;
        } else if (command.equals("4")) {
            if (!objectInContainer) {
                isObjectCreated = false;
            }
            numberOfObjects++;

            if (numberOfObjects == 2) {
                containerEnd = true;
            }

            consecutiveObject = false;
        }

        // If there exist an object right after the previous object
        if ((consecutiveObject && isObjectCreated)) {
            fullCommand.append(" containing ");
        }

        // If an object is inside another container, and it exists right after the previous object
        if ((numberOfObjects < 2 && isObjectCreated) && objectInContainer) {
            fullCommand.append(" and ");
        } else if (numberOfObjects >= 2) {
            objectInContainer = false;
        }

        // Add direction
        if ((numberOfObjects > 2)) {
            fullCommand.append(" and send one to ");
            fullCommand.append(getValidCommand(Integer.parseInt(command) - 1, directions));
        }

        if (containerEnd) {
            numberOfObjects++;
        }

        if (isFirstObj && isObjectCreated) {
            isFirstObj = false;
            isObjectCreated = false;
        }

        if (!isObjectCreated) {
            fullCommand.append(createObject(command));
        }
    }

    private String createObject(String command) {
        StringBuilder object = new StringBuilder();

        if (isMaterial) {
            String chosenMaterial = getValidCommand(Integer.parseInt(command), materials);
            if (isValidObject) {
                object.append("a ").append(chosenMaterial).append(" ");
                isMaterial = false;
            }
            isValidObject = true;
        } else {
            object.append(getValidCommand(Integer.parseInt(command), item));
            isMaterial = true;
            isObjectCreated = true;
        }

        return object.toString();
    }
}

class Part3 extends Parts {

}

class Parts {
    String chosenPart = "";
    String commandType = "";
    String[] objects = {"orangutans", "chimps", "gorillas", "mounted chimps", "mounted orangutans"};
    boolean isInvalidCommand = false;
    // Strings to construct attack command
    String action = "";
    String selectedObject = "";
    String quantity = "";

    // Strings to construct the search command
    String[] searchingAreas = {"hills", "marshes", "caves", "woods"};
    String[] directions = {"North", "East", "West", "South", "South-East",  "South-West", "North-East",  "North-West"};
    String[] retreatingLocations = {"Ape City","Forbidden Zone","Rocky Mountains"};
    String[] humans = {"humans","human males","human females","human children","mutated humans"};
    String[] materials = {"wooden","steel","stone","cotton"};
    String[] item = {"cage","net","helmet","shield"};
    String selectedSearchingAreas;
    String selectedDirection;
    String selectedHuman;
    String selectedRetreatingLocation;

    boolean isValidObject = true;
    boolean isMaterial = true;
    boolean isObjectCreated = false;
    boolean isDirection = false;
    boolean isFirstObj = true;
    boolean objectInContainer = false;
    boolean containerEnd = false;
    boolean consecutiveObject = true;

    int numberOfObjects = 0;

    StringBuilder fullCommand = new StringBuilder();

    public void getCommand(String command, int index) {

    }

    public String getDecodedCommand() {
        return this.fullCommand.toString();
    }

    public String getValidCommand(int index, String[] arr) {
        String selectedString = "";
        try {
           selectedString = arr[index];
        } catch (IndexOutOfBoundsException e) {
            selectedString = "...";
            isValidObject = false;
        }
        return selectedString;
    }
}



