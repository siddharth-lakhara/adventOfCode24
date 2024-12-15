package day12;

import common.Helper;
import day8.Day8;

import java.util.*;

public class Day12 {
    private class RegionDetails {
        public int perimeter;
        public int area;

        public RegionDetails(int perimeter, int area) {
            this.perimeter = perimeter;
            this.area = area;
        }
    }
    private int X_MAX;
    private int Y_MAX;

    private HashMap<Integer, HashSet<Integer>> visited;

    public void solvePart1() {
        long answer = 0;
        ArrayList<StringBuffer> garden = new ArrayList<>();

        try (Scanner inStream = Helper.readFile("day12_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                garden.add(new StringBuffer(line));
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        X_MAX = garden.size();
        Y_MAX = garden.get(0).length();

        answer = calculateFenceCost(garden);
        System.out.println("Answer: " + answer);
    }

    private long calculateFenceCost(ArrayList<StringBuffer> garden) {
        visited = new HashMap<>();
        long fenceCost = 0;
        for (int x = 0; x < garden.size(); x++) {
            for (int y = 0; y < garden.get(0).length(); y++) {
                if (isVisited(x, y)) {
                    continue;
                }

                RegionDetails regionDetails = new RegionDetails(0, 0);
                markRegion(garden, x, y, regionDetails);
                fenceCost += (long) regionDetails.area* regionDetails.perimeter;
            }
        }

        return fenceCost;
    }

    private void markRegion(ArrayList<StringBuffer> garden, int x, int y, RegionDetails regionDetails) {
        markVisited(x, y);
        regionDetails.area++;

        char plant = garden.get(x).charAt(y);

        // UP
        if (withinBounds(x-1, y)) {
            if (garden.get(x-1).charAt(y) != plant) {
                regionDetails.perimeter++;
            } else if (!isVisited(x-1, y)) {
                markRegion(garden, x-1, y, regionDetails);
            }
        } else {
            regionDetails.perimeter++;
        }

        // DOWN
        if (withinBounds(x+1, y)) {
            if (garden.get(x+1).charAt(y) != plant) {
                regionDetails.perimeter++;
            } else if (!isVisited(x+1, y)) {
                markRegion(garden, x+1, y, regionDetails);
            }
        } else {
            regionDetails.perimeter++;
        }

        // LEFT
        if (withinBounds(x, y-1)) {
            if (garden.get(x).charAt(y-1) != plant) {
                regionDetails.perimeter++;
            }  else if (!isVisited(x, y-1)) {
                markRegion(garden, x, y-1, regionDetails);
            }
        } else {
            regionDetails.perimeter++;
        }

        // RIGHT
        if (withinBounds(x, y+1)) {
            if (garden.get(x).charAt(y+1) != plant) {
                regionDetails.perimeter++;
            } else if (!isVisited(x, y+1)) {
                markRegion(garden, x, y+1, regionDetails);
            }
        } else {
            regionDetails.perimeter++;
        }
    }

    private void markVisited(int x, int y) {
        if (!visited.containsKey(x)) {
            visited.put(x, new HashSet<>());
        }
        visited.get(x).add(y);
    }

    private boolean isVisited(int x, int y) {
        return visited.containsKey(x) && visited.get(x).contains(y);
    }

    private boolean withinBounds(int x, int y) {
        return x >= 0 && x < X_MAX && y >= 0 && y < Y_MAX;
    }

    public void solvePart2() {
        long answer = 0;
        ArrayList<String> grid = new ArrayList<>();

        try (Scanner inStream = Helper.readFile("day12_example")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();

            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }
}

