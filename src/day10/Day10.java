package day10;

import common.Helper;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day10 {

    private record Position(int x, int y) {};
    private boolean IS_PART_2 = false;
    
    Pattern TRAILHEAD = Pattern.compile("0");
    public void solvePart1() {
        long answer = 0;
        ArrayList<StringBuffer> grid = new ArrayList<>();
        ArrayList<Position> trailHeads = new ArrayList<>();

        try (Scanner inStream = Helper.readFile("day10_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                Matcher m = TRAILHEAD.matcher(line);
                while (m.find()) {
                    int y = m.start();
                    trailHeads.add(new Position(grid.size(), y));
                }
                grid.add(new StringBuffer(line));
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        answer = sumTrailHeadScores(grid, trailHeads);
        System.out.println("Answer: " + answer);
    }

    private long sumTrailHeadScores(ArrayList<StringBuffer> grid, ArrayList<Position> trailHeads) {
        long score = 0;
        HashMap<Integer, HashMap<Integer, Long>> cache = new HashMap<>();
        for (Position trailHead: trailHeads) {
            if (!IS_PART_2) {
                cache = new HashMap<>();
            }
            score += findTrailHeadScore(grid, trailHead.x, trailHead.y, cache);
        }
        return score;
    }

    private static boolean keyExists(HashMap<Integer, HashMap<Integer, Long>> cache, int xPos, int yPos) {
        return cache.containsKey(xPos) && cache.get(xPos).containsKey(yPos);
    }

    private long findTrailHeadScore(ArrayList<StringBuffer> grid, int xPos, int yPos, HashMap<Integer, HashMap<Integer, Long>> cache) {
        if (keyExists(cache, xPos, yPos)) {
            if (IS_PART_2) {
                return cache.get(xPos).get(yPos);
            }
            return 0;
        }

        int currentLevel = grid.get(xPos).charAt(yPos) - '0';
        if (currentLevel == 9) {
            addToCache(cache, xPos, yPos, 1);
            return 1;
        }

        long score = 0;
        //        UP
        if (getGridPosition(grid, xPos-1, yPos) == currentLevel+1) {
            score += findTrailHeadScore(grid, xPos-1, yPos, cache);
        }

        //        DOWN
        if (getGridPosition(grid, xPos+1, yPos) == currentLevel+1) {
            score += findTrailHeadScore(grid, xPos+1, yPos, cache);
        }

        //        LEFT
        if (getGridPosition(grid, xPos, yPos-1) == currentLevel+1) {
            score += findTrailHeadScore(grid, xPos, yPos-1, cache);
        }

        //        RIGHT
        if (getGridPosition(grid, xPos, yPos+1) == currentLevel+1) {
            score += findTrailHeadScore(grid, xPos, yPos+1, cache);
        }

        addToCache(cache, xPos, yPos, score);
        return score;
    }

    private void addToCache(HashMap<Integer, HashMap<Integer, Long>> cache, int xPos, int yPos, long score) {
        if (!cache.containsKey(xPos)) {
            cache.put(xPos, new HashMap<>());
        }
        cache.get(xPos).put(yPos, score);
    }

    private int getGridPosition(ArrayList<StringBuffer> grid, int xPos, int yPos) {
        if (xPos < 0 || xPos >= grid.size() || yPos < 0 || yPos >= grid.get(0).length()) {
            return -1;
        }
        return grid.get(xPos).charAt(yPos) - '0';
    }

    public void solvePart2() {
        IS_PART_2 = true;

        long answer = 0;
        ArrayList<StringBuffer> grid = new ArrayList<>();
        ArrayList<Position> trailHeads = new ArrayList<>();

        try (Scanner inStream = Helper.readFile("day10_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                Matcher m = TRAILHEAD.matcher(line);
                while (m.find()) {
                    int y = m.start();
                    trailHeads.add(new Position(grid.size(), y));
                }
                grid.add(new StringBuffer(line));
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        answer = sumTrailHeadScores(grid, trailHeads);
        System.out.println("Answer: " + answer);
    }
}

