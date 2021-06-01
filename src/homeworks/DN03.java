package homeworks;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DN03 {

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        int passwordLength = Integer.parseInt(args[1]);
        int passwordSeed = Integer.parseInt(args[2]);
        Random random = new Random(passwordSeed);
        ArrayList<String> fileContent = readFile(filePath);
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            int tableIndex = random.nextInt(fileContent.size());
            System.out.print(tableIndex);
            String word = fileContent.get(tableIndex);
            int wordIndex = random.nextInt(word.length());
            System.out.println(wordIndex);
            password.append(word.charAt(wordIndex));
        }
        System.out.println(password);
    }

    private static ArrayList<String> readFile(String path) throws FileNotFoundException {
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> words = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            words.add(myReader.nextLine());
        }
        myReader.close();
        return words;
    }

}
