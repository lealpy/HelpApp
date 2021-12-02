package com.lealpy.simbirsoft_training.training.user

import android.util.Log

object UserProgram {

    private const val logTag = "UserProgram"
    private const val majorityAge = 18

    fun execute() {

        val egor = User( "Егор", 21, Type.DEMO)
        val olga = User("Ольга", 25, Type.FULL)
        val elena = User("Елена", 15, Type.FULL)
        val alex = User("Александр", 32, Type.FULL)
        val regina = User("Регина", 22, Type.DEMO)

        Log.i(logTag, egor.startTime)
        Thread.sleep(1000)
        Log.i(logTag, egor.startTime)

        /**
         * Создать список пользователей, содержащий в себе один объект класса User.
         * Используя функцию apply, добавить ещё несколько объектов класса User
         * в список пользователей.
         */

        val users = mutableListOf(egor)

        users.apply {
            add(olga)
            add(elena)
            add(alex)
            add(regina)
        }

        /**
         * Получить список пользователей, у которых имеется полный доступ
         * (поле type имеет значение FULL).
         */

        val fullAccessUsers = users.filter { it.type == Type.FULL }

        fullAccessUsers.forEach {
            Log.i(logTag, it.name)
        }

        /**
         * Преобразовать список User в список имен пользователей. Получить первый и последний
         * элементы списка и вывести их в лог.
         */

        val usersNames = users.map { it.name }
        Log.i(logTag, usersNames.first())
        Log.i(logTag, usersNames.last())

        /**
         * Тестируем функцию doAction
         */
        doAction(Registration)
        doAction(Login(olga))
        doAction(Login(elena))
        doAction(Logout)

    }

    /**
     * Создать функцию-расширение класса User, которая проверяет, что юзер старше 18 лет,
     * и в случае успеха выводит в лог, а в случае неуспеха возвращает ошибку.
     */

    private fun User.majorityCheck() : Boolean {
        return if(this.age >= majorityAge) {
            Log.i(logTag, "Пользователь ${this.name} совершеннолетний")
            true
        } else {
            Log.i(logTag, "Пользователь ${this.name} несовершеннолетний")
            false
        }
    }

    /**
     * Реализовать анонимный объект интерфейса AuthCallback. В методах необходимо вывести
     * в лог информацию о статусе авторизации.
     */

    private val authCallBackObject = object : AuthCallback {
        override fun authSuccess() {
            Log.i(logTag, "Авторизация прошла успешна")
        }

        override fun authFailed() {
            Log.i(logTag, "Авторизация не удалась")
        }
    }

    /**
     * Реализовать inline функцию auth, принимающую в качестве параметра функцию
     * updateCache. Функция updateCache должна выводить в лог информацию об обновлении кэша.
     *
     * Внутри функции auth вызвать метод коллбека authSuccess и переданный updateCache,
     * если проверка возраста пользователя произошла без ошибки. В случае получения
     * ошибки вызвать authFailed.
     */

    private fun updateCache() {
        Log.i(logTag, "Кэш обновлен")
    }

    private inline fun auth(user: User, updateCache: () -> Unit) {
        if(user.majorityCheck()) {
            authCallBackObject.authSuccess()
            updateCache()
        }
        else {
            authCallBackObject.authFailed()
        }
    }

    /**
     * Реализовать метод doAction, принимающий экземпляр класса Action. В зависимости от
     * переданного действия выводить в лог текст, к примеру “Auth started”. Для действия
     * Login вызывать метод auth.
     */

    private fun doAction(action: Action) {
        when (action) {
            is Registration -> Log.i(logTag, "Началась регистрация")
            is Login -> {
                Log.i(logTag, "Началась авторизация")
                auth (action.user) { updateCache() }
            }
            is Logout -> Log.i(logTag, "Началось завершение сеанса")
        }
    }

}