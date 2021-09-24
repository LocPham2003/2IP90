package com.company.Week3;

public class Part1 extends Parts {
    private int commandType;
    private String[] object = {"orangutans", "chimps", "gorillas", "mounted chimps", "mounted orangutans"};

    // Strings to construct attack command
    private String action;
    private String selectedObject;
    private String quantity;

    // Strings to construct the search command
    private String[] location = {"hills", "marshes", "caves", "woods"};
    private String[] direction = {"North", "East", "West", "South", "South-East",  "South-West", "North-East",  "North-West"};
    private String selectedLocation;
    private String selectedDirection;

    private boolean isDirection = false;
    // The command after being decrypted
    StringBuilder fullCommand = new StringBuilder();

    @Override
    // Command is what we need to translate to
    // Index is just the order of the command in the decrypted message
    public void getCommand(int command, int index) {
        if (index == 1) {
            commandType = command;
        }

        switch (commandType) {
            case 1:
                action = "Attack";
                this.attack(command, index);
                break;
            case 2:
                action = "Search";
                this.search(command, index);
                break;
            case 3:
                action = "Retreat to";
                this.retreat();
                break;
        }

        if (index == 1) {
            fullCommand.append(action).append(" ");
        }


    }

    private void attack(int command, int commandIndex) {
        switch (commandIndex) {
            case 2:
                if (command == 0) {
                    quantity = "with all your ";
                } else {
                    quantity = Integer.toString(command);
                }
                fullCommand.append(quantity).append(" ");
                break;
            case 3:
                selectedObject = object[command - 1];
                fullCommand.append(selectedObject).append(" ");
                break;
        }
    }

    private void search(int command, int index) {
        switch (index) {
            case 2:
                if (command == 0) {
                    isDirection = false;
                } else {
                    isDirection = true;
                    selectedDirection = direction[command - 1];
                }
                break;
            case 3:
                if (!isDirection) {
                    quantity = Integer.toString(command);
                } else {
                    
                }

        }
    }

    private void retreat() {
    }

}
