package hyunsoo.`8week`

/**
 * <문제>
 * [창고 다각형](https://www.acmicpc.net/problem/2304)
 *
 * n개의 막대 기둥이 일렬로 세워져 있음. 기둥들의 폭은 모두 1m이며 높이는 다름
 * 기둥들을 이용하여 양철로된 창고를 제작하려고함. 창고에는 모든 기둥이 들어가고 창고의 지붕을 다음과 같이 만들어야함.
 * - 지붕은 수평 부분과 수직 부분으로 구성되며, 모두 연결되어야함.
 * - 지붕의 수평 부분은 반드시 어떤 기둥의 윗면과 닿아야함.
 * - 지붕의 수직 부분은 반드시 어떤 기둥의 옆면과 닿아야함.
 * - 지붕의 가장자리는 땅에 닿아야함.
 * - 비가 올 때 물이 고이지 않도록 지붕의 어떤 부분도 오목하게 들어간 부분이 없어야함.
 *
 * 아이디어
 * - 가장 높은 기둥으로 부터 좌우로 그 다음 높은 기둥들을 탐색하면 될까?
 *
 * 입/출력
 * - 첫 줄에는 기둥의 개수를 나타내는 정수 N이 주어짐.
 * - 그 다음 N 개의 줄
 *   - 각 줄의 각 기둥의 왼쪽 면의 위치를 나타내는 정수 L과 높이를 나타내는 정수 H가 주어짐.
 *
 */

data class Pillar(val location: Int, val height: Int)

fun main() {

    var storageSize = 0

    val pillarCnt = readln().toInt()
    val pillarList = mutableListOf<Pillar>()

    repeat(pillarCnt) {
        val pillar = readln().split(" ").map { it.toInt() }.toPillar()
        pillarList.add(pillar)
    }
    // 위치를 기준으로 오름차순 정렬
    pillarList.sortBy { it.location }

    // 최댓값을 찾은 후 왼, 오 각각 탐색하기
    val heightMax = pillarList.maxOf { it.height }

    // 높이가 가장 높은게 두개로 나뉘어있으면 해당 영역은 먼저 더해버리기 - 하나만 있으면 동일한 값이 될 것임.
    val heightMaxLeftIndex = pillarList
        .indexOfFirst {
            it.height == heightMax
        }
    val heightMaxRightIndex = pillarList
        .indexOfLast {
            it.height == heightMax
        }

    storageSize +=
        if (heightMaxLeftIndex == heightMaxRightIndex) heightMax
        else heightMax *
                (pillarList[heightMaxRightIndex].location - pillarList[heightMaxLeftIndex].location + 1)

    var rightCursor = heightMaxRightIndex

    // 오른쪽 탐색
    while (rightCursor < pillarList.lastIndex) {
        // 갱신된 최댓값 이후의 최댓값(오른쪽)
        val rightSideMaxHeight = pillarList
            .filter {
                it.location > pillarList[rightCursor].location
            }.maxOf { it.height }

        // 갱신된 최댓값 이후의 최댓값의 인덱스(오른쪽)
        val rightSideMaxHeightIndex = pillarList
            .indexOfLast { it.height == rightSideMaxHeight }

        storageSize +=
            rightSideMaxHeight *
                    (pillarList[rightSideMaxHeightIndex].location - pillarList[rightCursor].location)

        rightCursor = rightSideMaxHeightIndex

    }

    var leftCursor = heightMaxLeftIndex

    // 왼쪽 탐색
    while (leftCursor > 0) {
        // 갱신된 최댓값 이후의 최댓값(왼쪽)
        val leftSideMaxHeight = pillarList
            .filter {
                it.location < pillarList[leftCursor].location
            }.maxOf { it.height }

        // 갱신된 최댓값 이후의 최댓값의 인덱스(왼쪽)
        val leftSideMaxHeightIndex = pillarList
            .indexOfFirst { it.height == leftSideMaxHeight }

        storageSize +=
            leftSideMaxHeight *
                    (pillarList[leftCursor].location - pillarList[leftSideMaxHeightIndex].location)

        leftCursor = leftSideMaxHeightIndex
    }

    println(storageSize)

}

fun List<Int>.toPillar() =
    Pillar(this.first(), this[1])
