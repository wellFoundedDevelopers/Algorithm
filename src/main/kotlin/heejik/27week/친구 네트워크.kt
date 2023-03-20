package heejik.`27week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class `친구 네트워크` {


    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    lateinit var parent : IntArray
    lateinit var weight : IntArray

    fun getTestCase() {
        repeat(br.readLine().toInt()) {
            solve()
        }
        bw.flush()
    }


    fun find(man: Int): Int {
        if (man == parent[man]) {
            return man
        }

        return find(parent[man])
    }

    fun union(man1: Int, man2: Int): Int {

        val root1 = find(man1)
        val root2 = find(man2)

        if (root1 != root2) {
            parent[root2] = root1
            weight[root1] += weight[root2]
        }
        return weight[root1]
    }

    fun solve() {
        parent = IntArray(200001) { it }
        weight = IntArray(200001) { 1 }
        var index = 0
        val map = hashMapOf<String, Int>()
        repeat(br.readLine().toInt()) {
            val (man1, man2) = br.readLine().split(' ')
            if (map.containsKey(man1).not()) {
                map[man1] = index++
            }
            if (map.containsKey(man2).not()) {
                map[man2] = index++
            }

            bw.write("${union(map[man1]!!, map[man2]!!)}\n")
        }
    }
}


fun main() {
    `친구 네트워크`().getTestCase()
}