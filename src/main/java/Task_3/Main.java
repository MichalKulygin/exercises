package Task_3;

import static Task_3.ShortestDistanceInGraphFileReader.*;

/**
 * @author MKgn
 */
class Main {
    public static void main(String[] args) {

        String task3FilePath = "src/main/resources/task3.txt";
//        String task3FilePath = "src/main/resources/task3_b.txt";
//        String task3FilePath = "src/main/resources/task3_trains.txt";
//        String task3FilePath = "src/main/resources/task3_telecom_core_network.txt";

        ShortestDistanceInGraphCalculator sd = ShortestDistanceInGraphCalculator.fromFile((readFile(task3FilePath)));
        sd.displayResults();
    }
}
