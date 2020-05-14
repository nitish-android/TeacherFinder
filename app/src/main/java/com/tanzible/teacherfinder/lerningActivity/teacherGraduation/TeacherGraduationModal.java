package com.tanzible.teacherfinder.lerningActivity.teacherGraduation;

public class TeacherGraduationModal  {
    private String name;
    private String experience;
    private String subject;
    private String priority;

    public TeacherGraduationModal(){

    }

    public TeacherGraduationModal(String name, String experience, String subject, String priority) {
        this.name = name;
        this.experience = experience;
        this.subject = subject;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public String getExperience() {
        return experience;
    }

    public String getSubject() {
        return subject;
    }

    public String getPriority() {
        return priority;
    }


}
