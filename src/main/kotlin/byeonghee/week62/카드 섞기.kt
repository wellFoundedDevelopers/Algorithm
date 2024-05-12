package byeonghee.week62

class 소병희_카드섞기 {
    companion object {
        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val owner = IntArray(n) { it % 3 }
            var card = IntArray(n) { it }

            val cardGoal = IntArray(n)
            val shuffle = IntArray(n)

            readLine().split(" ").forEachIndexed { i, v ->
                cardGoal[i] = v.toInt()
            }

            readLine().split(" ").forEachIndexed { i, v ->
                shuffle[i] = v.toInt()
            }

            for(i in 0 .. 1_000_000) {
                var cnt = 0

                for(c in 0 until n) {
                    if (owner[c] == cardGoal[card[c]]) cnt++
                }

                if (cnt == n) {
                    println(i)
                    return@with
                }

                val newCard = IntArray(n)
                for(c in 0 until n) {
                    newCard[shuffle[c]] = card[c]
                }
                card = newCard
            }

            println(-1)
        }
    }
}

fun main() {
    소병희_카드섞기.solve()
}