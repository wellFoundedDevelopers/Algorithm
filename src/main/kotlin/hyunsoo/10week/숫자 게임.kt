package hyunsoo.`10week`

/**
 * <문제>
 * [숫자 게임](https://www.acmicpc.net/problem/2303)
 *
 *
 */
fun main() {

    val cnt = readln().toInt()
    val scoreList = mutableListOf<Int>()

    repeat(cnt) {

        val cardList = readln()
            .split(" ")
            .map { it.toInt() }

        var maxScore = 0

        for(i in 0 .. 4)
            for(j in i + 1 .. 4)
                for(k in j + 1 .. 4) {
                    val curScore = (cardList[i] + cardList[j] + cardList[k]) % 10
                    if(maxScore < curScore) maxScore = curScore
                }

        scoreList.add(maxScore)

    }

    scoreList
        .indexOfLast { score ->
            score == scoreList.maxOrNull()!!
        }.apply {
            println(this + 1)
        }

}

