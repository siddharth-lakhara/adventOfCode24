package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
    public static Scanner readFile(String fileName) throws FileNotFoundException {
            BufferedReader br = new BufferedReader(
                    new FileReader("resources/"+fileName));
            return new Scanner(br);
    }

    public static void printGridWithBuffer(ArrayList<StringBuffer> grid) {
        for (StringBuffer line: grid) {
            System.out.println(line);
        }
    }

    public static void printGridWithString(ArrayList<String> grid) {
        for (String line: grid) {
            System.out.println(line);
        }
    }
}
