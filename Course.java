public class Course {

    private String name;
    private String instructor;
    private int credits;

    public Course(String name, String instructor, int credits) {
        this.name = name;
        this.instructor = instructor;
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public String getInstructor() {
        return instructor;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course{name='" + name +
                "', instructor='" + instructor +
                "', credits=" + credits + "}";
    }
}