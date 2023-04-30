package hyunsoo.`33week`

import java.util.LinkedList
import java.util.Queue
import kotlin.concurrent.thread
import kotlin.system.exitProcess

/**
 *
 * <문제>
 * [이모티콘](https://www.acmicpc.net/problem/14226)
 *
 * - 아이디어
 *
 * 이모티콘이 1개 이미 입력된 상태
 * - 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
 * - 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
 * - 화면에 있는 이모티콘 중 하나 삭제
 *
 * - 트러블 슈팅
 * 범위 지정 이슈
 *
 */
class `전현수_이모티콘` {

    private data class StickerInfo(val windowCnt: Int, val clipboardCnt: Int)

    private val queue: Queue<StickerInfo> = LinkedList()

    private val visited = Array(2001) {
        IntArray(2001)
    }

    fun solution() {

        val s = readln().toInt()

        queue.add(StickerInfo(1, 0))
        visited[1][0] = 0

        while (queue.isNotEmpty()) {

            val (windowCnt, clipboardCnt) = queue.poll()
            val curTime = visited[windowCnt][clipboardCnt] + 1

            if (windowCnt == s) {
                println(visited[windowCnt][clipboardCnt])
                exitProcess(0)
            }

            // 이모티콘을 모두 복사해서 클립보드에 저장하기
            if (visited[windowCnt][windowCnt].isNotVisited) {
                visited[windowCnt][windowCnt] = curTime
                queue.add(StickerInfo(windowCnt, windowCnt))
            }

            // 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
            if (windowCnt + clipboardCnt < 2001 && visited[windowCnt + clipboardCnt][clipboardCnt].isNotVisited) {
                visited[windowCnt + clipboardCnt][clipboardCnt] = curTime
                queue.add(StickerInfo(windowCnt + clipboardCnt, clipboardCnt))
            }

            // 화면에 있는 이모티콘 중 하나 삭제
            if (1 < windowCnt && visited[windowCnt - 1][clipboardCnt].isNotVisited) {
                visited[windowCnt - 1][clipboardCnt] = curTime
                queue.add(StickerInfo(windowCnt - 1, clipboardCnt))
            }

        }

    }

    private val Int.isNotVisited: Boolean
        get() = this == 0
}

fun main() {
    전현수_이모티콘().solution()
}