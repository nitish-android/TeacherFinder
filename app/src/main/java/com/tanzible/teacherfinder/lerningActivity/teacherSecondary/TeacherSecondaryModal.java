package com.tanzible.teacherfinder.lerningActivity.teacherSecondary;

public class TeacherSecondaryModal {
    private String teacher_image;
    private String name;
    private String experience;
    private String subject;
    private String priority;

    public TeacherSecondaryModal(String teacher_image, String name, String experience, String subject, String priority) {
        this.teacher_image = teacher_image;
        this.name = name;
        this.experience = experience;
        this.subject = subject;
        this.priority = priority;
    }

    public String getTeacher_image() {
        return teacher_image;
    }

    public void setTeacher_image(String teacher_image) {
        this.teacher_image = teacher_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
