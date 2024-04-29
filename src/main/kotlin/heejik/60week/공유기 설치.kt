package heejik.`60week`

class `공유기 설치` {

    fun solve() {
        val (n, c) = readln().split(' ').map { it.toInt() }
        val homes = MutableList(size = n) { 0L }
        repeat(n) {
            homes[it] = readln().toLong()
        }
        homes.sort()

        fun canInstall(distance: Long): Boolean {
            var now = homes.first()
            var installCount = 1
            homes.drop(1).forEach { pos ->
                if (pos - now >= distance) {
                    installCount += 1
                    now = pos
                }
            }

            return installCount >= c
        }

        var start = 1L
        var end: Long = homes.last() - homes.first()

        while (start <= end) {
            val middle = (start + end) / 2
            if (canInstall(middle)) { // 설치가 가능하면 오른쪽을 본다.
                start = middle + 1
            } else {    // 설치가 안되면 왼쪽을 본다.
                end = middle - 1
            }
        }
        println(end)
    }
}

fun main() {
    `공유기 설치`().solve()
}