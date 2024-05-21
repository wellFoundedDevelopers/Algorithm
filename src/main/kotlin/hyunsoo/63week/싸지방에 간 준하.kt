package hyunsoo.`63week`

import java.util.PriorityQueue

/**
 *
 * <문제>
 * [싸지방에 간 준하](https://www.acmicpc.net/problem/12764)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_싸지방에_간_준하` {

    private data class UseInfo(val start: Int, val end: Int)
    private data class ComInfo(val num: Int, val cnt: Int)
    private data class UsingInfo(val num: Int, val end: Int, val cnt: Int)

    fun solution() {

        val peopleCnt = readln().toInt()

        val useInfoPq = PriorityQueue<UseInfo>(
            Comparator { a, b ->
                a.start - b.start
            }
        )

        val computerPq = PriorityQueue<ComInfo> { a, b ->
            a.num - b.num
        }

        val usingInfoPq = PriorityQueue<UsingInfo> { a, b ->
            a.end - b.end
        }

        repeat(peopleCnt) {
            computerPq.add(ComInfo(it + 1, 0))

            val (start, end) = readln().split(" ").map { it.toInt() }
            useInfoPq.add(UseInfo(start, end))
        }


        while (useInfoPq.isNotEmpty()) {

            val (curStart, curEnd) = useInfoPq.poll()

            while (usingInfoPq.isNotEmpty() && usingInfoPq.peek().end < curStart) {
                val done = usingInfoPq.poll()
                computerPq.add(ComInfo(done.num, done.cnt))
            }

            val target = computerPq.poll()

            usingInfoPq.add(
                UsingInfo(target.num, curEnd, target.cnt + 1)
            )

        }

        val ansPq = PriorityQueue<ComInfo> { a, b ->
            a.num - b.num
        }
        while (usingInfoPq.isNotEmpty()) {
            val target = usingInfoPq.poll()
            ansPq.add(ComInfo(target.num, target.cnt))
        }

        while (computerPq.isNotEmpty()) {
            val target = computerPq.poll()
            if (target.cnt == 0) break
            ansPq.add(target)
        }

        println(ansPq.size)

        while (ansPq.isNotEmpty()) {

            ansPq.poll().apply {
                print("${this.cnt} ")
            }
        }

    }

}

fun main() {
    전현수_싸지방에_간_준하().solution()
}