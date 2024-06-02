package heejik.`65week`

import java.util.PriorityQueue
import kotlin.math.*

private class `캐슬 디펜스` {

    data class Pos(
        var x: Int,
        var y: Int
    )

    var n = 0
    var m = 0
    var d = 0
    var maxDeadEnemyCount = 0
    var archerPosition = listOf<Pos>()
    val enemyPosition = mutableListOf<Pos>()
    val localEnemyPosition = mutableListOf<Pos>()

    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = this[0]
            m = this[1]
            d = this[2]
        }
        repeat(n) { x ->
            readln().split(' ').map { it.toInt() }.forEachIndexed { y, i ->
                if (i == 1) {
                    enemyPosition.add(Pos(x = x, y = y))
                }
            }
        }
        setArcherPosition(startGame = ::startGame)

        println(maxDeadEnemyCount)
    }

    private fun setArcherPosition(
        startGame: () -> Unit,
        start: Int = 0,
        setArchers: List<Int> = emptyList()
    ) {
        if (setArchers.size == 3) {
            archerPosition = setArchers.map { Pos(n, it) }
            // 적의 위치 초기화
            for (enemy in enemyPosition) {
                localEnemyPosition.add(enemy)
            }
            while (isEndGame().not()) {
                startGame()
            }
            return
        }
        for (i in start until m) {
            setArcherPosition(
                startGame = startGame,
                start = i + 1,
                setArchers = setArchers + i
            )
        }
    }

    private fun startGame() {
        var deadEnemyCount = 0
        while (isEndGame().not()) {
            val targets = mutableSetOf<Pos>()
            archerPosition.forEach { pos ->
                targets.add(getTarget(archer = pos) ?: return@forEach)
            }
            targets.forEach { targetPos ->
                shootTarget(targetPos).also {
                    deadEnemyCount += 1
                }
            }
            advanceEnemies()
        }
        maxDeadEnemyCount = max(maxDeadEnemyCount, deadEnemyCount)
    }

    private fun isEndGame() = localEnemyPosition.isEmpty()


    private fun getTarget(archer: Pos): Pos? {
        val pq = PriorityQueue<Pair<Int, Pos>> { e1, e2 ->
            if (e1.first != e2.first) {
                e1.first - e2.first
            } else {
                e1.second.y - e2.second.y
            }
        }

        localEnemyPosition.forEach { pos ->
            val distance = abs(archer.x - pos.x) + abs(archer.y - pos.y)
            if (distance <= d) {
                pq.add(distance to pos)
            }
        }

        return pq.peek()?.second
    }

    private fun shootTarget(target: Pos): Boolean {
        return localEnemyPosition.remove(target)
    }

    private fun advanceEnemies() {
        for ((idx, pos) in localEnemyPosition.withIndex()) {
            localEnemyPosition[idx] = pos.copy(x = pos.x + 1)
        }

        localEnemyPosition.removeIf { it.x == n }
    }
}

fun main() {
    `캐슬 디펜스`().solve()
}