package byeonghee.`21week`

import java.io.*

class 소병희_ {

    companion object {
        val dr = arrayOf(intArrayOf(0, 1, 0), intArrayOf(-1, 0, 0))
        val dc = arrayOf(intArrayOf(1, 0, -1), intArrayOf(0, 1, -1))

        fun solve(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
            val T = readLine().toInt()
            for(tc in 0 until T) {
                val n = readLine().toInt() + 2
                val stickers = Array(2) { "0 ${readLine()} 0".split(" ").map { it.toInt() } }

                val lml = getLml(2 * n)
                val idxTree = IntArray(lml * 2)
                val tearAround = IntArray(3)

                fun updateTree(v: Int, idx: Int) : Int {
                    val ret = idxTree[idx]
                    idxTree[idx] = v

                    var p = idx / 2
                    while(p > 0) {
                        idxTree[p] = maxOf(idxTree[p * 2], idxTree[p * 2 + 1])
                        p /= 2
                    }
                    return ret
                }

                fun updateAround(r: Int, c: Int, v: IntArray) : IntArray {
                    return IntArray(3) { updateTree(v[it], lml + (r + dr[r][it]) * n + (c + dc[r][it])) }
                }

                for(j in 1 until n - 1) for(i in 0..1)  {
                    val save = updateAround(i, j, tearAround)
                    updateTree(idxTree[1] + stickers[i][j], lml + i * n + j)
                    updateAround(i, j, save)
                }

                println(idxTree[1])
            }

        }

        fun getLml(i: Int) : Int {
            var ret = 1
            while(ret < i) {
                ret *= 2
            }
            return ret
        }
    }
}

fun main() {
    소병희_.solve()
}