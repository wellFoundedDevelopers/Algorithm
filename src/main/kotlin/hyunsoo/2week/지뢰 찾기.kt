package hyunsoo.`2week`

/**
 *
 * - 지뢰찾기는 n * n 격자에서 이루어짐.
 * - m개의 지뢰가 각각 서로 다른 격자 위에 숨겨져 있음
 * - 지뢰를 건드리면 안되고, 지뢰가 없는 지점을 건드리면,
 *   해당 지점의 상하좌우, 대각선으로 인접한 8개의 칸에 지뢰가 몇 개 있는지 알려주는 0 - 8 범위의 숫자가 등장
 *
 * 아이디어
 * - 지뢰의 정보를 입력받고, 열린 칸의 정보를 입력받음
 * - 열린 칸만큼 지뢰찾기 규칙에 맞춰 근처 지뢰의 개수를 출력하기
 *
 * 실패 원인
 * - 무조건 탐색을 앞에서부터 순차적으로 한다고 생각했음.
 *
 * - 문제를 제대로 읽지 않아서 지뢰를 밟았을 때 판정을 구현하지 않았음.
 *      - 이 습관 고치려고 아이디어를 쓰고있는데... 마음이 급하니 대충읽고 대충 적게되네
 * - 지뢰를 밟았을 경우 지뢰의 위치는 전부 * 아닌 위치는 .으로 표기해야함.
 */

data class MyPos(val x: Int, var y: Int)

// 상하좌우 / 좌상 좌하 우상 우하
val searchDir = listOf(
    MyPos(-1, 0),
    MyPos(1, 0),
    MyPos(0, 1),
    MyPos(0, -1),
    MyPos(-1, -1),
    MyPos(1, -1),
    MyPos(-1, 1),
    MyPos(1, 1)
)

val landmineDataList = mutableListOf<String>()
var didIStepOnMine = false

fun main() {

    val n = readln().toInt()
    val checkedDataList = mutableListOf<MutableList<Char>>()
    val landmineLocationList = mutableListOf<MyPos>()
    // 지뢰 정보 입력받기
    repeat(n) { rowCnt ->

        val landMineData = readln().let {
            it.forEachIndexed { index, char ->
                if (char == '*') landmineLocationList.add(MyPos(rowCnt, index))
            }
            it
        }


        landmineDataList.add(landMineData)
    }

    // 열린 곳의 정보 입력 받기
    repeat(n) { row ->

        // 탐색한 지역(x)의 인덱스가 리스트로 저장됨
        val checkData = readln().let {
            val checkIndexList = mutableListOf<Int>()
            it.forEachIndexed { index, char ->
                if (char == 'x') checkIndexList.add(index)
            }
            checkIndexList
        }

        howManyLandminesAreThere(row, checkData)
            .run { checkedDataList.add(this) }

    }

    if (didIStepOnMine) {
        landmineLocationList.forEach { pos ->
            checkedDataList[pos.x][pos.y] = '*'
        }
    }

    checkedDataList.forEach {
        println(it.joinToString(""))
    }
}

fun howManyLandminesAreThere(rowIndex: Int, checkData: MutableList<Int>): MutableList<Char> {

    val gameData = StringBuilder()

    repeat(landmineDataList.size) { cnt ->
        if (checkData.contains(cnt)) {

            val searchPos = MyPos(rowIndex, cnt)

            if (didIStepOnAMine(searchPos)) didIStepOnMine = true

            gameData.append(search(searchPos))

        } else {
            gameData.append(".")
        }

    }

    return gameData.toMutableList()
}

fun search(startPos: MyPos): String {

    var landMineCnt = 0

    searchDir.forEach { pos ->
        val searchedPosX = startPos.x + pos.x
        val searchedPosY = startPos.y + pos.y

        // 범위를 초과하면 넘어감
        if ((searchedPosX < 0 || searchedPosX > landmineDataList.lastIndex ||
                    searchedPosY < 0 || searchedPosY > landmineDataList.lastIndex)
        ) return@forEach

        if (landmineDataList[searchedPosX][searchedPosY] == '*') landMineCnt++
    }

    return landMineCnt.toString()
}

fun didIStepOnAMine(startPos: MyPos) = landmineDataList[startPos.x][startPos.y] == '*'