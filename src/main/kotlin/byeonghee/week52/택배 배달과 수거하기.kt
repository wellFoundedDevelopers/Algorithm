package byeonghee.week52

class 소병희_택배배달과수거하기 {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var ans = 0L

        var delPos = n - 1
        var picPos = n - 1
        var delFar = -1L
        var picFar = -1L
        var delCap = 0
        var picCap = 0

        while(delPos >= 0 || picPos >= 0) {
            while(delCap > 0 && delPos >= 0) {
                delCap -= deliveries[delPos].coerceAtMost(delCap).also { deliveries[delPos] -= it }
                if (deliveries[delPos] == 0) delPos--
            }

            while(picCap > 0 && picPos >= 0) {
                picCap -= pickups[picPos].coerceAtMost(picCap).also { pickups[picPos] -= it }
                if (pickups[picPos] == 0) picPos--
            }

            ans += maxOf(delFar, picFar) + 1

            while(delPos >= 0 && deliveries[delPos] == 0) delPos--
            while(picPos >= 0 && pickups[picPos] == 0) picPos--

            delFar = 0L + delPos
            picFar = 0L + picPos
            delCap = cap
            picCap = cap
        }

        return ans * 2
    }
}