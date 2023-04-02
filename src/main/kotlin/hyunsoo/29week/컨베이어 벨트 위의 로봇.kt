package hyunsoo.`29week`

import java.util.LinkedList
import java.util.Queue

/**
 *
 * <문제>
 * [컨베이어 벨트 위의 로봇](https://www.acmicpc.net/problem/20055)
 *
 * - 아이디어
 *
 * 1 ~ 2n 컨베이어 벨트이고, 1이 올리는 위치 n이 내리는 위치
 * -> 0 ~ 2n - 1 컨베이어 벨트이고, 0이 올리는 위치, n - 1이 내리는 위치
 *
 * - 로봇은 올리는 위치에만 올릴 수 있음.
 * - 로봇이 내리는 위치에 도달하면 즉시 내림
 * - 로봇은 컨베이어 벨트 위에서 스스로 이동할 수 있음.
 * - 로봇을 올리는 위치에 올리거나 특정 칸으로 이동하면 해당 칸의 내구도는 즉시 1만큼 감소
 *
 * 로봇을 옮기는 과정
 * - 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다
 * - 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동
 *   - 이동하려는 칸에 로봇이 없으며, 내구도가 1 이상 남아 있어야 이동 가능
 * - 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올림
 * 내구도가 0인 칸의 개수가 k개 이상이라면 과정을 종료
 *
 * - 트러블 슈팅
 *
 */
class `전현수_컨베이어_벨트_위의_로봇` {

    private var n = 0
    private var k = 0

    private lateinit var beltHealth: MutableList<Int>
    private lateinit var isRobotOnInfo: BooleanArray
    private var putPos = 0
    private var removePos = 0
    private val robotQueue: Queue<Int> = LinkedList()

    fun solution() {

        var step = 0

        readln().split(" ").map { it.toInt() }.apply {
            n = this.first()
            k = this.last()
        }
        removePos = n - 1

        beltHealth = readln().split(" ").map { it.toInt() } as MutableList
        isRobotOnInfo = BooleanArray(beltHealth.size)

        while (beltHealth.count { it == 0 } < k) {

            step++

            // 벨트 회전(로봇 한 칸 이동 + 올리는 위치, 내리는 위치 조정)
            putPos--
            if (putPos == -1) putPos = 2 * n - 1
            removePos--
            if (removePos == -1) removePos = 2 * n - 1

            // 로봇 이동 + 내리는 칸에 있으면 제거
            repeat(robotQueue.size) {

                val curPos = robotQueue.poll()
                isRobotOnInfo[curPos] = false

                if (curPos == removePos) return@repeat

                val nextPos = (curPos + 1).run {
                    if (beltHealth.size <= this) 0 else this
                }

                // 이동할 수 있다면
                if (beltHealth[nextPos] != 0 && isRobotOnInfo[nextPos].not()) {

                    beltHealth[nextPos]--

                    if (nextPos != removePos) {
                        isRobotOnInfo[nextPos] = true
                        robotQueue.add(nextPos)
                    }

                    // 이동할 수 없다면
                } else {
                    isRobotOnInfo[curPos] = true
                    robotQueue.add(curPos)
                }

            }

            // 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올림
            if (beltHealth[putPos] != 0 && isRobotOnInfo[putPos].not()) {
                isRobotOnInfo[putPos] = true
                beltHealth[putPos]--
                robotQueue.add(putPos)
            }

        }

        println(step)

    }


}

fun main() {
    전현수_컨베이어_벨트_위의_로봇().solution()
}