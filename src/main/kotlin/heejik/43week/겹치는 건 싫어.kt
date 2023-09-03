package heejik.`43week`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StreamTokenizer
import kotlin.math.max
import kotlin.properties.Delegates

class `겹치는 건 싫어` {
    var n by Delegates.notNull<Int>()
    var k by Delegates.notNull<Int>()
    lateinit var numbers: List<Int>
    fun setting() = with(BufferedReader(InputStreamReader(System.`in`))) {
        readLine().split(' ').map { it.toInt() }.run {
            n = this[0]
            k = this[1]
        }
        numbers = readLine().split(' ').map { it.toInt() }
    }


    fun solve() {
        setting()

        var answer = 0
        val stored = ArrayDeque<Int>()
        val counts = MutableList(100001) { 0 }

        for (number in numbers) {
            val numberCount = counts[number]
            if (numberCount == k) {
                answer = max(answer, stored.size)
                while (true) {
                    val removeNumber = stored.removeFirst()
                    counts[removeNumber]--
                    if (removeNumber == number) break
                }
            }
            stored.add(number)
            counts[number]++
        }
        answer = max(answer, stored.size)

        println(answer)
    }
}

fun main() {
    `겹치는 건 싫어`().solve()
}