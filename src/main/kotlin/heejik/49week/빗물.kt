package heejik.`49week`

import kotlin.math.min
import kotlin.properties.Delegates

class 빗물 {

    var h by Delegates.notNull<Int>()
    var w by Delegates.notNull<Int>()
    private lateinit var heights: List<Int>

    fun solve() {
        setting()
        getRainWater().also { rainWater ->
            print(rainWater)
        }
    }

    private fun setting() {
        readln().split(' ').map { it.toInt() }.run {
            h = this[0]
            w = this[1]
        }
        heights = readln().split(' ').map { it.toInt() }
    }

    private fun getRainWater(): Int {
        var rainWater = 0

        heights.forEachIndexed { index, height ->
            if (index == 0 || index == heights.size - 1) return@forEachIndexed

            val minHeightOfBothSideMaxHeight = min(
                heights.subList(0, index + 1).max() ?: 0,
                heights.subList(index + 1, heights.size).max() ?: 0
            )

            with(minHeightOfBothSideMaxHeight - height) {
                if (this > 0) rainWater += this
            }

        }

        return rainWater
    }
}


fun main() {
    빗물().solve()
}