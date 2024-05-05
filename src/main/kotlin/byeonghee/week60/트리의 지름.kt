package byeonghee.week60

class 소병희_트리의지름 {
    companion object {
        data class Node(val i: Int, val w: Int)

        fun solve() = with(System.`in`.bufferedReader()) {
            val n = readLine().toInt()
            val tree = Array(n+1) { ArrayDeque<Node>() }
            val depth = Array(n+1) { IntArray(2) }

            repeat(n-1) {
                val (p, c, w) = readLine().split(" ").map { it.toInt() }
                tree[p].add(Node(c, w))
            }

            fun dfs(i: Int): Int {
                if (tree[i].isEmpty()) return 0

                for((c, w) in tree[i]) {
                    val cur = w + dfs(c)
                    if (cur > depth[i][0]) {
                        depth[i][1] = depth[i][0]
                        depth[i][0] = cur
                    }
                    else if (cur > depth[i][1]) {
                        depth[i][1] = cur
                    }
                }
                return depth[i][0]
            }

            dfs(1)

            println(depth.maxOf { it.sumOf { it }})
        }
    }
}

fun main() {
    소병희_트리의지름.solve()
}