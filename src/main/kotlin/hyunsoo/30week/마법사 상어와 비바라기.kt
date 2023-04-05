package hyunsoo.`30week`

/**
 *
 * <문제>
 * [마법사 상어와 비바라기](https://www.acmicpc.net/problem/21610)
 *
 * - 아이디어
 *
 * 구름 이동 로직
 * - 모든 구름이 d[i] 방향으로 s[i] 칸 이동
 * - 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
 * - 구름이 모두 사라짐
 * - 물이 증가한 칸(r,c)에 물복사버그 마법 시전
 *   - 대각선 방향으로 거리가 1인ㅇ 칸에 물이 있는 바구니의 수만큼 (r,c)에 있는 바구니의 물의 양이 증가
 *   - 이 때는 경계를 넘어가는 칸은 처리하지 않음.
 * - 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고 물의 양이 2 줄어들게 됨. 단, 이전에 구름이 사라진 위치가 아니어야 함
 *
 * - 구름 이동 후 해당 위치 물의 양 증가
 * - 구름 소멸 시키면서 구름의 좌표( == 물이 증가한 칸) 저장
 * - 물이 증가한 칸에 물복사 버그
 *   - 대각선 방향에 물이 있는 바구니의 수 만큼 바구니 물의 양이 증가
 *   - 범위 초과하면 판정 X
 * - 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고 물의 양이 2 줄어들게 됨
 *   - 물이 증가한 칸은 해당 안됨
 *
 * - 트러블 슈팅
 *
 */
class `전현수_마법사_상어와_비바라기` {

    private data class Command(val dir: Int, val cnt: Int)
    private data class Position(val x: Int, val y: Int)

    private var bucketSize = 0
    private var commandCnt = 0

    private val dirs = listOf(
        Position(0, -1),
        Position(-1, -1),
        Position(-1, 0),
        Position(-1, 1),
        Position(0, 1),
        Position(1, 1),
        Position(1, 0),
        Position(1, -1),
    )

    private val bucketData = mutableListOf<MutableList<Int>>()
    private val commandList = mutableListOf<Command>()
    private val cloudPositionList = mutableListOf<Position>()

    fun solution() {

        readln().split(" ").map { it.toInt() }.apply {
            bucketSize = this.first()
            commandCnt = this.last()
        }

        repeat(bucketSize) {
            val rowData = readln().split(" ").map { it.toInt() } as MutableList
            bucketData.add(rowData)
        }

        repeat(commandCnt) {
            val command = readln().split(" ").map { it.toInt() }.run {
                Command(this.first() - 1, this.last())
            }
            commandList.add(command)
        }

        // 초기 구름 설정
        cloudPositionList.add(Position(bucketSize - 1, 0))
        cloudPositionList.add(Position(bucketSize - 1, 1))
        cloudPositionList.add(Position(bucketSize - 2, 0))
        cloudPositionList.add(Position(bucketSize - 2, 1))

        commandList.forEach { curCommand ->

            moveCloud(curCommand)

            rain()

            duplicateWater()

            makeCloud()

        }

        println(
            bucketData.sumOf { rowData ->
                rowData.sumOf { it }
            }
        )

    }

    private fun moveCloud(command: Command) {

        val dir = dirs[command.dir]

        repeat(command.cnt) {

            for (i in 0 until cloudPositionList.size) {

                val curPos = cloudPositionList[i]

                val nx = (curPos.x + dir.x).run {
                    if (this < 0) {
                        bucketSize - 1
                    } else if (bucketSize <= this) {
                        0
                    } else this
                }
                val ny = (curPos.y + dir.y).run {
                    if (this < 0) {
                        bucketSize - 1
                    } else if (bucketSize <= this) {
                        0
                    } else this
                }

                cloudPositionList[i] = Position(nx, ny)
            }

        }
    }

    private fun rain() {
        cloudPositionList.forEach { cloudPos ->
            bucketData[cloudPos.x][cloudPos.y]++
        }
    }

    private fun duplicateWater() {
        cloudPositionList.forEach { cloudPos ->
            bucketData[cloudPos.x][cloudPos.y] += countWaterBucket(cloudPos)
        }
    }

    private fun makeCloud() {

        // 이전 구름 리스트
        val preCloudPositionList = cloudPositionList.toMutableList()

        // 이전 구름 없애기
        cloudPositionList.clear()

        for (i in 0 until bucketSize) {
            for (j in 0 until bucketSize) {

                if (2 <= bucketData[i][j]) {

                    val curPosition = Position(i, j)
                    if (curPosition !in preCloudPositionList) {
                        bucketData[i][j] -= 2
                        cloudPositionList.add(curPosition)
                    }
                }

            }
        }
    }

    private fun countWaterBucket(targetPos: Position): Int {
        var cnt = 0

        // 홀수 인덱스는 대각선
        dirs
            .filterIndexed { index, _ ->
                index % 2 != 0
            }.forEach { dir ->

                val nx = targetPos.x + dir.x
                val ny = targetPos.y + dir.y

                if (nx !in 0 until bucketSize || ny !in 0 until bucketSize) return@forEach

                if (0 < bucketData[nx][ny]) cnt++
            }

        return cnt
    }
}

fun main() {
    전현수_마법사_상어와_비바라기().solution()
}