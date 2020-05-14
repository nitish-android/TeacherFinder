package com.tanzible.teacherfinder.lerningActivity.teacherHigher;

public class TeacherHigherModal {
    private String name;
    private String experience;
    private String subject;
    private String priority;

    public TeacherHigherModal(){

    }

    public TeacherHigherModal(String name, String experience, String subject, String priority) {
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
