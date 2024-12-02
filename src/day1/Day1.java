package day1;

import common.Helper;

import java.util.*;

public class Day1 {
    public void solvePart1() {
        Scanner inStream = null;
        try {
            inStream = Helper.readFile("day1_input");

            ArrayList<Integer> list1 = new ArrayList<>();
            ArrayList<Integer> list2 = new ArrayList<>();

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] numStr = line.split("\\s+");

                list1.add(Integer.valueOf(numStr[0]));
                list2.add(Integer.valueOf(numStr[1]));
            }

            Collections.sort(list1);
            Collections.sort(list2);

            long counter = 0;
            for (int idx=0; idx<list1.size(); idx++) {
                int diff = Math.abs(list1.get(idx) - list2.get(idx));
                counter += diff;
            }

            System.out.println("Answer: " + counter);
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
            inStream = Helper.readFile("day1_input");

            ArrayList<Integer> list1 = new ArrayList<>();
            HashMap<Integer, Integer> counter = new HashMap<>();

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                String[] numStr = line.split("\\s+");

                list1.add(Integer.parseInt(numStr[0]));

                int secondNumber =Integer.parseInt(numStr[1]);
                counter.put(
                        secondNumber,
                        counter.getOrDefault(secondNumber, 0) + 1
                );
            }

            long similarityScore = 0;
            for (int num : list1) {
                similarityScore += (long) num * counter.getOrDefault(num, 0);
            }

            System.out.println("Answer: " + similarityScore);
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }
    }

}
