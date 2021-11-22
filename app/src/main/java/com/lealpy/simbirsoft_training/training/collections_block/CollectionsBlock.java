package com.lealpy.simbirsoft_training.training;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Набор тренингов по работе со списками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        ArrayList<T> unitedList = new ArrayList<>();
        unitedList.addAll(firstList);
        unitedList.addAll(secondList);
        unitedList.sort(Collections.reverseOrder());
        return unitedList;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        ArrayList<T> outputList = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            outputList.add(inputList.get(i));
            for (int k = 0; k < i; k++) {
                outputList.add(inputList.get(k));
            }
        }

        return outputList;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        HashSet firstSet = new HashSet(firstList);
        HashSet secondSet = new HashSet(secondList);

        return firstSet.equals(secondSet);
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        if(inputList.isEmpty()) return inputList;

        ArrayList<T> list = new ArrayList<>(inputList);
        int k = n % list.size();

        if(n >= 0) {
            for (int i = 0; i < list.size(); i++) {
                if (k < inputList.size() - i) {
                    list.set(i + k, inputList.get(i));
                }
                else {
                    list.set(k - (inputList.size() - 1 - i) - 1, inputList.get(i));
                }
            }
        }
        else {
            k = -k;
            for (int i = 0; i < list.size(); i++) {
                if (k <= i) {
                    list.set(i - k, inputList.get(i));
                }
                else {
                    list.set(inputList.size() + i - k, inputList.get(i));
                }
            }
        }

        return list;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        for (int i = 0; i < inputList.size(); i++) {
            if(inputList.get(i) == a) {
                inputList.set(i, b);
            }
        }
        return inputList;
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
    class Student {

        private String surname, name, patronymic, group;
        private int yearOfBirth, grade, markMath, markPhysics, markIT, markEnglish, markRussian;

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

    class StudentProgram {

        //Создадим 9 студентов: по 3 на каждый курс.

        Student alekseevMA = new Student(
                "Алексеев",
                "Максим",
                "Андреевич",
                2003,
                1,
                "Группа-1",
                4,
                3,
                4,
                5,
                5
        );

        Student ivanovSE = new Student(
                "Иванов",
                "Сергей",
                "Евгеньевич",
                2003,
                1,
                "Группа-1",
                5,
                4,
                3,
                4,
                4
        );

        Student maksimovVO = new Student(
                "Максимов",
                "Владимир",
                "Олегович",
                2002,
                1,
                "Группа-1",
                5,
                4,
                3,
                4,
                4
        );

        Student vasilievRS = new Student(
                "Васильев",
                "Руслан",
                "Сергеевич",
                2002,
                2,
                "Группа-2",
                4,
                3,
                3,
                5,
                3
        );

        Student kirillovAA = new Student(
                "Кириллов",
                "Александр",
                "Алексеевич",
                2003,
                2,
                "Группа-2",
                4,
                5,
                3,
                5,
                3
        );

        Student andreevVV = new Student(
                "Андреев",
                "Валерий",
                "Васильевич",
                2003,
                2,
                "Группа-2",
                3,
                4,
                3,
                3,
                5
        );

        Student sergeevKA = new Student(
                "Сергеев",
                "Кирилл",
                "Андреевич",
                2001,
                3,
                "Группа-3",
                3,
                4,
                5,
                4,
                3
        );

        Student aleksandrovMM = new Student(
                "Александров",
                "Максим",
                "Максимович",
                2002,
                3,
                "Группа-3",
                5,
                5,
                4,
                5,
                4
        );

        Student egorovSA = new Student(
                "Егоров",
                "Сергей",
                "Алексеевич",
                2000,
                3,
                "Группа-3",
                3,
                5,
                5,
                5,
                5
        );

        public void program() {
            ArrayList<Student> students = new ArrayList<> ();
            students.add(alekseevMA);
            students.add(ivanovSE);
            students.add(maksimovVO);
            students.add(vasilievRS);
            students.add(kirillovAA);
            students.add(andreevVV);
            students.add(sergeevKA);
            students.add(aleksandrovMM);
            students.add(egorovSA);

            /*

      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */

        //Сортировка по курсу, в алфавитном порядке:
        students = sortByGradeAndSurname(students);

        //Получение среднего балла каждой группы по предметам:
        getAverageMark(students, "Группа-1", "Математика");
        getAverageMark(students, "Группа-2", "Физика");
        getAverageMark(students, "Группа-3", "Информатика");
        getAverageMark(students, "Группа-1", "Русский язык");
        getAverageMark(students, "Группа-2", "Английский язык");

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

        class GradeComparator implements Comparator<Student> {
            @Override
            public int compare(Student a, Student b) {
                return Integer.compare(a.getGrade(), b.getGrade());
            }
        }

        class SurnameComparator implements Comparator<Student> {
            @Override
            public int compare(Student a, Student b) {
                return a.getName().compareTo(b.getName());
            }
        }

        class YearOfBirthComparator implements Comparator<Student> {
            @Override
            public int compare(Student a, Student b) {
                return Integer.compare(a.getYearOfBirth(), b.getYearOfBirth());
            }
        }

        public ArrayList<Student> sortByGradeAndSurname(ArrayList<Student> students) {
            students.sort(new GradeComparator().thenComparing(new SurnameComparator()));
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
            return Collections.max(students, new YearOfBirthComparator());
        }

        public Student getTheYongestStudent(ArrayList<Student> students) {
            return Collections.min(students, new YearOfBirthComparator());
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
}