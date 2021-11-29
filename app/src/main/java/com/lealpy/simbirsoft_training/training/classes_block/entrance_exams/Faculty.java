package com.lealpy.simbirsoft_training.training.classes_block.entrance_exams;

public enum Faculty {

    FACULTY_OF_ENERGY(Subject.RUSSIAN, Subject.MATH, Subject.PHYSICS, 11),
    FACULTY_OF_IT(Subject.RUSSIAN, Subject.MATH, Subject.IT, 13),
    FACULTY_OF_LAW(Subject.RUSSIAN, Subject.SOCIAL_STUDIES, Subject.ENGLISH, 12),
    FACULTY_OF_MANAGEMENT(Subject.RUSSIAN, Subject.MATH, Subject.ENGLISH, 10);

    Subject subject1;
    Subject subject2;
    Subject subject3;
    int passingScore;

    Faculty(Subject subject1, Subject subject2, Subject subject3, int passingScore) {
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.passingScore = passingScore;
    }

    public Subject getSubject1() {
        return subject1;
    }

    public Subject getSubject2() {
        return subject2;
    }

    public Subject getSubject3() {
        return subject3;
    }

    public int getPassingScore() {
        return passingScore;
    }
}
