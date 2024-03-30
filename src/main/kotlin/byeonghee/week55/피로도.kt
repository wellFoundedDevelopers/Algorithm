package byeonghee.week55

class 소병희_피로도 {
    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        val n = dungeons.size
        val visited = BooleanArray(n)

        fun explore(d: Int, rest: Int): Int {
            var ret = d

            for(i in 0 until n) {
                if (visited[i]) continue
                if (rest >= dungeons[i][0]) {
                    visited[i] = true
                    ret = maxOf(ret, explore(d+1, rest-dungeons[i][1]))
                    visited[i] = false
                }
            }

            return ret
        }

        return explore(0, k)
    }
}