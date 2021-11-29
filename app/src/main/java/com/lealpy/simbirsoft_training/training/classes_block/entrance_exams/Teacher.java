package com.lealpy.simbirsoft_training.training.classes_block.entrance_exams;

import java.util.Random;

public class Teacher {

    private String name;
    private String surname;
    private String patronymic;
    private Subject subject;

    Teacher (
            String name,
            String surname,
            String patronymic,
            Subject subject
    ) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.subject = subject;
    }

    public int getRandomMark() {
        Random random = new Random();
        return random.nextInt(5 - 2) + 2; // Имитируем выставление оценки
    }

    public int getMark(int mark) {
        return mark;
    }

}