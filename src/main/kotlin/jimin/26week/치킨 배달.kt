package jimin.`26week`

/*
<문제>
[치킨 배](https://www.acmicpc.net/problem/15686)

<구현 방법>
우선 town을 돌면서 치킨집 좌표를 담은 chickens 리스트와 집에 대한 좌표를 key로 하는 homes 맵을 만들었다.
이후 m개의 치킨집 조합을 만들어내는 getCombinaton()함수를 이용했다.
만들어낸 조합을 for문으로 돌면서 가능한 치킨집과 집의 좌표 차이에 대한 정보를 최솟값으로 업데이트하며 newHomes맵에 기록하고,
newHomes의 value 합이 제일 작은 것으로 result를 업데이트했다.

<트러블 슈팅>
처음에는 bfs를 돌면서 치킨집과 집 사이의 거리를 구했는데, 시간초과가 엄청 나와서 질문 게시판을 보았다.
bfs가 아닌 좌표 차이로 구하면 된다는 것을 깨닫고 해결했다!
*/

import java.lang.Math.*

class `치킨 배달` {
    val town = mutableListOf<MutableList<Int>>()
    val chickens = mutableListOf<Pair<Int, Int>>()
    val homes = mutableMapOf<Pair<Int, Int>, Int>()
    val combis = mutableListOf<List<Pair<Int, Int>>>()
    val CHICKEN = 2
    val HOME = 1
    var result = Int.MAX_VALUE

    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        repeat(n) {
            town.add(readln().split(" ").map { it.toInt() } as MutableList)
        }
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (town[i][j] == CHICKEN) {
                    chickens.add(Pair(i, j))
                } else if (town[i][j] == HOME) {
                    homes[Pair(i, j)] = Int.MAX_VALUE
                }
            }
        }
        getCombination(0, m, listOf())

        combis.forEach { c ->
            val newHomes = homes.toMutableMap()
            c.forEach { cc ->
                newHomes.keys.forEach { hh ->
                    newHomes[Pair(hh.first, hh.second)] = min(newHomes[Pair(hh.first, hh.second)]!!,
                            abs(hh.first - cc.first) + abs(hh.second - cc.second))
                }
            }
            result = min(result, newHomes.values.sum())
        }

        println(result)
    }

    fun getCombination(idx: Int, m: Int, numbers: List<Pair<Int, Int>>) {
        if (numbers.size == m) {
            combis.add(numbers)
            return
        }
        for (i in idx until chickens.size) {
            getCombination(i + 1, m, numbers.plus(chickens[i]))
        }
    }

}

fun main() {
    `치킨 배달`().solve()
}