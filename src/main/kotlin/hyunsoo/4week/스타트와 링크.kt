package hyunsoo.`4week`

/**
 * 축구를 하기 위해 N명이 모였고 N은 반드시 짝수
 * 1, 2가 팀이라면 2차원 배열[1][2] + 2차원 배열[2][1]이 팀의 능력치임.
 * 능력치가 최소가 되도록 해라..!
 *
 * 브루트 포스
 * n을 반씩 나누고 각 팀에 분배해서 전부 확인
 */
val peopleCnt = readln().toInt()
val NUM_LIST = (1..peopleCnt).toList()
val pickedNumList = mutableListOf<Int>()
val startTeamList = mutableListOf<MutableList<Int>>()

fun main() {

    val playerData = mutableListOf<List<Int>>()
    var minDiff = 100 * 100 * 100

    repeat(peopleCnt) {
        val data = readln().split(" ").map { it.toInt() }
        playerData.add(data)
    }

    combination(0, peopleCnt / 2, 0)

    startTeamList.forEach { startTeam ->
        val linkTeam = (1..peopleCnt)
            .toList()
            .filter { startTeam.contains(it).not() }

        var startTeamScore = 0
        var linkTeamScore = 0

        startTeam.forEach { curPlayer ->
            startTeam.forEach { otherPlayer ->
                if (curPlayer != otherPlayer) {
                    startTeamScore += playerData[curPlayer - 1][otherPlayer - 1]
                }
            }
        }

        linkTeam.forEach { curPlayer ->
            linkTeam.forEach { otherPlayer ->
                if (curPlayer != otherPlayer) {
                    linkTeamScore += playerData[curPlayer - 1][otherPlayer - 1]
                }
            }
        }

        val scoreDiff = calculateMin(startTeamScore, linkTeamScore)
        if (scoreDiff < minDiff) minDiff = scoreDiff

    }

    println(minDiff)
}

fun combination(cnt: Int, depth: Int, startWith: Int) {

    if (cnt == depth) {
        val startTeam = mutableListOf<Int>()
        pickedNumList.forEach { startTeam.add(it) }
        startTeamList.add(startTeam)
        return
    }
    for (index in startWith until peopleCnt) {

        val pickedNum = NUM_LIST[index]
        pickedNumList.add(pickedNum)
        combination(cnt + 1, depth, index + 1)
        pickedNumList.removeAt(pickedNumList.lastIndex)

    }

}

fun calculateMin(a: Int, b: Int) = if (a > b) a - b else b - a