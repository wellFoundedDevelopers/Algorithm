package heejik.`22week`

import java.util.*
import kotlin.text.StringBuilder

class 탑 {

    data class Top(
        val height: Int,
        val order: Int
    )

    private lateinit var tops: MutableList<Top>
    private val launchedTops = Stack<Top>()

    fun solve() {
        setting()
        start()
    }

    fun setting() {
        val n = readln().toInt()
        tops = readln()
            .split(' ')
            .map { it.toInt() }
            .mapIndexed { index, i -> Top(i, index + 1) }
            .toMutableList()
    }

    fun start() {
        val answer = mutableListOf<Int>()
        for (i in 0 until tops.size){
            answer.add(launch(tops[i]))
        }
        answer.forEach {
            print("$it ")
        }
    }

    private fun launch(top: Top): Int {
        var receivedTop = 0
        while (launchedTops.isNotEmpty()) {
            if (top.height <= launchedTops.peek().height) {
                receivedTop = launchedTops.peek().order
                break
            } else {
                launchedTops.pop()
            }
        }
        launchedTops.add(top)
        return receivedTop
    }
}


fun main() {
    탑().solve()
}