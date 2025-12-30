package com.ust.project;

public class Certification {

    private boolean completed;

    public Certification(boolean completed) {
        this.completed = completed;
    }

    public void showStatus() {
        if (completed) {
            System.out.println("Certification Completed ");
        } else {
            System.out.println("Certification Pending ");
        }
    }
}
