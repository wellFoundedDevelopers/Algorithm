package hyunsoo.`42week`

/**
 *
 * <문제>
 * [로또](https://www.acmicpc.net/problem/6603)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_로또` {

    private lateinit var elements: List<Int>
    private val currentList = mutableListOf<Int>()
    private lateinit var picked: BooleanArray

    fun solution() {

        while (true) {

            val input = readln().split(" ").map { it.toInt() }
                .apply {
                    if (this.first() == 0) return
                }

            elements = input.drop(1)
            picked = BooleanArray(elements.size)

            pickAndGet()

            println()
        }
    }

    private fun pickAndGet(startIndex: Int = 0) {
        if (currentList.size == LOTTO_CNT) {
            currentList.joinToString(" ").apply {
                println(this)
            }
            return
        }
        for (index in startIndex until elements.size) {

            if (picked[index]) continue
            if (currentList.isNotEmpty() &&
                elements[index] < currentList.last()
            ) continue

            currentList.add(elements[index])
            picked[index] = true
            pickAndGet(startIndex + 1)
            currentList.removeLast()
            picked[index] = false
        }
    }

    companion object {
        private const val LOTTO_CNT = 6
    }

}

fun main() {
    전현수_로또().solution()
}