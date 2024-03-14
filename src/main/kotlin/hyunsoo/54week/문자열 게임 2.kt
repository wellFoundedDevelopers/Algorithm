package hyunsoo.`54week`

/**
 *
 * <문제>
 * [문자열 게임 2](https://www.acmicpc.net/problem/20437)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_문자열_게임_2` {

    val br = System.`in`.bufferedReader()

    private data class CharWithIndex(val char: Char, val index: Int)

    fun solution() {

        val t = br.readLine().toInt()

        repeat(t) {

            var firstConditionResult = Int.MAX_VALUE
            var secondConditionResult = -1

            val w = br.readLine()
            val k = br.readLine().toInt()

            val alphabetCounter = IntArray(26)
            val validCharList = mutableSetOf<Char>()

            val validCharInfoList = mutableListOf<CharWithIndex>()

            if (k == 1) {
                println("1 1")
                return@repeat
            }

            w.forEachIndexed { index, char ->
                val curCharCnt = ++alphabetCounter[char.toSequence()]
                if (k <= curCharCnt)
                    validCharList.add(char)
            }

            w.forEachIndexed { index, char ->
                if (validCharList.contains(char)) {
                    validCharInfoList.add(CharWithIndex(char, index))
                }
            }

            val sortedValidCharInfoList = validCharInfoList
                .sortedWith(
                    compareBy<CharWithIndex> { it.char }
                        .thenBy { it.index }
                )

            for (i in sortedValidCharInfoList.indices) {

                val (target, startIndex) = sortedValidCharInfoList[i]
                var targetIncludedCnt = 1

                for (j in i + 1 until sortedValidCharInfoList.size) {

                    val (endTarget, endIndex) = sortedValidCharInfoList[j]
                    if (target != endTarget) {
                        break
                    }
                    targetIncludedCnt++
                    if (k != targetIncludedCnt) continue

                    val curLength = endIndex - startIndex + 1

                    if (curLength < firstConditionResult) firstConditionResult = curLength
                    if (secondConditionResult < curLength) secondConditionResult = curLength

                }
            }

            if (firstConditionResult == Int.MAX_VALUE || secondConditionResult == -1) {
                println(-1)
            } else {
                println("$firstConditionResult $secondConditionResult")
            }

        }
    }

    private fun Char.toSequence(): Int {
        return this.code - 97
    }

}

fun main() {
    전현수_문자열_게임_2().solution()
}