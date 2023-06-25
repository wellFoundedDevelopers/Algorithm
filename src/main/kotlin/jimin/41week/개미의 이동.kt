package jimin.`41week`

/*
<문제>
[개미의 이동](https://www.acmicpc.net/problem/3221)

<구현 방법>


<트러블 슈팅>
https://recordofwonseok.tistory.com/412 블로그 참고
근데 이해 잘 안감..ㅎㅎ
 */
class `개미의 이동` {
    fun solve() {
        val (l, t) = readln().split(" ").map { it.toInt()}
        val n = readln().toInt()
        val ants = mutableListOf<Int>()
        repeat(n) {
            readln().split(" ").apply {
                if (last() == "L") {
                    val move = t + l - first().toInt()
                    if(move < l) {
                        ants.add(l - move)
                    } else {
                        if ((move / l) % 2 == 1) {
                            ants.add(move % l)
                        } else {
                            ants.add(l - move % l)
                        }
                    }
                } else {
                    val move = t + first().toInt()
                    if(move < l) {
                        ants.add(move)
                    } else {
                        if ((move / l) % 2 == 1) {
                            ants.add(l - move % l)
                        } else {
                            ants.add(move % l)
                        }
                    }
                }
            }
        }
        println(ants.sorted().joinToString(" "))
    }
}

fun main() {
    `개미의 이동`().solve()
}