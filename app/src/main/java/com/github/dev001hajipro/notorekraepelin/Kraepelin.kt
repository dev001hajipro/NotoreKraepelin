package com.github.dev001hajipro.notorekraepelin

// クレペリンは１行に１１５の数字があり、それを前半１５行、後半１５行足して作業量と曲線を見るもの
/*
1と2は足し算で出現しない
「３　４　４」というように同じ数字は連続しない
「４　３　４」というように同じ数字が1つおきに出現しない
5つの数字ごとに答えの1つが10以下になること
5つの数字ごとに答えの1つが15以下になること
*/
object Kraepelin {
    private fun lessThan10(ls: List<Int>): Boolean {
        for (i in 0 until ls.size - 1) {
            if (ls[i] + ls[i + 1] <= 10) {
                return true
            }
        }
        return false
    }

    private fun lessThan15(ls: List<Int>): Boolean {
        for (i in 0 until ls.size - 1) {
            if (ls[i] + ls[i + 1] <= 15) {
                return true
            }
        }
        return false
    }

    fun geneList115(): List<Int> {
        var ls = newListWith2Elements()
        while (true) {
            ls = add3(ls)
            if (ls.size >= 115)
                return ls.take(115)
        }
    }

    private fun newListWith2Elements(): List<Int> {
        val e1 = (3..9).random() // generate element1.
        val e2 = (3..9).filterNot { it == e1 }.random() // generate element2 ignore e1.
        return listOf(e1, e2)
    }

    private fun add(ls: List<Int>): List<Int> {
        val e1 = ls.takeLast(2).first()
        val e2 = ls.takeLast(2).last()

        val e3 = (3..9).filter { it != e1 && it != e2 }.random()
        return ls + e3
    }

    private fun add3(ls: List<Int>): List<Int> {
        while (true) {
            val ls5 = add(add(add(ls)))
            val lsHas5elem = ls5.takeLast(5)
            if (lessThan10(lsHas5elem) && lessThan15(lsHas5elem))
                return ls5
        }
    }

    private fun f5(): List<Int> {
        val ls = newListWith2Elements()
        return add3(ls)
    }

    private fun add5(ls: List<Int>): List<Int> {
        while (true) {
            val ls5 = add(add(add(add(add(ls)))))
            val lsHas5elem = ls5.takeLast(5)
            if (lessThan10(lsHas5elem) && lessThan15(lsHas5elem))
                return ls5
        }
    }

    fun generateTest(limit: Int = 115): List<Int> {
        var ls = f5()
        while (true) {
            ls = add5(ls)
            if (ls.size >= limit)
                return ls.take(limit)
        }
    }
}
