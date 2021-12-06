package com.lealpy.simbirsoft_training.training.user

import java.text.SimpleDateFormat
import java.util.*

/**
 * Реализовать класс данных User с полями id, name, age и type. У класса User создать ленивое
 * свойство startTime, в котором получаем текущее время.
 */

data class User(val name : String, var age : Int, var type: Type) {

    val id = idList[idList.lastIndex] + 1

    val startTime: String by lazy {
        dataPattern.format(Date())
    }

    init{
        idList.add(id)
    }

    companion object {
        private val idList = mutableListOf<Long>(0)
        private val dataPattern = SimpleDateFormat("hh:mm:ss")
    }

}
