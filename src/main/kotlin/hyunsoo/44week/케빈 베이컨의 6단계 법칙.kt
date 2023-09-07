package hyunsoo.`44week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [케빈 베이컨의 6단계 법칙](https://www.acmicpc.net/problem/1389)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_케빈_베이컨의_6단계_법칙` {

    private lateinit var friendshipInfo: Array<BooleanArray>

    fun solution() {

        val (userCnt, relationCnt) = readln().split(" ").map { it.toInt() }

        var minSum = Int.MAX_VALUE
        var answer = 9999

        friendshipInfo = Array(userCnt + 1) {
            BooleanArray(userCnt + 1)
        }

        repeat(relationCnt) {
            val (first, second) = readln().split(" ").map { it.toInt() }
            friendshipInfo[first][second] = true
            friendshipInfo[second][first] = true
        }

        for (i in 1..userCnt) {

            val checkList = IntArray(userCnt + 1).apply {
                this[0] = -1
                this[i] = -1
            }

            val queue: Queue<Pair<Int, Int>> = LinkedList()

            val initFriends = friendshipInfo[i]
                .mapIndexed { index, isFriend ->
                    if (isFriend) index else -1
                }.filter { it != -1 }

            initFriends.forEach { friendIndex ->
                queue.add(friendIndex to 1)
                checkList[friendIndex] = 1
            }

            while (queue.isNotEmpty()) {
                val (friendIndex, distance) = queue.poll()

                val friends = friendshipInfo[friendIndex]
                    .mapIndexed { index, isFriend ->
                        if (isFriend && index != i) index else -1
                    }.filter { it != -1 }

                friends.forEach { friendIndex ->
                    if (checkList[friendIndex] == -1 ||
                        checkList[friendIndex] != 0
                    ) return@forEach
                    queue.add(friendIndex to distance + 1)
                    checkList[friendIndex] = distance + 1
                }

            }
            val currentBaconNumSum = checkList.sumOf { it } + 2
            if (currentBaconNumSum < minSum) {
                answer = i
                minSum = currentBaconNumSum
            }
        }

        println(answer)
    }
}

fun main() {
    전현수_케빈_베이컨의_6단계_법칙().solution()
}