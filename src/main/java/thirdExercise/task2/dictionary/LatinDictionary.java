package thirdExercise.task2.dictionary;

import java.util.*;

public class LatinDictionary {

    static String inputLine = "asdvzx dasd zxda sda авпвапвап";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String inputString = !scan.nextLine().equals("") ? scan.nextLine() : inputLine;
        scan.close();

        Map<Character, Integer> mapChar = new HashMap<>();
        for (char symbol : inputString.toCharArray()) {
            if (isLatinChar(symbol)) {
                if (mapChar.containsKey(symbol)) {
                    mapChar.put(symbol, mapChar.get(symbol) + 1);
                } else {
                    mapChar.put(symbol, 1);
                }
            }
        }
        System.out.println("Частотный словарь:");
        mapChar.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(System.out::println);
    }

    public static boolean isLatinChar(char symbol) {
        return ((int) symbol > 64 && (int) symbol < 91) || ((int) symbol > 96 && (int) symbol < 123);
    }
}



