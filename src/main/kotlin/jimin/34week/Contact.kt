package jimin.`34week`

/*
<문제>
[Contact](https://www.acmicpc.net/problem/1013)

<구현 방법>
idx로 접근하도록 했다.
01일때, 100일때로 분류하고 해당하지 않으면 무조건 틀리다고 판단했다.
0+이기 때문에 while문을 돌면서 0이 나올 때까지 idx를 늘려주고,
1+이기 때문에 1이 나오지 않을 경우 틀리다고 판단해주고, 1이 나올 때가지 while문을 돌면서 idx를 늘려주었다.
이때 다음에 100+1+ 가 또 나올 수도 있으므로 1이 2개 이상이면 예외처리를 해주었다.

<트러블 슈팅>
*/

class Contact {
    fun solve() {
        repeat(readln().toInt()) {
            val str = readln().chunked(1)
            var idx = 0
            var isRight = true
            while (idx < str.size) {
                if (idx + 1 < str.size && str[idx] == "0" && str[idx + 1] == "1") {
                    idx += 2
                } else if (idx + 2 < str.size && str[idx] == "1" && str[idx + 1] == "0" && str[idx + 2] == "0") {
                    idx += 3
                    while (idx < str.size && str[idx] == "0") {
                        idx += 1
                    }
                    if (idx >= str.size || str[idx] != "1") {
                        isRight = false
                        break
                    }
                    while (idx < str.size && str[idx] == "1") {
                        idx += 1
                    }
                    if (idx + 1 < str.size && str[idx - 2] == "1" && str[idx - 1] == "1" && str[idx] == "0" && str[idx + 1] == "0") {
                        idx -= 1
                    }
                } else {
                    isRight = false
                    break
                }
            }

            if (isRight) {
                println("YES")
            } else {
                println("NO")
            }

        }
    }
}

fun main() {
    Contact().solve()
}