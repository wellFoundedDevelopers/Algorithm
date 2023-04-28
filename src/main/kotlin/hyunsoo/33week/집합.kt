package hyunsoo.`33week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 *
 * <문제>
 * [집합](https://www.acmicpc.net/problem/11723)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_집합` {

    private val br = BufferedReader(InputStreamReader(System.`in`))
    private val bw = BufferedWriter(OutputStreamWriter(System.out))
    private val set = BooleanArray(21)

    fun solution() {

        val cnt = br.readLine().toInt()

        repeat(cnt) {

            val command = br.readLine()
            when {
                command.startsWith("add") -> {
                    val num = command.split(" ")[1].toInt()
                    set[num] = YES
                }

                command.startsWith("remove") -> {
                    val num = command.split(" ")[1].toInt()
                    set[num] = NO
                }

                command.startsWith("check") -> {
                    val num = command.split(" ")[1].toInt()
                    bw.write("${if (set[num]) 1 else 0}\n")

                }

                command.startsWith("toggle") -> {
                    val num = command.split(" ")[1].toInt()
                    set[num] = set[num].not()
                }

                command.startsWith("all") -> {
                    set.fill(YES)
                }

                command.startsWith("empty") -> {
                    set.fill(NO)
                }
            }
        }

        bw.flush()
        bw.close()
    }

    companion object {
        const val YES = true
        const val NO = false

    }
}

fun main() {
    전현수_집합().solution()
}