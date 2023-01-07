package hyunsoo.`17week`

/**
 *
 * <문제>
 * [추월](https://www.acmicpc.net/problem/2002)
 *
 * 대근이는 입구, 영식이는 출구
 *
 * - 아이디어
 * 들어갈 때랑 나올 때를 비교해서 판정하기
 * -- 완탐을 해도 10의 6승이니까 할만할 듯? <- 입/출력 작업까지 생각하면 긴가민가
 * - 내 앞에 있는 차들의 리스트를 만들어 놓고 나올 때 내 앞에 있는 차가 하나라도 사라졌다면 추월한 것?
 *
 *
 * - 트러블 슈팅
 * -- 처음의 인덱스보다 후의 인덱스가 앞서 있다면 추월한 것?
 * 처음에는 순차 탐색하면서 차의 종료 위치가 시작보다 앞서있으면 추월한 것이라고 생각했음.
 */

data class CarData(val carNum: String, val frontCarList: List<String>)

fun main() {

    val carCnt = readln().toInt()
    val startCarList = mutableListOf<CarData>()
    val endCarList = mutableListOf<CarData>()
    var overtakeCnt = 0

    repeat(carCnt) {
        val curCarNum = readln()
        val frontCarList = startCarList.map { it.carNum }
        startCarList.add(
            CarData(
                curCarNum,
                frontCarList
            )
        )
    }

    repeat(carCnt) {
        val curCarNum = readln()
        val frontCarList = endCarList.map { it.carNum }
        endCarList.add(
            CarData(
                curCarNum,
                frontCarList
            )
        )
    }

    endCarList.forEachIndexed { index, carData ->

        val curCarNum = carData.carNum
        val frontCarListWhenEnd = carData.frontCarList

        val frontCarListWhenStart = startCarList.find { it.carNum == curCarNum }!!.frontCarList

        frontCarListWhenStart.forEach {
            if (frontCarListWhenEnd.contains(it).not()) {
                overtakeCnt++
                return@forEachIndexed
            }
        }

    }

    println(overtakeCnt)
}