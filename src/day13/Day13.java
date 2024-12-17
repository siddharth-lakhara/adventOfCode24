package day13;

import common.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

    Pattern BUTTON_PATTERN = Pattern.compile(": X\\+(\\d+), Y\\+(\\d+)");
    Pattern TARGET_PATTERN = Pattern.compile(": X=(\\d+), Y=(\\d+)");

    public void solvePart1() {
        ArrayList<Integer> tokens = new ArrayList<>();
        long answer = 0;

        try (Scanner inStream = Helper.readFile("day13_input")) {
            while (inStream.hasNextLine()) {
                String buttonA = inStream.nextLine();
                int[] buttonADetails = extractButtonDetails(buttonA);

                String buttonB = inStream.nextLine();
                int[] buttonBDetails = extractButtonDetails(buttonB);

                String prize = inStream.nextLine();
                int[] prizeDetails = extractPrizeDetails(prize);

                int token = calculateMinTokens(buttonADetails, buttonBDetails, prizeDetails);
                tokens.add(token);

//                ignore the empty line between data
                if (inStream.hasNextLine()) {
                    inStream.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        for (int t: tokens) {
            answer += t;
        }
        System.out.println("Answer: " + answer);
    }

    private int[] extractButtonDetails(String buttonStr) {
        int x;
        int y;

        Matcher buttonMatcher = BUTTON_PATTERN.matcher(buttonStr);

        buttonMatcher.find();
        x = Integer.parseInt(buttonMatcher.group(1));
        y = Integer.parseInt(buttonMatcher.group(2));

        return new int[]{x, y};
    }

    private int[] extractPrizeDetails(String prizeStr) {
        int x;
        int y;

        Matcher prizeMatcher = TARGET_PATTERN.matcher(prizeStr);

        prizeMatcher.find();
        x = Integer.parseInt(prizeMatcher.group(1));
        y = Integer.parseInt(prizeMatcher.group(2));

        return new int[]{x, y};
    }

    private int calculateMinTokens(int[] buttonADetails, int[] buttonBDetails, int[] prizeDetails) {
        int a_numerator = (prizeDetails[0]*buttonBDetails[1]) - (prizeDetails[1]*buttonBDetails[0]);
        int a_denominator = buttonADetails[0]*buttonBDetails[1] - buttonBDetails[0]*buttonADetails[1];
        if (a_numerator % a_denominator != 0) {
            return 0;
        }
        int a = a_numerator/a_denominator;

        int b_numerator = prizeDetails[0] - a*buttonADetails[0];
        int b_denominator = buttonBDetails[0];
        if (b_numerator % b_denominator != 0) {
            return 0;
        }
        int b = b_numerator/b_denominator;

        return a*3+b;
    }

    public void solvePart2() {
        ArrayList<Integer> tokens = new ArrayList<>();
        long answer = 0;

        try (Scanner inStream = Helper.readFile("day13_example")) {
            while (inStream.hasNextLine()) {
                String buttonA = inStream.nextLine();
                String buttonB = inStream.nextLine();
                String prize = inStream.nextLine();

            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        for (int t: tokens) {
            answer += t;
        }
        System.out.println("Answer: " + answer);
    }
}

