package byeonghee.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

data class Turn(val answer: String, val strikes: Int, val balls: Int)

const val digits = "123456789"

fun convertListToTurn(list: List<String>) : Turn {
    return Turn(list[0], list[1].toInt(), list[2].toInt())
}

fun countStrikeAndBall(ans: String, ask: String) : Pair<Int, Int> {
    var strikes = 0
    var balls = 0

    ans.forEachIndexed { i, it ->
        if (it == ask[i]) {
            strikes++
        }
        else if (ask.contains(it)) {
            balls++
        }
    }

    return Pair(strikes, balls)
}

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().trim().toInt()
    val turns = mutableListOf<Turn>()
    var answer = 0

    repeat(n) {
        turns.add(convertListToTurn(readLine().trim().split(" ")))
    }

    var one: Char
    var two: Char
    var three: Char
    var restOne: MutableList<Char>
    var restTwo: MutableList<Char>
    var ask: String
    var fit: Boolean

    for(i in 0..8) {
        restOne = digits.toMutableList()
        one = restOne.removeAt(i)
        for (j in 0..7) {
            restTwo = mutableListOf()
            restTwo.addAll(restOne)
            two = restTwo.removeAt(j)
            for(k in 0..6) {
                three = restTwo[k]
                ask = "$one$two$three"
                fit = true

                turns.forEach {
                    if (countStrikeAndBall(ask, it.answer) != Pair(it.strikes, it.balls)) {
                        fit = false
                        return@forEach  // 내부적으로 계속 돈다고 했었나..?
                    }
                }

                if (fit) {
                    answer++
                }
            }
        }
    }

    println(answer)
}