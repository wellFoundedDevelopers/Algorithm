package heejik.`62week`

class `카드 섞기` {

    var n = 0
    lateinit var p: MutableList<Int>
    lateinit var s: MutableList<Int>
    lateinit var shuffled: MutableList<Int>
    val cardPosition = mutableListOf<Int>()
    val firstPosition = mutableListOf<Int>()
    var count = 0

    fun solve() {
        n = readln().toInt()
        p = readln().split(' ').map { it.toInt() }.toMutableList()
        s = readln().split(' ').map { it.toInt() }.toMutableList()
        shuffled = MutableList(size = n) { -1 }
        repeat(n) {
            cardPosition.add(it)
            firstPosition.add(it)
        }
        
        do {
            if (check()) {
                println(count)
                return
            }
            shuffle().also {
                count += 1
            }
        } while (cardPosition.hashCode() != firstPosition.hashCode())

        println(-1)
    }

    private fun shuffle() {
        s.forEachIndexed { index, i ->
            shuffled[i] = cardPosition[index]
        }
        for (i in 0 until n) {
            cardPosition[i] = shuffled[i]
        }
    }

    private fun check(): Boolean {
        cardPosition.forEachIndexed { index, i ->
            if (p[i] != index % 3) return false
        }
        return true
    }
}


fun main() {
    `카드 섞기`().solve()

    // 3
    // 2 0 1
    // 1 2 0
}

