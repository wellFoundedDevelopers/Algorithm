package heejik.`7week`

class `흙길 보수하기` {

    fun solve() {
        val (n, l) = readln().split(' ').map { it.toInt() }

        var start = -1
        var end = -1
        var cnt = 0

        val pool = mutableListOf<Pair<Int, Int>>()

        repeat(n) {
            pool.add(readln().split(' ').map { it.toInt() }.run {
                Pair(this.first(), this.last() - 1)
            })
        }
        pool.sortedWith(comparator = compareBy( {it.first}, {it.second} )).forEach {
            if (start == -1 || it.first > end) {
                start = it.first        // 1
                end = it.second         // 1
            } else if (it.second < end){
                return@forEach
            } else {
                start = end     // 4
                end = it.second // 5
            }
            val r = ((end - start+1) % l)   // 나머지
            val q = ((end - start+1) / l)   // 몫
            val _cnt =  if (r == 0) { q } else{ q+1 } // 1
            cnt += _cnt
            end = (_cnt * l) + start //4

        }
        println(cnt)
    }
}


fun main() {
    `흙길 보수하기`().solve()
}