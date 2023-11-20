package byeonghee.week50

class 소병희_북쪽나라의도로 {
    companion object {
        data class Node(
                var parent: Int = 0,
                var distance: Int = 0,
                var depth: Int = 0
        )

        fun solve() = with(System.`in`.bufferedReader()) {
            val input = ArrayDeque<String>()
            var answer = 0

            while(true) {
                val str = readLine()
                if (str.isNullOrBlank()) break
                input.add(str)
            }

            val n = input.size + 1
            val tree = Array(n + 1) { Node() }
            input.forEach {
                val (a, b, w) = it.split(" ").map { it.toInt() }
                var node = a
                var preParent = tree[a]
                tree[a] = Node(b, w)
                while(preParent.parent != 0) {
                    val tmpPreParent = tree[preParent.parent]
                    tree[preParent.parent] = Node(node, preParent.distance)
                    node = preParent.parent
                    preParent = tmpPreParent
                }
            }

            for(i in 1 .. n) {
                var p = tree[i].parent
                var d = 0
                while(p != 0) {
                    d++
                    p = tree[p].parent
                }
                tree[i].depth = d
            }

            for(s in 1 until n) for(e in s+1 .. n) {
                var lo = if (tree[s].depth > tree[e].depth) s else e
                var hi = if (lo == s) e else s
                var total = 0

                while(tree[lo].depth > tree[hi].depth) {
                    total += tree[lo].distance
                    lo = tree[lo].parent
                }

                while(lo != hi) {
                    total += tree[lo].distance + tree[hi].distance
                    lo = tree[lo].parent
                    hi = tree[hi].parent
                }

                answer = answer.coerceAtLeast(total)
            }

            println(answer)
        }
    }
}

fun main() {
    소병희_북쪽나라의도로.solve()
}