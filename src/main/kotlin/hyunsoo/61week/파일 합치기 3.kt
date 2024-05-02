package hyunsoo.`61week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [파일 합치기 3](https://www.acmicpc.net/problem/13975)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_파일_합치기_3` {

    fun solution() {
        val testCnt = readln().toInt()

        repeat(testCnt) {

            val fileCnt = readln().toInt()
            val pq = PriorityQueue<Long>()
            var answer = 0L
            readln().split(" ").forEach {
                pq.add(it.toLong())
            }

            while (pq.size != 1) {

                val fir = pq.poll()
                val sec = pq.poll()
                val sum = fir + sec
                answer += sum
                pq.add(sum)

            }

            println(answer)
        }
    }
}

fun main() {
    전현수_파일_합치기_3().solution()
}