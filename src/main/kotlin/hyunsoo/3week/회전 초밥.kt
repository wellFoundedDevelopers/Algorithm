package hyunsoo.`3week`

/**
 * 벨트 위에는 같은 종류의 초밥이 둘 이상 있을 수 있음.
 * 장사가 잘 안되어서 행사를 진행함.
 * - 벨트의 임의의 한 위치부터 k개의 접시를 연속으로 먹으면 할인된 정액 가격으로 제공
 * - 초밥의 종류 하나 쓰인 쿠폰을 발행, 위의 행상에 참여할 경우 이 쿠폰에 적혀진 초밥하나 무료로 제공.
 *      - 벨트에 해당하는 초밥이 없으면 요리사가 새로 만들어서 제공
 *
 * 입/출력
 * - 첫 번째 줄
 *      - 초밥 벨트에 놓인 접시의 수 n, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
 * - 두 번째 줄
 *      - N개의 줄만큼 초밥의 종류를 나타내는 1 ~ d 사이의 정수가 주어짐
 *
 *
 * 아이디어
 * - 회전하는 부분에 대한 처리는 어떻게 할까.. 그냥 리스트로하고 인덱스를 초과하면 크기만큼 빼주는 것도 좋은 듯
 *      - 원래는 동일한 리스트 두개를 이어붙이려고했음.
 * - 그냥 인덱스 완전 탐색하고 시작 인덱스 ~ 연속으로 먹어야하는 갯수만큼 set에 추가
 *      - set의 size == 먹을 수 있는 초밥의 가짓수
 */
fun main() {

    val (plateCnt, sushiKindCnt, seqCnt, coupon) = readln().split(" ").map { it.toInt() }

    val sushiList = mutableListOf<Int>()
    val tempSushiKindCnt = mutableSetOf<Int>()
    var maxKindOfSushiCnt = 0

    repeat(plateCnt) {
        sushiList.add(readln().toInt())
    }

    for (index in 0 until plateCnt) {
        tempSushiKindCnt.add(coupon)
        repeat(seqCnt) { seq ->

            val sushiIndex =
                if (index + seq < plateCnt) {
                    index + seq
                } else index + seq - plateCnt
            tempSushiKindCnt.add(sushiList[sushiIndex])
        }

        if (tempSushiKindCnt.size > maxKindOfSushiCnt) maxKindOfSushiCnt = tempSushiKindCnt.size

        tempSushiKindCnt.clear()
    }

    println(maxKindOfSushiCnt)

}