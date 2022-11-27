package hyunsoo.`11week`

import java.lang.Math.min
import java.util.Collections

/**
 * <문제>
 * [꽃길](https://www.acmicpc.net/problem/14620)
 *
 * 모든 곳에 씨앗을 심는 경우를 확인하고 정상적으로 심어지는 경우의 최솟값을 갱신해주면 될 듯
 */


val flowerBedLength = readln().toInt()
val flowerBedData = mutableListOf<MutableList<Int>>()

val plantedCheck = Array(flowerBedLength) {
    BooleanArray(flowerBedLength) { false }
}

var minCostForFlower = Int.MAX_VALUE

val dirList = listOf(
    Pair(-1, 0),
    Pair(1, 0),
    Pair(0, -1),
    Pair(0, 1),
)

fun main() {

    repeat(flowerBedLength) {
        val rowData = readln().split(" ").map { it.toInt() }.toMutableList()
        flowerBedData.add(rowData)
    }

    combinationForPlantFlower(0, 0)

    println(minCostForFlower)
}

fun combinationForPlantFlower(count: Int, sum: Int) {
    if (count == 3) {
        minCostForFlower = minCostForFlower.coerceAtMost(sum)
    } else {
        for (x in 1 until flowerBedLength - 1) {
            for (y in 1 until flowerBedLength - 1) {

                if (plantedCheck[x][y]) continue

                if (canPlant(x, y)) {
                    plant(x, y)
                    combinationForPlantFlower(count + 1, sum + blossom(x, y))
                    digUp(x, y)
                }

            }
        }
    }
}

fun blossom(x: Int, y: Int): Int {

    var costForFlower = flowerBedData[x][y]

    dirList.forEach { pos ->

        val nx = x + pos.first
        val ny = y + pos.second

        costForFlower += flowerBedData[nx][ny]
    }

    return costForFlower
}

fun canPlant(x: Int, y: Int): Boolean {
    dirList.forEach { pos ->
        if (plantedCheck[x + pos.first][y + pos.second]) return false
    }
    return true
}

fun plant(x: Int, y: Int) {
    dirList.forEach { pos ->
        plantedCheck[x + pos.first][y + pos.second] = true
    }
}

fun digUp(x: Int, y: Int) {
    dirList.forEach { pos ->
        plantedCheck[x + pos.first][y + pos.second] = false
    }
}