package day14;

import common.Helper;

import javax.swing.text.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day14 {

    private static class Coordinates {
        public int x;
        public int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class QuadrantDetails {
        public int x_start;
        public int x_end;
        public int y_start;
        public int y_end;
        public long count;

        public QuadrantDetails(int x_start, int x_end, int y_start, int y_end) {
            this.x_start = x_start;
            this.x_end = x_end;
            this.y_start = y_start;
            this.y_end = y_end;
            count = 0;
        }
    }

    private final Pattern ROBO = Pattern.compile("p=(\\d+),(\\d+) v=([-]?\\d+),([-]?\\d+)");

    private int GRID_LIMIT_X;
    private int GRID_LIMIT_Y;

    public void solvePart1() {
        ArrayList<Coordinates> positions = new ArrayList<>();
        ArrayList<Coordinates> velocities = new ArrayList<>();

        try (Scanner inStream = Helper.readFile("day14_input")) {
//            For example
//            GRID_LIMIT_X = 7;
//            GRID_LIMIT_Y = 11;

            GRID_LIMIT_X = 103;
            GRID_LIMIT_Y = 101;

            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                Matcher m = ROBO.matcher(line);
                m.find();

                positions.add(new Coordinates(
                        Integer.parseInt(m.group(2)),
                        Integer.parseInt(m.group(1))
                ));
                velocities.add(new Coordinates(
                        Integer.parseInt(m.group(4)),
                        Integer.parseInt(m.group(3))
                ));
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        updateRoboPositions(positions, velocities, 100);
        long answer = findSafetyFactor(positions);
        System.out.println("Answer: " + answer);
    }

    private void updateRoboPositions(ArrayList<Coordinates> positions, ArrayList<Coordinates> velocities, int timePassed) {
        for (int idx = 0; idx < positions.size(); idx++) {
            Coordinates position = positions.get(idx);
            Coordinates velocity = velocities.get(idx);

            position.x = (position.x + (velocity.x * timePassed))%GRID_LIMIT_X;
            position.y = (position.y + (velocity.y * timePassed))%GRID_LIMIT_Y;

            if (position.x < 0) {
                position.x = GRID_LIMIT_X+position.x;
            }
            if (position.y < 0) {
                position.y = GRID_LIMIT_Y+position.y;
            }
        }
    }

    private long findSafetyFactor(ArrayList<Coordinates> positions) {
        HashMap<String, QuadrantDetails> quadrants = initQuadrants();
        determineRoboQuadrants(positions, quadrants);
//        DEBUG
//        drawPositionsOnMap(positions);
        return quadrants.get("q1").count * quadrants.get("q2").count * quadrants.get("q3").count * quadrants.get("q4").count;
    }

//    DEBUG function
    private void drawPositionsOnMap(ArrayList<Coordinates> positions) {
        ArrayList<StringBuffer> grid = new ArrayList<>();
        for (int x=0; x<GRID_LIMIT_X; x++) {
            grid.add(new StringBuffer());
            for (int y=0; y<GRID_LIMIT_Y; y++) {
                grid.get(x).append('.');
            }
        }
        for (Coordinates p: positions) {
            grid.get(p.x).setCharAt(p.y, 'X');
        }

        System.out.println("==== PRINTING GRID START ====");
        for (StringBuffer stringBuffer : grid) {
            System.out.println(stringBuffer);
        }
        System.out.println("==== PRINTING GRID END ====");
    }

//    -------
//    |q1|q2|
//    |q3|q4|
//    -------
    private HashMap<String, QuadrantDetails> initQuadrants() {
        HashMap<String, QuadrantDetails> quadrants = new HashMap<>();
        quadrants.put("q1", new QuadrantDetails(
                0,
                GRID_LIMIT_X/2,
                0,
                GRID_LIMIT_Y/2
        ));
        quadrants.put("q2", new QuadrantDetails(
                0,
                GRID_LIMIT_X/2,
                GRID_LIMIT_Y/2 + 1,
                GRID_LIMIT_Y
        ));
        quadrants.put("q3",new QuadrantDetails(
                GRID_LIMIT_X/2 + 1,
                GRID_LIMIT_X,
                0,
                GRID_LIMIT_Y/2
        ) );
        quadrants.put("q4",new QuadrantDetails(
                GRID_LIMIT_X/2 + 1,
                GRID_LIMIT_X,
                GRID_LIMIT_Y/2 + 1,
                GRID_LIMIT_Y
        ));

        return quadrants;
    }

    private void determineRoboQuadrants(ArrayList<Coordinates> positions, HashMap<String, QuadrantDetails> quadrants) {
        for (Coordinates position: positions) {
            if (isInQuadrant(quadrants.get("q1"), position)) {
                quadrants.get("q1").count++;
            } else if (isInQuadrant(quadrants.get("q2"), position)) {
                quadrants.get("q2").count++;
            } else if (isInQuadrant(quadrants.get("q3"), position)) {
                quadrants.get("q3").count++;
            } else if (isInQuadrant(quadrants.get("q4"), position)) {
                quadrants.get("q4").count++;
            }
        }
    }

    private boolean isInQuadrant(QuadrantDetails quadrant, Coordinates position) {
        return position.x >= quadrant.x_start && position.x < quadrant.x_end && position.y >= quadrant.y_start && position.y < quadrant.y_end;
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

