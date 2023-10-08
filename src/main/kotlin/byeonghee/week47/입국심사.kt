package byeonghee.week47

class 소병희_입국심사 {
    fun solution(n: Int, times: IntArray): Long {
        times.sort()

        var lb = 0L
        var ub = times.last() * n.toLong()
        var mid = 0L

        while(lb + 1 < ub) {
            mid = lb + (ub - lb) / 2
            val cost = times.sumOf { mid / it }
            if (cost < n)  lb = mid
            else ub = mid
        }

        return ub
    }
}