package jimin.`20week`

/*
<문제>
[에너지 모으기](https://www.acmicpc.net/problem/16198)

<구현 방법>
부르트포스! 완탐!
재귀를 통해서 모든 경우의 수를 탐색하고 energy size가 2이면 result를 업데이트하도록 하였다.

<트러블 슈팅>
*/

import java.lang.Integer.max

class `에너지 모으기` {
    var result = 0
    fun solve() {
        readln().toInt()
        val energy = readln().split(" ").map{ it.toInt() }.toMutableList()

        getEnergy(energy, 0)
        println(result)

    }

    fun getEnergy(energy: List<Int>, sum: Int) {
        if (energy.size == 2) {
            result = max(result, sum)
            return
        }

        for(i in 1 until energy.size - 1) {
            getEnergy(energy.subList(0, i) + energy.subList(i+1, energy.size), sum + energy[i - 1] * energy[i + 1])
        }
    }
}

fun main() {
    `에너지 모으기`().solve()
}