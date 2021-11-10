package Task_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * AlmostPrimeNumbers - filters almost prime numbers from file (second line).
 * Assumptions:
 * File should contain two lines of integers split by space. First line tells how many integer will be analysed (line is skipped, stream does the job).
 * Second line should contain numbers to analyse -> integers (value less then 10_0000).
 * @author MKgn
 */
class AlmostPrimeNumbers {

    List<Integer> almostPrimeNumbers(String filePath) {

        int[] inputNumbers;

        try (BufferedReader br = Files.newBufferedReader(Path.of(filePath))) {
            br.readLine();
            String line2 = br.readLine();

            inputNumbers = Stream.of(line2.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            return Arrays.stream(inputNumbers).filter(AlmostPrimeNumbers::isAlmostPrime).boxed().collect(Collectors.toList());

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        throw new RuntimeException("file interrupted");
    }

    private static boolean isAlmostPrime(int input) {

        int counter = 0;
        for (int j = 2; j <= input / 2; j++) {
            if (input % j == 0) {
                counter++;
            }
        }
        return counter == 2;
    }
}
