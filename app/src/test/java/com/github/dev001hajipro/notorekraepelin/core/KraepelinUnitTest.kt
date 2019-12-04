package com.github.dev001hajipro.notorekraepelin.core

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class KraepelinUnitTest {
    @Test
    fun test_geneList115() {
        val ls = Kraepelin.geneList115()
        assertEquals(115, ls.size)
    }

    @Test
    fun test_getRandomValue() {
        val ls3 = Kraepelin.newListWith2Elements()
        val e1 = ls3.takeLast(2).first()
        val e2 = ls3.takeLast(2).last()
        assertNotEquals(e1, e2)
    }

    @Test
    fun test_addNextElem() {
        val ls = Kraepelin.newListWith2Elements()
        val ls2 = Kraepelin.add(ls)
        assertNotEquals(ls2.first(), ls2.last())
        assertNotEquals(ls2.takeLast(2).first(), ls2.last())

        val ls5 = Kraepelin.add(Kraepelin.add(ls2))
        Kraepelin.lessThan10(ls5) && Kraepelin.lessThan15(ls5)
    }

    @Test
    fun test_add3() {
        val ls = Kraepelin.newListWith2Elements()
        assertEquals(2, ls.size)
        val ls5 = Kraepelin.add3(ls)
        assertEquals(5, ls5.size)
    }
}
