package heejik.`13week`

class 시계 {

    val nums = listOf(
        "####.##.##.####",  // 0
        "..#..#..#..#..#",  // 1
        "###..#####..###",  // 2
        "###..####..####",  // 3
        "#.##.####..#..#",  // 4
        "####..###..####",  // 5
        "####..####.####",  // 6
        "###..#..#..#..#",  // 7
        "####.#####.####",  // 8
        "####.####..####"   // 9
    )

    fun solve() {
        val watch = mutableListOf<List<String>>()
        repeat(5) {
            watch.add(readln().split(' '))
        }

        val hourFirst = find(watch.joinToString(separator = "") { it[0] })
        val hourSecond = find(watch.joinToString(separator = "") { it[1] })
        val minuteFirst = find(watch.joinToString(separator = "") { it[2] })
        val minuteSecond = find(watch.joinToString(separator = "") { it[3] })

        println("$hourFirst$hourSecond:$minuteFirst$minuteSecond")
    }

    fun find(diode: String): Int {
        nums.forEach { num ->
            num.forEachIndexed { index, c ->
                if (c == '.' && diode[index] == '#') return@forEach
            }
            return nums.indexOf(num)
        }
        return -1
    }
}

fun main() {
    시계().solve()
}