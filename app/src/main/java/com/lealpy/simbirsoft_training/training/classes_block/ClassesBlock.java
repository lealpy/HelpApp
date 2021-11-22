package com.lealpy.simbirsoft_training.training;

import java.lang.reflect.Array;
import java.util.ArrayList;

import kotlin.random.Random;

/**
 * Набор заданий по работе с классами в java.
 * <p>
 * Задания подразумевают создание класса(ов), выполняющих задачу.
 * <p>
 * Проверка осуществляется ментором.
 */


public interface ClassesBlock {

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */

    class Numbers {

        private int num1, num2;

        public int getNum1() {return num1;}
        public int getNum2() {return num2;}

        public void setNum1(int num) {this.num1 = num;}
        public void setNum2(int num) {this.num2 = num;}

        public int getSum() {return num1 + num2;}

        public int getMax() {
            if(num1 > num2) return num1;
            else return num2;
        }
    }

    /*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

    class Triangle {

        private double xA, xB, xC, yA, yB, yC, sideAB, sideBC, sideCA, angleA, angleB, angleC;

        Triangle (double xA, double yA, double xB, double yB, double xC, double yC) {
            this.xA = xA;
            this.xB = xB;
            this.xC = xC;
            this.yA = yA;
            this.yB = yB;
            this.yC = yC;

            this.sideAB = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));
            this.sideBC = Math.sqrt(Math.pow((xB - xC), 2) + Math.pow((yB - yC), 2));
            this.sideCA = Math.sqrt(Math.pow((xB - xA), 2) + Math.pow((yB - yA), 2));

            this.angleA = Math.acos((sideAB * sideAB + sideCA * sideCA - sideBC * sideBC) / (2 * sideAB * sideCA));
            this.angleB = Math.acos((sideAB * sideAB + sideBC * sideBC - sideCA * sideCA) / (2 * sideAB * sideBC));
            this.angleC = Math.acos((sideBC * sideBC + sideCA * sideCA - sideAB * sideAB) / (2 * sideBC * sideCA));
        }

        double getArea() {return 0.5 * sideAB * sideBC * Math.sin(angleB);}

        double getPerimeter() {return sideAB + sideBC + sideCA;}

        String getCentroidCoordinates() {
            double x = (xA + xB + xC) / 3;
            double y = (yA + yB + yC) / 3;
            return "Точка пересечения медиан треугольника (" + x + "; " + y + ")";
        }
    }

    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */

    class Time {

        private int hour, minute, second;

        Time (int hour, int minute, int second) throws Exception {
            setTime(hour, minute, second);
        }

        public void setTime(int hour, int minute, int second) throws Exception {
            setHour(hour);
            setMinute(minute);
            setSecond(second);
        }

        public void setHour(int hour) throws Exception {
            if(hour < 0 || hour > 23) {
                throw new Exception("Неверный формат часов");
            }
            this.hour = hour;
        }

        public void setMinute(int minute) throws Exception {
            if(minute < 0 || minute > 59) {
                throw new Exception("Неверный формат минут");
            }
            this.minute = minute;
        }

        public void setSecond(int second) throws Exception {
            if(second < 0 || second > 59) {
                throw new Exception("Неверный формат секунд");
            }
            this.second = second;
        }

        public void increaseHour(int hour) throws Exception {
            setHour(this.hour + hour);
        }

        public void increaseMinute(int minute) throws Exception {
            setMinute(this.minute + minute);
        }

        public void increaseSecond(int second) throws Exception {
            setSecond(this.second + second);
        }
    }

    /*
      V

      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */

    class Customer {
        private long id;
        String
                surname,
                name,
                patronymic,
                address,
                cardNumber;
        private double
                debit,
                credit;
        private int
                currentMinutesIntercity,
                currentMinutesIntracity,
                defaultMinutesIntercity,
                defaultMinutesIntracity;

        Customer(String surname,
                 String name,
                 String patronymic,
                 String address,
                 String cardNumber,
                 double debit,
                 double credit,
                 int defaultMinutesIntercity,
                 int defaultMinutesIntracity
                    ) {
            setId();
            setSurname(surname);
            setName(name);
            setPatronymic(patronymic);
            setAddress(address);
            setCardNumber(cardNumber);
            setDebit(debit);
            setCredit(credit);
            setDefaultMinutesIntercity(defaultMinutesIntercity);
            this.currentMinutesIntercity = defaultMinutesIntercity;
            setDefaultMinutesIntracity(defaultMinutesIntracity);
            this.currentMinutesIntracity = defaultMinutesIntracity;
        }

