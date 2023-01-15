package hyunsoo.`18week`

/**
 *
 * <문제>
 * [거짓말](https://www.acmicpc.net/problem/1043)
 * 거짓말쟁이 지민이...
 *
 * 진실을 아는 사람이 포함된 곳에서는 거짓말 하지 않기.
 * 진실을 말하게 된다면 거기에 있는 사람들 모두 진실을 아는 사람들이 된다..!
 *
 * - 아이디어
 * 서로소 집합 자료구조를 사용했음.
 * 부모가 0이라면 진실을 아는 사람
 *
 * - 트러블 슈팅
 * 처음에는 그냥 집합을 사용해서 순차탐색하며 진실을 아는 사람의 포함여부로 거짓말가능 여부를 판단하려했음.
 * 거짓말을 듣고 후에 진실을 들을 수도 있게 되니까 해당 방법으로는 불가능
 *
 *
 * 진실을 아는 사람들과 겹치면 모두 진실을 아는 사람이 된다..!
 * 진실을 아는 사람을 먼저 판단 후 파티정보를 순회하며 거짓말 여부 판단하기!!
 * 연결의 연결의 연결된 사람은 감지하지 못해서 틀림.
 * -- DFS혹은 서로소 집합을 사용하기
 *
 *
 */

fun main() {

    val (peopleCnt, partyCnt) = readln().split(" ").map { it.toInt() }
    val parent = IntArray(peopleCnt + 1) { it }
    var lieCnt = 0

    readln().split(" ").map { it.toInt() }.drop(1).forEach { knowTruth ->
        parent[knowTruth] = 0
    }

    val partyInfoList = mutableListOf<List<Int>>()

    repeat(partyCnt) {

        val partyInfo = readln().split(" ").map { it.toInt() }.drop(1)

        partyInfoList.add(partyInfo)

        for (i in partyInfo.indices) {
            for (j in i + 1 until partyInfo.size) {
                val targetA = parent[partyInfo[i]]
                val targetB = parent[partyInfo[j]]
                union(parent, targetA, targetB)
            }
        }

    }

    partyInfoList.forEach infoListLoop@{ partyInfo ->
        partyInfo.forEach { people ->
            if (findParent(parent, people) == 0) return@infoListLoop
        }
        lieCnt++
    }

    println(lieCnt)
}

fun findParent(parent: IntArray, target: Int): Int {
    if (parent[target] != target)
        return findParent(parent, parent[target])
    return target
}

fun union(parent: IntArray, targetA: Int, targetB: Int) {
    val aParent = findParent(parent, targetA)
    val bParent = findParent(parent, targetB)
    if (aParent < bParent) {
        parent[bParent] = aParent
    } else {
        parent[aParent] = bParent
    }
}