package day7;

import common.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day7 {
    private boolean IS_PART_2 = false;

    public void solvePart1() {
        long answer = 0;
        try (Scanner inStream = Helper.readFile("day7_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                long target = Long.parseLong(line.split(":")[0]);
                ArrayList<Integer> numbers = (ArrayList<Integer>) Arrays.stream(line.split(":")[1].trim().split(" "))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                if (isCombinationPossible(numbers, target)) {
                    answer += target;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }

    private boolean isCombinationPossible(ArrayList<Integer> numbers, long target) {
        return makeAllCombinations(numbers, 1, target, numbers.get(0));
    }

    private boolean makeAllCombinations(ArrayList<Integer> numbers, int currIdx, long target, long acc) {
        int currNum = numbers.get(currIdx);
        if (currIdx == numbers.size()-1) {
            if (IS_PART_2) {
                long concatenated = concatenate(acc, currNum);
                return acc+currNum == target || acc*currNum == target || concatenated == target;
            } else {
                return acc+currNum == target || acc*currNum == target;
            }

        } else {
            if (IS_PART_2) {
                return makeAllCombinations(numbers, currIdx + 1, target, acc + currNum) ||
                        makeAllCombinations(numbers, currIdx + 1, target, acc * currNum) ||
                        makeAllCombinations(numbers, currIdx + 1, target, concatenate(acc, currNum));
            } else {
                return makeAllCombinations(numbers, currIdx + 1, target, acc + currNum) ||
                        makeAllCombinations(numbers, currIdx + 1, target, acc * currNum);
            }
        }
    }

    private static long concatenate(long acc, int currNum) {
        String numStr = Long.toString(acc) + Integer.toString(currNum);
        return Long.parseLong(numStr);
    }

    public void solvePart2() {
        IS_PART_2 = true;
        long answer = 0;
        try (Scanner inStream = Helper.readFile("day7_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                long target = Long.parseLong(line.split(":")[0]);
                ArrayList<Integer> numbers = (ArrayList<Integer>) Arrays.stream(line.split(":")[1].trim().split(" "))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                if (isCombinationPossible(numbers, target)) {
                    answer += target;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }

}

