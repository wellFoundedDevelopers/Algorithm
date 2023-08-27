package byeonghee.week41

import java.io.StreamTokenizer

class 소병희_트리 {

    companion object {
        fun solve() = StreamTokenizer(System.`in`.bufferedReader()).run {
            fun input() : Int {
                nextToken()
                return nval.toInt()
            }

            val n = input()
            val tree = List(n) { mutableListOf<Int>() }
            val parent = IntArray(n) { input() }
            val ban = input()
            var root = 0
            var answer = 0

            repeat(n) { i ->
                if (parent[i] == -1) root = i
                else if (i != ban) tree[parent[i]].add(i)
            }

            fun dfs(i: Int) {
                if (i == ban) return
                if (tree[i].isEmpty()) answer++
                else for(c in tree[i]) dfs(c)
            }

            dfs(root)
            print(answer)
        }
    }
}

fun main() {
    소병희_트리.solve()
}