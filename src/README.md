# Student Record Management and Ranking System Using Parallel Merge Sort

## Project Overview
This DAA mini-project implements a student record management and ranking system using:
- Sequential Merge Sort
- Multithreaded Merge Sort
- Best-case, worst-case and random-case datasets
- Execution-time measurement
- Speedup calculation

Student records are ranked from highest marks to lowest marks.

## Technology
- Java
- JDK 17 or higher
- Java Fork/Join Framework
- VS Code / IntelliJ IDEA / Eclipse

## Project Structure
- `Student.java` - Student data model
- `MergeSort.java` - Sequential Merge Sort
- `ParallelMergeSort.java` - Multithreaded Merge Sort using Fork/Join
- `DataGenerator.java` - Generates test datasets
- `PerformanceAnalyzer.java` - Measures and compares execution time
- `Main.java` - Console application and benchmark runner

## How to Run

### Windows
Open a terminal inside the `src` folder:

```text
javac *.java
java Main
```

### Linux/macOS
Open a terminal inside the `src` folder:

```text
javac *.java
java Main
```

## Benchmark
Choose option 5 from the menu to run:
- 1,000 records
- 10,000 records
- 100,000 records
- 500,000 records
- 1,000,000 records

for:
- BEST_CASE
- WORST_CASE
- RANDOM_CASE

The program prints actual execution times and speedup values. Use those measured values in the final project report.

## Important Note
Benchmark results depend on the computer, Java version, CPU load and JVM state. Do not copy example timings from a report as final experimental results; use the results generated on the machine used for the project.
