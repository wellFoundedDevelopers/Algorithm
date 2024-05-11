package hyunsoo.`62week`

/**
 *
 * <문제>
 * [카드 섞기](https://www.acmicpc.net/problem/1091)
 *
 * - 아이디어
 *
 * - 트러블 슈팅
 *
 */
class `전현수_카드_섞기` {

    fun solution() {

        val n = readln().toInt()
        val init = readln().split(" ").map { it.toInt() }.toIntArray()
        val how = readln().split(" ").map { it.toInt() }
        var cnt = 0

        val cur = IntArray(n)

        init.forEachIndexed { index, num ->
            cur[index] = num
        }

        val temp = IntArray(n)

        while (true) {

            if (cnt != 0 && init.contentEquals(cur)) {
                println(-1)
                return
            }

            if (cur.isValid()) {
                println(cnt)
                return
            }

            how.forEachIndexed { index, pos ->
                temp[pos] = cur[index]
            }

            temp.forEachIndexed { index, num ->
                cur[index] = num
            }

            cnt++

        }
    }

    private fun IntArray.isValid(): Boolean {
        this.forEachIndexed { index, num ->
            if (index % 3 != num) return false
        }
        return true
    }
}

fun main() {
    전현수_카드_섞기().solution()
}