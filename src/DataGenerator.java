import java.util.Random;

public class DataGenerator {

    public enum CaseType {
        BEST_CASE,
        WORST_CASE,
        RANDOM_CASE
    }

    private static final String[] FIRST_NAMES = {
        "Aarav", "Aditi", "Amit", "Ananya", "Arjun",
        "Diya", "Isha", "Karan", "Neha", "Priya",
        "Rahul", "Riya", "Sahil", "Sneha", "Varun"
    };

    private static final String[] DEPARTMENTS = {
        "Computer Engineering",
        "Information Technology",
        "Electronics Engineering",
        "Mechanical Engineering"
    };

    private DataGenerator() {}

    public static Student[] generate(int size, CaseType type, long seed) {
        if (size < 1) {
            throw new IllegalArgumentException("Size must be positive.");
        }

        Random random = new Random(seed);
        Student[] students = new Student[size];

        for (int i = 0; i < size; i++) {
            double marks;

            if (type == CaseType.BEST_CASE) {
                // Already sorted in descending order for our ranking criterion.
                marks = 100.0 - (99.0 * i / Math.max(1, size - 1));
            } else if (type == CaseType.WORST_CASE) {
                // Reverse sorted for our ranking criterion.
                marks = 1.0 + (99.0 * i / Math.max(1, size - 1));
            } else {
                marks = 35.0 + random.nextDouble() * 65.0;
            }

            String name = FIRST_NAMES[i % FIRST_NAMES.length] + " " + (i + 1);
            String department = DEPARTMENTS[i % DEPARTMENTS.length];
            students[i] = new Student(i + 1, name, marks, department);
        }

        return students;
    }

    public static Student[] sampleStudents() {
        return new Student[] {
            new Student(101, "Rahul", 87.5, "Computer Engineering"),
            new Student(102, "Priya", 92.0, "Computer Engineering"),
            new Student(103, "Amit", 74.5, "Information Technology"),
            new Student(104, "Sneha", 95.0, "Computer Engineering"),
            new Student(105, "Karan", 81.0, "Mechanical Engineering"),
            new Student(106, "Neha", 89.5, "Electronics Engineering"),
            new Student(107, "Riya", 78.0, "Information Technology"),
            new Student(108, "Arjun", 91.0, "Computer Engineering")
        };
    }
}
