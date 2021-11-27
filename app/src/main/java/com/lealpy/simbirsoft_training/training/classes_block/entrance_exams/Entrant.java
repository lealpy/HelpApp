package com.lealpy.simbirsoft_training.training.classes_block.entrance_exams;


public class Entrant {

    private String name;
    private String surname;
    private String patronymic;
    private Faculty faculty;
    public boolean isGraduated = false;
    public int markExam1;
    public int markExam2;
    public int markExam3;

    Entrant(
            String name,
            String surname,
            String patronymic,
            Faculty faculty
    ) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.faculty = faculty;
    }

    public Faculty getFaculty() {
        return faculty;
    }

}