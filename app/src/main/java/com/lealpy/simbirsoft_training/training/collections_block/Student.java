package com.lealpy.simbirsoft_training.training.collections_block;

     /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */

public class Student {

    private String surname;
    private String name;
    private String patronymic;
    private String group;
    private int yearOfBirth;
    private int grade;
    private int markMath;
    private int markPhysics;
    private int markIT;
    private int markEnglish;
    private int markRussian;

    Student (
            String surname,
            String name,
            String patronymic,
            int yearOfBirth,
            int grade,
            String group,
            int markMath,
            int markPhysics,
            int markIT,
            int markEnglish,
            int markRussian
    ) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.yearOfBirth = yearOfBirth;
        this.grade = grade;
        this.group = group;
        this.markMath = markMath;
        this.markPhysics = markPhysics;
        this.markIT = markIT;
        this.markEnglish = markEnglish;
        this.markRussian = markRussian;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public int getGrade() {
        return grade;
    }

    public String getGroup() {
        return group;
    }

    public int getMarkMath() {
        return markMath;
    }

    public int getMarkPhysics() {
        return markPhysics;
    }

    public int getMarkIT() {
        return markIT;
    }

    public int getMarkEnglish() {
        return markEnglish;
    }

    public int getMarkRussian() {
        return markRussian;
    }
}