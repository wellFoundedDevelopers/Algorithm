package heejik.`22week`

import kotlin.math.max


class `평범한 배낭` {

    data class Item(
        val w: Int,
        val v: Int,
    )

    fun solve() {

        val (n, k) = readln().split(' ').map { it.toInt() }
        val dp = MutableList(k + 1) { 0 }
        val items = mutableListOf<Item>()

        repeat(n) {
            readln().split(' ').map { it.toInt() }.run {
                items.add(Item(w = this.first(), v = this.last()))
            }
        }

        items.forEach { item ->
            val tmp = mutableListOf<Int>()
            dp.forEach { tmp.add(it) }
            for (i in 1..k) {
                if (i < item.w) continue
                dp[i] = max(tmp[i - item.w] + item.v, tmp[i])
            }
        }

        println(dp[k])
    }
}

fun main() {
    `평범한 배낭`().solve()
}