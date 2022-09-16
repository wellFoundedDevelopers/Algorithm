package byeonghee.`1week`

/** 백준 제출 코드 START*/
var R = 0
var C = 0

val dr = arrayOf(0, 1, 0, 1)
val dc = arrayOf(0, 0, 1, 1)

fun mainForSubmit_2897() {
    readln().split(" ").map{ it.toInt() }.apply{
        R = first()
        C = last()
    }

    val parkingMap = arrayListOf<String>()
    val answer = arrayOf(0, 0, 0, 0, 0)

    repeat(R) {
        parkingMap.add(readln())
    }

    row@ for(r in 0 until (R - 1)) {
        col@ for(c in 0 until (C - 1)) {
            var carN = 0
            twoByTwo@ for(i in 0 until 4) {
                when(parkingMap[r + dr[i]][c + dc[i]]) {
                    '#' -> {
                        carN = -1
                        continue@col
                    }
                    'X' -> carN++
                }
            }
            answer[carN]++
        }
    }

    answer.forEach {
        println(it)
    }
}
/** 백준 제출 코드 END*/

fun main() {
    val tc = arrayListOf<Array<String>>()
    tc.add(arrayOf("4 4", "#..#", "..X.", "..X.", "#XX#"))
    tc.add(arrayOf("4 4", "....", "....", "....", "...."))
    tc.add(arrayOf("4 5", "..XX.", ".#XX.", "..#..", "....."))

    val ans = arrayListOf<Array<Int>>()
    ans.add(arrayOf(1, 1, 2, 1, 0))
    ans.add(arrayOf(9, 0, 0, 0, 0))
    ans.add(arrayOf(2, 1, 1, 0, 1))

    for(i in tc.indices) {
        println("TEST CASE ${i+1}")
        println("출력:")
        test(tc[i])
        println("정답:")
        println(ans[i].joinToString("\n"))
        println()
    }
}

fun test_2897(args: Array<String>) {
    var argIdx = 0
    args[argIdx++].split(" ").map{ it.toInt() }.apply{
        R = first()
        C = last()
    }

    val parkingMap = arrayListOf<String>()
    val answer = arrayOf(0, 0, 0, 0, 0)

    repeat(R) {
        parkingMap.add(args[argIdx++])
    }

    row@ for(r in 0 until (R - 1)) {
        col@ for(c in 0 until (C - 1)) {
            var carN = 0
            twoByTwo@ for(i in 0 until 4) {
                when(parkingMap[r + dr[i]][c + dc[i]]) {
                    '#' -> {
                        carN = -1
                        continue@col
                    }
                    'X' -> carN++
                }
            }
            answer[carN]++
        }
    }

    answer.forEach {
        println(it)
    }
}