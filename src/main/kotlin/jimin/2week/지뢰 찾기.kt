package jimin.`2week`

/*
<문제>
https://www.acmicpc.net/problem/4396

<구현 방법>
지뢰의 정보를 담는 landMineList를 만들어 지뢰를 열었을 경우 clickLandMine을 true로 주어
지뢰를 나타내도록 하였다.
지뢰가 주변에 몇개있는지는 forEachIndexed를 돌면서 열었을 경우에만 탐색하도록 하였다.

<트러블 슈팅>

 */
fun main() {
    val N = readln().toInt()
    val landMineInfoList = arrayListOf<List<String>>()
    val playerResultInfoList = arrayListOf<List<String>>()
    for (i in 0 until N) {
        landMineInfoList.add(readln().chunked(1))
    }
    for (i in 0 until N) {
        playerResultInfoList.add(readln().chunked(1))
    }
    makeResult(landMineInfoList, playerResultInfoList)
}

fun makeResult(landMineInfoList: ArrayList<List<String>>, playerResultInfoList: ArrayList<List<String>>) {
    val resultList = arrayListOf<MutableList<String>>()
    val landMineList = arrayListOf<Pair<Int, Int>>()
    for (i in 0 until landMineInfoList.size) {
        resultList.add(".".repeat(landMineInfoList.size).chunked(1).toMutableList())
    }
    var clickLandMine = false
    playerResultInfoList.forEachIndexed { i, row ->
        row.forEachIndexed { j, column ->
            when (column) {
                "x" -> {
                    when (landMineInfoList[i][j]) {
                        "*" -> {
                            clickLandMine = true
                            landMineList.add(Pair(i, j))
                        }
                        "." -> {
                            var num = 0
                            if (i != 0 && landMineInfoList[i - 1][j] == "*") num += 1
                            if (j != 0 && landMineInfoList[i][j - 1] == "*") num += 1
                            if (i != landMineInfoList.size - 1 && landMineInfoList[i + 1][j] == "*") num += 1
                            if (j != landMineInfoList.size - 1 && landMineInfoList[i][j + 1] == "*") num += 1
                            if (i != landMineInfoList.size - 1 && j != landMineInfoList.size - 1 && landMineInfoList[i + 1][j + 1] == "*") num += 1
                            if (i != 0 && j != 0 && landMineInfoList[i - 1][j - 1] == "*") num += 1
                            if (i != 0 && j != landMineInfoList.size - 1 && landMineInfoList[i - 1][j + 1] == "*") num += 1
                            if (i != landMineInfoList.size - 1 && j != 0 && landMineInfoList[i + 1][j - 1] == "*") num += 1
                            resultList[i][j] = num.toString()
                        }
                    }

                }
                "." -> {
                    if (landMineInfoList[i][j] == "*") landMineList.add(Pair(i, j))
                }
            }
        }

    }

    if (clickLandMine) {
        for (landMine in landMineList) {
            resultList[landMine.first][landMine.second] = "*"
        }
    }
    for (result in resultList) {
        println(result.joinToString(separator = ""))
    }
}