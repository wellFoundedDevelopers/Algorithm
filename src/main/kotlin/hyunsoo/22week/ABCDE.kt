package hyunsoo.`22week`

import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [ABCDE](https://www.acmicpc.net/problem/13023)
 *
 * - 아이디어
 *
 * A -> B -> C -> D -> E
 * 음.. 그냥 DFS하면서 다른 종류의 친구 관계가 5개 있으면 되려나?
 * 인접 리스트로 DFS 해보자!!
 *
 * - 트러블 슈팅
 *
 */
class `전현수_ABCDE` {

    private lateinit var friendsInfo: List<MutableList<Int>>
    private lateinit var visitedInfo: BooleanArray

    fun solution() {

        val (peopleCnt, relationshipCnt) = readln().split(" ").map { it.toInt() }

        friendsInfo = List(peopleCnt) {
            mutableListOf()
        }

        visitedInfo = BooleanArray(peopleCnt)

        repeat(relationshipCnt) {
            val relationship = readln().split(" ").map { it.toInt() }
            friendsInfo[relationship[0]].add(relationship[1])
            friendsInfo[relationship[1]].add(relationship[0])
        }

        repeat(peopleCnt) { startPerson ->
            findTarget(startPerson)
        }

        println(NOT_EXIST)
    }

    private fun findTarget(curPerson: Int) {

        if (visitedInfo[curPerson]) return

        visitedInfo[curPerson] = true

        if (visitedInfo.count { it } == 5) {
            println(EXIST)
            exitProcess(0)
        }

        friendsInfo[curPerson].forEach { myFriend ->
            findTarget(myFriend)
        }
        visitedInfo[curPerson] = false
    }

    companion object {
        const val EXIST = 1
        const val NOT_EXIST = 0
    }
}

fun main() {
    전현수_ABCDE().solution()
}