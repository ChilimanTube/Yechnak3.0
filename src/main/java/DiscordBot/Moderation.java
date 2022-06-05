package DiscordBot;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Moderation {
    static Scanner scanner;
    public static boolean badWordDetection(String @NotNull [] words){
        boolean result = false;
        {
            try {
                File badWords = new File("src/badwords.txt");
                scanner = new Scanner(badWords);
                int k = 0;
                for(int i = 0; i < words.length; i++){
                    for(int j = 0; scanner.hasNextLine(); j++) {
                        String word = scanner.nextLine();
                        if (words[i].equalsIgnoreCase(word)) {
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
