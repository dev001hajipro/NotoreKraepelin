package com.github.dev001hajipro.notorekraepelin

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test

class KraepelinTest {

    @Test
    fun lessThan10() {

    }

    @Test
    fun lessThan15() {
    }

    @Test
    fun geneList115() {
    }

    @Test
    fun newListWith2Elements_createList_returnListWithTwoElements() {
        val ls = Kraepelin.newListWith2Elements()
        assertThat(ls.size, `is`(2))
    }

    @Test
    fun add() {
    }

    @Test
    fun add3_inputList_returnListAddedThreeElements() {
        // Given
        val ls = Kraepelin.newListWith2Elements()
        assertThat(ls.size, `is`(2))

        // When
        val ls5 = Kraepelin.add3(ls)

        // Then
        assertThat(ls5.size, `is`(5))
    }
}
