package heejik.`18week`

class `부분수열의 합` {

    var cnt = 0
    var n = 0
    var s = 0
    lateinit var numArray: MutableList<Int>
    fun solve() {
        readln().split(' ').map { it.toInt() }.run {
            n = first()
            s = last()
        }
        numArray = readln().split(' ').map { it.toInt() }.toMutableList()

        rec(0, 0)
        println(cnt)
    }

    fun rec(idx: Int, _total: Int) {
        if (idx == n) return

        val total = _total + numArray[idx]

        if (total == s) cnt++

        rec(idx+1, total)
        rec(idx+1, total - numArray[idx])
    }
}


fun main() {
    `부분수열의 합`().solve()
}

