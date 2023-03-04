package jimin.`25week`

/*
<문제>
[선수과목](https://www.acmicpc.net/problem/14567)

<구현 방법>
classInfo라는 트리를 만들어줬으며,
indegree라는 진입차수를 나타내는 리스트를 만들어주었다.
진입차수가 0인 root들을 queue에 담고,
자식 노드들을 살피면서 값을 업데이트 해주면서 진입차수를 1씩 감소시켰다.
그러면서 진입차수가 0이되는 친구를 queue에 담아주었다.

<트러블 슈팅>
처음에는 그냥 자식 노드면 queue에 삽입을 해주었는데, 그렇게 하니 시간초과가 떴다.
진입차수가 0이 되는 노드를 queue에 넣어줘야 했다.

이코테 위상정렬 참고
 */

import java.lang.Integer.*

class 선수과목 {
    lateinit var classInfo: MutableList<MutableList<Int>>
    lateinit var time: MutableList<Int>
    lateinit var indegree: MutableList<Int>
    fun solve() {
        val (n, m) = readln().split(" ").map { it.toInt() }
        classInfo = MutableList(n + 1) { mutableListOf() }
        time = MutableList(n + 1) { 1 }
        indegree = MutableList(n + 1) { 0 }
        repeat(m) {
            readln().split(" ").map { it.toInt() }.apply {
                classInfo[first()].add(last())
                indegree[last()] += 1
            }
        }

        setClassTime()
        println(time.drop(1).joinToString(" "))
    }

    fun setClassTime() {
        val queue = ArrayDeque<Int>()
        for (i in 1 until indegree.size) {
            if (indegree[i] == 0) {
                queue.add(i)
            }
        }

        while(queue.isNotEmpty()) {
            val now = queue.removeFirst()
            classInfo[now].forEach{
                time[it] = max(time[it], time[now] + 1)
                indegree[it] -= 1
                if (indegree[it] == 0) {
                    queue.add(it)
                }
            }
        }
    }
}

fun main() {
    선수과목().solve()
}