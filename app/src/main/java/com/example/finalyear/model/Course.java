package com.example.finalyear.model;

public class Course {
    private int id;
    private String code, unit, status;

    public Course(int id, String code, String unit, String status) {

        this.id = id;
        this.code = code;
        this.unit = unit;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getUnit() {
        return unit;
    }

    public String getStatus() {
        return status;
    }
}
