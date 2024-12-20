package day15;

import common.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.Helper.printGridWithBuffer;

public class Day15 {

    private final Pattern ROBO = Pattern.compile("@");
    private ArrayList<StringBuffer> grid = new ArrayList<>();
    private int robo_x = 0;
    private int robo_y = 0;

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
    }

    private final HashMap<Character, Direction> DirectionCharacterMap = new HashMap<>() {{
        put('^', Direction.UP);
        put('>', Direction.RIGHT);
        put('v', Direction.DOWN);
        put('<', Direction.LEFT);
    }};

    public void solvePart1() {
        grid = new ArrayList<>();
        ArrayList<String> instructions = new ArrayList<>();

        try (Scanner inStream = Helper.readFile("day15_input")) {
            String topWall = inStream.nextLine();
            grid.add(new StringBuffer(topWall));

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                if (line.isEmpty()) {
                    break;
                }
                grid.add(new StringBuffer(line));

                Matcher m = ROBO.matcher(line);
                if (m.find()) {
                     robo_x = grid.size()-1;
                     robo_y = m.start();
                }
            }
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                instructions.add(line);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        executeInstructions(instructions);
        printGridWithBuffer(grid);
        long answer = calculateGPS();
        System.out.println("ANSWER: " + answer);
    }

    private long calculateGPS() {
        long score = 0;
        int x = -1;
        for (StringBuffer line: grid) {
            x++;
            for (int y = 0; y < line.length(); y++) {
                if (line.charAt(y) == 'O') {
                    score += (100L*x + y);
                }
            }
        }
        return score;
    }

    private void executeInstructions(ArrayList<String> instructions) {
        for (String instruction: instructions) {
            for (int idx = 0; idx < instruction.length(); idx++) {
                executeInstructions(instruction.charAt(idx));
            }
        }
    }

    private void executeInstructions(char instruction) {
        Direction dir = DirectionCharacterMap.get(instruction);
        if (move(dir, robo_x, robo_y)) {
            int[] target = getTargetCoordinates(dir, robo_x, robo_y);
            robo_x = target[0];
            robo_y = target[1];
        }
    }

    private boolean move(Direction dir, int x, int y) {
        boolean hasMoved = false;
        int[] target = getTargetCoordinates(dir, x, y);
        int target_x = target[0];
        int target_y = target[1];

        if (withinBounds(target_x, target_y) && grid.get(target_x).charAt(target_y) != '#') {
            if (grid.get(target_x).charAt(target_y) == 'O') {
                move(dir, target_x, target_y);
            }
            if (grid.get(target_x).charAt(target_y) == '.') {
                grid.get(target_x).setCharAt(target_y, grid.get(x).charAt(y));
                grid.get(x).setCharAt(y, '.');
                hasMoved = true;
            }
        }

        return hasMoved;
    }

    private boolean withinBounds(int x, int y) {
        return x > 0 && x < grid.size()-1 && y > 0 && y < grid.get(0).length()-1;
    }

    private int[] getTargetCoordinates(Direction dir, int x, int y) {
        int target_x = x;
        int target_y = y;

        switch (dir) {
            case UP -> target_x--;
            case DOWN -> target_x++;
            case LEFT ->target_y--;
            case RIGHT -> target_y++;
        }

        return new int[]{target_x, target_y};
    }

    public void solvePart2() {
        try (Scanner inStream = Helper.readFile("day14_example")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }
}

