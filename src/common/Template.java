package common;

import java.util.Scanner;

public class Template {
    public void solveExample() {
        Scanner inStream = null;
        try {
            inStream = Helper.readFile("day1_example");

        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        } finally {
            if (inStream != null)
                inStream.close();
        }
    }

}
