package hyunsoo.`3week`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


/**
 * N개의 도시가 있다.
 * 도시들을 수평으로 두고, 제일 왼쪽의 도시에서 제일 오른쪽의 도시로 자동차를 이용하여 이동하려고 한다.
 * 처음 출발하면 자동차에 기름을 넣어야함.
 * 1km당 1리터의 기름을 사용함.
 *
 * 입/출력
 * - 첫 번째 줄에는 도시의 개수를 나타내는 정수 N
 * - 두 번째 줄에는 인접한 두 도시를 연결하는 도로의 길이가 주어짐
 * - 세 번째 줄에는 주유소의 리터당 가격이 주어짐
 *
 * 아이디어
 * 1. 그리디?
 *      - 현재 위치에서 기름을 풀로 땡겼을 때 가장 싸다면 풀로 땡기기
 *      - 아니면 다음에 갈 정도만 땡기기
 *          - 잘못된 로직 ^_^
 *      - 현재 도시보다 더 싼 도시가 있을 때까지 기름을 구매!
 *
 */

val br = BufferedReader(InputStreamReader(System.`in`))
val bw = BufferedWriter(OutputStreamWriter(System.out))

var totalPay: Long = 0
val cityCnt = br.readLine().toInt()
val lengthBetweenCities = br.readLine().split(" ").map { it.toLong() }
val oilPrice = br.readLine().split(" ").map { it.toLong() }

fun main() {


    var curLocationCityIndex = 0
    while (curLocationCityIndex < cityCnt) {

        // 현재 도시의 기름값보다 더 싼 도시를 찾기
        val cheaperCityIndex = cheaperPriceCityIndex(oilPrice[curLocationCityIndex])

        // 현재 도시의 기름값이 더 싸다면 끝까지 사버리기
        if (cheaperCityIndex == -1) {
            totalPay +=
                oilPrice[curLocationCityIndex] * lengthBetweenCities
                    .subList(curLocationCityIndex, lengthBetweenCities.lastIndex + 1).sum()
            writeFast()
            return
            // 찾았다면 거기까지만 갈 정도로 기름을 구매하기!
        } else {
            totalPay +=
                oilPrice[curLocationCityIndex] * lengthBetweenCities
                    .subList(curLocationCityIndex, cheaperCityIndex).sum()
            curLocationCityIndex = cheaperCityIndex
        }
    }
    writeFast()
}

fun cheaperPriceCityIndex(curCityPrice: Long): Int {
    return oilPrice.indexOf(oilPrice.find { it < curCityPrice } ?: return -1)
}

fun writeFast() {
    bw.write("$totalPay")
    bw.flush()
    bw.close()
}

