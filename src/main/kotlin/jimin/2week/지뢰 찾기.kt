package jimin.`2week`

// . 지뢰 x
// * 지뢰o
// 이미 열린칸 x 안열린거 .
// 지뢰x && 열림 -> 0~8 숫자
// 지뢰o && 열림 -> 지뢰가 있는 모든 칸 *
// 다른지점은 .

//문자열로 넣으면 안됨.
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