package day11;

import common.Helper;

import java.util.*;

public class Day11 {
//    Stone -> depth -> answer
    private HashMap<Long, HashMap<Integer, Long>> cache = new HashMap<>();

    public void solvePart1() {
        long answer = 0;

        try (Scanner inStream = Helper.readFile("day11_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] numbersStr = line.split(" ");
                System.out.println(Arrays.toString(numbersStr));
                for (String numStr: numbersStr) {
                    long number = Long.parseLong(numStr);
                    answer += countStones(number, 25);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }

    private long countStones(long number, int depth) {
        if (depth == 0) {
            return 1;
        }

        String numStr = String.valueOf(number);
        if (number == 0) {
            return countStones(1, depth-1);
        } else if (numStr.length() % 2 == 0) {
            long leftSplit = Long.parseLong(numStr.substring(0, numStr.length()/2));
            long rightSplit = Long.parseLong(numStr.substring(numStr.length()/2));

            return countStones(leftSplit, depth-1) + countStones(rightSplit, depth-1);
        } else {
            return countStones(number*2024, depth-1);
        }
    }

    public void solvePart2() {
        long answer = 0;
        ArrayList<Long> numbers = new ArrayList<>();
        try (Scanner inStream = Helper.readFile("day11_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] numbersStr = line.split(" ");

                for (String numStr: numbersStr) {
                    long number = Long.parseLong(numStr);
                    numbers.add(number);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("DEBUG: " + numbers);
        for (long number: numbers) {
            System.out.println("DEBUG: Finding answer for " + number);
            long ans = countStonesWithCache(number, 75);
            System.out.println("DEBUG: Found answer for " + number + " = " + ans);
            answer += ans;
        }
        System.out.println("Answer: " + answer);
    }

    private long countStonesWithCache(long number, int depth) {
        if (depth == 0) {
            return 1;
        }

        if (isPresentInCache(number, depth)) {
            System.out.println("DBEUG: Cache HIT: " + number + " " + depth);
            return cache.get(number).get(depth);
        } else {
            System.out.println("DBEUG: Cache MISS: " + number + " " + depth);
        }

        String numStr = String.valueOf(number);
        if (number == 0) {
            long count = countStonesWithCache(1, depth - 1);
            updateCache(number, depth, count);
            return count;
        } else if (numStr.length() % 2 == 0) {
            long leftSplit = Long.parseLong(numStr.substring(0, numStr.length() / 2));
            long leftCount = countStonesWithCache(leftSplit, depth - 1);

            long rightSplit = Long.parseLong(numStr.substring(numStr.length() / 2));
            long rightCount = countStonesWithCache(rightSplit, depth - 1);

            updateCache(number, depth, leftCount+rightCount);
            return leftCount + rightCount;
        } else {
            long newNumber = number * 2024;
            long count = countStonesWithCache(newNumber, depth - 1);
            updateCache(number, depth, count);
            return count;
        }
    }

    private boolean isPresentInCache(long number, int depth) {
        return cache.containsKey(number) && cache.get(number).containsKey(depth);
    }

    private void updateCache(long number, int depth, long answer) {
        if (!cache.containsKey(number)) {
            cache.put(number, new HashMap<>());
        }

        cache.get(number).put(depth, answer);
    }
}

