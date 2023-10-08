package heejik.`47week`

class 입국심사 {
    fun solution(n: Int, times: IntArray): Long {
        var minTime : Long = 1L
        var maxTime : Long = times.maxOrNull()!!.toLong() * n

        while (minTime <= maxTime) {
            val midTime = (minTime + maxTime) / 2

            var count = 0L
            times.forEach { time -> count += midTime / time }

            if (count >= n) {
                maxTime = midTime - 1
            } else {
                minTime = midTime + 1
            }
        }
        return minTime
    }
}