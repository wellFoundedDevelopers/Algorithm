package heejik.`8week`

class `색종이 만들기` {

    var white = 0
    var blue = 0


    fun solve() {

        val paper = mutableListOf<MutableList<Int>>()

        repeat(readln().toInt()) {
            paper.add(readln().split(' ').map { it.toInt() }.toMutableList())
        }
        div(paper)
        println(white)
        println(blue)

    }

    fun div(paper: List<MutableList<Int>>) {

        if (paper.sumOf { it.sum() } == paper.size * paper.size) {
            blue += 1
            return
        }
        if (paper.sumOf { it.sum() } == 0) {
            white += 1
            return
        }

        val leftTop = paper.map {
            it.subList(0, it.size / 2)
        }.subList(0, paper.size / 2)

        val rightTop = paper.map {
            it.subList((paper.size / 2), paper.size)
        }.subList(0, paper.size / 2)

        val leftBottom = paper.map {
            it.subList(0, it.size / 2)
        }.subList((paper.size / 2), paper.size)

        val rightBottom = paper.map {
            it.subList((paper.size / 2), paper.size)
        }.subList((paper.size / 2), paper.size)


        div(leftTop)
        div(rightTop)
        div(leftBottom)
        div(rightBottom)
    }
}


fun main() {
    `색종이 만들기`().solve()
}