package com.company.Week3;

import java.util.Objects;
import java.util.Scanner;

/**
 * ApeIntelligence.java
 * @author Loc Pham
 * @ID 1682512
 */

public class ApeIntelligence {
    static Scanner userInput = new Scanner(System.in);
    static String wholeCommand;

    public static int getUserInput(String i, Parts chosenPart) {
        if (Objects.equals(i, "-1")) {
            System.out.println(chosenPart.getDecodedCommand().trim());
            return -1;
        }

        if (chosenPart.firstIndex.equals("")) {
            chosenPart.firstIndex = i;
        }

        chosenPart.getCommand(i);

        return getUserInput(userInput.next(), chosenPart);
    }

    public static void main(String[] args) {
        Parts selectedPart = new Parts();
        String part = userInput.nextLine();
        wholeCommand = userInput.nextLine();
        userInput = new Scanner(wholeCommand);

        switch (part) {
            case "Part 1":
                selectedPart = new Part1();
                break;
            case "Part 2":
                selectedPart = new Part2();
                break;
            case "Part 3":
                selectedPart = new Part3();
                selectedPart.wholeCommand = wholeCommand.substring(0, wholeCommand.length() - 3);
                selectedPart.setUpInverseRecursion();
                break;
        }
        getUserInput(userInput.next(), selectedPart);

    }
}

class Part1 extends Parts {
    @Override
    // Command is what we need to translate to
    // Index is just the order of the command in the decrypted message
    public void getCommand(String command) {
        if (isChoosingCommand) {
            commandType = command;
        }

        switch (commandType) {
            case "0":
                action = "Attack";
                this.attack(command);
                break;
            case "1":
                action = "Search";
                this.search(command);
                break;
            case "2":
                action = "Retreat to";
                this.retreat(command);
                break;
            default:
                action = "...";
                if (isChoosingCommand) {
                    fullCommand.append(action);
                }
                isChoosingCommand = false;
                break;
        }

    }

    private void attack(String command) {
        if (!isChoosingCommand) {
            if (isGettingQuantity) {
                if (Objects.equals(command, "0")) {
                    quantity = " with all your ";
                } else {
                    quantity = " " + command;
                }
                fullCommand.append(quantity);
                isGettingQuantity = false;
            } else if (isGettingObject) {
                selectedObject = objects[Integer.parseInt(command) - 1];
                fullCommand.append(selectedObject);
                isGettingObject = false;
            }
        } else {
            fullCommand.append(action);
            isChoosingCommand = false;
        }

    }

    private void search(String command) {
        if (!isChoosingCommand) {
            if (isChoosingSearchOption) {
                if (command.equals("0")) {
                    isDirection = false;
                } else {
                    selectedDirection = " " + directions[Integer.parseInt(command) - 1];
                    fullCommand.append(selectedDirection);
                }
                isChoosingSearchOption = false;
            } else if (!isDirection && isGettingQuantity) {
                quantity = " " + command;
                fullCommand.append(quantity);
                isGettingQuantity = false;
            } else if (!isDirection && isGettingLocation) {
                selectedSearchingAreas = " " + searchingAreas[Integer.parseInt(command)];
                fullCommand.append(selectedSearchingAreas);
                isGettingLocation = false;
            } else {
                selectedHuman = " and look for " + humans[Integer.parseInt(command)];
                fullCommand.append(selectedHuman);
            }
        } else {
            fullCommand.append(action);
            isChoosingCommand = false;
        }

    }

