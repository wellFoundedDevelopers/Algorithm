package byeonghee.`10week`

import java.io.*
import kotlin.math.max
import kotlin.math.min

class `소병희_햄버거 분배` {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var answer = 0

        fun solve() {
            val (n, k) = br.readLine().split(" ").map{ it.toInt() }
            val line = br.readLine().toMutableList()

            for(p in line.indices) {
                if (line[p] == 'P') {
                    h@ for(h in max(p - k, 0)..min(line.lastIndex, p + k)) {
                        if (line[h] == 'H') {
                            line[h] = 'X'
                            answer++
                            break@h
                        }
                    }
                }
            }

            println(answer)
        }
    }
}

fun main() {
    `소병희_햄버거 분배`.solve()
}