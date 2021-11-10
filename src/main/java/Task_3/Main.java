package Task_3;

/**
 * @author MKgn
 */
class Main {
    public static void main(String[] args) {

        String task3FilePath = "src/main/resources/task3.txt";
//        String task3FilePath = "src/main/resources/task3_b.txt";
//        String task3FilePath = "src/main/resources/task3_trains.txt";
//        String task3FilePath = "src/main/resources/task3_telecom_core_network.txt";

        ShortestDistanceInGraph shortestDistanceInGraph = ShortestDistanceInGraph.shortestDistanceInGraphFromFile(task3FilePath);
        shortestDistanceInGraph.displayResults();
    }
}
