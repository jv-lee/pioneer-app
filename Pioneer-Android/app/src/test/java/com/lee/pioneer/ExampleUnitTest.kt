package com.lee.pioneer

import com.lee.pioneer.library.common.model.entity.Data
import org.junit.Test

import org.junit.Assert.*
import kotlin.math.sqrt

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

        var data1 = Data(1, 2)
        var data2 = Data(1, 2)
        println(data1 == data2)

        var count = 106
        var pageSize = 20
        var pageCount = 0
        pageCount = if (count % pageSize != 0) {
            count / pageSize + 1
        } else {
            count / pageSize
        }
        println(pageCount)

    }

    private fun codePtNumber(width: Int, height: Int): Double {
        return (sqrt((width * width + height * height).toDouble()) / 72)
    }
}
