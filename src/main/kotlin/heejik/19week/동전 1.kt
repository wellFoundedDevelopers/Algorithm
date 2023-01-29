package heejik.`19week`

class `동전 1` {



    fun solve() {
        val (n, k) = readln().split(' ').map { it.toInt() }
        val store = MutableList(k+1) { 0 }
        val coins = mutableListOf<Int>()
        repeat(n) {
            val coin = readln().toInt()
            coins.add(coin)
        }
        coins.forEach { coin ->
            if (coin > k) return@forEach
            store[coin] += 1
            for (i in 1..k) {
                if (i - coin < 0) continue
                store[i] = store[i] + store[i - coin]
            }
        }
        println(store[k])
    }
}

fun main() {
    `동전 1`().solve()
}