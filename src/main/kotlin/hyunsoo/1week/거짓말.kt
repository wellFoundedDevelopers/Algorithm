package hyunsoo.`1week`

/**
 *
 * 입/출력
 * - 첫째 줄에 사람의 수 N과 파티의 수 M이 주어짐
 * - 둘째 줄에는 이야기의 진실을 아는 사람의 수와 번호가 주어짐
 *      - 진실을 아는 사람의 수가 먼저 주어지고 그 개수만큼 사람들의 번호가 주어짐
 * - 셋째 줄부터 M개의 줄에는 각 파티마다 오는 사람의 수와 번호가 같은 방식으로 주어짐
 *
 * 아이디어
 * - 진실을 아는 사람들 목록 + 연관된 사람들을 확인하고
 * - 이 사람들이 있는 파티에서는 진실을 말해야하는 것으로 판단
 * - 모든 파티를 탐색하여 진실을 아는 사람과 겹치는 사람은 진실을 아는 사람으로 판단.
 *      - 골드치고는 너무 간단하다 생각을 하긴했지만... 역시나 틀렸다.
 *      - 위와 같은 방식으로는 이미 진실을 말했다고 생각한 파티가 잘못된 것을 수정할 수 없음.
 *      즉, 사실을 들었던 사람이 거짓말을 들었던 사람에게 알려줄 수 있는 것에 대한 처리를 못함.
 *      - 3 3
 *        1 3
 *        1 1
 *        2 1 2
 *        2 2 3
 *        의 케이스와 같은 경우에 오답 발생
 *        정답 = 0 / 내답 = 1
 *
 * 그래서!
 * - 유니온 파인드를 사용하자
 * - 부모가 0이면 진실을 아는 사람과 연관이 되어있다는 뜻
 *
 */

fun main() {

    val (n, m) = readln().split(" ").map { it.toInt() }

    val partyInfoList = mutableListOf<List<Int>>()
    val knowTruthList = mutableSetOf<Int>()
    val knowTruth = readln()
    var canLieCnt = 0
    var peopleParentList = (0..n + 1).toMutableList()

    // 진실을 아는 사람이 없을 경우
    if (knowTruth == "0") {
        println(m)
        return
    } else {
        knowTruth.trim().split(" ").map { it.toInt() }
            .drop(1).forEach {
                peopleParentList[it] = 0
            }
    }

    repeat(m) {

        val partyInfo = readln().trim().split(" ").map { it.toInt() }.drop(1)
        partyInfoList.add(partyInfo)

        partyInfo.drop(1).forEach {
            unionParent(peopleParentList, partyInfo.first(), it)
        }

    }

    partyInfoList.forEach { partyInfo ->
        var canLie = true

        partyInfo.forEach {
            if (findParent(peopleParentList, it) == 0) {
                canLie = false
                return@forEach
            }
        }

        if (canLie) canLieCnt++
    }

    println(canLieCnt)

}

fun unionParent(parent: MutableList<Int>, a: Int, b: Int) {

    val aParent = findParent(parent, a)
    val bParent = findParent(parent, b)
    if (aParent > bParent) parent[aParent] = bParent
    else parent[bParent] = aParent
}

fun findParent(parent: MutableList<Int>, a: Int): Int {
    if (parent[a] == a) return parent[a]
    return findParent(parent, parent[a])
}