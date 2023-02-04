package byeonghee.`21week`

import java.io.*

class 소병희_부등호 {

    companion object {
        const val LARGE = 0
        const val SMALL = 1

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val k = readLine().toInt()
            val seq = readLine().split(" ")

            val base = "0123456789"
            val ans = Array(2) { StringBuilder() }
            val stack = Array(2) { CharArray(k + 1) }
            var largeTop = 0
            var smallTop = 0

            stack[LARGE][0] = '9'
            stack[SMALL][0] = '0'
            for(i in seq.indices) {
                if (seq[i] == "<") {
                    while(smallTop >= 0) {
                        ans[SMALL].append(stack[SMALL][smallTop--])
                    }
                }
                else {
                    while(largeTop >= 0) {
                        ans[LARGE].append(stack[LARGE][largeTop--])
                    }
                }
                stack[LARGE][++largeTop] = base[8 - i]
                stack[SMALL][++smallTop] = base[i + 1]
            }
            while(largeTop >= 0) {
                ans[LARGE].append(stack[LARGE][largeTop--])
            }

            while(smallTop >= 0) {
                ans[SMALL].append(stack[SMALL][smallTop--])
            }

            println(ans[LARGE])
            println(ans[SMALL])
        }
    }
}

fun main() {
    소병희_부등호.solve()
}