package heejik.`19week`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs
import kotlin.math.min
import kotlin.properties.Delegates


class `치킨 배달` {
    data class Pos(
        val x: Int,
        val y: Int
    ) {
        fun getDistance(other: Pos) = abs(this.x - other.x) + abs(this.y - other.y)
    }

    val br = BufferedReader(InputStreamReader(System.`in`))
    private val city = mutableListOf<List<Int>>()
    private val chickenPoses = mutableListOf<Pos>()
    private val homePoses = mutableListOf<Pos>()
    private lateinit var visited: MutableList<Boolean>
    var n by Delegates.notNull<Int>()
    var m by Delegates.notNull<Int>()
    var answer = Int.MAX_VALUE

    fun setting() {
        br.readLine().split(' ').map { it.toInt() }.run {
            n = first()
            m = last()
        }

        repeat(n) {
            city.add(br.readLine().split(' ').map { it.toInt() })
        }

        city.forEachIndexed { x, row ->
            row.forEachIndexed { y, info ->
                if (info == 2) chickenPoses.add(Pos(x, y))
                else if (info == 1) homePoses.add(Pos(x, y))
            }
        }
        visited = MutableList(chickenPoses.size) { false }

        rec(0, 0)
        println(answer)
    }

    fun rec(cnt: Int, start: Int) {
        println("cnt : $cnt, start: $start, visited: $visited")
        if (cnt == m) {
            var tmp = 0
            homePoses.forEach {
                var minDistance = Int.MAX_VALUE
                visited.forEachIndexed { index, b ->
                    if (b.not()) return@forEachIndexed
                    minDistance = min(it.getDistance(chickenPoses[index]), minDistance)
                }
                tmp += minDistance
            }
            answer = min(answer, tmp)
            return
        }

        for (i in start until visited.size) {
            visited[i] = true
            rec(cnt + 1, i + 1)
            visited[i] = false
        }
    }
}

fun main() {
    `치킨 배달`().setting()
}