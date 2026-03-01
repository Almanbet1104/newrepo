public class ExamPreparation extends PlanItem {

    private Course course;

    public ExamPreparation(String title, Course course,
                           int estimatedHours, int daysUntil) {
        super(title, daysUntil, estimatedHours);
        this.course = course;
    }

    @Override
    public boolean isUrgent() {
        if (completed) return false;
        return daysUntil <= 5;
    }

    @Override
    public String toString() {
        return "ExamPreparation{" +
                "title='" + title + '\'' +
                ", course=" + course.getName() +
                ", daysUntil=" + daysUntil +
                ", estimatedHours=" + estimatedHours +
                ", completed=" + completed +
                '}';
    }
}