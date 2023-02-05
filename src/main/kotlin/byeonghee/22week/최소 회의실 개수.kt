package byeonghee.`22week`

import java.io.*
import java.util.PriorityQueue

class 소병희_최소회의실개수 {
    // 지민님이 저번주에 물어보셨는데 말하는거 까먹을까봐 주석 남깁니다 ㅋㅋㅋ
    // 클래스 내부에서 정의할거면 const val은 컴패니언 안에서 정의해야 한다고 해서
    // 겸사겸사 복붙 편의상 코드를 싹 다 컴패니언 안에 넣고 있습니다!
    companion object {
        const val START = 0
        const val END = 1

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val pq = PriorityQueue(Comparator<Pair<Int, Int>> { a, b -> if (a.first == b.first) b.second - a.second else a.first - b.first})

            repeat(n) {
                readLine().split(" ").let {
                    pq.add(Pair(it[0].toInt(), START))
                    pq.add(Pair(it[1].toInt(), END))
                }
            }

            var answer = 0
            var duplicated = 0
            var lastTime : Pair<Int, Int>
            while(pq.isNotEmpty()) {
                lastTime = pq.poll()
                if (lastTime.second == START) {
                    answer = maxOf(answer, ++duplicated)
                }
                else {
                    duplicated--
                }
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_최소회의실개수.solve()
}