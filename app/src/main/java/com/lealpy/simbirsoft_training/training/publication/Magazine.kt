package com.lealpy.simbirsoft_training.training.publication

//Создать класс Magazine, реализующий интерфейс Publication. В методе getType для класса Magazine
// возвращаем строку “Magazine”.

class Magazine(price : Double, override val wordCount: Int) : Publication {

    init {
        require(price >= 0) { "Цена не должна быть отрицательной" }
        require(wordCount > 0) { "Количество слов должно быть положительным" }
    }

    override var price: Double = price
        set(value) {
            if(value < 0) field = 0.0
            else field = value
        }

    override fun getType(): String {
        return "Magazine"
    }
}