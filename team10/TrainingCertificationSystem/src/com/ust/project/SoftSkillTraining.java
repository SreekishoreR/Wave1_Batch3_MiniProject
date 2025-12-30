package com.ust.project;

public class SoftSkillTraining extends Training {

    public SoftSkillTraining(int id, String name, String trainer) {
        super(id, name, trainer);
    }

    @Override
    public void evaluate() {
        System.out.println("Soft Skill Training evaluated by presentation");
    }
}
