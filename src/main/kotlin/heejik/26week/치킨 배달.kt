    package heejik.`26week`

    import kotlin.math.absoluteValue
    import kotlin.math.min
    import kotlin.properties.Delegates

    class `치킨 배달` {

        data class Pos(
            val x: Int,
            val y: Int
        ) {
            operator fun minus(other: Pos) = (x - other.x).absoluteValue + (y - other.y).absoluteValue

        }

        var n by Delegates.notNull<Int>()
        var m by Delegates.notNull<Int>()

        var answer = Int.MAX_VALUE
        val city = mutableListOf<MutableList<Int>>()
        val chickenPoses = mutableListOf<Pos>()
        val homePoses = mutableListOf<Pos>()

        fun solve() {
            setting()
            pickChickenStores(start = 0)

            println(answer)
        }

        private fun setting() {
            readln().split(' ').map { it.toInt() }.run {
                n = this[0]
                m = this[1]
            }

            repeat(n) {
                city.add(readln().split(' ').map { it.toInt() }.toMutableList())
            }

            city.forEachIndexed { x, row ->
                row.forEachIndexed { y, i ->
                    if (i == 2) {
                        chickenPoses.add(Pos(x, y))
                    }
                    if (i == 1) {
                        homePoses.add(Pos(x, y))
                    }
                }
            }
        }

        private fun pickChickenStores(stores: List<Pos> = listOf(), start: Int) {
            if (stores.size == m) {
                answer = min(answer, getChickenDistance(stores))
                return
            }
            for (i in start until chickenPoses.size) {
                pickChickenStores(stores.plus(chickenPoses[i]), start = i + 1)
            }
        }

        private fun getChickenDistance(stores: List<Pos>): Int {
            var sumOfDistance = 0
            homePoses.forEach { homePos ->
                var minOfDistanceByHome = Int.MAX_VALUE
                stores.forEach { chickenPos ->
                    minOfDistanceByHome = min(minOfDistanceByHome, homePos - chickenPos)
                }
                sumOfDistance += minOfDistanceByHome
            }

            return sumOfDistance
        }
    }

    fun main() {
        `치킨 배달`().solve()
    }