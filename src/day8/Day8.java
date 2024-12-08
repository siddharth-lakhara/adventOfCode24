package day8;

import common.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day8 {
    private final Pattern antennaPattern = Pattern.compile("[\\d\\w]");

    private record Position(int x, int y) {};

    public void solvePart1() {
        Scanner inStream = null;
        HashMap<String, ArrayList<Position>> antennas = new HashMap<>();
        ArrayList<StringBuffer> grid = new ArrayList<>();

        try {
            int xLoc = -1;
            inStream = Helper.readFile("day8_input");
            while (inStream.hasNextLine()) {
                xLoc++;
                String line = inStream.nextLine();
                grid.add(new StringBuffer(line));

                Matcher matcher = antennaPattern.matcher(line);
                while (matcher.find()) {
                    int yLoc = matcher.start();
                    String frequency = matcher.group(0);

                    if (!antennas.containsKey(frequency)) {
                        antennas.put(frequency, new ArrayList<>());
                    }
                    antennas.get(frequency).add(new Position(xLoc, yLoc));
                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }

        int answer = countUniqueAntiNodes(grid, antennas);
        System.out.println("Answer: " + answer);

//        DEBUG
        System.out.println("============");
        for (StringBuffer s: grid) {
            System.out.println(s);
        }
    }

    private int countUniqueAntiNodes(ArrayList<StringBuffer> grid, HashMap<String, ArrayList<Position>> antennas) {
        int count = 0;
        for (String antenna: antennas.keySet()) {
            count += countAntennaAntiNodes(grid, antennas.get(antenna));
        }
        return count;
    }

    private int countAntennaAntiNodes(ArrayList<StringBuffer> grid, ArrayList<Position> positions) {
        int count = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            for (int j = i+1; j < positions.size(); j++) {
                Position ant1 = positions.get(i);
                Position ant2 = positions.get(j);

                Position diff = new Position(Math.abs(ant1.x - ant2.x), Math.abs(ant1.y - ant2.y));
                int dirX = ant1.x - ant2.x > 0 ? 1 : -1;
                int dirY = ant1.y - ant2.y > 0 ? 1 : -1;

                Position antiNode1 = new Position(
                        ant1.x + dirX*diff.x,
                        ant1.y + dirY*diff.y
                );
                if (withinBounds(antiNode1, grid)) {
                    if (grid.get(antiNode1.x).charAt(antiNode1.y) != '#') {
                        count++;
                        grid.get(antiNode1.x).setCharAt(antiNode1.y, '#');
                    }
                }

                Position antiNode2 = new Position(
                        ant2.x + (-dirX)*diff.x,
                        ant2.y + (-dirY)*diff.y
                );
                if (withinBounds(antiNode2, grid)) {
                    if (grid.get(antiNode2.x).charAt(antiNode2.y) != '#') {
                        count++;
                        grid.get(antiNode2.x).setCharAt(antiNode2.y, '#');
                    }
                }
            }
        }
        return count;
    }

    private boolean withinBounds(Position position, ArrayList<StringBuffer> grid) {
        int X_MAX = grid.size();
        int Y_MAX = grid.get(0).length();

        return position.x >= 0 && position.x < X_MAX && position.y >= 0 && position.y < Y_MAX;
    }

    public void solvePart2() {
        Scanner inStream = null;
        HashMap<String, ArrayList<Position>> antennas = new HashMap<>();
        ArrayList<StringBuffer> grid = new ArrayList<>();

        try {
            int xLoc = -1;
            inStream = Helper.readFile("day8_input");
            while (inStream.hasNextLine()) {
                xLoc++;
                String line = inStream.nextLine();
                grid.add(new StringBuffer(line));

                Matcher matcher = antennaPattern.matcher(line);
                while (matcher.find()) {
                    int yLoc = matcher.start();
                    String frequency = matcher.group(0);

                    if (!antennas.containsKey(frequency)) {
                        antennas.put(frequency, new ArrayList<>());
                    }
                    antennas.get(frequency).add(new Position(xLoc, yLoc));
                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }

        int answer = countUniqueAntiNodesPart2(grid, antennas);
        System.out.println("Answer: " + answer);

//        DEBUG
        System.out.println("============");
        for (StringBuffer s: grid) {
            System.out.println(s);
        }
    }

    private int countUniqueAntiNodesPart2(ArrayList<StringBuffer> grid, HashMap<String, ArrayList<Position>> antennas) {
        int count = 0;
        for (String antenna: antennas.keySet()) {
            count += countAntennaAntiNodesPart2(grid, antennas.get(antenna));
        }
        return count;
    }

    private int countAntennaAntiNodesPart2(ArrayList<StringBuffer> grid, ArrayList<Position> positions) {
        int count = 0;
        for (int i = 0; i < positions.size() - 1; i++) {
            for (int j = i+1; j < positions.size(); j++) {
                Position ant1 = positions.get(i);
                Position ant2 = positions.get(j);

                Position diff = new Position(Math.abs(ant1.x - ant2.x), Math.abs(ant1.y - ant2.y));
                int dirX = ant1.x - ant2.x > 0 ? 1 : -1;
                int dirY = ant1.y - ant2.y > 0 ? 1 : -1;

                int harmonic = 0;
                while(true) {
                    Position antiNode = new Position(
                            ant1.x + harmonic*dirX*diff.x,
                            ant1.y + harmonic*dirY*diff.y
                    );
                    if (withinBounds(antiNode, grid)) {
                        if (grid.get(antiNode.x).charAt(antiNode.y) != '#') {
                            count++;
                            grid.get(antiNode.x).setCharAt(antiNode.y, '#');
                        }
                    } else {
                        break;
                    }
                    harmonic++;
                }

                harmonic = 0;
                while(true) {
                    Position antiNode = new Position(
                            ant1.x + harmonic*(-dirX)*diff.x,
                            ant1.y + harmonic*(-dirY)*diff.y
                    );
                    if (withinBounds(antiNode, grid)) {
                        if (grid.get(antiNode.x).charAt(antiNode.y) != '#') {
                            count++;
                            grid.get(antiNode.x).setCharAt(antiNode.y, '#');
                        }
                    } else {
                        break;
                    }
                    harmonic++;
                }
            }
        }
        return count;
    }
}

