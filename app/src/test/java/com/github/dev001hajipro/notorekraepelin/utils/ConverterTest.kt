package com.github.dev001hajipro.notorekraepelin.utils

import org.hamcrest.Matchers.`is`
import org.junit.Test

import org.junit.Assert.*

class ConverterTest {

    @Test
    fun millisToString() {
        val milliSeconds = 60000L
        val actual = Converter.millisToString(milliSeconds)
        assertThat(actual, `is`("01:00.0"))
    }
}
