package byeonghee.`25week`

import java.io.*
import kotlin.system.measureNanoTime

class 소병희_오큰수 {

    companion object {
        data class NGE(val idx: Int, val v: Int)

        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {

            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val st = ArrayDeque<NGE>(n)
            val answer = IntArray(n)
            var x : Int

            repeat(n) { i ->
                x = input()
                while(st.isNotEmpty() && st.last().v < x) {
                    answer[st.removeLast().idx] = x
                }
                st.addLast(NGE(i, x))
            }

            while(st.isNotEmpty()) {
                answer[st.removeLast().idx] = -1
            }

            val time1 = measureNanoTime {
                val sb = StringBuilder()
                answer.forEach {
                    sb.append(it)
                    sb.append(" ")
                }
                println(sb)
            }
            println("스트링빌더시간: $time1")

            val time2 = measureNanoTime {
                println(answer.joinToString(" "))
            }
            println("조인스트링시간: $time2")

            val time3 = measureNanoTime {
                val sw = BufferedWriter(OutputStreamWriter(System.`out`))
                answer.forEach {
                    sw.write(it.toString())
                    sw.write(" ")
                }
                sw.flush()
            }
            println("버퍼라이터시간: $time3")
        }
    }
}

fun main() {
    소병희_오큰수.solve()
}