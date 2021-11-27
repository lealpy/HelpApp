package com.lealpy.simbirsoft_training.training.classes_block.customer;

import java.util.Random;

    /*
      V

      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации.

      (Продолжение в файле CustomerProgram.java)
     */

public class Customer {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private String address;
    private String cardNumber;
    private double debit;
    private double credit;
    private int currentMinutesIntercity;
    private int currentMinutesCity;
    private int defaultMinutesIntercity;
    private int defaultMinutesCity;

    public Customer(
            String surname,
            String name,
            String patronymic,
            String address,
            String cardNumber,
            double debit,
            double credit,
            int defaultMinutesIntercity,
            int defaultMinutesCity
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
        setDefaultMinutesCity(defaultMinutesCity);
        this.currentMinutesCity = defaultMinutesCity;
    }

    private void setId() {
        // Получаем последний id. Например, из БД.
        Random random = new Random();
        long lastId = random.nextLong();

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

    public int getCurrentMinutesIntercity() {
        return currentMinutesIntercity;
    }

    public void makeIntercityCall(int callDuration) {
        currentMinutesIntercity -= callDuration;
    }

    public void setDefaultMinutesCity(int timeCityCalls) {
        this.defaultMinutesCity = timeCityCalls;
    }

    public int getDefaultMinutesCity() {
        return defaultMinutesCity;
    }

    public int getCurrentMinutesCity() {
        return currentMinutesCity;
    }

    public void makeCityCall(int callDuration) {
        currentMinutesCity -= callDuration;
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
                "\n timeCityCalls: " + defaultMinutesCity;
    }
}
