package com.company.Week3;

import java.util.Objects;
import java.util.Scanner;

/**
 * ApeIntelligence.java
 * @author Loc Pham
 * @ID 1682512
 */

public class ApeIntelligence {
    static Scanner userInput = new Scanner(System.in); // Get the encrypted message individually
    static String wholeCommand; // Get the whole encrypted message (for part 3)

    /**
     * This function takes in all upcoming user input and choose a proper part to translate the encrypted message
     */
    public static int getUserInput(String i, Parts chosenPart) {
        if (Objects.equals(i, "-1")) {
            System.out.println(chosenPart.getDecodedCommand().trim());
            return -1;
        }

        chosenPart.getCommand(i);

        return getUserInput(userInput.next(), chosenPart);
    }

    public static void main(String[] args) {
        Parts selectedPart = new Parts(); // Get the selected part
        String part = userInput.nextLine(); // Get the user input of the selected part
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

/**
 * This class contains variables and function to run part 1 of the assignment
 */
class Part1 extends Parts {
    @Override
    // Command is what we need to translate to
    // Index is just the order of the command in the decrypted message

    /**
     * Get the translated command
     */
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
                break;
        }

        if (isChoosingCommand) {
            fullCommand.append(action);
            isChoosingCommand = false;
        }

    }

    /**
     * This function will translate upcoming message into an attack command
     */
    private void attack(String command) {
        if (!isChoosingCommand) {
            if (isGettingQuantity) {
                if (Objects.equals(command, "0")) {
                    quantity = " with all your";
                } else {
                    quantity = " with " + command;
                }
                fullCommand.append(quantity);
                isGettingQuantity = false;
            } else if (isGettingObject) {
                selectedObject = " " + getValidCommand(Integer.parseInt(command) - 1, objects);
                fullCommand.append(selectedObject);
                isGettingObject = false;
            }
        }
    }

    /**
     * This function will translate upcoming message into an search command
     */
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
        }
    }

    /**
     * This function will translate upcoming message into a retreat command
     */
    private void retreat(String command) {
        if (!isChoosingCommand) {
            if (isGettingRetreatLocation) {
                selectedRetreatingLocation = " " + getValidCommand(Integer.parseInt(command), retreatingLocations);
                fullCommand.append(selectedRetreatingLocation);
                isGettingRetreatLocation = false;
            } else if (isGettingQuantity) {
                if (command.equals("0")) {
                    quantity = " all your";
                } else {
                    quantity = " " + command;
                }
                fullCommand.append(" and move").append(quantity);
                isGettingQuantity = false;
            } else if (isGettingObject) {
                selectedObject = " " + getValidCommand(Integer.parseInt(command) - 1, objects);
                fullCommand.append(selectedObject).append(" to");
                isGettingObject = false;
            } else if (!isDirection) {
                selectedDirection = " " + getValidCommand(Integer.parseInt(command) - 1, directions);
                fullCommand.append(selectedDirection);
            }
        }
    }
}

/**
 * This class will execute part 2 of the assignment
 */
class Part2 extends Parts {
    /**
     * Class initializer
     */
    Part2() {
        fullCommand.append("Pack");
    }

    /**
     * This function return the translated message for part 2
     */
    @Override
    public void getCommand(String command) {
        if (command.equals("5")) {
            fullCommand.append(" containing");
            numberOfObjects = 2;
            if (isGettingNumberOfContainers) {
                numberOfContainers++;
            }
            isSpecification = true;
            isConsecutiveObject = false;
        } else if (command.equals("4")) {
            isGettingNumberOfContainers = false;
            numberOfObjects--;
            if (numberOfObjects == 0) {
                numberOfContainers--; // Container is filled
            }
            if (numberOfContainers != 0) {
                fullCommand.append(" and");
            }
            if (numberOfContainers == 0) {
                isDirection = true;
            }
            isConsecutiveObject = false;
            isSpecification = true;
        } else {
            if (isConsecutiveObject) {
                fullCommand.append(" containing");
                isConsecutiveObject = false;
            }
            isSpecification = false;
        }

        if (!isObjectCreated && !isSpecification && !isDirection) {
            fullCommand.append(createObject(command));
        }

        if (isObjectCreated) {
            isObjectCreated = false;
            isConsecutiveObject = true;
        }

        // Add direction
        if (isDirection && !isSpecification) {
            fullCommand.append(" and send one to");
            fullCommand.append(" ").append(getValidCommand(Integer.parseInt(command) - 1, directions));
        }

    }

