package heejik.`25week`

import java.util.PriorityQueue
import kotlin.properties.Delegates

class 오큰수 {

    private var n by Delegates.notNull<Int>()
    private lateinit var sequence: MutableList<Int>
    private var endSequence = ArrayDeque<Int>()
    fun solve() {
        setting()
        getNGEs()
    }

    private fun setting() {
        n = readln().toInt()
        sequence = readln().split(' ').map { it.toInt() }.toMutableList()
    }

    private fun getNGEs() {
        val NGEs = mutableListOf<Int>()
        while (sequence.isNotEmpty()) {
            NGEs.add(getNGE(sequence.removeLast()))
        }
        print(NGEs.reversed().joinToString(separator = " "))
    }

    private fun getNGE(num: Int): Int {
        var nge = -1

        if (num > (endSequence.lastOrNull() ?: 0)) {
            endSequence.clear()
            endSequence.addFirst(num)
            return nge
        }

        while (true) {
            val tmp = endSequence.firstOrNull() ?: break
            if (tmp > num) {
                nge = tmp
                break
            }
            else endSequence.removeFirst()
        }

        endSequence.addFirst(num)

        return nge
    }
}

fun main() {
    오큰수().solve()
    val pq = PriorityQueue<Int>()
    pq.remove()
}