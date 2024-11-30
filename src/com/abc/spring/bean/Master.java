package com.abc.spring.bean;

public class Master {
    private Integer masterId;
    private String name;
    private String skill;

    public Master(Integer masterId, String name, String skill) {
        this.masterId = masterId;
        this.name = name;
        this.skill = skill;
    }

    public Master() {
    }

    public Integer getMasterId() {
        return masterId;
    }

    public void setMasterId(Integer masterId) {
        this.masterId = masterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Master{" +
                "masterId=" + masterId +
                ", name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }
}
