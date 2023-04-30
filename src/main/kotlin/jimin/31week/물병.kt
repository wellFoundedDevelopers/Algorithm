package jimin.`31week`

/*
<문제>
[물병](https://www.acmicpc.net/problem/1052)

<구현 방법>
물병을 추가로 가져오지 않고 합쳤을 때 생기는 개수를 cnt에 담아 구한다.
cnt가 k보다 작거나 같으면 종료한다.
아니라면 n의 개수를 1 추가해 물병을 추가로 가져오는 것을 구현해 다시 cnt를 구하는 걸 반복한다.

<트러블 슈팅>
https://ongveloper.tistory.com/150
*/

class 물병 {
    fun solve(){
        var (n, k) = readln().split(" ").map{ it.toInt() }
        var result = 0

        while (true) {
            var newN = n
            var cnt = 0
            while(newN > 0) {
                if (newN % 2 != 0) {
                    cnt += 1
                }
                newN /= 2
            }
            if (cnt <= k) {
                break
            }
            result += 1
            n += 1
        }
        println(result)
    }
}

fun main() {
    물병().solve()
}