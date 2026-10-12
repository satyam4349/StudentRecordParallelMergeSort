# Student Record Management and Ranking System Using Parallel Merge Sort

A Java-based implementation and performance analysis of **Sequential Merge Sort** and **Multithreaded Merge Sort** for sorting and ranking student records.

## 📌 Project Overview

This mini project implements and compares two approaches to sorting student records:

1. **Sequential Merge Sort**
2. **Multithreaded Merge Sort**

The algorithms are tested with different dataset sizes and input conditions to compare execution time and speedup.

The project analyzes:

- Best-case input
- Worst-case input
- Random input
- Different dataset sizes
- Sequential vs. multithreaded execution

The multithreaded implementation uses Java's **Fork/Join Framework** to divide sorting work into parallel subtasks.

---

## 🎯 Objectives

- Implement Merge Sort for sorting student records.
- Implement Multithreaded Merge Sort using parallel processing.
- Compare execution time of sequential and multithreaded Merge Sort.
- Analyze performance for best-case and worst-case inputs.
- Study the effect of input size on algorithm performance.

---

## 📝 Problem Statement

> Implement Merge Sort and Multithreaded Merge Sort for sorting student records and compare the execution time of both algorithms. The performance of both approaches is analyzed for best-case and worst-case inputs using different dataset sizes.

---

## 🏗️ System Architecture

```text
                    ┌───────────────────────┐
                    │    Student Records    │
                    └───────────┬───────────┘
                                │
                                ▼
                    ┌───────────────────────┐
                    │   Data Generation     │
                    │ Best / Worst / Random │
                    └───────────┬───────────┘
                                │
                    ┌───────────┴───────────┐
                    │                       │
                    ▼                       ▼
        ┌────────────────────┐   ┌────────────────────────┐
        │ Sequential         │   │ Multithreaded          │
        │ Merge Sort         │   │ Merge Sort             │
        └─────────┬──────────┘   └───────────┬────────────┘
                  │                          │
                  └────────────┬─────────────┘
                               ▼
                    ┌───────────────────────┐
                    │ Performance Analyzer  │
                    │ Execution Time        │
                    │ Speedup               │
                    │ Sorting Verification  │
                    └───────────┬───────────┘
                                │
                                ▼
                    ┌───────────────────────┐
                    │ Performance Results   │
                    └───────────────────────┘
```

---

## 🧠 Algorithms Used

### Sequential Merge Sort

Merge Sort follows the **Divide and Conquer** strategy:

1. Divide the input into two halves.
2. Recursively sort both halves.
3. Merge the sorted halves.

**Time Complexity:** `O(n log n)` for best, average, and worst cases.

**Space Complexity:** `O(n)`

### Multithreaded Merge Sort

The multithreaded version processes independent portions of the input concurrently using Java's **Fork/Join Framework**.

A **parallel threshold of 2048 elements** is used. Smaller portions are processed sequentially to reduce task-management overhead.

---

## ⚙️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Programming Language |
| Java Fork/Join Framework | Multithreaded Processing |
| `System.nanoTime()` | Execution-Time Measurement |
| VS Code | Development Environment |
| Git & GitHub | Version Control |

---

## 📂 Project Structure

```text
StudentRecordParallelMergeSort/
│
├── src/
│   ├── Student.java
│   ├── MergeSort.java
│   ├── ParallelMergeSort.java
│   ├── DataGenerator.java
│   ├── PerformanceAnalyzer.java
│   └── Main.java
│
├── run_project.bat
├── run_project.sh
├── README.md
└── .gitignore
```

### Source File Description

- **Student.java** — Defines student records such as ID, name, department and marks.
- **MergeSort.java** — Sequential Merge Sort implementation.
- **ParallelMergeSort.java** — Multithreaded Merge Sort using Fork/Join.
- **DataGenerator.java** — Generates best-case, worst-case and random datasets.
- **PerformanceAnalyzer.java** — Measures execution time, speedup and sorting correctness.
- **Main.java** — Provides the console menu and runs the application.

---

# ▶️ How to Run

## Prerequisites

Install **Java JDK 17 or later**.

Check installation:

```bash
java -version
javac -version
```

## Using VS Code

Open the project folder in VS Code, then open the terminal and run:

```bash
cd src
javac *.java
java Main
```

## Windows

Run:

```bash
run_project.bat
```

## Linux / macOS

Run:

```bash
chmod +x run_project.sh
./run_project.sh
```

---

# 🖥️ Application Menu

```text
==============================================
 STUDENT RECORD MANAGEMENT & RANKING SYSTEM
==============================================
1. Display Sample Student Records
2. Sort Sample Records - Sequential Merge Sort
3. Sort Sample Records - Multithreaded Merge Sort
4. Run Performance Benchmark
5. Run Complete Performance Analysis
6. Exit
==============================================
Enter your choice:
```

---

# 📊 Performance Analysis

Tested dataset sizes:

```text
1,000
10,000
100,000
500,000
1,000,000
```

Multithreaded configuration:

```text
Threads: 8
Parallel Threshold: 2048
```

## Best-Case Results

