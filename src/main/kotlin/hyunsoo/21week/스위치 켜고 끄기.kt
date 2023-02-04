package hyunsoo.`21week`

/**
 *
 * <문제>
 * [스위치 켜고 끄기](https://www.acmicpc.net/problem/1244)
 *
 * - 아이디어
 * 스위치 객체를 만들어서 시뮬레이션 해볼까~
 * 병희님 비트로 풀어오실 것 같은데 기대해보겠습니다 ㅎㅎ
 *
 * - 트러블 슈팅
 * ... 배수를 *= 2로 구해서 틀렸다...
 *
 */

class `전현수_스위치_켜고_끄기` {

    class Switch(private val size: Int) {

        private lateinit var status: IntArray

        fun controlSwitchByBoy(target: Int) {

            var targetIndex = target
            while (targetIndex <= size) {
                val switchNum = targetIndex - 1
                onAndOff(switchNum)
                targetIndex += target
            }
        }

        fun controlSwitchByGirl(target: Int) {
            val switchNum = target - 1
            var range = 1
            val palindromeRanges = mutableListOf<Int>()

            while (true) {

                val left = switchNum - range
                val right = switchNum + range

                if (left < 0 || size <= right) break

                // 좌우가 같은지
                if (status[left] == status[right]) {
                    palindromeRanges.add(range)
                    range++
                } else {
                    break
                }
            }

            onAndOff(switchNum)
            palindromeRanges.forEach { it ->
                onAndOff(switchNum - it)
                onAndOff(switchNum + it)
            }
        }

        fun setStatus(newStatus: List<Int>) {
            this.status = newStatus.toIntArray()
        }

        private fun onAndOff(targetIndex: Int) {
            if (status[targetIndex] == 1) status[targetIndex] = 0
            else status[targetIndex] = 1
        }

        fun showStatus() {
            var printedCnt = 0
            status.forEach {
                print("$it ")
                printedCnt++
                if (printedCnt == 20) {
                    printedCnt = 0
                    println()
                }
            }
        }
    }

    fun solution() {

        val switchSize = readln().toInt()
        val switchObject = Switch(switchSize)

        switchObject.setStatus(readln().split(" ").map { it.toInt() })

        val studentCnt = readln().toInt()

        repeat(studentCnt) {
            val (gender, target) = readln().split(" ").map { it.toInt() }

            if (gender == BOY) {
                switchObject.controlSwitchByBoy(target)
            } else {
                switchObject.controlSwitchByGirl(target)
            }
        }

        switchObject.showStatus()
    }

    companion object {
        private const val BOY = 1
        private const val GIRL = 2
    }
}

fun main() {
    전현수_스위치_켜고_끄기().solution()
}