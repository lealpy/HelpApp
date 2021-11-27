package com.lealpy.simbirsoft_training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.lealpy.simbirsoft_training.training.publication.Book
import com.lealpy.simbirsoft_training.training.publication.Magazine
import com.lealpy.simbirsoft_training.training.publication.Publication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        publicationProgram()
    }

    private fun publicationProgram() {

        // Создать два объекта класса Book и один объект Magazine. Вывести в лог для каждого объекта
        // тип, количество строк и цену в евро в отформатированном виде.

        val onegin1 = Book(6.75, 24428)
        val onegin2 = Book( 6.75, 24428)
        val gamlet = Book(3.99, 29551)
        val forbes = Magazine( 4.25, 12230)

        Log.i("Publication", " \nТип: ${onegin1.getType()};\nЦена: ${onegin1.price}€\nКоличество строк: ${onegin1.wordCount}")
        Log.i("Publication", " \nТип: ${gamlet.getType()};\nЦена: ${gamlet.price}€\nКоличество строк: ${gamlet.wordCount}")
        Log.i("Publication", " \nТип: ${forbes.getType()};\nЦена: ${forbes.price}€\nКоличество строк: ${forbes.wordCount}")

        // У класса Book переопределить метод equals и произвести сравнение сначала по ссылке,
        // затем используя метод equals. Результаты сравнений вывести в лог.

        if(onegin1 == onegin2) {
            Log.i("Publication", "Объекты onegin1 и onegin2 эквивалентны")
        }
        else {
            Log.i("Publication", "Объекты onegin1 и onegin2 не эквивалентны")
        }

        if(onegin1 === onegin2) {
            Log.i("Publication", "Объекты onegin1 и onegin2 совпадают по ссылке")
        }
        else {
            Log.i("Publication", "Объекты onegin1 и onegin2 не совпадают по ссылке")
        }

        // Создать метод buy, который в качестве параметра принимает Publication (notnull - значения)
        // и выводит в лог “The purchase is complete. The purchase amount was [цена издания]”.

        fun buy (publication : Publication?) {
            Log.i("Publication", "The purchase is complete. The purchase amount was ${publication?.price}€")
        }

        // Создать две переменных класса Book, в которых могут находиться null значения. Присвоить
        // одной null, а второй любое notnull значение и вызвать метод buy с каждой из переменных.

        val book1 : Book? = null
        val book2 : Book? = Book(7.15, 253311)

        buy(book1)
        buy(book2)

        // Создать переменную sum и присвоить ей лямбда-выражение, которое будет складывать два
        // переданных ей числа и выводить результат в лог. Вызвать данное лямбда-выражение с
        // произвольными параметрами.

        val sum : (Int, Int) -> Unit = {x, y ->
            Log.i("Publication", "sum($x, $y) = ${x + y}")
        }

        sum(12, 7)

    }

}