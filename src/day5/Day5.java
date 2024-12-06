package day5;

import common.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class Day5 {
    public void solvePart1() {
        HashMap<Integer, ArrayList<Integer>> pageOrderingRules = new HashMap<>();
        int answer = 0;
        try (Scanner inStream = Helper.readFile("day5_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                String[] graphNodesStr = line.split("\\|");
                int[] graphNodes = new int[]{Integer.parseInt(graphNodesStr[0]), Integer.parseInt(graphNodesStr[1])};
                if (!pageOrderingRules.containsKey(graphNodes[1])) {
                    pageOrderingRules.put(
                            graphNodes[1],
                            new ArrayList<>()
                    );
                }

                pageOrderingRules.get(graphNodes[1]).add(graphNodes[0]);
            }

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                ArrayList<Integer> update = (ArrayList<Integer>) Arrays.stream(line.split(","))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                if (isSafe(update, pageOrderingRules)) {
                    answer += update.get(update.size()/2);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }

    private boolean isSafe(ArrayList<Integer> update, HashMap<Integer, ArrayList<Integer>> pageOrderingRules) {
        HashSet<Integer> forbiddenPages = new HashSet<>();
        for (int page: update) {
            if (forbiddenPages.contains(page)) {
                return false;
            }
            forbiddenPages.addAll(
                    pageOrderingRules.getOrDefault(page, new ArrayList<>())
            );
        }
        return true;
    }

    public void solvePart2() {
        HashMap<Integer, ArrayList<Integer>> pageOrderingRules = new HashMap<>();
        int answer = 0;
        try (Scanner inStream = Helper.readFile("day5_example")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                String[] graphNodesStr = line.split("\\|");
                int[] graphNodes = new int[]{Integer.parseInt(graphNodesStr[0]), Integer.parseInt(graphNodesStr[1])};
                if (!pageOrderingRules.containsKey(graphNodes[1])) {
                    pageOrderingRules.put(
                            graphNodes[1],
                            new ArrayList<>()
                    );
                }

                pageOrderingRules.get(graphNodes[1]).add(graphNodes[0]);
            }

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                ArrayList<Integer> update = (ArrayList<Integer>) Arrays.stream(line.split(","))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());

                if (!isSafe(update, pageOrderingRules)) {
                    correctUpdate(update, pageOrderingRules);
                    answer += update.get(update.size()/2);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }

    private void correctUpdate(ArrayList<Integer> update, HashMap<Integer, ArrayList<Integer>> pageOrderingRules) {

    }
}

