package byeonghee.`7week`

import java.io.*

class `소병희_최소 힙` {

    var n = 0
    val br = BufferedReader(InputStreamReader(System.`in`))
    lateinit var minHeap: IntArray
    var count = 0

    fun solution() {
        n = br.readLine().toInt()
        minHeap = IntArray(n + 1) { Int.MAX_VALUE }

        repeat(n) {
            br.readLine().toInt().let {
                when (it) {
                    0 -> println(topDown())
                    else -> bottomUp(it)
                }
            }
        }
    }

    fun bottomUp(x: Int) {
        var p = count + 1
        minHeap[p] = x

        while(p > 1) {
            if (minHeap[p] < minHeap[p/2]) {
                minHeap[p/2] = minHeap[p].also{ minHeap[p] = minHeap[p/2]}
            }
            p /= 2
        }
        if (count == 0) minHeap[1] = x
        count++
    }

    fun topDown(): Int {
        if (count == 0) return 0
        var ans = minHeap[1]
        minHeap[1] = minHeap[count].also{ minHeap[count--] = Int.MAX_VALUE }

        var p = 1
        while (minHeap[p*2] < minHeap[p] || minHeap[p*2 + 1] < minHeap[p] ) {
            if (minHeap[p*2] <= minHeap[p*2 + 1]) {
                minHeap[p] = minHeap[p*2].also { minHeap[p*2] = minHeap[p] }
                p *= 2
            }
            else {
                minHeap[p] = minHeap[p*2 + 1].also { minHeap[p*2 + 1] = minHeap[p] }
                p = p * 2 + 1
            }
        }
        return ans
    }
}

fun main() {
    `소병희_최소 힙`().solution()
}