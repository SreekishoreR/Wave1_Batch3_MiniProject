package com.ust.project;

public class MainApp {

    public static void main(String[] args) {

        Employee emp = new Employee(101, "Rahul");

        Training tech = new TechnicalTraining(1, "Java Full Stack", "Mr. Kumar");
        Training soft = new SoftSkillTraining(2, "Communication Skills", "Ms. Anitha");

        System.out.println("Employee Name: " + emp.getEmpName());

        System.out.println("\nTraining 1: " + tech.getTrainingName());
        tech.evaluate();

        System.out.println("\nTraining 2: " + soft.getTrainingName());
        soft.evaluate();

        Certification cert = new Certification(true);
        cert.showStatus();
    }
}
