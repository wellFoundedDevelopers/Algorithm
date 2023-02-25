package heejik.`24week`

import kotlin.properties.Delegates
import kotlin.system.exitProcess

class 좋은수열 {

    var n by Delegates.notNull<Int>()

    fun solve() {
        setting()
        pickNumber(intArray = mutableListOf())
    }

    private fun setting() {
        n = readln().toInt()
    }

    private fun pickNumber(intArray: MutableList<Int>) {
        if (checkGoodSequence(intArray)) {
            if (intArray.size == n) {
                println(intArray.joinToString(""))
                exitProcess(0)
            }
        } else {
            return
        }

        for (i in 1..3) {
            intArray.add(i)
            pickNumber(intArray)
            intArray.removeLast()
        }
    }

    private fun checkGoodSequence(intArray: MutableList<Int>): Boolean {
        for (i in 2..intArray.size step 2) {
            for (j in 0 .. intArray.size - i) {
                val slice = intArray.slice(j until i + j)
                if (slice.slice(0 until slice.size / 2) ==
                    slice.slice(slice.size / 2 until slice.size)
                )
                    return false
            }
        }
        return true
    }
}

fun main() {
    좋은수열().solve()
}