    /**
     * This function create an object by taking in a material and an item
     */
    private String createObject(String command) {
        StringBuilder object = new StringBuilder();
        if (isMaterial) {
            String chosenMaterial = getValidCommand(Integer.parseInt(command), materials);
            object.append(" a ").append(chosenMaterial).append(" ");
            isMaterial = false;
        } else {
            object.append(getValidCommand(Integer.parseInt(command), item));
            isMaterial = true;
            isObjectCreated = true;
        }
        return object.toString();
    }
}

/**
 * This class will execute part 2 of the assignment
 */
class Part3 extends Parts {
    /**
     * Class initializer
     */
    Part3() {
        fullCommand.append("Build a concrete base");
    }

    /**
     * This function set up the first and only inverse recursion to get the number of painted boxes
     */
    @Override
    public void setUpInverseRecursion() {
        StringBuilder reversedCommand = new StringBuilder(wholeCommand);
        wholeCommand = reversedCommand.reverse().append(" ").toString();
        checkPaint = new Scanner(wholeCommand);
        // This to get the paintCount
        backwardRecursion(checkPaint.next());
        wholeCommand = wholeCommand.substring(constantPaintCount * 2 + 2) + " ";
    }

    /**
     * This function recurse backwards on the encoded command to see how many boxes are painted
     */
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

    /**
     * This function get the encoded digit and translate it into human language
     */
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
    String commandType = ""; // Store the selected resource command
    // Array of available monkeys
    String[] objects = {"orangutans", "chimps", "gorillas", "mounted chimps", "mounted orangutans"};

    // Boolean indication for Attach
    boolean isChoosingCommand = true; // See if the user is choosing a resource command
    boolean isGettingQuantity = true; // See if the user is choosing the quantity
    boolean isGettingObject = true; // See if the user is picking an object (monkey)

    // Strings to construct attack command
    String action = ""; // Get the chosen resource command
    String selectedObject = ""; // Get the selected object (monkey)
    String quantity = ""; // Get the quantity

    // Array of searching areas
    String[] searchingAreas = {"hills", "marshes", "caves", "woods"};
    // Array of directions
    String[] directions = {"North", "East", "West", "South", "South-East", "South-West", "North-East", "North-West"};
    // Array of locations to retreat to
    String[] retreatingLocations = {"Ape City", "Forbidden Zone", "Rocky Mountains"};
    // Array of humans to search for
    String[] humans = {"humans", "human males", "human females", "human children", "mutated humans"};
    // Array of possible materials
    String[] materials = {"wooden", "steel", "stone", "cotton"};
    // Array of available items
    String[] item = {"cage", "net", "helmet", "shield"};
    String selectedSearchingAreas; // Get the selected searching area
    String selectedDirection; // Get the selected direction
    String selectedHuman; // Get the selected human
    String selectedRetreatingLocation; // Get the selected retreating location

    // Boolean to construct search command
    boolean isChoosingSearchOption = true; // see if the user is choosing to search direction or location
    boolean isGettingLocation = true; //  see if the user decided to choose the location

    // Boolean to construct retreat command
    boolean isGettingRetreatLocation = true; // See if the user is picking a retreating location

    // Booleans for part 2
    boolean isGettingNumberOfContainers = true; // See if the recursion is still getting the number of container
    boolean isMaterial = true; // See if the up-coming character is a material or not
    boolean isConsecutiveObject = false; // See if the current object has any objects exist before it
    boolean isSpecification = false; // See if the upcoming character is a container specification
    boolean isObjectCreated = false; // See if an object is created or not (material + item)
    boolean isDirection = false; // See if a direction is chosen to send the container to
    int numberOfObjects = 2; // Get the number of object in the container
    int numberOfContainers = 0; // Get the number of containers

    // Variables for part 3
    String wholeCommand = ""; // Get the entire command as a string
    String boxSize = " 2x2x2"; // Store the current box size
    String selectedMaterial; // Store the selectedMaterial
    boolean getPaintCount = true; // Check if the recursion is getting the number of painted boxes
    boolean getPaintedObject = false; // Check if the recursion is getting the object painted on the box
    int constantPaintCount = 0; // Store the number of painted boxes
    int paintCount = -1; // Imitate the number of painted box
    Scanner checkPaint = new Scanner(wholeCommand); // Input to see if the current box is painted or not
    StringBuilder fullCommand = new StringBuilder(); // The final output

    /**
     * This function get the encoded digit and translate it into human language
     */
    public void getCommand(String command) {
    }

    /**
     * This function return the fully decrypted message of the chosen part for humanity
     */
    public String getDecodedCommand() {
        return this.fullCommand.toString();
    }

    /**
     * This function set up the first and only inverse recursion to get the number of painted boxes
     */
    public void setUpInverseRecursion() {

    }

    /**
     * This function check if the incoming message can be decoded, if not then return a "..."
     */
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
