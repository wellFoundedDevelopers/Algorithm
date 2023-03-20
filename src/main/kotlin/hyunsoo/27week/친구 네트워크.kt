package hyunsoo.`27week`

/**
 *
 * <문제>
 * [친구 네트워크](https://www.acmicpc.net/problem/4195)
 *
 * - 아이디어
 *
 * 새로운 이름이 나오면 map에 저장 후 키 값을 부분 집합의 index로 사용
 *
 * - 트러블 슈팅
 * union 연산을.... 부모가 아니라 자식한테 해줬음...
 *
 */
class `전현수_친구_네트워크` {

    fun solution() {

        val caseCnt = readln().toInt()

        repeat(caseCnt) {

            val relationCnt = readln().toInt()

            val parent = IntArray(200_000) { it }
            val relationCntInfo = IntArray(200_000) { 1 }
            val peopleMap = hashMapOf<String, Int>()

            repeat(relationCnt) {

                val (first, second) = readln().split(" ")

                val firstIndex = peopleMap.getOrDefault(first, NONE).run {
                    if (this == NONE) {
                        peopleMap[first] = peopleMap.size
                        peopleMap.size - 1
                    } else this
                }

                val secondIndex = peopleMap.getOrDefault(second, NONE).run {
                    if (this == NONE) {
                        peopleMap[second] = peopleMap.size
                        peopleMap.size - 1
                    } else this
                }

                union(parent, relationCntInfo, firstIndex, secondIndex)
                println(relationCntInfo[getParent(parent, firstIndex)])
            }

        }
    }

    private fun getParent(parent: IntArray, target: Int): Int {
        return if (parent[target] == target) target
        else getParent(parent, parent[target])
    }

    private fun union(parent: IntArray, relationCntInfo: IntArray, a: Int, b: Int) {

        val aParent = getParent(parent, a)
        val bParent = getParent(parent, b)

        if (aParent < bParent) {
            parent[bParent] = aParent
            relationCntInfo[aParent] += relationCntInfo[bParent]
        } else if (bParent < aParent) {
            parent[aParent] = bParent
            relationCntInfo[bParent] += relationCntInfo[aParent]
        }
    }

    companion object {
        const val NONE = -1
    }
}

fun main() {
    전현수_친구_네트워크().solution()
}