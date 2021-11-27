package com.lealpy.simbirsoft_training.training.publication

import java.lang.Exception

// Создать класс Book, реализующий интерфейс Publication. В методе getType для класса Book
// возвращаем строку с зависимости от количества слов. Если количество слов не превышает 1000,
// то вывести “Flash Fiction”, 7,500 –“Short Story”, всё, что выше – “Novel”.
// У класса Book переопределить метод equals

class Book (price : Double, override val wordCount: Int) : Publication {

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
        return when(wordCount) {
            in 1..1000 -> "Flash Fiction"
            in 1001..7500 -> "Short Story"
            in 7501..Int.MAX_VALUE -> "Novel"
            else -> throw Exception("Word count must be positive")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (price != other.price) return false
        if (wordCount != other.wordCount) return false

        return true
    }
}