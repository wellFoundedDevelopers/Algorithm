package heejik.`36week`

class `자유 이용권` {

    fun solve() {
        readln().toInt()
        val rides = readln().split(' ').map { it.toLong() }.sortedDescending()
        if (rides.first() <= rides.drop(1).sum() + 1) print(rides.sum())
        else println(rides.drop(1).sum() * 2 + 1)
    }
}

fun main() {
    `자유 이용권`().solve()
}