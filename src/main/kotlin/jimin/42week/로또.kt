package jimin.`42week`

/*
<문제>
[로또](https://www.acmicpc.net/problem/6603)

<구현 방법>
재귀를 사용해 조합해주었다.

<트러블 슈팅>
조합하는 방법을 까먹었다.. (충격)
https://velog.io/@sloools/Python-%EC%88%9C%EC%97%B4Permutation-%EC%A1%B0%ED%95%A9Combination
블로그를 참고해 조합을 공부했다.
 */

class 로또 {
    fun solve() {
        while (true) {
            val lottos = readln().split(" ").map{ it.toInt() }
            if (lottos.first() == 0) {
                break
            } else {
                printCombi(lottos.drop(1), listOf(), 0)
                println()
            }
        }

    }

    private fun printCombi(nums: List<Int>, picks: List<Int>, n:Int) {
        if (picks.size == 6) {
            println(picks.joinToString(" "))
            return@printCombi
        }
        if (n == nums.size) {
            return@printCombi
        }
        val newPicks = picks.plus(nums[n])
        printCombi(nums, newPicks, n + 1)
        printCombi(nums, picks, n + 1)

    }
}

fun main() {
    로또().solve()
}