package jimin.`26week`

/*
<문제>
[절댓값 힙](https://www.acmicpc.net/problem/11286)

<구현 방법>
입력 받을 때 0이 아니면 heap에 추가해준다.
이때 Pair로 추가해줘서 첫번째값은 절댓값, 두번째값은 원래 값으로 하여 이 순서로 우선순위가 되도록 하였다.

<트러블 슈팅>
*/

import java.util.*
class `절댓값 힙` {
    fun solve() {
        val heap = PriorityQueue(Comparator<Pair<Int, Int>>{ a, b -> if (a.first != b.first) a.first - b.first else a.second - b.second})

        repeat(readln().toInt()) {
            readln().toInt().apply{
                if (this == 0) {
                    if (heap.isNotEmpty()) {
                        println(heap.remove().second)
                    } else {
                        println(0)
                    }

                } else if (this < 0) {
                    heap.add(Pair(this * (-1), this))
                } else {
                    heap.add(Pair(this, this))
                }
            }
        }
    }
}

fun main() {
    `절댓값 힙`().solve()
}