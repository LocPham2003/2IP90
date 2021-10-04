package com.company.Week4;// CycleBubble -- try out the cycle bubble solution

// import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class CycleBubble {
    final int BUBBLE_SIZE;     // number of riders in the bubble
    final int TESTCAP;        // number of tests available

    private final int badguy;   //id of infected rider; 0 if no one is infected
    private int testcount = 0;  // number of tests performed so far
    private boolean hasCheated = false;   // has the user cheated?
    private Random random = new Random();  

    public CycleBubble(int size, int testcap) {
        if (size <= 0 || testcap <= 0) {
            BUBBLE_SIZE = 100;
            TESTCAP = 7;
        } else {
            BUBBLE_SIZE = size;
            TESTCAP = testcap;
        }
        badguy = random.nextInt(BUBBLE_SIZE + 1);

    }
    
    public CycleBubble() {
        this(100, 7);
    }

    public boolean test(List<Integer> batch) {
        return batch.contains(badguy);
    }

    public boolean test(int[] batch) {
        if (testcount >= TESTCAP) {
            throw new IllegalStateException("Number of available tests has been reached. We didn't see this coming. --The RIVM");
        }
        testcount++;
        // List<Integer> list = Arrays.asList(batch);
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int n : batch) {
            list.add(n);
        }
        return test(list);
    }

    public int cheat() {
        hasCheated = true;
        return badguy;
    }

    public String check(int id) {
        if (testcount > TESTCAP) {
            throw new IllegalStateException("Check already used.");
        }
        testcount = TESTCAP + 1; // to prevent checking further    
        if (id == badguy) {
            String solution = "Indeed, "+(badguy==0 ? "no one was infected," : badguy+" is infected. ");
            return solution+"You solved it, "+(hasCheated ? "with cheating, though." : "without cheating. Very good!");
        } else {
            return "Alas. You blamed the wrong guy.";
        }
    }

    public static void main(String[] args) {
        ArrayList<String> cyclist = new ArrayList<>();
        ArrayList<String> checkList = new ArrayList<>();

        final int badGuy = new Random().nextInt(100 + 1);   //id of infected rider; 0 if no one is infected
        StringBuilder binaryBadGuy = new StringBuilder();
        binaryBadGuy.append("0".repeat(7 - Integer.toBinaryString(badGuy).length()));
        binaryBadGuy.append(Integer.toBinaryString(badGuy));

        boolean isPositive = false;

        StringBuilder binary = new StringBuilder();

        for (int i = 1; i <= 100; i++) {
            binary.append("0".repeat(7 - Integer.toBinaryString(i).length()));
            binary.append(Integer.toBinaryString(i));
            cyclist.add(binary.toString());
        }

        for (int i = 0; i < 7; i++) {
            for (String s : cyclist) {
                if (s.charAt(i) == (char) i) {
                    checkList.add(s);
                }
            }
            for (String checkers : checkList) {

            }
        }

        System.out.println(binaryBadGuy);
    }
}