package DiscordBot;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Moderation {
    static Scanner scanner;
    public static boolean badWordDetection(String @NotNull [] words){
        boolean result = false;
        {
            try {
                File badWords = new File("src/badwords.txt");
                scanner = new Scanner(badWords);
                for (String s : words) {
                    while (scanner.hasNextLine()) {
                        String word = scanner.nextLine();
                        if (s.equalsIgnoreCase(word)) {
                            result = true;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while loading the file.");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
      return result;
    }
}
