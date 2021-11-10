package Task_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Returns m-th largest number from second line of input file.
 * Assumptions:
 * File should contain two lines of integers split by space. First line indicates which m-max value (second parameter).
 * Second line should contain numbers to analyse (integers).
 *
 * @author MKgn
 */
class MLargestElement {

    int of(String filePath) {

        int m;
        int[] intArr;

        try (BufferedReader br = Files.newBufferedReader(Path.of(filePath))) {
            String line1 = br.readLine();
            String[] line1Arr = line1.split(" ");

            m = Integer.parseInt(line1Arr[1]);

            String line2 = br.readLine();

            intArr = Stream.of(line2.split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            if (m > intArr.length) {
                throw new RuntimeException("m-parameter is too large! Try another one (smaller then number of integers to analyse");
            } else {
                return mLargestInArray(intArr, m);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        throw new RuntimeException("file interrupted");
    }

    private static int mLargestInArray(int[] arr, int m) {
        Arrays.sort(arr);
        return arr[arr.length - m];
    }
}
