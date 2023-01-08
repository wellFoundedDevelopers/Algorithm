package hyunsoo.`3week`

/**
 * IBA는 매주 사장 실력있는 선수들의 랭킹 정보를 기록했음.
 * 매주 랭킹에 선수의 이름이 오를 때마다 포인트가 1씩 증가.
 * 할아버지가 가장 많은 포인트를 얻어 최고의 브릿지 선수가 됨.
 * 할아버지는 2등이 궁금함
 * 매주마다의 랭킹 정보 리스트를 받아 2등이 누군지를 찾아야함.
 *
 * 입/출력
 * - 선수는 1 ~ 10000까지 있음.
 * - 테스트 케이스의 첫 행은 (n. m)이 주어짐.
 *      - 다음 행부터
 *      - n주 동안의 매주 상위 m명의 랭킹 정보가 주어짐.
 * - 테스트 케이스마다 2등읜 번호를 출력해야함.
 *      - 2등이 여러명이면 선수 번호를 공백으로 구분하여 오름차순으로 출력
 *
 * 아이디어
 * - 계수 정렬
 *
 */
fun main() {

    var playerPoint = IntArray(10001) { 0 }
    while (true) {

        val (n, m) = readln().trim().split(" ").map { it.toInt() }

        if (n == 0 && m == 0) return

        repeat(n) {

            val rankedPlayers = readln().trim().split(" ").map { it.toInt() }

            rankedPlayers.forEach { player ->
                playerPoint[player]++
            }
        }


        val maxPoint = playerPoint.sortedByDescending { it }.first() // playerPoint.max() 는 컴파일 에러...
        var secondPlayerPoint: Int? = null

        playerPoint.sortedByDescending { it }.forEach { point ->
            secondPlayerPoint ?: run {
                if (maxPoint > point) {
                    secondPlayerPoint = point
                    return@forEach
                }
            }
        }

        playerPoint.forEachIndexed { index, point ->
            if (point == secondPlayerPoint) print("${index} ")
        }
        println()
        playerPoint = IntArray(10001) { 0 }
    }
}