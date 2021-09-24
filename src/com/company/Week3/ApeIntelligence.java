package com.company.Week3;

import java.io.IOException;
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
    public static void main(String[] args) throws IOException {
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
    private String commandType = "";
    private String[] object = {"orangutans", "chimps", "gorillas", "mounted chimps", "mounted orangutans"};

    // Strings to construct attack command
    private String action = "";
    private String selectedObject = "";
    private String quantity = "";

    // Strings to construct the search command
    private String[] location = {"hills", "marshes", "caves", "woods"};
    private String[] direction = {"North", "East", "West", "South", "South-East",  "South-West", "North-East",  "North-West"};
    private String[] humans = {"humans","human males","human females","human children","mutated humans"};
    private String selectedLocation;
    private String selectedDirection;
    private String selectedHuman;

    private boolean isDirection = false;
    // The command after being decrypted
    StringBuilder fullCommand = new StringBuilder();

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
                this.retreat();
                break;
        }

        if (index == 0) {
            fullCommand.append(action).append(" ");
        }


    }

    private void attack(String command, int commandIndex) {
        switch (commandIndex) {
            case 1:
                if (Objects.equals(command, "0")) {
                    quantity = "with all your";
                } else {
                    quantity = command;
                }
                fullCommand.append(quantity).append(" ");
                break;
            case 2:
                selectedObject = object[Integer.parseInt(command) - 1];
                fullCommand.append(selectedObject).append(" ");
                break;
        }
    }

    private void search(String command, int index) {
        switch (index) {
            case 1:
                if (command.equals("0")) {
                    isDirection = false;
                } else {
                    isDirection = true;
                    selectedDirection = direction[Integer.parseInt(command)];
                    fullCommand.append(selectedDirection).append(" ");
                }
                break;
            case 2:
                if (!isDirection) {
                    quantity = command + " ";
                } else {
                    selectedHuman = humans[Integer.parseInt(command)];
                    fullCommand.append("and look for ").append(selectedHuman).append(" ");
                }
                break;
            case 3:
                selectedLocation = location[Integer.parseInt(command)];
                fullCommand.append(quantity).append(selectedLocation).append(" ");
                break;
            case 4:
                selectedHuman = humans[Integer.parseInt(command)];
                fullCommand.append("and look for ").append(selectedHuman).append(" ");
                break;
        }
    }

    private void retreat() {

    }

    @Override
    public String getDecodedCommand() {
        return fullCommand.toString();
    }

    @Override
    public void whatCommand() {
        System.out.println(this.action);
    }
}

class Part2 extends Parts {

}

class Parts {
    StringBuilder fullCommand = new StringBuilder();

    public void getCommand(String command, int index) {

    }

    public String getDecodedCommand() {
        return this.fullCommand.toString();
    }

    public void whatCommand() {
        System.out.println("none chosen");
    }
}

class Part3 extends Parts {

}

