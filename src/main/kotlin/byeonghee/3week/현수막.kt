package byeonghee.`3week`

/**
 * 시간복잡도: N^2 * a
 * parents: 현수막 글자의 id를 인덱스로 하는, 해당 글자에 속한 Pos 리스트
 *
 * 이중 포문으로 현수막을 탐색하며 v가 1인 Pixel에 대해,
 * 인접하고 & 먼저 확인했고 & v가 1인 픽셀들의 parent 값들을 localParents에 저장
 * localParents가 비었다면 현재 확인 중인 픽셀의 parent를 parents.size로 할당(= 속한 글자의 id)
 * localParents가 비지 않았다면 (현재 픽셀 + localParents 요소들)의 parent를 localParents의 최소값으로 설정
 */

import java.io.*

class `소병희_현수막` {
    companion object {
        fun getSolution(): Solution {
            return Solution()
        }
    }

    class Solution {
        companion object {
            const val INF = 987654321
        }

        var answer = 0

        val dr = listOf(-1, -1, -1, 0)
        val dc = listOf(-1, 0, 1, -1)

        data class Pos(val r: Int, val c: Int)
        data class Pixel(val v: Int, var parent: Int = INF)

        fun solution() {
            val br = BufferedReader(InputStreamReader(System.`in`))

            val (M, N) = br.readLine().trim().split(" ").map{ it.toInt() }
            val parents = mutableListOf<MutableList<Pos>>()
            val banner = MutableList<MutableList<Pixel>>(M){ mutableListOf() }

            repeat(M) { i ->
                banner[i].add(Pixel(0))
                br.readLine().trim().split(" ").forEach {
                    banner[i].add(Pixel(it.toInt()))
                }
                banner[i].add(Pixel(0))
            }
            banner.add(0, MutableList(N + 2){Pixel(0)})
            banner.add(MutableList(N + 2){Pixel(0)})

            for(i in 1..M) for(j in 1..N) {
                if (banner[i][j].v == 1) {
//            println("($i, $j)")
                    val localParents = mutableSetOf<Int>()
                    for(d in 0..3) {
                        if (banner[i+dr[d]][j+dc[d]].v == 1) {
                            localParents.add(banner[i+dr[d]][j+dc[d]].parent)
                        }
                    }
                    if (localParents.isEmpty()) {
//                println("\t인접한 픽셀 중 가장 먼저 접근했습니다.(${parents.size}")
                        banner[i][j].parent = parents.size
                        parents.add(mutableListOf(Pos(i, j)))
                    }
                    else {
                        val minParent = localParents.toList().minOf{ it }
//                println("\t인접한 픽셀 중 가장 빠른 글자: $minParent")
                        banner[i][j].parent = minParent
                        parents[minParent].add(Pos(i, j))
                        localParents.remove(minParent)
                        localParents.forEach { par ->
                            parents[par].forEach {
                                banner[it.r][it.c].parent = minParent
                            }
                            parents[minParent].addAll(parents[par])
                            parents[par].clear()
                        }
                    }
                }
            }

            parents.removeAll{ it.isEmpty() }
            println(parents.size)
        }
    }
}

fun main() {
    `소병희_현수막`.getSolution()
}