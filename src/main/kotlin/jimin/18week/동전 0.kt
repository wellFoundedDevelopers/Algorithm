package jimin.`18week`

fun main() {
    var (n, k) = readln().split(" ").map{ it.toInt() }
    val coinTypes = mutableListOf<Int>()
    repeat(n) {
        coinTypes.add(readln().toInt())
    }
    coinTypes.reverse()
    var idx = 0
    var result = 0
    while (k > 0) {
        if (coinTypes[idx] <= k) {
            result +=  k / coinTypes[idx]
            k %= coinTypes[idx]
        }
        idx += 1
    }
    println(result)
}