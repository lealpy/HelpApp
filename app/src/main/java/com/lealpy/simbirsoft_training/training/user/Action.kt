package com.lealpy.simbirsoft_training.training.user

/**
 * Реализовать изолированный класс Action и его наследников – Registration, Login и
 * Logout. Login должен принимать в качестве параметра экземпляр класса User.
 */

sealed class Action
object Registration : Action()
class Login (val user : User) : Action()
object Logout : Action()
