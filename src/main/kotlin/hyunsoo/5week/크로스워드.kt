package hyunsoo.`5week`

import java.util.*

/**
 * <문제>
 * [크로스워드](https://www.acmicpc.net/problem/1706)
 *
 * 깊이우선 탐색 혹은 너비우선 탐색을 이용해서 모든 낱말 확인하기
 * - 단어 판정
 *   - 길이가 1이상인 것
 *   - 시작 지점 혹은 #이면 탐색 시작
 */

val myQueue: Queue<Int> = ArrayDeque()
fun main() {

    val puzzleData = mutableListOf<MutableList<Char>>()
    val madeWordList = mutableListOf<String>()

    val (r, c) = readln().split(" ").map { it.toInt() }

    repeat(r) {
        val puzzleRow = readln().chunked(1).map { it.first() }.toMutableList()
        puzzleData.add(puzzleRow)
    }

    val duplicatedPuzzle = puzzleData.deepCopy()

    for (i in 0 until r) {
        for (j in 0 until c) {

            makeWordVertical(i, j, duplicatedPuzzle)?.let {
                if (it.length > 1) madeWordList.add(it)
            }
            makeWordHorizontal(i, j, puzzleData)?.let {
                if (it.length > 1) madeWordList.add(it)
            }

        }
    }

    println(madeWordList.minOrNull())

}

fun makeWordHorizontal(x: Int, y: Int, puzzleData: MutableList<MutableList<Char>>): String? {

    if (puzzleData[x][y] == '#') return null

    val wordBuilder = StringBuilder()
    wordBuilder.append(puzzleData[x][y])

    puzzleData[x][y] = '#'
    myQueue.add(y)

    while (myQueue.isNotEmpty()) {
        val ny = myQueue.poll() + 1

        if (ny >= puzzleData.first().size ||
            puzzleData[x][ny] == '#'
        ) return wordBuilder.toString()

        wordBuilder.append(puzzleData[x][ny])
        puzzleData[x][ny] = '#'
        myQueue.add(ny)
    }

    return wordBuilder.toString()
}

fun makeWordVertical(x: Int, y: Int, puzzleData: MutableList<MutableList<Char>>): String? {

    if (puzzleData[x][y] == '#') return null

    val wordBuilder = StringBuilder()
    wordBuilder.append(puzzleData[x][y])

    puzzleData[x][y] = '#'
    myQueue.add(x)

    while (myQueue.isNotEmpty()) {
        val nx = myQueue.poll() + 1

        if (nx >= puzzleData.size ||
            puzzleData[nx][y] == '#'
        ) return wordBuilder.toString()

        wordBuilder.append(puzzleData[nx][y])
        puzzleData[nx][y] = '#'
        myQueue.add(nx)
    }

    return wordBuilder.toString()
}

fun <T> MutableList<MutableList<T>>.deepCopy(): MutableList<MutableList<T>> {
    val newList = mutableListOf<MutableList<T>>()
    this.forEach {
        val mutableList = it.toMutableList()
        newList.add(mutableList)
    }
    return newList
}