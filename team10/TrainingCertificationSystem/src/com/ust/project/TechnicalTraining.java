package com.ust.project;

public class TechnicalTraining extends Training {

    public TechnicalTraining(int id, String name, String trainer) {
        super(id, name, trainer);
    }

    @Override
    public void evaluate() {
        System.out.println("Technical Training evaluated by coding test");
    }
}
