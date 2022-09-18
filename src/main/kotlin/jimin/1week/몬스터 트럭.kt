package jimin.`1week`
/*
<문제>
https://www.acmicpc.net/problem/2897

<구현 방법>
2중 for문으로 주차구역을 다 돌때, 4개의 영역에 #이 포함되어있는지,
X의 개수가 몇개인지를 판별해 주차 가능 공간 개수를 구하였다.

<트러블 슈팅>
array에 특정 값이 포함되어있는 개수를 구하고 싶다면
=> 배열.count { it.contains(찾고자 하는 값) }

 */
fun main() {
    val first = readLine()!!.split(" ")
    val x = first.first().toInt()
    val y = first.last().toInt()
    val parking = arrayListOf<ArrayList<String>>()
    for (i in 0 until x) {
        parking.add(
            readLine()!!.split("").drop(1).dropLast(1)
                    as ArrayList<String>
        )
    }

    checkPossibleParkingLot(parking).forEach {
        println(it)
    }
}

private fun checkPossibleParkingLot(parking: ArrayList<ArrayList<String>>): ArrayList<Int> {
    val fourParking = arrayListOf<String>()
    val possibleParking = arrayListOf(0, 0, 0, 0, 0)

    for (i in 0 until parking.size - 1) {
        for (j in 0 until parking[0].size - 1) {
            fourParking.add(parking[i][j])
            fourParking.add(parking[i + 1][j])
            fourParking.add(parking[i][j + 1])
            fourParking.add(parking[i + 1][j + 1])

            if ("#" in fourParking) {
                fourParking.clear()
                continue
            }
            when (fourParking.count { it.contains("X") }) {
                0 -> possibleParking[0] += 1
                1 -> possibleParking[1] += 1
                2 -> possibleParking[2] += 1
                3 -> possibleParking[3] += 1
                4 -> possibleParking[4] += 1
            }

            fourParking.clear()
        }
    }

    return possibleParking
}