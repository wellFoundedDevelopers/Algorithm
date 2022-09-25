package byeonghee.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

fun min(a: Int, b: Int) = if (a < b) a else b
fun max(a: Int, b: Int) = if (a > b) a else b

// 틀림!!
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val size = readLine().trim().toInt()
    val arr = mutableListOf<Int>()

    repeat(size) {
        arr.add(readLine().trim().toInt())
    }
    arr.sort()

    var answer = 4
    var successive = 1
    var successMax = 0
    var added = 4
    var addedMin = 4

    for(i in 1 until size) {
        when (arr[i] - arr[i-1]) {
            1 -> {
                successive = min(5, successive + 1)
                added = max(0, added - 1)
            }
            2, 3 -> {
                successMax = max(successMax, successive)
                successive = 1
                added = max(0, added - 1)
            }
            else -> {
                successMax = max(successMax, successive)
                successive = 1
                addedMin = min(addedMin, added)
                added = 4
            }
        }
    }
    successMax = max(successMax, successive)
    addedMin = min(addedMin, added)
    answer = min(5 - successMax, addedMin)

    println(answer)
}