package hyunsoo.`24week`

import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [도서관](https://www.acmicpc.net/problem/1461)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 * 책이 하나만 있을 때 해당 책이 음수 경로에 있을 때 인덱스 에러 발생
 *
 */
class `전현수_도서관` {

    val packagedPositions = mutableListOf<List<Int>>()
    val tempPackage = mutableListOf<Int>()

    fun solution() {

        var answer = 0
        val (bookCnt, maxCarryCnt) = readln().split(" ").map { it.toInt() }
        val bookPositions = readln()
                .split(" ")
                .map { it.toInt() }
                .sorted()

        // 최초로 음수 -> 양수가 바뀌는 인덱스를 찾기
        val pivot = bookPositions.count { it < 0 }

        // 한 번에 들 수 있는 책의 개수만큼 묶기
        // 양수
        for (index in bookPositions.lastIndex downTo pivot) {
            if (maxCarryCnt == tempPackage.size) packAndPut()
            tempPackage.add(bookPositions[index])
        }
        packAndPut()

        // 음수
        for (index in 0 until pivot) {
            if (maxCarryCnt == tempPackage.size) packAndPut()
            tempPackage.add(bookPositions[index])
        }
        packAndPut()

        val packagedDistance = packagedPositions
                .map { packaged ->
                    packaged.map { it.absoluteValue }
                }.sortedByDescending { absolutePackaged ->
                    absolutePackaged.maxOf { it }
                }

        println(packagedDistance)

        packagedDistance.apply {
            answer += first().maxOf { it }
            this.drop(1).forEach { distances ->
                answer += distances.maxOf { it } * 2
            }
        }

        println(answer)

    }

    private fun packAndPut() {
        if (tempPackage.isNotEmpty()) {
            packagedPositions.add(tempPackage.toList())
            tempPackage.clear()
        }
    }

}

fun main() {
    전현수_도서관().solution()
}