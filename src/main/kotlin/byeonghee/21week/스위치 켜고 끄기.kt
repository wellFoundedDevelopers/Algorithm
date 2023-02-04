package byeonghee.`21week`

import java.io.*

class 소병희_스위치켜고끄기 {

    companion object {
        const val ON = 1
        const val BOY = 1

        fun BooleanArray.flip(i: Int) {
            this[i] = this[i].not()
        }

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val n = readLine().toInt()
            val switchOn = BooleanArray(n + 1)
            readLine().split(" ").forEachIndexed { i, v -> switchOn[i + 1] = v.toInt() == ON }
            val students = readLine().toInt()

            for(i in 0 until students) {
                val (stu, num) = readLine().split(" ").map { it.toInt() }
                if (stu == BOY) {
                    for (i in num .. n step num) {
                        switchOn.flip(i)
                    }
                }
                else {
                    switchOn[num] = switchOn[num].not()
                    for (offset in 1 .. (n - num).coerceAtMost(num - 1)) {
                        if (switchOn[num - offset] != switchOn[num + offset]) break
                        switchOn.flip(num - offset)
                        switchOn.flip(num + offset)
                    }
                }
            }

            for(line in switchOn.drop(1).chunked(20)) {
                println(line.joinToString(" ") { if (it) "1" else "0"})
            }
        }
    }
}

fun main() {
    소병희_스위치켜고끄기.solve()
}