package heejik.`8week`

class `창고 다각형` {

    data class Pos(
        val x: Int,
        val y: Int
    )

    fun solve() {
        val n = readln().toInt()
        val pillars = mutableListOf<Pos>()
        var currentY = 0

        val requirePillars = mutableListOf<Pos>()

        repeat(n) {
            readln().split(' ').map { it.toInt() }.run {
                pillars.add(Pos(x = this.first(), y = this.last()))
            }
        }

        pillars.sortedBy { it.x }.run {
            forEachIndexed { index, pillar ->
                val afterPillarsWithSorted = this.subList(index,this.size).sortedByDescending { it.y }
                val firstTallPillar = afterPillarsWithSorted[0]
                if (pillar.y == firstTallPillar.y) { // 뒤에 기둥들 중에 최대 높이라면
                    requirePillars.add(pillar)
                    currentY = firstTallPillar.y
                    if (index != n -1) {
                        val secondTallPillar = afterPillarsWithSorted[1]
                        currentY = secondTallPillar.y
                        requirePillars.add(Pos(pillar.x+1, secondTallPillar.y))
                    }
                    return@forEachIndexed
                }
                if (pillar.y > currentY){
                    currentY = pillar.y
                    requirePillars.add(pillar)
                }
            }
        }

        println(getArea(requirePillars)+currentY)
    }

    private fun getArea(pillars: MutableList<Pos>): Int{
        var area = 0
        pillars.forEachIndexed { index, pillar ->
            if (index == pillars.size-1) return@forEachIndexed
            val nextPillar = pillars[index+1]
            area += (nextPillar.x - pillar.x) * pillar.y
        }
        return area
    }
}

fun main() {
    `창고 다각형`().solve()
}