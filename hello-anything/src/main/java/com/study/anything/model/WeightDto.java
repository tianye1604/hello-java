package com.study.anything.model;

public class WeightDto {

    private String name;

    private int weight;

    public WeightDto(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }
}