| Input Size | Sequential (ms) | Multithreaded (ms) | Speedup |
|---:|---:|---:|---:|
| 1,000 | 0.051 | 0.348 | 0.15× |
| 10,000 | 0.484 | 1.614 | 0.30× |
| 100,000 | 7.588 | 6.034 | 1.26× |
| 500,000 | 106.770 | 36.043 | 2.96× |
| 1,000,000 | 224.592 | 126.155 | 1.78× |

## Worst-Case Results

| Input Size | Sequential (ms) | Multithreaded (ms) | Speedup |
|---:|---:|---:|---:|
| 1,000 | 0.031 | 0.329 | 0.09× |
| 10,000 | 0.423 | 0.842 | 0.50× |
| 100,000 | 6.461 | 3.067 | 2.11× |
| 500,000 | 115.108 | 35.937 | 3.20× |
| 1,000,000 | 244.256 | 75.121 | 3.25× |

## Random-Case Results

| Input Size | Sequential (ms) | Multithreaded (ms) | Speedup |
|---:|---:|---:|---:|
| 1,000 | 0.070 | 0.359 | 0.20× |
| 10,000 | 1.451 | 1.592 | 0.91× |
| 100,000 | 21.392 | 9.196 | 2.33× |
| 500,000 | 181.266 | 59.496 | 3.05× |
| 1,000,000 | 353.677 | 137.348 | 2.58× |

---

## 📈 Performance Observations

For small datasets, sequential Merge Sort can be faster because multithreading introduces task creation, scheduling and synchronization overhead.

For larger datasets, multithreaded Merge Sort provides a clear performance advantage because the workload can be divided among multiple threads.

For the tested worst-case dataset containing **1,000,000 records**:

```text
Sequential      : 244.256 ms
Multithreaded   : 75.121 ms
Speedup         : 3.25×
```

### Speedup Formula

```text
Speedup = Sequential Execution Time
          -------------------------
          Multithreaded Execution Time
```

A speedup greater than `1×` indicates that the multithreaded implementation was faster for that test.

---

## ✅ Sorting Verification

The application verifies the sorted output after execution.

```text
Sorting Verified: YES
```

This ensures that performance improvement does not compromise sorting correctness.

---

# 🔬 Experimental Analysis

The experiment demonstrates that practical algorithm performance depends on factors such as:

- Input size
- Processor cores
- Thread/task overhead
- Memory access
- Parallelization threshold
- Dataset characteristics

Both implementations have an asymptotic complexity of **O(n log n)**, but their actual execution times differ because of parallel execution overhead and available computing resources.

---

# 🏆 Key Findings

- Sequential Merge Sort performs well for small datasets.
- Multithreaded Merge Sort becomes more effective as input size increases.
- Parallel processing introduces overhead for small workloads.
- Large datasets provide enough computation to benefit from multiple threads.
- The tested worst-case dataset achieved a maximum observed speedup of **3.25×**.
- Both implementations produced correctly sorted results.
- The project demonstrates the practical use of **Divide and Conquer and Parallel Processing**.

---

# 🎓 Academic Information

**Mini Project:** Student Record Management and Ranking System Using Parallel Merge Sort

**Subject:** Design and Analysis of Algorithms (DAA)

**Department:** Computer Engineering

**Institute:** Marathwada Mitra Mandal's Institute of Technology (MMIT), Lohgaon, Pune

**Academic Year:** 2026–2027

### Team Members

| Student | Exam No. |
|---|---|
| Geet Jamdal | BEA56 |
| Satyam Patil | BEB14 |
| Mayur Sarode | BEB34 |

### Project Guide

**Dr. Monika Dangore**

---

# 📚 Learning Outcomes

- Divide and Conquer
- Merge Sort
- Recursion
- Multithreading
- Parallel Processing
- Java Fork/Join Framework
- Algorithm Complexity
- Performance Measurement
- Speedup Analysis
- Best-Case and Worst-Case Analysis
- Experimental Algorithm Comparison

---

# 📷 Project Screenshots

Recommended folder structure:

```text
docs/
└── screenshots/
    ├── main-menu.png
    ├── sequential-sort.png
    ├── parallel-sort.png
    └── performance-analysis.png
```

Screenshots of the actual application can be added to this folder and referenced here.

---

# 📜 Conclusion

The project successfully implements and compares Sequential and Multithreaded Merge Sort for student record management. The experimental results demonstrate that multithreaded sorting can significantly reduce execution time for sufficiently large datasets, while sequential processing remains effective for smaller inputs.

The project provides a practical demonstration of how **parallel algorithms can improve computational performance on multi-core systems** and highlights the importance of considering both theoretical complexity and real-world execution overhead.

---

## ⭐ Project Highlights

```text
✔ Sequential Merge Sort
✔ Multithreaded Merge Sort
✔ Java Fork/Join Framework
✔ Student Record Management
✔ Best/Worst/Random Dataset Generation
✔ Performance Benchmarking
✔ Execution Time Comparison
✔ Speedup Calculation
✔ Sorting Verification
✔ Multi-size Experimental Analysis
```

---

## 👨‍💻 Developed For

**Design and Analysis of Algorithms — Mini Project**

**MMIT, Lohgaon, Pune**

**Academic Year 2026–2027**
