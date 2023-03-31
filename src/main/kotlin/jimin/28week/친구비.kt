package jimin.`28week`

/*
<문제>
[절댓값 힙](https://www.acmicpc.net/problem/16562)

<구현 방법>
union-find 문제!

<트러블 슈팅>
union-find의 경우 한번 더 getParent로 정리를 해주어야 자기 부모를 잘 찾아간다.
*/

class 친구비 {
    private lateinit var moneys: MutableList<Int>
    private lateinit var parents: MutableList<Int>

    fun solve() {
        val (n, m, k) = readln().split(" ").map{ it.toInt() }
        moneys = readln().split(" ").map{ it.toInt() } as MutableList
        parents = MutableList(n + 1) { it }
        moneys.add(0, 0)
        var v = 0
        var w = 0
        repeat(m) {
            readln().split(" ").map{ it.toInt() }.apply{
                v = first()
                w = last()
            }
            setParent(v, w)
        }
        var sum = 0
        val onlyParents = mutableSetOf<Int>()
        parents.drop(1).forEach{
            onlyParents.add(getParent(it))
        }
        onlyParents.forEach{
            sum += moneys[it]
        }
        if (sum <= k) {
            println(sum)
        } else {
            println("Oh no")
        }

    }

    fun getParent(v:Int): Int {
        if (v != parents[v]) {
            parents[v] = getParent(parents[v])
        }
        return parents[v]
    }

    fun setParent(v: Int, w:Int) {
        val vParent = getParent(v)
        val wParent = getParent(w)

        if (moneys[vParent] < moneys[wParent]) {
            parents[wParent] = vParent
        } else {
            parents[vParent] = wParent
        }
//        else {
//            if (wParent < vParent) {
//                parents[vParent] = wParent
//            } else {
//                parents[wParent] = vParent
//            }
//        }
    }
}

fun main() {
    `친구비`().solve()
}