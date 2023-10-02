package hyunsoo.`46week`

/**
 *
 * <문제>
 * [빌런 호석](https://www.acmicpc.net/problem/22251)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_빌런_호석` {

    private val nums = listOf(
        listOf(ON, ON, ON, ON, ON, ON, OFF),
        listOf(OFF, ON, ON, OFF, OFF, OFF, OFF),
        listOf(ON, ON, OFF, ON, ON, OFF, ON),
        listOf(ON, ON, ON, ON, OFF, OFF, ON),
        listOf(OFF, ON, ON, OFF, OFF, ON, ON),
        listOf(ON, OFF, ON, ON, OFF, ON, ON),
        listOf(ON, OFF, ON, ON, ON, ON, ON),
        listOf(ON, ON, ON, OFF, OFF, OFF, OFF),
        listOf(ON, ON, ON, ON, ON, ON, ON),
        listOf(ON, ON, ON, ON, OFF, ON, ON)
    )


    fun solution() {

        // n k p x
        val (maxFloor, digit, maxCanControl, stopFloor) = readln().split(" ").map { it.toInt() }

        var answer = 0

        for (currentFloor in 1..maxFloor) {
            if (currentFloor == stopFloor) continue

            var cnt = 0
            var from = stopFloor
            var to = currentFloor

            repeat(digit) {
                for (digitalNumIndex in 0 until 7) {
                    if (nums[from % 10][digitalNumIndex] != nums[to % 10][digitalNumIndex]) cnt++
                }
                from /= 10
                to /= 10
            }

            if (cnt <= maxCanControl) answer++

        }
        println(answer)
    }

    companion object {
        const val ON = 1
        const val OFF = 0
    }

}

fun main() {
    전현수_빌런_호석().solution()
}