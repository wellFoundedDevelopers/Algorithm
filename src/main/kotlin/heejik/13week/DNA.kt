package heejik.`13week`

import java.lang.Integer.max


class DNA {

    fun solve() {

        var answerDna = ""
        var answerCnt = 0

        val (n, m) = readln().split(' ').map { it.toInt() }

        val dnaList = mutableListOf<String>()

        repeat(n) { dnaList.add(readln()) }

        repeat(m) {
            var dnaByIdx = ""
            dnaList.forEach { dna -> dnaByIdx += dna[it] }

            var maxCnt = 0
            "ACGT".forEach { c -> maxCnt = max(maxCnt, dnaByIdx.count { c == it }) }

            answerDna += dnaByIdx.filter { c -> maxCnt == dnaByIdx.count { c == it } }.minOf{ it }
            answerCnt += dnaByIdx.count { it != answerDna.last() }
        }

        println(answerDna)
        println(answerCnt)
    }
}

fun main() {

    DNA().solve()
}