void main() {
    class AssignmentTask {
        private String title;
        private Course course;
        private int estimatedHours;
        private int daysUntilDue;
        private boolean completed;

        public AssignmentTask(String title, Course course,
                              int estimatedHours, int daysUntilDue) {
            this.title = title;
            this.course = course;
            this.estimatedHours = estimatedHours;
            this.daysUntilDue = daysUntilDue;
            this.completed = false; // default
        }

        public String getTitle() {
            return title;
        }

        public Course getCourse() {
            return course;
        }

        public int getEstimatedHours() {
            return estimatedHours;
        }

        public int getDaysUntilDue() {
            return daysUntilDue;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void markCompleted() {
            completed = true;
        }

        public boolean isUrgent() {
            return daysUntilDue <= 2 && !completed;
        }

        @Override
        public String toString() {
            return "AssignmentTask{title='" + title +
                    "', course='" + course.getName() +
                    "', estHours=" + estimatedHours +
                    ", dueIn=" + daysUntilDue +
                    ", completed=" + completed + "}";
        }
    }
}