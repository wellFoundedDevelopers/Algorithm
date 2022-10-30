package byeonghee.`7week`

import java.io.*

class `소병희_미로 만들기` {

    companion object {
        const val R = 1
        const val L = -1
    }

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.`out`))

    fun Int.turn(dir: Int) : Int {
        return (this + 4 + dir) % 4
    }

    fun Char.dir() : Int {
        return if (this == 'R') R else L
    }

    data class Pos(var r: Int, var c: Int) {
        operator fun plus(pos: Pos) : Pos {
            return Pos(r + pos.r, c + pos.c)
        }
    }

    val directions = listOf(
        Pos(1, 0),
        Pos(0, -1),
        Pos(-1, 0),
        Pos(0, 1)
    )

    var minPos = Pos(50, 50)
    var maxPos = Pos(50, 50)
    var curDir = 0
    var curPos = Pos(50, 50)
    val maze = Array(101) { CharArray(101){'#'} }

    fun solution() {
        val n = br.readLine().toInt()
        br.readLine().toCharArray().forEach {
            when(it) {
                'F' -> {
                    curPos += directions[curDir]
                    with(curPos) {
                        maze[r][c] = '.'

                        maxPos = Pos(Integer.max(maxPos.r, r), Integer.max(maxPos.c, c))
                        minPos = Pos(Integer.min(minPos.r, r), Integer.min(minPos.c, c))
                    }
                }
                else -> curDir = curDir.turn(it.dir())
            }
        }
        maze[50][50] = '.'

        for(r in minPos.r..maxPos.r) {
            for(c in minPos.c..maxPos.c) {
                bw.append(maze[r][c])
            }
            bw.newLine()
        }
        bw.flush()
    }
}

fun main() {
    `소병희_미로 만들기`().solution()
}

