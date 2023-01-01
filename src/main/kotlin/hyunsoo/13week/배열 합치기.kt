package hyunsoo.`13week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 * <문제>
 *
 * [배열 합치기](https://www.acmicpc.net/problem/11728)
 */

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (aSize, bSize) = br.readLine().split(" ").map { it.toInt() }
    val arrayA = br.readLine().split(" ").map { it.toInt() }
    val arrayB = br.readLine().split(" ").map { it.toInt() }
    val sortedArray = IntArray(aSize + bSize)

    var aPointer = 0
    var bPointer = 0
    var sortedPointer = 0

    while (aPointer <= arrayA.lastIndex && bPointer <= arrayB.lastIndex) {

        if (arrayA[aPointer] < arrayB[bPointer]) {
            sortedArray[sortedPointer++] = arrayA[aPointer++]
        } else {
            sortedArray[sortedPointer++] = arrayB[bPointer++]
        }
    }

    while (aPointer <= arrayA.lastIndex) {
        sortedArray[sortedPointer++] = arrayA[aPointer++]
    }

    while (bPointer <= arrayB.lastIndex) {
        sortedArray[sortedPointer++] = arrayB[bPointer++]
    }


    sortedArray.forEach {
        bw.write("$it ")
    }

    bw.flush()
    bw.close()
}