package com.lealpy.simbirsoft_training.training.user

/**
 * Создать интерфейс AuthCallback с методами authSuccess, authFailed
 */

interface AuthCallback {
    fun authSuccess()
    fun authFailed()
}