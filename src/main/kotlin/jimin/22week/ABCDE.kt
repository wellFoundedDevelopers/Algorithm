package jimin.`22week`

/*
<문제>
[ABCDE](https://www.acmicpc.net/problem/13023)

<구현 방법>
입력을 받을 때, 인덱스 위치로 해당 친구 관계를 넣었다.
dfs를 돌면서 visited의 size가 5가 되면 나오게 했으며,
result가 1이 되면 dfs를 더 이상 돌지 않도록 하였다.

<트러블 슈팅>
visited를 n 크기 만큼의 boolean인 mutablelist로 하니 시간초과가 났다.
*/


class ABCDE {
    private lateinit var friends: MutableList<MutableList<Int>>
    private var n: Int = 0
    private var m: Int = 0
    private var result = 0
    fun solve() {
        readln().split(" ").map { it.toInt() }.apply{
            n = first()
            m = last()
        }
        friends = MutableList(n) { mutableListOf() }
        repeat(m) {
            readln().split(" ").map { it.toInt() }.apply{
                friends[first()].add(last())
                friends[last()].add(first())
            }
        }

        for(i in 0 until n) {
            dfs(mutableListOf(i))
            if (result == 1) {
                break
            }
        }

        println(result)
    }

    fun dfs(visited: MutableList<Int>) {
        if (visited.size == 5) {
            result = 1
            return
        }

        friends[visited.last()].forEach {
            if (it !in visited) {
                dfs(visited.plus(it) as MutableList)
            }
        }
    }
}

fun main() {
    ABCDE().solve()
}