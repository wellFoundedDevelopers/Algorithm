package hyunsoo.`52week`

/**
 *
 * <문제>
 * [222-폴링](https://www.acmicpc.net/problem/17829)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_222_풀링` {

    private val coordinate = mutableListOf<MutableList<Int>>()

    fun solution() {

        val size = readln().toInt()

        repeat(size) {
            val row = readln().split(" ").map { it.toInt() }
            coordinate.add(row as MutableList)
        }

        var resultList = coordinate

        while (resultList.size != 2) {

            val newResult = mutableListOf<MutableList<Int>>()

            for (i in 0 until resultList.size step 2) {

                val newRow = mutableListOf<Int>()

                for (j in 0 until resultList.size step 2) {

                    val currentBlock = mutableListOf<Int>()

                    for (x in i until i + 2) {
                        for (y in j until j + 2) {
                            currentBlock.add(resultList[x][y])
                        }
                    }
                    newRow.add(currentBlock.sorted()[2])
                }
                newResult.add(newRow)
            }

            resultList = newResult

        }

        println((resultList[0] + resultList[1]).sorted()[2])

    }

}

fun main() {
    전현수_222_풀링().solution()
}