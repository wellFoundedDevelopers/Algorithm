package byeonghee.week46

val number = intArrayOf(
    "1110111".toInt(2),
    "0010010".toInt(2),
    "1011101".toInt(2),
    "1011011".toInt(2),
    "0111010".toInt(2),
    "1101011".toInt(2),
    "1101111".toInt(2),
    "1010010".toInt(2),
    "1111111".toInt(2),
    "1111011".toInt(2)
)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k, p, x) = readLine().split(" ").map { it.toInt() }
    val cases = Array(p) { IntArray(10) }
    val digit = x.toString().length
    val q = ArrayDeque<IntArray>()

    for(i in 0 .. 9) {
        for(j in 0 .. 9) {
            if (i == j) continue
            val change = countOnes(i.xor(j))
            if (change > p) continue
            cases[change][i]++
        }
    }

    // 조합...하기
}

fun countOnes(n: Int) : Int {
    var ans = 0
    var offset = 1
    while(offset <= n) {
        if (n and offset == 1) ans++
        offset *= 2
    }
    return ans
}