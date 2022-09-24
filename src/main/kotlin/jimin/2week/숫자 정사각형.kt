package jimin.`2week`

import kotlin.math.max

fun main() {
    val N = readln().split(" ").first().toInt()
    val rectList = arrayListOf<List<Int>>()
    for (i in 0 until N) {
        rectList.add(readln().chunked(1).map { it.toInt() })
    }

    println(getRectMaxSize(rectList))
}

fun getRectMaxSize(rectList: ArrayList<List<Int>>): Int {
    val rectSet = rectList.flatten().toSet()
    val possibleRectNumber = arrayListOf<Int>()
    rectSet.forEach { set ->
        if (rectList.flatten().count { it == set } >= 4) {
            possibleRectNumber.add(set)
        }
    }
    var max = 1
    for ((i, row) in rectList.withIndex()){
        for ((j,column) in row.withIndex()){
            if (column !in possibleRectNumber) continue
            for (y in j + 1 until row.size){
                if (row[y] == column) {
                    for (x in i + 1 until rectList.size) {
                        if (column == rectList[x][j]) {
                            if (column == rectList[x][y]) {
                                max = max(max, (x - i + 1) * (y - j + 1))
                                //possibleRectNumber.remove(column)
                            }
                        }
                    }
                }
            }
        }
    }
    return max
}
