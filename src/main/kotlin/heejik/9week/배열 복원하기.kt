package heejik.`9week`

class `배열 복원하기` {

    fun solve() {

        val (h, w, x, y) = readln().split(' ').map { it.toInt() }

        val a = MutableList(h) { MutableList(w) { 0 } }
        val b = mutableListOf<MutableList<Int>>()

        repeat(h + x) {
            b.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }

        for (i in 0 until h) {
            for (j in 0 until w) {
                if (i >= x && j >= y) {
                    a[i][j] = b[i][j] - a[i-x][j-y]
                }
                else {
                    a[i][j] = b[i][j]
                }
            }
        }
        a.forEach { row ->
            row.forEach { value ->
                print("$value ")
            }
            println()
        }
    }
}


fun main() {
    `배열 복원하기`().solve()
}