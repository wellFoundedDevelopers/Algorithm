package hyunsoo.`27week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 *
 * <문제>
 * [문자열 폭발](https://www.acmicpc.net/problem/9935)
 *
 * - 아이디어
 *
 * 폭발 과정
 * - 문자열이 폭발 문자열을 포함하고 있다면 모든 폭발 문자열이 폭발
 *   - 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 생성
 * - 새로 생긴 문자열에 폭발 문자열이 있을 수도 있음.
 * - 폭발 문자열이 아예 없을 때 까지 계속 폭발
 *
 * - 트러블 슈팅
 *
 */
class `전현수_문자열_폭발` {

    fun solution() {

        val br = BufferedReader(InputStreamReader(System.`in`))
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        val target = br.readLine()
        val boom = br.readLine()
        val sb = StringBuilder()

        target.forEach {
            sb.append(it)
            if (boom.length <= sb.length) {
                val lastString = sb.substring(sb.length - boom.length, sb.length)
                if (lastString == boom) sb.delete(sb.length - boom.length, sb.length)
            }
        }

        sb.toString().run {
            bw.write(this.ifEmpty { "FRULA" })
        }

        bw.flush()
        bw.close()
    }
}

fun main() {
    전현수_문자열_폭발().solution()
}