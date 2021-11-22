package com.lealpy.simbirsoft_training.training.classes_block.entrance_exams;


public class Entrant {

    private String name, surname, patronymic;
    private Faculty faculty;
    boolean isGraduated = false;
    int markExam1, markExam2, markExam3;

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