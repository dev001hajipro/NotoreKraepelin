package com.github.dev001hajipro.notorekraepelin

import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class KraepelinTest {

    @Test
    fun generateTest_withArguments_shouldSameElementSize() {
        // Given list size
        val s = 115

        // When generate test
        val ls = Kraepelin.generateTest(s)

        // Then
        assertThat(ls.size, `is`(115))
    }

    @Test
    fun generateTest_withZeroValue_shouldReturnEmptyList() {
        // Given list size
        val s = 0

        // When generate test
        val ls = Kraepelin.generateTest(s)

        // Then
        assertThat(ls, `is`(notNullValue()))
        assertThat(ls.size, `is`(0))
    }
}