        private void setId () {
            // Получаем последний id. Например, из БД.
            Random random = new Random() {
                @Override
                public int nextBits(int i) {
                    return 0;
                }
            };
            long lastId = random.nextLong(2147483647);

            this.id = lastId + 1;
        }

        public long getId() {
            return id;
        }


        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getSurname() {
            return surname;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAddress() {
            return address;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setDebit(double debit) {
            this.debit = debit;
        }

        public double getDebit() {
            return debit;
        }

        public void setCredit(double credit) {
            this.credit = credit;
        }

        public double getCredit() {
            return credit;
        }

        public void setDefaultMinutesIntercity(int defaultMinutesIntercity) {
            this.defaultMinutesIntercity = defaultMinutesIntercity;
        }

        public int getDefaultMinutesIntercity() {
            return defaultMinutesIntercity;
        }

        public void makeIntercityCall(int callDuration) {
            currentMinutesIntercity -= callDuration;
        }

        public void setDefaultMinutesIntracity(int timeIntracityCalls) {
            this.defaultMinutesIntracity = timeIntracityCalls;
        }

        public int getDefaultMinutesIntracity() {
            return defaultMinutesIntracity;
        }

        public void makeIntracityCall(int callDuration) {
            currentMinutesIntracity -= callDuration;
        }

        public String getInfo() {
            return "id: " + id +
                    "\n surname: " + surname +
                    "\n name: " + name +
                    "\n patronymic: " + patronymic +
                    "\n address: " + address +
                    "\n cardNumber: " + cardNumber +
                    "\n debit: " + debit +
                    "\n credit: " + credit +
                    "\n timeIntercityCalls: " + defaultMinutesIntercity +
                    "\n timeIntracityCalls: " + defaultMinutesIntracity;
        }
    }

    class Program {
        public void function() {
            Customer ivanovSV = new Customer(
                    "Иванов",
                    "Сергей",
                    "Витальевич",
                    "г. Москва, ул. Тверская, д. 13, кв. 2",
                    "2434 4353 6546 3245",
                    1000.00,
                    0,
                    250,
                    1000
            );
            ivanovSV.makeIntercityCall(23);
            ivanovSV.makeIntracityCall(1245);

            Customer petrovVA = new Customer(
                    "Петров",
                    "Василий",
                    "Алексеевич",
                    "г. Владимир, ул. Ленина, д. 4, кв. 12",
                    "4263 2383 6236 9845",
                    1290.12,
                    0,
                    300,
                    1200
            );
            petrovVA.makeIntercityCall(202);
            petrovVA.makeIntracityCall(130);

            Customer sergeevEI = new Customer(
                    "Сергеев",
                    "Егор",
                    "Иванович",
                    "г. Новосибирск, ул. Фрунзе, д. 19, кв. 8",
                    "5432 4124 1465 2956",
                    16.76,
                    0,
                    0,
                    300
            );
            sergeevEI.makeIntercityCall(0);
            sergeevEI.makeIntracityCall(500);

            Customer[] customers = new Customer[]{ivanovSV, petrovVA, sergeevEI};
            Customer[] customersIntracityOvertime = getCustomersIntracityOvertime(customers);
            Customer[] customersUsedIntercity = getCustomersUsedIntercity(customers);

        }

        public Customer[] getCustomersIntracityOvertime(Customer[] customers) {
            ArrayList<Customer> customersIntracityOvertime = new ArrayList<> ();

            for(Customer customer : customers)
            {
                if(customer.currentMinutesIntracity > customer.defaultMinutesIntracity) {
                    customersIntracityOvertime.add(customer);
                }
            }
            return (Customer[]) customersIntracityOvertime.toArray();
        }

        public Customer[] getCustomersUsedIntercity(Customer[] customers) {
            ArrayList<Customer> customersUsedIntercity = new ArrayList<> ();

            for(Customer customer : customers)
            {
                if(customer.currentMinutesIntercity < customer.defaultMinutesIntercity) {
                    customersUsedIntercity.add(customer);
                }
            }
            return (Customer[]) customersUsedIntercity.toArray();
        }

        /*
        public String getSortedCustomers(Customer[] customers) {
            ArrayList<String> customersNames = new ArrayList<>();

            for(Customer customer : customers)
            {
                String element = customer.surname + customer.name + customer.patronymic;
                customersNames.add(element);
            }
            customersNames.sort();
            return
        }

         */
    }

    /*
      V


      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */

    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */

    /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */
}

