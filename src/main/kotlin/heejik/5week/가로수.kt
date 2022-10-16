package heejik.`5week`

fun main() {

    val n = readln().toInt()
    val tree = mutableListOf<Int>()
    val diff = mutableListOf<Int>()
    var gcd = 1

    repeat(n) {
        val location = readln().toInt()
        if (tree.isNotEmpty()) {
            diff.add(location - tree.last())
        }
        tree.add(location)
    }

    gcd = getGcd(diff[0], diff[1])
    for (i in 2 until diff.size) {
        gcd = getGcd(gcd,diff[i])
    }

    println(((tree.last() - tree.first()) / gcd) + 1 - tree.size)

}

fun getGcd(_a: Int, _b: Int): Int {
    var a = _a
    var b = _b
    while (b > 0) {
        val tmp = a
        a = b
        b = tmp % b
    }
    return a
}