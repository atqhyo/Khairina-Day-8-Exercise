import java.io.*;
import java.util.*;

public class FileProcessor {

    // Method to read integers from a file
    public List<Integer> readFile(String fileName) throws IOException {
        List<Integer> num = new ArrayList<>();

        try (FileReader reader = new FileReader(fileName);
            BufferedReader in = new BufferedReader(reader)) {

            String line;
            while ((line = in.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    num.add(number);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number format " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while opening the file: " + e.getMessage());
            throw e;
        }

        return num;
    }

    // Method to divide numbers and handle division by zero
    public List<String> divideNumbers (List<Integer> num, int divisor) {
        List<String> results = new ArrayList<>();

        for (int number : num) {
            try {
                int result = number / divisor;
                results.add(String.valueOf(result));
            } catch (ArithmeticException e) {
                results.add("Division by zero error for number " + number);
            }
        }

        return results;
    }

    // Method to write results to a file
    public void writeFile(String fileName, List<String> results) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter((fileName)))) {
            for (String result : results) {
                writer.write(result + "\n");
            }
        }
    }
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor();
        Scanner scanner = new Scanner(System.in);

        try {
            List<Integer> numbers = fileProcessor.readFile("input.txt");
            System.out.println("Numbers read from file: " + numbers);

            System.out.println("Enter a divisor: ");
            int divisor = scanner.nextInt();

            List<String> results = fileProcessor.divideNumbers(numbers, divisor);
            System.out.println("Results of division: " + results);

            fileProcessor.writeFile("output.txt", results);
            System.out.println("Division results written to output.txt.");
        } catch (IOException e) {
            System.out.println("File processing failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}