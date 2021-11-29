package com.lealpy.simbirsoft_training.training.classes_block.entrance_exams;

    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */

import static com.lealpy.simbirsoft_training.training.classes_block.entrance_exams.Faculty.FACULTY_OF_ENERGY;
import static com.lealpy.simbirsoft_training.training.classes_block.entrance_exams.Faculty.FACULTY_OF_IT;
import static com.lealpy.simbirsoft_training.training.classes_block.entrance_exams.Faculty.FACULTY_OF_LAW;
import static com.lealpy.simbirsoft_training.training.classes_block.entrance_exams.Faculty.FACULTY_OF_MANAGEMENT;

public class EntranceProgram {

    public void program() {

        //Зарегистрировался новый студент:
        Entrant andreevVA = new Entrant(
                "Андреев",
                "Владимир",
                "Алексеевич",
                FACULTY_OF_ENERGY
        );

        //Студент сдает экзамены:
            getExams(andreevVA);

        }

    private void getExams(Entrant entrant) {

        switch (entrant.getFaculty()) {
            case FACULTY_OF_ENERGY:
                entrant.markExam1 = passExam(FACULTY_OF_ENERGY.getSubject1());
                entrant.markExam2 = passExam(FACULTY_OF_ENERGY.getSubject2());
                entrant.markExam3 = passExam(FACULTY_OF_ENERGY.getSubject3());
                break;

            case FACULTY_OF_IT:
                entrant.markExam1 = passExam(FACULTY_OF_IT.getSubject1());
                entrant.markExam2 = passExam(FACULTY_OF_IT.getSubject2());
                entrant.markExam3 = passExam(FACULTY_OF_IT.getSubject3());
                break;

            case FACULTY_OF_LAW:
                entrant.markExam1 = passExam(FACULTY_OF_LAW.getSubject1());
                entrant.markExam2 = passExam(FACULTY_OF_LAW.getSubject2());
                entrant.markExam3 = passExam(FACULTY_OF_LAW.getSubject3());
                break;

            case FACULTY_OF_MANAGEMENT:
                entrant.markExam1 = passExam(FACULTY_OF_MANAGEMENT.getSubject1());
                entrant.markExam2 = passExam(FACULTY_OF_MANAGEMENT.getSubject2());
                entrant.markExam3 = passExam(FACULTY_OF_MANAGEMENT.getSubject3());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + entrant.getFaculty());
        }

        int summaryScore = entrant.markExam1 + entrant.markExam2 + entrant.markExam3;

        if(summaryScore >= FACULTY_OF_ENERGY.getPassingScore()) {
            entrant.isGraduated = true;
        }
        else {
            entrant.isGraduated = false;
        }

    }

    private int passExam (Subject subject) {
        switch (subject) {
            case RUSSIAN:
                return TeachersDB.vorobievaGN.getRandomMark();
            case MATH:
                return TeachersDB.sergeenkoSL.getRandomMark();
            case PHYSICS:
                return TeachersDB.vladimirovTK.getRandomMark();
            case IT:
                return TeachersDB.gerasivovEM.getRandomMark();
            case ENGLISH:
                return TeachersDB.kozlovaSV.getRandomMark();
            case SOCIAL_STUDIES:
                return TeachersDB.timofeevaYS.getRandomMark();
            default:
                throw new IllegalStateException("Unexpected value: " + subject);
        }
    }

}
