package com.lealpy.simbirsoft_training.training.publication

//Необходимо создать интерфейс Publication, у которого должно быть свойства – price и wordCount,
// а также метод getType, возвращающий строку.

interface Publication {
    var price : Double
    val wordCount : Int

    fun getType () : String
}