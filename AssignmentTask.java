public class AssignmentTask extends PlanItem {

    private Course course;

    public AssignmentTask(String title, Course course,
                          int estimatedHours, int daysUntil) {
        super(title, daysUntil, estimatedHours);
        this.course = course;
    }

    @Override
    public boolean isUrgent() {
        if (completed) return false;
        return daysUntil <= 2;
    }

    @Override
    public String toString() {
        return "AssignmentTask{" +
                "title='" + title + '\'' +
                ", course=" + course.getName() +
                ", daysUntil=" + daysUntil +
                ", estimatedHours=" + estimatedHours +
                ", completed=" + completed +
                '}';
    }
}