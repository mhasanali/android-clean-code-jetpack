package com.example.doctorconsultationapp

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun email_isValid(){
        val email = "test@test.com"
        assertEquals(true,email.length > 6)
    }

    @Test
    fun password_isAtleastEightCharacters(){
        val password = "12345678"
        assertEquals(true,password.length >= 8)
    }
}