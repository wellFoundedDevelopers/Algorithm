package byeonghee.`5week`

import java.io.*

/**
 * 1. Key: 자르는 길이, value: 만들 수 있는 랜선 개수 를 쌍으로 가지는 맵을 아예 만들었었다
 * 2. key가 1부터 2^31 - 1까지 갈 수 있기 때문에 이진탐색을 하면서 key에 대해 value를 계산하는 것이 빠름
 * 3. 2로 바꾸고 나니 key가 int일 필요 없어서 lb, ub, maxLength, mid를 전부 long타입으로 수정
 * 4. 마찬가지로 2로 바꾸고 나니 maxLength가 큰 수여도 괜찮아져서 그냥 제일 긴 랜선 값 할당
 */

class `소병희_랜선 자르기` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`,))

        var maxLength = 0L
        var lans = IntArray(0)
//      var searchMap = mapOf<Int, Int>()

        var lb = 1L
        var ub = 1L
        var mid = 1L

        fun solution() {
            val (k, n) = br.readLine().split(" ").map { it.toInt() }
            lans = IntArray(k) { br.readLine().toInt() }

            maxLength = lans.maxOf { it }.toLong()
            //maxLength = Integer.max(lans.minOf { it }, (lans.sumOf { it.toLong() } / n).toInt())
            //searchMap = (1..maxLength).toList().map { len -> len to lans.sumOf { it / len } }.toMap()

            println(bisearch(n))
        }

        fun bisearch(n: Int): Int {
            lb = 1
            ub = maxLength + 1

            while(lb + 1 < ub) {
                mid = lb + ((ub - lb) / 2)
                if(lans.sumOf { it / mid.toInt() } >= n) lb = mid
                else ub = mid
            }

            return lb.toInt()
        }
    }
}

fun main() {
    `소병희_랜선 자르기`.getSolution().solution()
}