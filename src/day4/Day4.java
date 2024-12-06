package day4;

import common.Helper;

import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    final static String XMAS = "XMAS";
    private ArrayList<String> grid;

    public void solvePart1() {
        grid = new ArrayList<>();
        try (Scanner inStream = Helper.readFile("day4_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                grid.add(line);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        int xmasCount = 0;
        for (int gx = 0; gx < grid.size(); gx++) {
            int gy = -1;
            while (gy < grid.get(gx).length()) {
                gy = grid.get(gx).indexOf('X', gy+1);
                if (gy == -1) break;
                xmasCount += findAt(gx, gy);
            }
        }
        System.out.println("Answer: " + xmasCount);
    }

    public void solvePart2() {
        grid = new ArrayList<>();
        try (Scanner inStream = Helper.readFile("day4_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                grid.add(line);
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        int xmasCount = 0;
        for (int gx = 0; gx < grid.size(); gx++) {
            int gy = -1;
            while (gy < grid.get(gx).length()) {
                gy = grid.get(gx).indexOf('A', gy+1);
                if (gy == -1) break;
                if (findPlus(gx, gy) || findCross(gx, gy)) {
                    xmasCount++;
                }
            }
        }
        System.out.println("Answer: " + xmasCount);
    }

    private boolean findCross(int gx, int gy) {
        if (outOfBounds(gx-1, gy-1) || outOfBounds(gx+1, gy+1)) {
            return false;
        }

        String diagonal1 = "" + grid.get(gx-1).charAt(gy-1) + 'A' + grid.get(gx+1).charAt(gy+1);
        boolean isDiagonal1Valid = diagonal1.equals("MAS") || diagonal1.equals("SAM");

        String diagonal2 = "" + grid.get(gx+1).charAt(gy-1) + 'A' + grid.get(gx-1).charAt(gy+1);
        boolean isDiagonal2Valid = diagonal2.equals("MAS") || diagonal2.equals("SAM");

        return isDiagonal1Valid && isDiagonal2Valid;
    }

    private boolean findPlus(int gx, int gy) {
        if (outOfBounds(gx-1, gy-1) || outOfBounds(gx+1, gy+1)) {
            return false;
        }

        String horizontal = "" + grid.get(gx).charAt(gy-1) + 'A' + grid.get(gx).charAt(gy-1);
        boolean isHorizontalValid = horizontal.equals("MAS") || horizontal.equals("SAM");

        String vertical = "" + grid.get(gx-1).charAt(gy) + 'A' + grid.get(gx+1).charAt(gy);
        boolean isVerticalValid = vertical.equals("MAS") || vertical.equals("SAM");

        return isHorizontalValid && isVerticalValid;
    }

    //    Grid X, Grid Y, Pattern index
    private int findAt(int gx, int gy) {
        return findUp(gx, gy, 0) + findDown(gx, gy, 0) + findLeft(gx, gy, 0) + findRight(gx, gy, 0) + findRightUp(gx, gy, 0)
                + findRightDown(gx, gy, 0) + findLeftUp(gx, gy, 0) + findLeftDown(gx, gy, 0);
    }

    private boolean outOfBounds(int gx, int gy) {
        if (gx < 0 || gx >= grid.get(0).length()) {
            return true;
        } else {
            return gy < 0 || gy >= grid.size();
        }
    }

    private int findLeftDown(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findLeftDown(gx+1, gy-1, pi+1);
        }
        return 0;
    }

    private int findLeftUp(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findLeftUp(gx-1, gy-1, pi+1);
        }
        return 0;
    }

    private int findRightDown(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findRightDown(gx+1, gy+1, pi+1);
        }
        return 0;
    }

    private int findRightUp(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findRightUp(gx-1, gy+1, pi+1);
        }
        return 0;
    }

    private int findRight(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findRight(gx, gy+1, pi+1);
        }
        return 0;
    }

    private int findLeft(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findLeft(gx, gy-1, pi+1);
        }
        return 0;
    }

    private int findDown(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findDown(gx+1, gy, pi+1);
        }
        return 0;
    }

    private int findUp(int gx, int gy, int pi) {
        if (outOfBounds(gx, gy)) {
            return 0;
        }

        if (grid.get(gx).charAt(gy) == XMAS.charAt(pi)) {
            if (pi == XMAS.length()-1) {
                return 1;
            }
            return findUp(gx-1, gy, pi+1);
        }
        return 0;
    }
}

