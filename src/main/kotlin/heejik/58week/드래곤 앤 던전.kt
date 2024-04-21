package heejik.`58week`

import kotlin.math.min

class `드래곤 앤 던전` {

    fun solve() {
        var hp = 0L
        var answer = hp

        var (n, atk) = readln().split(' ').map { it.toLong() }
        repeat(n.toInt()) {
            val (t, a, h) = readln().split(' ').map { it.toLong() }
            if (t.toInt() == 1) {
                val damagedCnt = if (h % atk != 0L) (h / atk) else ((h / atk) - 1)
                hp -= (damagedCnt * a)
                answer = min(answer, hp)
            } else {
                atk += a
                hp = min(0, hp + h)
            }
        }
        print(-answer + 1)
    }
}


fun main() {
    `드래곤 앤 던전`().solve()
}