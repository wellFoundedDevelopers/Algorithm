package jimin.`23week`

/*
<문제>
[센서](https://www.acmicpc.net/problem/2212)

<구현 방법>
센서들 간의 거리와 해당 index를 가지는 distance 리스트를 만들었다.
이를 센서들 간의 거리를 기준으로 내림차순으로 정렬하고 k - 1 개만큼 sublist를 만들고 나서,
마지막 부분도 들어가야하니 추가하고 난 뒤 다시 index를 기준으로 오름차순 정렬한 distance 리스트를 구했다.
이를 for문으로 돌면서 센서간의 차이를 result에 더하여 출력했다.

<트러블 슈팅>
처음에 문제를 잘못이해해서 삽질했다..
k가 n 이상일 때는 런타임 에러가 난다! 이 경우 바로 0을 출력하게 하였다.
 */

class 센서 {
    fun solve() {
        val n = readln().toInt()
        val k = readln().toInt()
        val sensors = readln().split(" ").map { it.toInt() }.sorted()
        var distances = mutableListOf<Pair<Int, Int>>()

        if (n <= k) {
            println(0)
            return
        }

        for(i in 1 until n) {
            distances.add(Pair(i - 1, sensors[i] - sensors[i - 1]))
        }
        distances.sortByDescending { it.second }
        distances = (distances.subList(0, k - 1) + mutableListOf(Pair(n - 1, 0))) as MutableList
        distances.sortBy{ it.first }
        var result = 0

        distances.forEachIndexed{ idx, d ->
            result += if (idx == 0) {
                sensors[d.first] - sensors[0]
            } else {
                sensors[d.first] - sensors[distances[idx - 1].first + 1]
            }
        }

        println(result)
    }
}

fun main() {
    센서().solve()
}