package byeonghee.`6week`

import java.io.*

class `소병희_트리의 부모 찾기` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var n = 0
        var parents = IntArray(0)
        var edges = mutableMapOf<Int, MutableList<Int>>()
        var q = ArrayDeque<Pair<Int, Int>>()
        lateinit var cur: Pair<Int, Int>

        fun solution() {
            n = br.readLine().toInt()
            parents = IntArray(n + 1)

            repeat(n-1) { i ->
                br.readLine().split(" ").map{ it.toInt() }.run {
                    edges.getOrPut(first()) { mutableListOf() }
                    edges[first()]!!.add(last())
                    edges.getOrPut(last()) { mutableListOf() }
                    edges[last()]!!.add(first())
                    if (first() == 1) q.add(Pair(first(), last()))
                    else if (last() == 1) q.add(Pair(last(), first()))
                }
            }

            while(q.isNotEmpty()) {
                cur = q.removeFirst()
                parents[cur.second] = cur.first
                for(i in edges[cur.second]!!) {
                    if (parents[i] == 0) q.add(Pair(cur.second, i))
                }
            }

            for(r in 2..n) {
                println(parents[r])
            }
        }
    }
}

fun main() {
    `소병희_트리의 부모 찾기`.getSolution().solution()
}

/*var n = 0
var parents = IntArray(0)
var depths = IntArray(0)
var child = 0
var parent = 0

fun main() {
    n = br.readLine().toInt()
    parents = IntArray(n + 1) { -1 }
    parents[1] = 0
    depths = IntArray(n + 1) { 100001 }
    depths[0] = -1
    depths[1] = 0

    repeat(n-1) {
        br.readLine().trim().split(" ").map{ it.toInt() }.run {
            if (depths[first()] > depths[last()]) {
                updateParents(first(), last())
            }
            else {
                updateParents(last(), first())
            }
        }
    }

    for(r in 2..n) {
        println(parents[r])
    }
}

fun updateParents(child: Int, parent: Int) {
    var tmp = parents[child]
    parents[child] = parent
    depths[child] = depths[parent] + 1

    if (tmp == -1) return
    updateParents(tmp, child)
}*/