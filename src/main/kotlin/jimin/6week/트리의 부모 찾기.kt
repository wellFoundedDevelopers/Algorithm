package jimin.`6week`

import java.io.BufferedReader
import java.io.InputStreamReader
/*
<문제>
[트리의 부모 찾기] (https://www.acmicpc.net/problem/11725)

<구현 방법>
입력으로 주어진 정보를 map에 담아 정리하였다.
이를 bfs로 돌면서 parent 정보를 기록하였다.

<트러블 슈팅>
parent를 기록해야한다는 아이디어 참고함.
 */

fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val n = readLine().toInt()
    var treeList = mutableMapOf<Int, MutableList<Int>>()
    repeat(n - 1) {
        val input = readLine().split(" ").map { it.toInt() }
        if (treeList[input[0]] == null) treeList[input[0]] = mutableListOf(input[1])
        else treeList[input[0]]!!.add(input[1])
        if (treeList[input[1]] == null) treeList[input[1]] = mutableListOf(input[0])
        else treeList[input[1]]!!.add(input[0])
    }


    bfs(treeList,MutableList(n + 1) { i -> 0 })


}

fun bfs(treeList: MutableMap<Int, MutableList<Int>>, parent: MutableList<Int>) {
    val queue = arrayListOf<Int>()
    queue.add(1)
    parent[0] = -1
    parent[1] = -1

    while (queue.isNotEmpty()) {
        val v = queue.removeFirst()
        treeList[v]?.forEach {
            if (parent[it] == 0){
                queue.add(it)
                parent[it] = v
            }
        }
    }

    for (i in 2 until parent.size) {
        println(parent[i])
    }

}

