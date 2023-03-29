package heejik.`29week`

import kotlin.math.max
import kotlin.properties.Delegates

class 안녕 {
    var answer = 0
    var n by Delegates.notNull<Int>()
    lateinit var hp: MutableList<Int>
    lateinit var pleasure: MutableList<Int>

    fun solve() {
        setting()
        getMans(listOf())
        println(answer)
    }

    fun setting() {
        n = readln().toInt()
        hp = readln().split(' ').map { it.toInt() }.toMutableList()
        pleasure = readln().split(' ').map { it.toInt() }.toMutableList()
    }

    private fun getMans(mans: List<Int>, start: Int = 0) {
        if (mans.sumOf { hp[it] } >= 100) return
        getPleasure(mans)

        for (i in start until n) {
            getMans(mans.plus(i), start = i + 1)
        }
    }

    private fun getPleasure(mans: List<Int>) {
        answer = max(answer, mans.sumOf { pleasure[it] })
    }
}

fun main() {
    안녕().solve()
}