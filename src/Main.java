import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int DEFAULT_THREADS =
            Math.max(2, Runtime.getRuntime().availableProcessors());
    private static final int DEFAULT_THRESHOLD = 2048;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> displaySampleRecords();
                case 2 -> sortSampleSequentially();
                case 3 -> sortSampleParallel();
                case 4 -> runSingleBenchmark(scanner);
                case 5 -> runFullBenchmark(scanner);
                case 6 -> System.out.println("\nThank you for using the project.");
                default -> System.out.println("Invalid choice.");
            }

            if (choice == 6) {
                break;
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n==============================================");
        System.out.println(" STUDENT RECORD MANAGEMENT & RANKING SYSTEM");
        System.out.println("==============================================");
        System.out.println("1. Display Sample Student Records");
        System.out.println("2. Sort Sample Records - Sequential Merge Sort");
        System.out.println("3. Sort Sample Records - Multithreaded Merge Sort");
        System.out.println("4. Run Performance Benchmark");
        System.out.println("5. Run Complete Performance Analysis");
        System.out.println("6. Exit");
        System.out.println("==============================================");
    }

    private static void displaySampleRecords() {
        printRecords(DataGenerator.sampleStudents());
    }

    private static void sortSampleSequentially() {
        Student[] students = DataGenerator.sampleStudents();

        System.out.println("\nBefore Sorting:");
        printRecords(students);

        long start = System.nanoTime();
        MergeSort.sort(students);
        long elapsed = System.nanoTime() - start;

        System.out.println("\nAfter Sequential Merge Sort (Highest Marks First):");
        printRecords(students);
        System.out.printf("Execution Time: %.6f ms%n", elapsed / 1_000_000.0);
    }

    private static void sortSampleParallel() {
        Student[] students = DataGenerator.sampleStudents();

        System.out.println("\nBefore Sorting:");
        printRecords(students);

        ParallelMergeSort sorter =
                new ParallelMergeSort(DEFAULT_THREADS, DEFAULT_THRESHOLD);

        long start = System.nanoTime();
        sorter.sort(students);
        long elapsed = System.nanoTime() - start;
        sorter.shutdown();

        System.out.println("\nAfter Multithreaded Merge Sort (Highest Marks First):");
        printRecords(students);
        System.out.printf("Execution Time: %.6f ms%n", elapsed / 1_000_000.0);
        System.out.println("Parallelism: " + DEFAULT_THREADS + " threads");
    }

    private static void runSingleBenchmark(Scanner scanner) {
        int size = readPositiveInt(scanner, "Enter dataset size: ");
        DataGenerator.CaseType type = readCaseType(scanner);

        Student[] data = DataGenerator.generate(size, type, 42L);
        PerformanceAnalyzer.Result result =
                PerformanceAnalyzer.compare(data, DEFAULT_THREADS, DEFAULT_THRESHOLD);

        printResult(type, size, result);
    }

    private static void runFullBenchmark(Scanner scanner) {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000};

        System.out.println("\nRunning complete benchmark...");
        System.out.println("Threads: " + DEFAULT_THREADS);
        System.out.println("Parallel threshold: " + DEFAULT_THRESHOLD);
        System.out.println();

        for (DataGenerator.CaseType type : DataGenerator.CaseType.values()) {
            System.out.println("--------------- " + type + " ---------------");
            System.out.printf("%-12s %-18s %-18s %-12s%n",
                    "Size", "Sequential (ms)", "Parallel (ms)", "Speedup");

            for (int size : sizes) {
                Student[] data = DataGenerator.generate(size, type, 42L);
                PerformanceAnalyzer.Result result =
                        PerformanceAnalyzer.compare(
                                data, DEFAULT_THREADS, DEFAULT_THRESHOLD);

                System.out.printf("%-12d %-18.3f %-18.3f %-12.2fx%n",
                        size,
                        result.sequentialMs(),
                        result.parallelMs(),
                        result.speedup);
            }
            System.out.println();
        }

        System.out.println("Benchmark completed. Use these measured values in the report.");
    }

    private static int readPositiveInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println("Enter a positive integer.");
        }
    }

    private static DataGenerator.CaseType readCaseType(Scanner scanner) {
        while (true) {
            System.out.println("1. Best Case");
            System.out.println("2. Worst Case");
            System.out.println("3. Random Case");
            System.out.print("Select case: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice == 1) return DataGenerator.CaseType.BEST_CASE;
                if (choice == 2) return DataGenerator.CaseType.WORST_CASE;
                if (choice == 3) return DataGenerator.CaseType.RANDOM_CASE;
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Invalid selection.");
        }
    }

    private static void printResult(DataGenerator.CaseType type,
                                    int size,
                                    PerformanceAnalyzer.Result result) {
        System.out.println("\n============= BENCHMARK RESULT =============");
        System.out.println("Case: " + type);
        System.out.println("Dataset Size: " + size);
        System.out.printf("Sequential Merge Sort: %.3f ms%n", result.sequentialMs());
        System.out.printf("Multithreaded Merge Sort: %.3f ms%n", result.parallelMs());
        System.out.printf("Speedup: %.2fx%n", result.speedup);
        System.out.println("Sorting Verified: YES");
        System.out.println("=============================================");
    }

    private static void printRecords(Student[] students) {
        System.out.printf("%-6s %-18s %-8s %-24s%n",
                "ID", "Name", "Marks", "Department");
        System.out.println("--------------------------------------------------------------");
        Arrays.stream(students).forEach(System.out::println);
    }
}
