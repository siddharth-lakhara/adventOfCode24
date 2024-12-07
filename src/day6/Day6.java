package day6;

import common.Helper;

import java.util.*;
import java.util.stream.Collectors;

public class Day6 {
    ArrayList<StringBuffer> grid;
    public void solvePart1() {
        grid = new ArrayList<>();
        int guardX = -1;
        int guardY = -1;
        try (Scanner inStream = Helper.readFile("day6_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                int guardLocation = line.indexOf("^");
                if (guardLocation != -1) {
                    guardY = guardLocation;
                    guardX = grid.size();
                }
                grid.add(new StringBuffer(line));
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        long answer = countUniqueSteps(guardX, guardY) + 1;
        System.out.println("Answer: " + answer);
    }

    private long countUniqueSteps(int guardX, int guardY) {
        int stepsIncrease = 0;
        boolean foundEnd = false;
        char direction = grid.get(guardX).charAt(guardY);
        switch (direction) {
            case '^':
                if (outOfBounds(guardX-1, guardY)) {
                    foundEnd = true;
                    break;
                }

                if (grid.get(guardX-1).charAt(guardY) == '#')
                    grid.get(guardX).setCharAt(guardY, '>');
                else {
                    if (grid.get(guardX-1).charAt(guardY) == '.') {
                        stepsIncrease++;
                    }
                    grid.get(guardX-1).setCharAt(guardY, '^');
                    grid.get(guardX).setCharAt(guardY, 'X');
                    guardX--;
                }
                break;
            case '>':
                if (outOfBounds(guardX, guardY+1)) {
                    foundEnd = true;
                    break;
                }

                if (grid.get(guardX).charAt(guardY+1) == '#')
                    grid.get(guardX).setCharAt(guardY, 'V');
                else {
                    if (grid.get(guardX).charAt(guardY+1) == '.') {
                        stepsIncrease++;
                    }
                    grid.get(guardX).setCharAt(guardY+1, '>');
                    grid.get(guardX).setCharAt(guardY, 'X');
                    guardY++;
                }
                break;
            case 'V':
                if (outOfBounds(guardX+1, guardY)) {
                    foundEnd = true;
                    break;
                }

                if (grid.get(guardX+1).charAt(guardY) == '#')
                    grid.get(guardX).setCharAt(guardY, '<');
                else {
                    if (grid.get(guardX+1).charAt(guardY) == '.') {
                        stepsIncrease++;
                    }
                    grid.get(guardX+1).setCharAt(guardY, 'V');
                    grid.get(guardX).setCharAt(guardY, 'X');
                    guardX++;
                }
                break;
            case '<':
            default:
                if (outOfBounds(guardX, guardY-1)) {
                    foundEnd = true;
                    break;
                }

                if (grid.get(guardX).charAt(guardY-1) == '#')
                    grid.get(guardX).setCharAt(guardY, '^');
                else {
                    if (grid.get(guardX).charAt(guardY-1) == '.') {
                        stepsIncrease++;
                    }
                    grid.get(guardX).setCharAt(guardY-1, '<');
                    grid.get(guardX).setCharAt(guardY, 'X');
                    guardY--;
                }
                break;
        }

        if (foundEnd) {
            return 0;
        } else {
            return countUniqueSteps(guardX, guardY) + stepsIncrease;
        }

    }

    private boolean outOfBounds(int x, int y) {
        if (x<0 || x>=grid.size()) {
            return true;
        } else {
            return y<0 || y>=grid.get(0).length();
        }
    }

    public void solvePart2() {
        try (Scanner inStream = Helper.readFile("day5_example")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: ");
    }
}

