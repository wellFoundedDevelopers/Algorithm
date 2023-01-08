package jimin.`17week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[트리 순회](https://www.acmicpc.net/problem/1991)

<구현 방법>
Node라는 data class를 만들고 이를 타입으로 하는 Map을 만듬
이를 통해 A를 시작으로 순회를 돌면서 prev 전 후 next 후로 string을 만들어주었다.

<트러블 슈팅>
*/

val tree = mutableMapOf<String, Node>()
val results = mutableListOf("", "", "")
fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    repeat(readLine().toInt()) {
        val (str, prev, next) = readLine().split(" ")
        tree[str] = Node(prev, next)
    }
    setTraversal("A")
    results.forEach { println(it) }
}

fun setTraversal(str: String) {
    val node = tree[str] ?: Node(".",".")
    results[0] += str
    if (node.prev != ".") setTraversal(node.prev)
    results[1] += str
    if (node.next != ".") setTraversal(node.next)
    results[2] += str
}

data class Node(
    val prev: String,
    val next: String
)