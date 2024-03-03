package hyunsoo.`52week`

/**
 *
 * <문제>
 * [종이의 개수](https://www.acmicpc.net/problem/1780)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_종이의_개수` {

    private val coordinate = mutableListOf<List<Int>>()

    private var negativeOneCnt = 0
    private var zeroCnt = 0
    private var oneCnt = 0

    fun solution() {

        val size = readln().toInt()

        repeat(size) {
            val row = readln().split(" ").map { it.toInt() }
            coordinate.add(row)
        }

        divideAndConquer(0, 0, size)

        println(negativeOneCnt)
        println(zeroCnt)
        println(oneCnt)
    }

    private fun divideAndConquer(x: Int, y: Int, length: Int) {

        val criteria = coordinate[x][y]

        for (i in x until x + length) {
            for (j in y until y + length) {

                val curNum = coordinate[i][j]

                if (curNum != criteria) {

                    val newLength = length / 3

                    divideAndConquer(x, y, newLength)
                    divideAndConquer(x + newLength, y, newLength)
                    divideAndConquer(x, y + newLength, newLength)
                    divideAndConquer(x + newLength * 2, y, newLength)
                    divideAndConquer(x, y + newLength * 2, newLength)
                    divideAndConquer(x + newLength, y + newLength, newLength)
                    divideAndConquer(x + newLength * 2, y + newLength, newLength)
                    divideAndConquer(x + newLength, y + newLength * 2, newLength)
                    divideAndConquer(x + newLength * 2, y + newLength * 2, newLength)

                    return
                }
            }
        }

        when (criteria) {
            -1 -> negativeOneCnt += 1
            0 -> zeroCnt += 1
            1 -> oneCnt += 1
        }

    }

}

fun main() {
    전현수_종이의_개수().solution()
}