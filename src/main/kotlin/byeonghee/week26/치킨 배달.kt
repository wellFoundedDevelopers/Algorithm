package byeonghee.week26

import java.io.StreamTokenizer
import kotlin.math.abs

class 소병희_치킨배달 {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {

            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val m = input()
            val house = mutableListOf<Pair<Int, Int>>()
            val franchise = mutableListOf<Pair<Int, Int>>()
            var cityDist = Integer.MAX_VALUE

            for(r in 0 until n) for(c in 0 until n) {
                when(input()) {
                    1 -> house.add(Pair(r, c))
                    2 -> franchise.add(Pair(r, c))
                }
            }

            val houseCnt = house.size
            val franchiseCnt = franchise.size
            val distMap = Array(houseCnt) { h -> IntArray(franchiseCnt) { f -> house[h].calcDist(franchise[f]) } }

            fun checkCityDist(chickenDist: IntArray) {
                var ret = 0
                for(h in 0 until houseCnt) {
                    ret += chickenDist[h]
                }
                cityDist = cityDist.coerceAtMost(ret)
            }

            fun calcNewDist(f: Int, oldDist: IntArray) : IntArray {
                return IntArray(houseCnt) { h -> oldDist[h].coerceAtMost(distMap[h][f]) }
            }

            fun makeM(f: Int, i: Int, chickenDist: IntArray) {
                if (f == franchiseCnt && i < m) return
                if (i == m) {
                    checkCityDist(chickenDist)
                    return
                }

                makeM(f + 1, i + 1, calcNewDist(f, chickenDist))
                if(franchiseCnt - f >= m - i) makeM(f + 1, i, chickenDist)
            }

            makeM(0, 0, IntArray(houseCnt) { n * n })
            print(cityDist)
        }

        fun Pair<Int, Int>.calcDist(p: Pair<Int, Int>) : Int {
            return abs(first - p.first) + abs(second - p.second)
        }
    }
}

fun main() {
    소병희_치킨배달.solve()
}