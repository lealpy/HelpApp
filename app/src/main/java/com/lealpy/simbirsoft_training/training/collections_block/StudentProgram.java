package com.lealpy.simbirsoft_training.training.collections_block;

import com.lealpy.simbirsoft_training.training.collections_block.comparators.StudentGradeComparator;
import com.lealpy.simbirsoft_training.training.collections_block.comparators.StudentSurnameComparator;
import com.lealpy.simbirsoft_training.training.collections_block.comparators.StudentYearOfBirthComparator;
import java.util.ArrayList;
import java.util.Collections;

  /*
  Упорядочите студентов по курсу, причем студенты одного курса располагались
  в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
  Определите самого старшего студента и самого младшего студентов.
  Для каждой группы найдите лучшего с точки зрения успеваемости студента.
  */

public class StudentProgram {

    public void program() {

        ArrayList<Student> students = new ArrayList<> ();
        students.add(StudentsDB.alekseevMA);
        students.add(StudentsDB.ivanovSE);
        students.add(StudentsDB.maksimovVO);
        students.add(StudentsDB.vasilievRS);
        students.add(StudentsDB.kirillovAA);
        students.add(StudentsDB.andreevVV);
        students.add(StudentsDB.sergeevKA);
        students.add(StudentsDB.aleksandrovMM);
        students.add(StudentsDB.egorovSA);

        //Сортировка по курсу, в алфавитном порядке:
        students = sortByGradeAndSurname(students);

        //Получение среднего балла каждой группы по предметам:
        int averageMarkMathGroup1 = getAverageMark(students, "Группа-1", "Математика");
        int averageMarkPhysicsGroup2 = getAverageMark(students, "Группа-2", "Физика");
        int averageMarkITGroup3 = getAverageMark(students, "Группа-3", "Информатика");
        int averageMarkRussianGroup1 = getAverageMark(students, "Группа-1", "Русский язык");
        int averageMarkEnglishGroup2 = getAverageMark(students, "Группа-2", "Английский язык");

        //Получение самого старшего и самого младшего студента:
        Student theOldestStudent = getTheOldestStudent(students);
        Student theYongestStudent = getTheYongestStudent(students);

        //Получение лучших с точки зрения успеваемости студентов для каждой группы
        //Примечание: выводит список студентов, а не одного студента, поскольку у нескольких
        // студентов может быть одинаковый средний балл
        ArrayList<Student> theBestStudentsOfGroup1 = getTheBestStudents(students, "Группа-1");
        ArrayList<Student> theBestStudentsOfGroup2 = getTheBestStudents(students, "Группа-2");
        ArrayList<Student> theBestStudentsOfGroup3 = getTheBestStudents(students, "Группа-3");

    }

    public ArrayList<Student> sortByGradeAndSurname(ArrayList<Student> students) {
        students.sort(new StudentGradeComparator().thenComparing(new StudentSurnameComparator()));
        return students;
    }

    public int getAverageMark(ArrayList<Student> students, String group, String subject) {

        int counter = 0;
        int summaryMark = 0;

        for (int i = 0; i < students.size(); i++) {

            if(students.get(i).getGroup() == group) {

                counter++;

                switch (subject) {
                    case "Математика" :
                        summaryMark += students.get(i).getMarkMath();
                        break;
                    case "Физика" :
                        summaryMark += students.get(i).getMarkPhysics();
                        break;
                    case "Информатика" :
                        summaryMark += students.get(i).getMarkIT();
                        break;
                    case "Русский язык" :
                        summaryMark += students.get(i).getMarkRussian();
                        break;
                    case "Английский язык" :
                        summaryMark += students.get(i).getMarkEnglish();
                        break;
                }
            }
        }

        if(summaryMark > 0) return summaryMark / counter;
        else return 0;
    }

    public Student getTheOldestStudent(ArrayList<Student> students) {
        return Collections.max(students, new StudentYearOfBirthComparator());
    }

    public Student getTheYongestStudent(ArrayList<Student> students) {
        return Collections.min(students, new StudentYearOfBirthComparator());
    }

    public ArrayList<Student> getTheBestStudents(ArrayList<Student> students, String group) {
        int maxAverageMark = 0;
        ArrayList<Student> theBestStudents = new ArrayList<>();

        for (int i = 0; i < students.size(); i++) {
            if(students.get(i).getGroup() == group) {
                int averageMark = (
                        students.get(i).getMarkMath() +
                        students.get(i).getMarkPhysics() +
                        students.get(i).getMarkIT() +
                        students.get(i).getMarkRussian() +
                        students.get(i).getMarkEnglish()
                    ) / 5;

                if(averageMark > maxAverageMark) {
                    maxAverageMark = averageMark;
                    theBestStudents.clear();
                    theBestStudents.add(students.get(i));
                }
                else if(averageMark == maxAverageMark) {
                    theBestStudents.add(students.get(i));
                }
            }
        }
        return theBestStudents;
    }

}
