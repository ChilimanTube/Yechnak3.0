package DiscordBot;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Moderation {
    static Scanner scanner;
    static ArrayList<String> wordList = new ArrayList<>();
    /*{
        try {
            File badWords = new File("src/badwords.txt");
            scanner = new Scanner(badWords);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                wordList.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading the file.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }*/

    public static boolean badWordDetection(String @NotNull [] words){
        {
            try {
                File badWords = new File("src/badwords.txt");
                scanner = new Scanner(badWords);
                while (scanner.hasNextLine()) {
                    String word = scanner.nextLine();
                    wordList.add(word);
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred while loading the file.");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < wordList.size(); j++){
                if (words[i].equalsIgnoreCase(wordList.get(j))){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return false;
    }

}
