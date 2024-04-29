package heejik.`60week`

class 뮤탈리스크 {

    data class Scves(
        val hp1: Int,
        var hp2: Int,
        var hp3: Int
    ) {
        fun isAllDie() = hp1 <= 0 && hp2 <= 0 && hp3 <= 0
        fun countLive(): Int {
            var count = if (hp1 > 0) 1 else 0
            if (hp2 > 0) count += 1
            if (hp3 > 0) count += 1
            return count
        }

        fun getLiveHP(): List<Int> {
            val tmp = mutableListOf<Int>()
            if (hp1 > 0) tmp.add(hp1)
            if (hp2 > 0) tmp.add(hp2)
            if (hp3 > 0) tmp.add(hp3)
            return tmp
        }
    }


    val cases = listOf(
        listOf(9, 3, 1),
        listOf(9, 1, 3),
        listOf(3, 9, 1),
        listOf(3, 1, 9),
        listOf(1, 3, 9),
        listOf(1, 9, 3)
    )

    fun solve() {
        val n = readln().toInt()
        val hps = readln().split(' ').map { it.toInt() }
        val scves = Scves(hp1 = hps.first(), hp2 = hps.getOrElse(1) { 0 }, hp3 = hps.getOrElse(2) { 0 })
        val queue = ArrayDeque<Pair<Scves, Int>>()
        queue.add(scves to 0)

        while (queue.isNotEmpty()) {
            val nowHps = queue.removeFirst()
            if (nowHps.first.isAllDie()) {
                println(nowHps.second)
                break
            }
            val liveCount = nowHps.first.countLive()

            if (liveCount == 1) {
                queue.add(
                    nowHps.first.copy(
                        hp1 = nowHps.first.hp1 - 9,
                        hp2 = nowHps.first.hp2 - 9,
                        hp3 = nowHps.first.hp3 - 9,
                    ) to nowHps.second + 1
                )
            } else if (liveCount == 2) {
                val (hp1, hp2) = nowHps.first.getLiveHP()
                for ((case1, case2) in listOf((9 to 3), (3 to 9))) {
                    queue.add(
                        nowHps.first.copy(
                            hp1 = hp1 - case1,
                            hp2 = hp2 - case2,
                            hp3 = 0
                        ) to nowHps.second + 1
                    )
                }
            } else {
                for (case in cases) {
                    queue.add(
                        nowHps.first.copy(
                            hp1 = nowHps.first.hp1 - case[0],
                            hp2 = nowHps.first.hp2 - case[1],
                            hp3 = nowHps.first.hp3 - case[2],
                        ) to nowHps.second + 1
                    )
                }
            }
        }
    }
}

fun main() {
    뮤탈리스크().solve()
}