package hyunsoo.`3week`

/**
 * 친구의 친구는 나의 친구
 * A와 B의 친구라면 B도 A의 친구?.. 말이 왜이래?
 *
 * 입/출력
 * - 첫째 줄
 *      - 사람의 수 N(1 ~ 50)과 처음 친구 관계의 수 M(1 ~ n * (n-1)/ 2)이 주어짐.
 * - 둘째 줄부터 M개의 줄
 *      - 두 정수 A, B (1 ≤ A ≤ N, 1 ≤ B ≤ N, A < B)
 *
 * 아이디어
 * - 양방향 그래프이므로 2차원 배열을 사용하고 [a] = b / [b] = a 형식으로 그래프 정보를 저장
 * - 매번 친구관계들을 모두 확인해서 친구로 저장
 * - 새로운 친구 관계 판정은 어떻게 해야할까
 *      - 관계를 Pair로 묶은 후 Set을 통해 관리하고 한 번에 계산하자!
 *      - 그렇지 않고 반복문 내부에서 리스트를 직접 조작하면 Exception in thread "main" java.util.ConcurrentModificationException 빌셍
 */


fun main() {

    val (n, m) = readln().split(" ").map { it.toInt() }

    // 친구 관계 데이터가 담길 그래프
    val friendDataGraph = Array(n + 1) { mutableListOf<Int>() }
    // 새로운 관계를 임시로 담을 리스트
    val newRelationShipList = mutableSetOf<Pair<Int, Int>>()
    // - 하루마다 생성되는 친구 관계의 수 - 정답
    val madeRelationShipCntList = mutableListOf<Int>()

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        friendDataGraph[a].add(b)
        friendDataGraph[b].add(a)
    }

    // 모두가 친구가 돨 때까지!
    while (isEveryoneFriend(friendDataGraph, n - 1).not()) {
        // 친구 관계도를 순차 탐색
        friendDataGraph.forEachIndexed { target, targetfriendList ->

            // 타겟의 친구들을 순차 탐색
            targetfriendList.forEach { myfriend ->

                // 타겟의 친구의 친구들을 순차 탐색
                friendDataGraph[myfriend].forEach { friendOfFriend ->
                    // 친구의 친구가 친구가 아니었다면
                    if ((friendOfFriend in targetfriendList).not() && target != friendOfFriend) {
                        // 새로운 관계(새 친구)!
                        val newRelationShip = makeSortedRelationShip(target, friendOfFriend)
                        newRelationShipList.add(newRelationShip)
                    }
                }
            }
        }

        // 새로운 관계 적용
        newRelationShipList.forEach { relationShipData ->
            val (a, b) = relationShipData
            friendDataGraph[a].add(b)
            friendDataGraph[b].add(a)
        }
        // 하루마다 만들어지는 새로운 관계의 수를 저장
        madeRelationShipCntList.add(newRelationShipList.size)
        // 새로운 관계를 모두 적용했으므로 초기화
        newRelationShipList.clear()

    }

    // 정답형식에 맞게 출력
    madeRelationShipCntList.apply {
        println(this.size)
        this.forEach {
            println(it)
        }
    }


}

// 더 작은 순서가 앞으로 나오게
fun makeSortedRelationShip(who: Int, andWho: Int) =
    if (who < andWho) Pair(who, andWho) else Pair(andWho, who)

// 모두가 친구인가?
fun isEveryoneFriend(friendDataGraph: Array<MutableList<Int>>, friendCnt: Int): Boolean {

    friendDataGraph.forEachIndexed { index, friendList ->
        if (index != 0 && friendList.size != friendCnt) return false
    }

    return true
}