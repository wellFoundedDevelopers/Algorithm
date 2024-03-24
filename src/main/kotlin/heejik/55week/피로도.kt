package heejik.`55week`

import kotlin.math.max

class 피로도 {
    var answer = 0
    var dungeonCount = 0
    var k = 0
    lateinit var dungeons: Array<IntArray>

    fun solution(_k: Int, _dungeons: Array<IntArray>): Int {
        k = _k
        dungeons = _dungeons
        dungeonCount = dungeons.size

        findDungeons(visited = emptyList(), fatigue = 0)
        return answer
    }

    fun findDungeons(visited: List<Int>, fatigue: Int) {
        if (fatigue > k) return
        for (i in dungeons.indices) {
            if (i !in visited && fatigue + dungeons[i][0] <= k) {
                findDungeons(visited.plus(i), fatigue + dungeons[i][1])
            }
        }
        answer = max(answer, visited.size)
    }
}
