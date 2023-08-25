package Internship;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter 'text' to input text manually, or 'file' to provide a file: ");
        String inputType = scanner.next();

        String text = "";

        if (inputType.equalsIgnoreCase("text")) {
            System.out.print("Enter your text: ");
            scanner.nextLine(); // Consume newline
            text = scanner.nextLine();
        } else if (inputType.equalsIgnoreCase("file")) {
            System.out.print("Enter the file path: ");
            String filePath = scanner.next();
            try {
                text = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
                return;
            }
        } else {
            System.out.println("Invalid input type.");
            return;
        }

        String[] words = text.split("\\s+|\\p{Punct}");

        int totalWordCount = words.length;

        // Count word frequency
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            if (!word.isEmpty()) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }

        System.out.println("Total word count: " + totalWordCount);
        System.out.println("Unique word count: " + wordFrequency.size());

        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }
}
