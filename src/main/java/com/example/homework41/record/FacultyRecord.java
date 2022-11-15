package com.example.homework36.record;

import javax.validation.constraints.NotBlank;

public class FacultyRecord {

    private Long id;

    @NotBlank(message = "Название факультета должно быть заполнено!")
    private String name;
    @NotBlank(message = "цвет факультета должно быть заполнено!")
    private String color;

    public FacultyRecord() {
    }

    public FacultyRecord(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

