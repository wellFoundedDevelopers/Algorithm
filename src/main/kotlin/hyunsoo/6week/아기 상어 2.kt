package hyunsoo.`6week`

import java.util.*
import java.util.ArrayDeque


/**
 * <문제>
 * [아기 상어 2](https://www.acmicpc.net/problem/17086)
 *
 * N * M 크기의 공간에 아기 상어 여러 마리가 있음.
 * 한 칸에는 아기 상어가 최대 1마리 존재
 * 어떤 칸의 안전 거리는 그 칸과 가장 거리가 가까운 아기 상어와이 거리임.
 * 안전 거리의 최댓값을 출력하기
 *
 * 아이디어
 * - 모든 좌표에서 8방향 깊이들 최대한 탐색 후 최소 안전 거리가 현재 위치에서의 안전 거리
 *
 * - 상어에서 탐색을 해보자.
 * - 상어를 -1로 두고 ~~
 * 이슈
 * - 각 한 방향만으로 깊게 들어가는 것을 안전거리라고 판단했음...
 */

data class CustomPos(val x: Int, val y: Int) {
    operator fun plus(pos: CustomPos): CustomPos {
        return CustomPos(
            this.x + pos.x,
            this.y + pos.y
        )
    }
}

// 키보드 기준 4 7 8 9 6 3 2 1
val directions = listOf(
    CustomPos(0, -1),
    CustomPos(-1, -1),
    CustomPos(-1, 0),
    CustomPos(-1, 1),
    CustomPos(0, 1),
    CustomPos(1, 1),
    CustomPos(1, 0),
    CustomPos(1, -1),
)

val spaceData = mutableListOf<MutableList<Int>>()
val sharkQueue: Queue<CustomPos> = ArrayDeque()
fun main() {

    val (n, m) = readln().split(" ").map { it.toInt() }
    repeat(n) {
        val rowData = readln().split(" ").map { it.toInt() }.toMutableList()
        spaceData.add(rowData)
    }
    val visit = Array(n) {
        BooleanArray(m) { false }
    }

    for (x in 0 until n) {
        for (y in 0 until m) {
            // 상어에서 탐색
            if (spaceData[x][y] == 1) findSafeDistance(x, y, visit)
            // 방문 기록 초기화
            visit.fillWithFalse()
        }
    }
    println(spaceData.findMax() - 1)
}

fun findSafeDistance(x: Int, y: Int, visit: Array<BooleanArray>) {

    sharkQueue.add(CustomPos(x, y))
    while (sharkQueue.isNotEmpty()) {
        val basePos = sharkQueue.poll()
        directions.forEach { pos ->
            val nextPos = basePos + pos

            // 범위 유효성 검사 && 상어 아닌 곳 탐색
            if (nextPos.x in 0 until spaceData.size &&
                nextPos.y in 0 until spaceData.first().size &&
                spaceData[nextPos.x][nextPos.y] != 1
            ) {
                // 방문하지 않은 곳이라면 탐색
                if (visit[nextPos.x][nextPos.y].not()) {
                    // 이전 칸의 안전거리( +1한 것이 현재 칸의 안전거리가 될 것임)
                    val previous = spaceData[nextPos.x - pos.x][nextPos.y - pos.y]
                    // 0이라면 아직 안전 거리를 계산하기 전,
                    // 예전에 계산되어있던 안전 거리보다 더 작은 안전 거리가 나오면 갱신
                    if (spaceData[nextPos.x][nextPos.y] == 0 ||
                        previous + 1 < spaceData[nextPos.x][nextPos.y]
                    ) spaceData[nextPos.x][nextPos.y] = previous + 1

                    sharkQueue.add(nextPos)
                }
                // 방문 처리
                visit[nextPos.x][nextPos.y] = true
            }

        }

    }
}

fun Array<BooleanArray>.fillWithFalse() {
    this.forEach {
        it.fill(false)
    }
}

fun MutableList<MutableList<Int>>.findMax(): Int {
    return this.maxOf { list ->
        list.maxOf { it }
    }
}