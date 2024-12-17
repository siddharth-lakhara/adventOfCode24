package day13;

import common.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 {

    private final Pattern BUTTON_PATTERN = Pattern.compile(": X\\+(\\d+), Y\\+(\\d+)");
    private final Pattern TARGET_PATTERN = Pattern.compile(": X=(\\d+), Y=(\\d+)");

    boolean IS_PART_2 = false;

    public void solvePart1() {
        ArrayList<Long> tokens = new ArrayList<>();
        long answer = 0;

        try (Scanner inStream = Helper.readFile("day13_input")) {
            while (inStream.hasNextLine()) {
                String buttonA = inStream.nextLine();
                long[] buttonADetails = extractButtonDetails(buttonA);

                String buttonB = inStream.nextLine();
                long[] buttonBDetails = extractButtonDetails(buttonB);

                String prize = inStream.nextLine();
                long[] prizeDetails = extractPrizeDetails(prize);

                long token = calculateMinTokens(buttonADetails, buttonBDetails, prizeDetails);
                tokens.add(token);

//                ignore the empty line between data
                if (inStream.hasNextLine()) {
                    inStream.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        for (long t: tokens) {
            answer += t;
        }
        System.out.println("Answer: " + answer);
    }

    private long[] extractButtonDetails(String buttonStr) {
        long x;
        long y;

        Matcher buttonMatcher = BUTTON_PATTERN.matcher(buttonStr);

        buttonMatcher.find();
        x = Long.parseLong(buttonMatcher.group(1));
        y = Long.parseLong(buttonMatcher.group(2));

        return new long[]{x, y};
    }

    private long[] extractPrizeDetails(String prizeStr) {
        long x;
        long y;

        Matcher prizeMatcher = TARGET_PATTERN.matcher(prizeStr);

        prizeMatcher.find();
        x = Long.parseLong(prizeMatcher.group(1));
        y = Long.parseLong(prizeMatcher.group(2));

        if (IS_PART_2) {
            long CORRECTION = 10000000000000L;
            x += CORRECTION;
            y += CORRECTION;
        }

        return new long[]{x, y};
    }

    private long calculateMinTokens(long[] buttonADetails, long[] buttonBDetails, long[] prizeDetails) {
        long a_numerator = prizeDetails[0]*buttonBDetails[1] - prizeDetails[1]*buttonBDetails[0];
        long a_denominator = buttonADetails[0]*buttonBDetails[1] - buttonBDetails[0]*buttonADetails[1];
        if (a_numerator % a_denominator != 0) {
            return 0;
        }

        long a = a_numerator/a_denominator;

        long b_numerator = prizeDetails[0] - a*buttonADetails[0];
        long b_denominator = buttonBDetails[0];
        if (b_numerator % b_denominator != 0) {
            return 0;
        }
        long b = b_numerator/b_denominator;

        return a*3+b;
    }

    public void solvePart2() {
        IS_PART_2 = true;
        ArrayList<Long> tokens = new ArrayList<>();
        long answer = 0;

        try (Scanner inStream = Helper.readFile("day13_input")) {
            while (inStream.hasNextLine()) {
                String buttonA = inStream.nextLine();
                long[] buttonADetails = extractButtonDetails(buttonA);

                String buttonB = inStream.nextLine();
                long[] buttonBDetails = extractButtonDetails(buttonB);

                String prize = inStream.nextLine();
                long[] prizeDetails = extractPrizeDetails(prize);

                long token = calculateMinTokens(buttonADetails, buttonBDetails, prizeDetails);
                tokens.add(token);

//                ignore the empty line between data
                if (inStream.hasNextLine()) {
                    inStream.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        for (long t: tokens) {
            answer += t;
        }
        System.out.println("Answer: " + answer);
    }
}

