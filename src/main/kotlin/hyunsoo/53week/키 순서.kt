package hyunsoo.`53week`

/**
 *
 * <문제>
 * [키 순서](https://www.acmicpc.net/problem/2458)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_키_순서` {

    private lateinit var tallerInfoList: Array<MutableList<Int>>
    private lateinit var shorterInfoList: Array<MutableList<Int>>

    fun solution() {

        var answer = 0

        val (studentCnt, comparedCnt) = readln()
            .split(" ")
            .map { it.toInt() }

        tallerInfoList = Array(studentCnt + 1) {
            mutableListOf()
        }

        shorterInfoList = Array(studentCnt + 1) {
            mutableListOf()
        }

        repeat(comparedCnt) {
            val (short, tall) = readln()
                .split(" ")
                .map { it.toInt() }

            tallerInfoList[short].add(tall)
            shorterInfoList[tall].add(short)
        }

        for (criterion in 1..studentCnt) {

            val comparedInfo = BooleanArray(studentCnt + 1).apply {
                this[0] = true
                this[criterion] = true
            }
            val visitedInfo = BooleanArray(studentCnt + 1).apply {
                this[0] = true
                this[criterion] = true
            }

            checkForTaller(comparedInfo, visitedInfo, criterion)
            checkForShorter(comparedInfo, visitedInfo, criterion)

            if (comparedInfo.all { it }) answer++

        }

        println(answer)
    }

    private fun checkForTaller(comparedInfo: BooleanArray, visitedInfo: BooleanArray, criterion: Int) {

        tallerInfoList[criterion].forEach { comparedStudent ->

            if (visitedInfo[comparedStudent]) return@forEach

            comparedInfo[comparedStudent] = true
            visitedInfo[comparedStudent] = true

            checkForTaller(comparedInfo, visitedInfo, comparedStudent)
        }

    }

    private fun checkForShorter(comparedInfo: BooleanArray, visitedInfo: BooleanArray, criterion: Int) {

        shorterInfoList[criterion].forEach { comparedStudent ->

            if (visitedInfo[comparedStudent]) return@forEach

            comparedInfo[comparedStudent] = true
            visitedInfo[comparedStudent] = true

            checkForShorter(comparedInfo, visitedInfo, comparedStudent)
        }

    }
}

fun main() {
    전현수_키_순서().solution()
}