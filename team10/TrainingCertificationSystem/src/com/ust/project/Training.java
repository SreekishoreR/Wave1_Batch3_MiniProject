package com.ust.project;

public class Training {

    private int trainingId;
    private String trainingName;
    private String trainerName;

    public Training(int trainingId, String trainingName, String trainerName) {
        this.trainingId = trainingId;
        this.trainingName = trainingName;
        this.trainerName = trainerName;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void evaluate() {
        System.out.println("General training evaluation");
    }
}
