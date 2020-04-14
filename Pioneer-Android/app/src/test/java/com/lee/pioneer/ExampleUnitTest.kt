package com.lee.pioneer

import org.junit.Test

import org.junit.Assert.*
import kotlin.math.sqrt
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
        println("1080 1920number -> ${codePtNumber(1080, 1920)}")
        println("320 760number -> ${codePtNumber(360, 720)}")

        println(Random.nextInt() % 2)
        println(Random.nextInt() % 2)
        println(Random.nextInt() % 2)
        println(Random.nextInt() % 2)
        println(Random.nextInt() % 2)
        println(Random.nextInt() % 2)
        println(Random.nextInt() % 2)

    }

    private fun codePtNumber(width: Int, height: Int): Double {
        return (sqrt((width * width + height * height).toDouble()) / 72)
    }
}
