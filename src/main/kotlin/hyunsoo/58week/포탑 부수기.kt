package hyunsoo.`58week`

import java.util.*

/**
 *
 * <문제>
 * [포탑 부수기](https://www.codetree.ai/training-field/frequent-problems/problems/destroy-the-turret/description?page=1&pageSize=20)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_포탑_부수기` {

    private data class PositionData(val pos: Position, val cost: Int)
    private data class Position(val x: Int, val y: Int)

    private val fourDirs = listOf(
        Position(0, 1),
        Position(1, 0),
        Position(0, -1),
        Position(-1, 0),
    )

    private val eightDirs = listOf(
        Position(-1, 0),
        Position(-1, 1),
        Position(0, 1),
        Position(1, 1),
        Position(1, 0),
        Position(1, -1),
        Position(0, -1),
        Position(-1, -1),
    )

    private val board = mutableListOf<MutableList<Int>>()
    private lateinit var recentAttackTime: Array<IntArray>

    fun solution() {
        // 세로, 가로, 명령 횟수
        val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }

        repeat(n) {
            val row = readLine()!!.split(" ").map { it.toInt() } as MutableList
            board.add(row)
        }

        recentAttackTime = Array(n) {
            IntArray(m)
        }

        repeat(k) { commandIndex ->

            if (board.flatten().count { it != BROKEN } == 1) return@repeat

            // 공격자 선정
            val attacker = findAttackerOrTarget(true)
            recentAttackTime[attacker.x][attacker.y]++
            // 타겟 선정
            val target = findAttackerOrTarget(false)
            board[attacker.x][attacker.y] += (n + m)

            // 레이저 공격 확인용
            // 부서진 포탑 -1
            val laserInfo = board.map { row ->
                row.map {
                    if (it == 0) -1 else 0
                } as MutableList
            }.toMutableList()

            val queue: Queue<PositionData> = LinkedList<PositionData>().apply {
                this.add(PositionData(attacker, 1))
            }

            while (queue.isNotEmpty()) {

                val (curPos, curCost) = queue.poll()

                // 이미 방문 함
                if (laserInfo[curPos.x][curPos.y] != 0) continue
                laserInfo[curPos.x][curPos.y] = curCost

                fourDirs.forEach { dir ->
                    val nx = (curPos.x + dir.x).run {
                        if (board.size <= this) this - board.size
                        else if (this < 0) this + board.size
                        else this
                    }
                    val ny = (curPos.y + dir.y).run {
                        if (board.first().size <= this) this - board.first().size
                        else if (this < 0) this + board.first().size
                        else this
                    }

                    if (laserInfo[nx][ny] != 0) return@forEach

                    queue.add(PositionData(Position(nx, ny), curCost + 1))

                }
            }

            val attackedTurret = mutableSetOf<Position>()
            // 레이저가 닿지 않음 == 폭탄 공격
            if (laserInfo[target.x][target.y] == 0) {
                bombAttack(target, board[attacker.x][attacker.y]) {
                    attackedTurret.add(it)
                }
                // 레이저 공격
            } else {

                // 도착 확인용 플래그
                laserInfo[target.x][target.y] = -2

                val attackList = Stack<Position>()
                val visited = Array(n) {
                    BooleanArray(m)
                }
                visited[attacker.x][attacker.y] = true
                var pos = attacker
                run {
                    while (true) {
                        var nextPos = Position(0, 0)
                        var leastCost = Int.MAX_VALUE
                        fourDirs.forEach { dir ->
                            val nx = (pos.x + dir.x).run {
                                if (board.size <= this) this - board.size
                                else if (this < 0) this + board.size
                                else this
                            }
                            val ny = (pos.y + dir.y).run {
                                if (board.first().size <= this) this - board.first().size
                                else if (this < 0) this + board.first().size
                                else this
                            }

                            // 부서진 포탑은 -1
                            if (visited[nx][ny] ||
                                laserInfo[nx][ny] == BROKEN_WHEN_LASER_INFO
                            ) return@forEach

                            if (laserInfo[nx][ny] < leastCost) {
                                leastCost = laserInfo[nx][ny]
                                nextPos = Position(nx, ny)
                            }
                            visited[nextPos.x][nextPos.y] = true
                        }
                        if (laserInfo[nextPos.x][nextPos.y] == TARGET_WHEN_LASER_INFO) return@run
                        attackList.add(nextPos)
                        pos = nextPos

                    }
                }

                while (attackList.isNotEmpty()) {
                    val cur = attackList.pop()
                    board[cur.x][cur.y] -= board[attacker.x][attacker.y] / 2
                    attackedTurret.add(Position(cur.x, cur.y))
                }
                board[target.x][target.y] -= board[attacker.x][attacker.y]
                attackedTurret.add(Position(target.x, target.y))

            }

            // 포탑 부수기
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (board[i][j] < 0) board[i][j] = 0
                }
            }

            // 포탑 정비
            for (i in 0 until n) {
                for (j in 0 until m) {
                    if (board[i][j] == BROKEN) continue
                    // 없어도 됨
                    if (Position(i, j) == attacker) continue

                    if (attackedTurret.contains(Position(i, j)).not()) {
                        board[i][j]++
                    }
                }
            }

        }

        println(board.flatten().max())
    }

    private fun findAttackerOrTarget(isAttacker: Boolean): Position {

        val candidates: Stack<Position> = Stack()

        if (isAttacker) {

            var min = Int.MAX_VALUE

            for (i in 0 until board.size) {
                for (j in 0 until board.first().size) {

                    val cur = board[i][j]

                    if (cur == BROKEN) continue
                    if (cur < min) {
                        candidates.clear()
                        candidates.push(Position(i, j))
                        min = cur
                    } else if (cur == min) {
                        candidates.push(Position(i, j))
                    }
                }
            }
        } else {

            var max = -1

            for (i in 0 until board.size) {
                for (j in 0 until board.first().size) {

                    val cur = board[i][j]

                    if (cur == BROKEN) continue
                    if (max < cur) {
                        candidates.clear()
                        candidates.push(Position(i, j))
                        max = cur
                    } else if (cur == max) {
                        candidates.push(Position(i, j))
                    }
                }
            }

        }
        if (candidates.size == 1) return candidates.pop()
        else {

            // 공격자라면 공격한 지 가장 최근에 공격한 포탑을 구하기
            val secConCandidates: Stack<Position> = Stack()

            if (isAttacker) recentCandidates(candidates, secConCandidates)
            else oldCandidates(candidates, secConCandidates)
            if (secConCandidates.size == 1) return secConCandidates.pop()

            // 공격자라면 각 포탑 위치의 행과 열의 합이 가장 큰 포탑 구하기
            val thirdConCandidates: Stack<Position> = Stack()
            if (isAttacker) maxSumRowAndColCandidates(secConCandidates, thirdConCandidates)
            else minSumRowAndColCandidates(secConCandidates, thirdConCandidates)

            if (thirdConCandidates.size == 1) return thirdConCandidates.pop()

            return if (isAttacker) maxColCandidate(thirdConCandidates) else minColCandidate(thirdConCandidates)
        }

    }

    private fun recentCandidates(preCandidates: Stack<Position>, newCandidates: Stack<Position>): Stack<Position> {
        var recentAgo = -1
        while (preCandidates.isNotEmpty()) {
            val cur = preCandidates.pop()
            if (recentAgo < recentAttackTime[cur.x][cur.y]) {
                newCandidates.clear()
                newCandidates.push(cur)
                recentAgo = recentAttackTime[cur.x][cur.y]
            } else if (recentAgo == recentAttackTime[cur.x][cur.y]) {
                newCandidates.push(cur)
            }
        }
        return newCandidates
    }

    private fun oldCandidates(preCandidates: Stack<Position>, newCandidates: Stack<Position>): Stack<Position> {
        var longestAgo = Int.MAX_VALUE
        while (preCandidates.isNotEmpty()) {
            val cur = preCandidates.pop()
            if (recentAttackTime[cur.x][cur.y] < longestAgo) {
                newCandidates.clear()
                newCandidates.push(cur)
                longestAgo = recentAttackTime[cur.x][cur.y]
            } else if (recentAttackTime[cur.x][cur.y] == longestAgo) {
                newCandidates.push(cur)
            }
        }
        return newCandidates
    }

    private fun maxSumRowAndColCandidates(
        preCandidates: Stack<Position>,
        newCandidates: Stack<Position>,
    ): Stack<Position> {
        var maxSum = -1
        while (preCandidates.isNotEmpty()) {
            val cur = preCandidates.pop()
            if (maxSum < cur.x + cur.y) {
                newCandidates.clear()
                newCandidates.push(cur)
                maxSum = cur.x + cur.y
            } else if (maxSum == cur.x + cur.y) {
                newCandidates.push(cur)
            }
        }
        return newCandidates
    }

    private fun minSumRowAndColCandidates(
        preCandidates: Stack<Position>,
        newCandidates: Stack<Position>,
    ): Stack<Position> {
        var minSum = Int.MAX_VALUE
        while (preCandidates.isNotEmpty()) {
            val cur = preCandidates.pop()
            if (cur.x + cur.y < minSum) {
                newCandidates.clear()
                newCandidates.push(cur)
                minSum = cur.x + cur.y
            } else if (cur.x + cur.y == minSum) {
                newCandidates.push(cur)
            }
        }
        return newCandidates
    }

    private fun maxColCandidate(preCandidates: Stack<Position>): Position {
        var maxCol = -1
        var newCandidates = Position(0, 0)
        while (preCandidates.isNotEmpty()) {
            val cur = preCandidates.pop()
            if (maxCol < cur.y) {
                newCandidates = cur
                maxCol = cur.y
            }
        }
        return newCandidates
    }

    private fun minColCandidate(preCandidates: Stack<Position>): Position {
        var minCol = Int.MAX_VALUE
        var newCandidates = Position(0, 0)
        while (preCandidates.isNotEmpty()) {
            val cur = preCandidates.pop()
            if (cur.y < minCol) {
                newCandidates = cur
                minCol = cur.y
            }
        }
        return newCandidates
    }

    private fun bombAttack(
        target: Position, power: Int,
        onAttacked: (Position) -> Unit,
    ) {
        board[target.x][target.y] -= power
        onAttacked(Position(target.x, target.y))
        eightDirs.forEach { dir ->
            val nx = (target.x + dir.x).run {
                if (board.size <= this) this - board.size
                else if (this < 0) this + board.size
                else this
            }
            val ny = (target.y + dir.y).run {
                if (board.first().size <= this) this - board.first().size
                else if (this < 0) this + board.first().size
                else this
            }

            if (board[nx][ny] == BROKEN) return@forEach

            board[nx][ny] -= power / 2
            onAttacked(Position(nx, ny))
        }
    }

    companion object {
        const val BROKEN = 0
        const val BROKEN_WHEN_LASER_INFO = -1
        const val TARGET_WHEN_LASER_INFO = -2
    }
}

fun main() {
    전현수_포탑_부수기().solution()
}