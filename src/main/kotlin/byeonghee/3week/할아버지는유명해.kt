package byeonghee.`3week`

import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * 최대값 차대값을 저장하는 식으로 계속 풀었었는데 틀려서 sorted를 이용했다.
 * 나중에 생각해보니 최댓값이 스스로를 갱신하는 경우 존재하지 않는 값이 차대값으로 내려가버려서 계속 틀린 거였다.
 * 그리고 입력마다 최대값을 계산하면 시간복잡도가 N^2이니 아마 내부적으로 NlogN일 정렬을 이용하는 편이 더 나을 것 같다.
 */

val br = BufferedReader(InputStreamReader(System.`in`))

var N = 0
var silver = 0
var accRanking = IntArray(10001){ 0 }

fun main() {

    while(true) {
        N = br.readLine().trim().split(" ").first().toInt()
        if (N == 0) break

        repeat(N) {
            br.readLine().trim().split(" ").forEach { p ->
                accRanking[p.toInt()]++
            }
        }

        silver = accRanking.toSortedSet().reversed().drop(1).first()

        for(i in 1..10000) {
            if (accRanking[i] == silver) {
                print("$i ")
            }
        }
        println()

        accRanking = IntArray(10001){ 0 }
    }
}