package heejik.`22week`

import kotlin.system.exitProcess

class `죽음의 게임` {

    fun solve() {
        val (n, k) = readln().split(' ').map { it.toInt() }
        val targets = MutableList(n) { -1 }

        repeat(n) {
            targets[it] = readln().toInt()
        }

        var cnt = 1
        var target = targets[0]


        while (cnt <= n * 2) {
            if (target == k) {
                println(cnt)
                exitProcess(0)
            }
            target = targets[target]
            cnt++
        }
        println(-1)
    }
}

fun main() {
    `죽음의 게임`().solve()
}