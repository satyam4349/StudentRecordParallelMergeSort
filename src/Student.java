public class Student {
    private final int id;
    private final String name;
    private final double marks;
    private final String department;

    public Student(int id, String name, double marks, String department) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("%-6d %-18s %-8.2f %-24s",
                id, name, marks, department);
    }
}
