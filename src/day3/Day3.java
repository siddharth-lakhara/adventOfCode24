package day3;

import common.Helper;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {


    public void solvePart1() {
        final Pattern instructionPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Scanner inStream = null;
        try {
            inStream = Helper.readFile("day3_input");
            long answer = 0;
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                Matcher matcher = instructionPattern.matcher(line);
                while (matcher.find()) {
                    int num1 = Integer.parseInt(matcher.group(1));
                    int num2 = Integer.parseInt(matcher.group(2));

                    answer += (long) num1*num2;
                }
            }
            System.out.println(answer);
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }
    }

    public void solvePart2() {
        final Pattern instructionPattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)|(don't)|(do)");
        Scanner inStream = null;
        try {
            inStream = Helper.readFile("day3_input");
            long answer = 0;
            boolean isMatchingEnabled = true;

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                Matcher matcher = instructionPattern.matcher(line);
                while (matcher.find()) {
                    String match = matcher.group();
                    if (match.equals("don't")) {
                        isMatchingEnabled = false;
                    } else if (match.equals("do")) {
                        isMatchingEnabled = true;
                    } else if (isMatchingEnabled) {
                        int num1 = Integer.parseInt(matcher.group(1));
                        int num2 = Integer.parseInt(matcher.group(2));

                        answer += (long) num1*num2;
                    }

                }
            }
            System.out.println(answer);
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }
    }
}

