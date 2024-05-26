package byeonghee.week64

class `소병희_삼각 달팽이` {
    fun solution(n: Int): IntArray {
        var answer: IntArray = intArrayOf()

        val triangle = Array(n) { ArrayDeque<Int>() }
        var side = n
        var round = 0
        var sl = 0
        var count = 1

        while(side > 0) {
            for(i in sl until sl + side) {
                triangle[i].add(round, count++)
            }

            repeat(side - 1) { i ->
                triangle[sl + side - 1].add(round + i + 1, count++)
            }

            for(i in sl + side-2 downTo sl + 1) {
                triangle[i].add(triangle[i].size - round, count++)
            }

            round++
            side -= 3
            sl += 2
        }

        return triangle.flatMap { it.toList() }.toIntArray()
    }
}

fun main() {
    val sol = `소병희_삼각 달팽이`()
    println(sol.solution(4).joinToString(" "))
    println(sol.solution(5).joinToString(" "))
    println(sol.solution(6).joinToString(" "))
    println(sol.solution(7).joinToString(" "))
}