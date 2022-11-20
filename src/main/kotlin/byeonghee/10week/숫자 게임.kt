package byeonghee.`10week`

import java.io.*
import java.util.PriorityQueue
import kotlin.system.measureTimeMillis

class `소병희_숫자 게임` {

    companion object {
        const val CARD_NUM = 5

        data class Game(val p: Int, val v: Int)

        val br = BufferedReader(InputStreamReader(System.`in`))
        val pq = PriorityQueue(Comparator<Game> { a, b -> if (a.v == b.v) b.p - a.p else b.v - a.v })

        fun solve() {
            val n = br.readLine().toInt()

            var sum = 0
            var sumMinusFirst = 0
            repeat(n) { p ->
                br.readLine().split(" ").map { it.toInt() }.let { nums ->
                    sum = nums.sumOf { it }
                    for(first in 0 until CARD_NUM - 1) {
                        sumMinusFirst = sum - nums[first]
                        for(second in first + 1 until CARD_NUM) {
                            pq.add(Game(p + 1, (sumMinusFirst - nums[second]) % 10))
                        }
                    }
                }
            }
            println(pq.poll().p)
        }
    }
}

fun main() {
    `소병희_숫자 게임`.solve()
}