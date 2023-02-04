package jimin.`21week`

/*
<문제>
[스위치 켜고 끄기](https://www.acmicpc.net/problem/1244)

<구현 방법>
입력받고, 남자이면 for문을 해당 수의 배수만큼 돌고,
여자이면 해당 수에서 한칸씩 앞뒤로 체크하며 스위치를 갱신해준다.

<트러블 슈팅>
출력을 20개씩 하라는걸 빼먹었다!!
*/

class `스위치 켜고 끄기` {
    private val MALE = "1"
    private val FEMALE = "2"

    fun solve() {
        val n = readln().toInt()
        val switches = readln().split(" ").map { it == "0" }.toMutableList()
        switches.add(0, false)
        val students = mutableListOf<Pair<String, Int>>()
        repeat(readln().toInt()) {
            readln().split(" ").apply {
                if (first() == MALE) {
                    students.add(Pair(MALE, last().toInt()))
                } else {
                    students.add(Pair(FEMALE, last().toInt()))
                }
            }
        }

        students.forEach { student ->
            if (student.first == MALE) {
                for (i in student.second..n step (student.second)) {
                    switches[i] = switches[i].not()
                }
            } else {
                var idx = 1
                switches[student.second] = switches[student.second].not()
                while (student.second + idx in 1..n && student.second - idx in 1..n) {
                    if (switches[student.second + idx] == switches[student.second - idx]) {
                        switches[student.second + idx] = switches[student.second + idx].not()
                        switches[student.second - idx] = switches[student.second - idx].not()
                        idx += 1
                    } else {
                        break
                    }
                }
            }
        }
        switches.drop(1).map { if (it) 0 else 1 }.forEachIndexed{ idx, switch ->
            if (idx % 20 == 0 && idx != 0) {
                println()
            }
            print("$switch ")
        }
    }
}

fun main() {
    `스위치 켜고 끄기`().solve()
}