package com.example.homework36.record;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class StudentRecord {

    private Long id;

    @NotBlank(message = "Имя студента должно быть заполнено!")
    private String name;

    @Min(value = 17,message = "минимальный возраст студента 17 лет!")
    @Max(value = 25,message = "максимальный возраст студента 25 лет!")
    private int age;

    private FacultyRecord faculty;

    private AvatarRecord avatar;

    public StudentRecord() {
    }

    public StudentRecord(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public FacultyRecord getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyRecord faculty) {
        this.faculty = faculty;
    }

    public AvatarRecord getAvatar() {
        return avatar;
    }

    public void setAvatar(AvatarRecord avatar) {
        this.avatar = avatar;
    }
}

