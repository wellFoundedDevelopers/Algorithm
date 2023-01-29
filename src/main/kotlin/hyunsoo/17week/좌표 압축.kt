package hyunsoo.`17week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

/**
 *
 * <문제>
 * [좌표 압축](https://www.acmicpc.net/problem/18870)
 *
 * 좌표 xi를 압축한 결과 x'i의 값은 xi > xj를 만족하는 서로 다른 좌표의 개수와 같아야함.
 * xi를 기준으로 xi보다 작은 xj의 개수가 압축된 좌표임.
 *
 * - 아이디어
 * 좌표의 개수는 100만.. 2중 포문으로 탐색하면 안됨.
 * -- 아하 정렬하고 이분 탐색으로 인덱스 찾으면 된대요..!! <- 좌표 압축!!
 *
 * - 트러블 슈팅
 *
 */

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val coordinateCnt = readln().toInt()
    val coordinateList = br.readLine().split(" ").map { it.toInt() }
    val sortedCoordinateList = coordinateList.sorted().distinct()

    coordinateList.forEach { target ->

        var start = 0
        var end = sortedCoordinateList.lastIndex

        while (start <= end) {

            val mid = (start + end) / 2
            val midCoordinate = sortedCoordinateList[mid]

            if (target < midCoordinate) {
                end = mid - 1
            } else if (midCoordinate < target) {
                start = mid + 1
            } else {
                bw.write("$mid ")
                break
            }

        }

    }

    bw.flush()
    bw.close()

}