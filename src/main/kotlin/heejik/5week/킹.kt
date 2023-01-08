package heejik.`5week`


val y = listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H')
val move = listOf("R", "L", "B", "T", "RT", "LT", "RB", "LB")
val dx = listOf(0, 0, -1, 1, 1, 1, -1, -1)
val dy = listOf(1, -1, 0, 0, 1, -1, 1, -1)

fun main() {

    var (king, rock, n) = readln().split(' ')

    repeat(n.toInt()) {
        val location = move.indexOf(readln())

        var kingX = (king[1].digitToInt()) - 1
        var kingY = y.indexOf(king[0])

        var rockX = (rock[1].digitToInt()) - 1
        var rockY = y.indexOf(rock[0])

        val kingNx = kingX + dx[location]
        val kingNy = kingY + dy[location]


        if ((kingNx in 0..7 && kingNy in 0..7).not()) return@repeat

        if (kingNx == rockX && kingNy == rockY) {
            val rockNx = rockX + dx[location]
            val rockNy = rockY + dy[location]
            if ((rockNx in 0..7 && rockNy in 0..7).not()) return@repeat
            rockX = rockNx
            rockY = rockNy
        }
        kingX = kingNx
        kingY = kingNy

        king = (y[kingY].toString()) + (kingX + 1).toString()
        rock = (y[rockY].toString()) + (rockX + 1).toString()

    }
    println(king)
    println(rock)
}