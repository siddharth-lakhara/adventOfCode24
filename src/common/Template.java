package common;

import java.util.Scanner;

public class Template {
    public void solvePart1() {
        try (Scanner inStream = Helper.readFile("day13_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }
    }
}