    private void retreat(String command) {
        if (!isChoosingCommand) {
            if (isGettingRetreatLocation) {
                selectedRetreatingLocation = " " + getValidCommand(Integer.parseInt(command), retreatingLocations);
                fullCommand.append(selectedRetreatingLocation).append(" and move");
                isGettingRetreatLocation = false;
            } else if (isGettingQuantity) {
                if (command.equals("0")) {
                    quantity = " all";
                } else {
                    quantity = " " + command;
                }
                fullCommand.append(quantity);
                isGettingQuantity = false;
            } else if (isGettingObject) {
                selectedObject = " " + getValidCommand(Integer.parseInt(command) - 1, objects);
                fullCommand.append(selectedObject).append(" to");
                isGettingObject = false;
            } else if (!isDirection) {
                selectedDirection = " " + getValidCommand(Integer.parseInt(command) - 1, directions);
                fullCommand.append(selectedDirection);
            }
        } else {
            fullCommand.append(action);
            isChoosingCommand = false;
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
    public void getCommand(String command) {
        if (command.equals("5")) {
            fullCommand.append(" containing ");
            isFirstObj = true;
            objectInContainer = true;
            isObjectCreated = false;
            consecutiveObject = false;
            isSpecification = true;
            numberOfObjects = 0;
        } else if (command.equals("4")) {
            if (!objectInContainer) {
                numberOfObjects++;
            }
            numberOfObjects++;
            isSpecification = true;
            if (numberOfObjects == 2) {
                containerEnd = true;
            }
            consecutiveObject = false;
        } else {
            isSpecification = false;
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

        if (!isObjectCreated && !isSpecification && !containerEnd) {
            fullCommand.append(createObject(command));
        }
    }

    private String createObject(String command) {
        StringBuilder object = new StringBuilder();

        if (isMaterial) {
            String chosenMaterial = getValidCommand(Integer.parseInt(command), materials);

            object.append("a ").append(chosenMaterial).append(" ");

            isMaterial = false;
        } else {
            object.append(getValidCommand(Integer.parseInt(command), item));
            isMaterial = true;
            isObjectCreated = true;
        }

        return object.toString();
    }
}


class Part3 extends Parts {
    Part3() {
        fullCommand.append("Build a concrete base");
    }

    @Override
    public void setUpInverseRecursion() {
        StringBuilder reversedCommand = new StringBuilder(wholeCommand);
        wholeCommand = reversedCommand.reverse().append(" ").toString();
        checkPaint = new Scanner(wholeCommand);
        // This to get the paintCount
        backwardRecursion(checkPaint.next());
        wholeCommand = wholeCommand.substring(constantPaintCount * 2 + 2) + " ";
    }

    public int backwardRecursion(String i) {
        if (i.equals("4")) {
            getPaintCount = false;
            return 0;
        } else {
            if (getPaintCount) {
                constantPaintCount++;
            }
        }

        if (paintCount > 0 && i.equals(selectedMaterial)) {
            boxSize = " 1x1x1";
            return 0;
        } else {
            paintCount--;
        }

        if (paintCount == 0) {
            return 0;
        }

        return backwardRecursion(checkPaint.next());
    }

    @Override
    public void getCommand(String command) {
        selectedMaterial = command; // Get the material of the current building block
        paintCount = constantPaintCount;

        checkPaint = new Scanner(wholeCommand);

        if (command.equals("4") && !getPaintedObject) {
            getPaintedObject = true;
        }

        // Get the correct box size, do not run if the program is reading digits after number 4
        if (!getPaintedObject) {
            backwardRecursion(checkPaint.next());
            selectedMaterial = " " + getValidCommand(Integer.parseInt(command), materials);
            fullCommand.append(" on top of which is a").append(boxSize).append(selectedMaterial).append(" cube");
        } else {
            if (!command.equals("4")) {
                selectedObject = getValidCommand(Integer.parseInt(command) - 1, objects);
                fullCommand.append(" with ").append(selectedObject).append(" painted on the sides");
            }
        }
    }
}

class Parts {
    String commandType = "";
    String[] objects = {"orangutans", "chimps", "gorillas", "mounted chimps", "mounted orangutans"};

    // Boolean indication for Attach
    boolean isChoosingCommand = true;
    boolean isGettingQuantity = true;
    boolean isGettingObject = true;

    // Strings to construct attack command
    String action = "";
    String selectedObject = "";
    String quantity = "";

    // Strings to construct the search command
    String[] searchingAreas = {"hills", "marshes", "caves", "woods"};
    String[] directions = {"North", "East", "West", "South", "South-East", "South-West", "North-East", "North-West"};
    String[] retreatingLocations = {"Ape City", "Forbidden Zone", "Rocky Mountains"};
    String[] humans = {"humans", "human males", "human females", "human children", "mutated humans"};
    String[] materials = {"wooden", "steel", "stone", "cotton"};
    String[] item = {"cage", "net", "helmet", "shield"};
    String selectedSearchingAreas;
    String selectedDirection;
    String selectedHuman;
    String selectedRetreatingLocation;

    // Boolean to construct search command
    boolean isChoosingSearchOption = true;
    boolean isGettingLocation = true;

    // Boolean to construct retreat command
    boolean isGettingRetreatLocation = true;


    // Booleans for part 2
    boolean isMaterial = true;
    boolean isSpecification = false;
    boolean isObjectCreated = false;
    boolean isDirection = false;
    boolean isFirstObj = true;
    boolean objectInContainer = false;
    boolean containerEnd = false;
    boolean consecutiveObject = true;
    int numberOfObjects = 0;

    // Variables for part 3
    String wholeCommand = "";
    String firstIndex = "";
    String boxSize = " 2x2x2";
    String selectedMaterial;
    boolean getPaintCount = true;
    boolean getPaintedObject = false;
    int constantPaintCount = 0;
    int paintCount = -1;
    Scanner checkPaint = new Scanner(wholeCommand);
    StringBuilder fullCommand = new StringBuilder();

    public void getCommand(String command) {
    }

    public String getDecodedCommand() {
        return this.fullCommand.toString();
    }

    public void setUpInverseRecursion() {

    }

    public String getValidCommand(int index, String[] arr) {
        String selectedString;
        try {
            selectedString = arr[index];
        } catch (IndexOutOfBoundsException e) {
            selectedString = "...";
        }
        return selectedString;
    }

}
