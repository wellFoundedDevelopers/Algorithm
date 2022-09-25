package jimin.`2week`

/*
<문제>
https://www.acmicpc.net/problem/2503

<구현 방법>
전체 baseball 리스트를 만들어 예제로 들어준 조건을 다 만족하는 것만 뽑아냈다.

<트러블 슈팅>
처음에는 조건 하나씩만 고려해서 그 조건을 만족하는 것을 filter하였는데,
이렇게 하니 여러 조건을 고려해야 하는 경우를 고려하지 못했다.
 */

fun main() {
    val baseballInfoList = arrayListOf<List<Any>>()
    var totalBaseball = mutableListOf<List<Int>>()
    for (i in 1..9) {
        for (j in 1..9) {
            for (k in 1..9) {
                if (setOf(i, j, k).size == 3) {
                    totalBaseball.add(listOf(i, j, k))
                }
            }
        }
    }
    repeat(readln().toInt()) {
        baseballInfoList.add(readln().split(" ").map { it.toInt() })
    }

    val realBaseballList = arrayListOf<String>()
    for (baseball in totalBaseball) {
        var possible = 0
        for (info in baseballInfoList) {
            var strike = 0
            var ball = 0
            val guessed = info[0].toString().chunked(1).map { it.toInt() }
            for (i in guessed.indices) {
                if (guessed[i] == baseball[i]) strike += 1
                else if (guessed[i] in baseball) ball += 1
            }
            if (info[1] == strike && info[2] == ball) possible += 1
        }
        if (possible == baseballInfoList.size) realBaseballList.add(baseball.joinToString(""))
    }

    println(realBaseballList.size)
}


/*
fun main() {
    //val baseballInfoList = arrayListOf<List<Int>>()
    var totalBaseball = mutableListOf<List<Int>>()
    for (i in 1..9) {
        for (j in 1..9) {
            for (k in 1..9) {
                if (setOf(i, j, k).size == 3){
                    totalBaseball.add(listOf(i, j, k))
                }
            }
        }
    }
    repeat(readln().toInt()) {
        val info = readln().split(" ").map { it.toInt() }
        val baseball = info[0].toString().chunked(1).map { it.toInt() }
        val strike = info[1]
        val ball = info[2]
        var possibleBaseball = mutableListOf<List<Int>>()
        when (strike) {
            0 -> {
                when (ball) {
                    0 -> {
                        possibleBaseball += totalBaseball.filterNot { it.contains(baseball[0]) }.toMutableList()
                        possibleBaseball += totalBaseball.filterNot { it.contains(baseball[1]) }.toMutableList()
                        possibleBaseball += totalBaseball.filterNot { it.contains(baseball[2]) }.toMutableList()
                    }
                    1 -> {
                        possibleBaseball += totalBaseball.filter { it.contains(baseball[0]) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it.contains(baseball[1]) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it.contains(baseball[2]) }.toMutableList()
                    }
                    2 -> {
                        possibleBaseball +=
                            totalBaseball.filter { it.containsAll(listOf(baseball[0], baseball[1])) }.toMutableList()
                        possibleBaseball +=
                            totalBaseball.filter { it.containsAll(listOf(baseball[1], baseball[2])) }.toMutableList()
                        possibleBaseball +=
                            totalBaseball.filter { it.containsAll(listOf(baseball[2], baseball[0])) }.toMutableList()
                    }
                    3 -> {
                        possibleBaseball += totalBaseball.filter { it.containsAll(baseball) }.toMutableList()
                    }
                }
            }
            1 -> {
                when (ball) {
                    0 -> {
                        possibleBaseball += totalBaseball.filter { it[0] == baseball[0] }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[1] == baseball[1] }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[2] == baseball[2] }.toMutableList()
                    }
                    1 -> {
                        possibleBaseball += totalBaseball.filter { it[0] == baseball[0] && it.contains(baseball[1]) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[0] == baseball[0] && it.contains(baseball[2]) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[1] == baseball[1] && it.contains(baseball[0]) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[1] == baseball[1] && it.contains(baseball[2]) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[2] == baseball[2] && it.contains(baseball[0]) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[2] == baseball[2] && it.contains(baseball[1]) }.toMutableList()
                    }
                    2 -> {
                        possibleBaseball += totalBaseball.filter { it[0] == baseball[0] && it.containsAll(listOf(baseball[1], baseball[2])) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[1] == baseball[1] && it.containsAll(listOf(baseball[0], baseball[2])) }.toMutableList()
                        possibleBaseball += totalBaseball.filter { it[2] == baseball[2] && it.containsAll(listOf(baseball[0], baseball[1])) }.toMutableList()
                    }
                }
            }
            2 -> {
                possibleBaseball += totalBaseball.filter { it[0] == baseball[0] && it[1] == baseball[1] }.toMutableList()
                possibleBaseball += totalBaseball.filter { it[1] == baseball[1] && it[2] == baseball[2] }.toMutableList()
                possibleBaseball += totalBaseball.filter { it[0] == baseball[0] && it[2] == baseball[2] }.toMutableList()
            }
            3 -> {
                possibleBaseball += mutableListOf(baseball)
            }
        }
        totalBaseball = possibleBaseball
        println(totalBaseball)
        println("---")
    }

    println(totalBaseball)
    println(totalBaseball.size)
}
 */