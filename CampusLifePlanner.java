public class CampusLifePlanner {
    public static void main(String[] args) {

        Course oop = new Course("OOP", "Dr. Lee", 6);
        Course math = new Course("Discrete Math", "Prof. Smith", 3);

        AssignmentTask lab2 = new AssignmentTask("Lab 2", oop, 3, 1);
        AssignmentTask homework1 = new AssignmentTask("HW 1", math, 5, 5);

        System.out.println(oop);
        System.out.println(math);

        System.out.println();

        System.out.println(lab2);
        System.out.println("Is urgent? " + lab2.isUrgent());

        System.out.println();

        System.out.println(homework1);
        System.out.println("Is urgent? " + homework1.isUrgent());

        System.out.println();

        lab2.markCompleted();
        System.out.println("After completing Lab 2:");
        System.out.println(lab2);
        System.out.println("Is urgent? " + lab2.isUrgent());
    }
}
