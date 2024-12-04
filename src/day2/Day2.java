package day2;

import common.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day2 {
    public void solvePart1() {
        Scanner inStream = null;
        try {
            inStream = Helper.readFile("day2_input");
            int safeCounter = 0;
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                ArrayList<Integer> report = (ArrayList<Integer>) Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                if (report.size() < 2) {
                    safeCounter++;
                    continue;
                }

                if (report.get(1) > report.get(0)) {
                    if (isStrictlyIncreasing(report)) {
                        safeCounter++;
                    }
                } else {
                    if (isStrictlyDecreasing(report)) {
                        safeCounter++;
                    }
                }
            }
            System.out.println("Answer: " + safeCounter);
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }
    }

    public void solvePart2() {
        Scanner inStream = null;
        try {
            inStream = Helper.readFile("day2_input");
            int safeCounter = 0;
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                ArrayList<Integer> report = (ArrayList<Integer>) Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                if (report.size() < 2) {
                    safeCounter++;
                    continue;
                }

//                NOT IMPLEMENTED
            }
            System.out.println("Answer: " + safeCounter);
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }
    }

    private boolean isStrictlyIncreasing(ArrayList<Integer> report) {
        for (int idx=1; idx<report.size(); idx++) {
            int diff = report.get(idx) - report.get(idx-1);
            if (diff > 3 || diff <= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isStrictlyDecreasing(ArrayList<Integer> report) {
        for (int idx=1; idx<report.size(); idx++) {
            int diff = report.get(idx-1) - report.get(idx);
            if (diff > 3 || diff <= 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidReport(ArrayList<Integer> report) {

        return true;
    }
}

