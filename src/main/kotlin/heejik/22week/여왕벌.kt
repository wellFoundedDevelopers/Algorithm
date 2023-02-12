package heejik.`22week`

import kotlin.properties.Delegates

class 여왕벌 {
    val br = System.`in`.bufferedReader()
    var m by Delegates.notNull<Int>()
    var n by Delegates.notNull<Int>()
    private lateinit var beeHome: MutableList<MutableList<Int>>
    private lateinit var growths: IntArray

    fun solve() {
        setting()
        start()
    }

    fun setting() {
        br.readLine().split(' ').map { it.toInt() }.run {
            m = first()
            n = last()
            beeHome = MutableList(m) { MutableList(m) { 1 } }
            growths = IntArray(2 * m - 1)
        }
    }

    fun start() {
        repeat(n) {
            val (zeroCnt, oneCnt, _) = br.readLine().split(' ').map { it.toInt() }
            for (i in zeroCnt until zeroCnt + oneCnt) {
                growths[i] += 1
            }
            for (i in zeroCnt + oneCnt until 2 * m - 1) {
                growths[i] += 2
            }
        }

        beeHome.apply {
            growSelf()
            growRest()
        }.forEach {
            println(it.joinToString(" "))
        }
    }

    private fun MutableList<MutableList<Int>>.growSelf() {
        var i = 0
        for (x in m - 1 downTo 1) {
            this[x][0] += growths[i++]
        }
        this[0][0] += growths[i++]

        for (y in 1 until m) {
            this[0][y] += growths[i++]
        }
    }

    private fun MutableList<MutableList<Int>>.growRest() {
        for (x in 1 until m) {
            for (y in 1 until m) {
                this[x][y] = this[0][y]
            }
        }
    }
}


fun main() {
    `여왕벌`().solve()
}