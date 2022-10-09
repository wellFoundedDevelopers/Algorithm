package hyunsoo.`4week`

/**
 * n개의 기차가 어둠을 헤치고 은하수를 건너려고 함.
 * 20개의 일렬로 된 좌석이 있고, 한 개의 좌석에는 한 명의 사람이 탈 수 있다.
 * 기차의 번호는 1 ~ N
 *
 * M 개의 명령이 주어짐.
 *
 * 명령의 종류
 * 1 i x
 *   - 1번째 기차에 x번째 좌석에 사람을 태우기 - 이미 사람이 있으면 무식
 * 2 i x
 *   - i번째 기차에 x번째 좌석에 앉은 사람은 하차 - 사람이 없다면 무시
 * 3 i
 *   - i번째 기차에 앉아있는 승객들이 모두 한칸씩 뒤로간다.
 *   - 20번째 자리에 앉은 사람은 이 명령 후 하차
 * 4 i
 *   - i번째 기차에 앉아있는 승객들이 모두 한칸씩 앞으로간다.
 *   - 1번째 자리에 사람이 앉은 사람은 이 명령 후 하차
 *
 * M번의 명령 후 1번째 기차부터 순서대로 한 기차씩 은하수를 건넌다.
 * 승객이 앉은 상태를 목록에 기록하는데 이미 지나간 기차와 기록이 동일한 기차는 은하수를 건널 수 없음
 */
fun main() {


    val (trainCnt, commandCnt) = readln().split(" ").map { it.toInt() }
    val trainDataList = Array(trainCnt) {
        MutableList(20) { 0 }
    }

    // 지나간 기차의 기록
    val arrivedTrainData = mutableSetOf<MutableList<Int>>()

    repeat(commandCnt) {
        val command = readln().split(" ").map { it.toInt() }
        val kindOfCommand = command.first()
        val targetTrain = command[1] - 1

        when (kindOfCommand) {
            1 -> {
                val targetLocation = command[2] - 1
                if (targetLocation < 20) {
                    trainDataList[targetTrain][targetLocation] = 1
                }
            }

            2 -> {
                val targetLocation = command[2] - 1
                if (targetLocation < 20) {
                    trainDataList[targetTrain][targetLocation] = 0
                }
            }

            3 -> {
                trainDataList[targetTrain] =
                    (mutableListOf(0) + trainDataList[targetTrain].copy().dropLast(1).toMutableList())
                        .toMutableList()
            }

            4 -> {
                trainDataList[targetTrain] =
                    (trainDataList[targetTrain].copy().drop(1).toMutableList() + mutableListOf(0))
                        .toMutableList()
            }
        }
    }

    trainDataList.forEach {
        arrivedTrainData.add(it)
    }

    println(arrivedTrainData.size)

}

fun <T> MutableList<T>.copy(): MutableList<T> {
    val newMutableList = mutableListOf<T>()
    this.forEach {
        newMutableList.add(it)
    }
    return newMutableList
}