package com.lealpy.simbirsoft_training.training.classes_block;

    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */

public class Time {

    private int hour, minute, second;
    final int HOURS_AT_DAY_START = 0;
    final int HOURS_AT_DAY_FINISH = 24;

    Time(int hour, int minute, int second) throws Exception {
        setTime(hour, minute, second);
    }

    public void setTime(int hour, int minute, int second) throws Exception {
        setHour(hour);
        setMinute(minute);
        setSecond(second);
    }

    public void setHour(int hour) throws Exception {
        if (hour < HOURS_AT_DAY_START || hour >= HOURS_AT_DAY_FINISH) {
            throw new Exception("Неверный формат часов");
        }
        this.hour = hour;
    }

    public void setMinute(int minute) throws Exception {
        if (minute < 0 || minute > 59) {
            throw new Exception("Неверный формат минут");
        }
        this.minute = minute;
    }

    public void setSecond(int second) throws Exception {
        if (second < 0 || second > 59) {
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
