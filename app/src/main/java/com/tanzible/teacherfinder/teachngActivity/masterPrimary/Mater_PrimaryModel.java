package com.tanzible.teacherfinder.teachngActivity.masterPrimary;

public class Mater_PrimaryModel {
    private String student_image;

    private String name;
    private String priority;
    private String student_class;
    private String subject;

    public Mater_PrimaryModel( String student_image, String name, String priority,String subject,String student_class) {
        this.student_image = student_image;
        this.name = name;
        this.priority = priority;
        this.student_class = student_class;
        this.subject = subject;
    }

    public String getStudent_image() {
        return student_image;
    }

    public void setStudent_image(String student_image) {
        this.student_image = student_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
