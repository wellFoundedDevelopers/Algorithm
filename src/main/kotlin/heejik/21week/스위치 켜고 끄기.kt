package heejik.`21week`

import java.lang.Integer.min
import kotlin.properties.Delegates

class `스위치 켜고 끄기` {

    var n by Delegates.notNull<Int>()
    private lateinit var switch: MutableList<Int>

    fun solve() {
        n = readln().toInt()
        switch = readln().split(' ').map { it.toInt() }.toMutableList()
        switch.add(0, -1)

        repeat(readln().toInt()) {
            readln().split(' ').map { it.toInt() }.run {
                play(gender = this[0], switchNumber = this[1])
            }
        }

        switch.drop(1).chunked(20).forEach {
            println(it.joinToString(" "))
        }
    }

    private fun play(gender: Int, switchNumber: Int) {
        if (gender == 1) {
            playByMan(switchNumber)
        } else {
            playByWoman(switchNumber)
        }
    }

    private fun playByMan(switchNumber: Int) {
        for (num in switchNumber until switch.size step switchNumber) {
            switch[num] = changeSwitch(switch[num])
        }
    }

    private fun playByWoman(switchNumber: Int) {
        var resultOffset = 0
        val times = min((switch.size - 1) - switchNumber, switchNumber - 1)

        for (offset in 1..times) {
            val backNum = switchNumber - offset
            val frontNum = switchNumber + offset

            if (switch[backNum] != switch[frontNum]) {
                break
            }

            resultOffset = offset
        }

        for (i in switchNumber - resultOffset .. switchNumber + resultOffset) {
            switch[i] = changeSwitch(switch[i])
        }
    }

    private fun changeSwitch(state: Int) = (state - 1) * -1
}

fun main() {
    `스위치 켜고 끄기`().solve()
}
