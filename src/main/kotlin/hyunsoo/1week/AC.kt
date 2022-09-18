package hyunsoo.`1week`

/*
 * AC는 정수 배열에 연산을 하기 위해 만든 언어이다.
 * - R, D 함수가있음.
 *      - R 함수는 뒤집기
 *          - 배열에 있는 수의 순서를 뒤집음
 *      - D 함수는 버리기
 *          - 첫 번째 수를 버리기.
 *          - 배열이 비어있는데 D를 사용하면 에러 발생.
 *
 * 입/출력
 * - 첫째 줄에는 테스트 케이스의 개수 T가 주어짐. (100 이하)
 *      - 각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어짐.(1 이상 100,000 이하)
 *      - 그 다음에는 배열에 들어있는 수의 개수 n이 주어진다. (0 이상 100,000 이하)
 *      - 그 다음에는 [1,2,3] 형태로 배열에 들어있는 정수가 주어짐.(1이상 100이하)
 *
 *
 * 실패한 아이디어
 * - 함수 문자열을 하나하나 순차적으로 탐색하여 해당 함수의 동작을 하려고 했음.
 *      - 시간초과가 발생할 것이라고 예상
 *      - 실제로 시간초과 발생
 * - 연속된 R, D의 개수를 판단하고 한번에 연산을 하기
 *      - 연속된 R이 짝수면 앞에서 빼기
 *      - 연속된 R이 홀수면 뒤에서 빼기
 *          - D로 먼저 시작하는 경우 반례가 발생
 *
 * 성공한 아이디어
 * - 함수 문자열을 하나하나 순차적으로 탐색하여 R이면 rCnt에 추가
 * - D라면 rCnt가 짝수인지 홀수인지 여부를 확인하고 앞에서 뺄지 뒤에서 뺄지 결정
 *      - rCnt가 짝수라면(ex. 2일경우) 뒤집고 뒤집으니 원위치, 따라서 앞에서 빼기
 *      - rCnt가 홀수라면 뒤에서 빼기
 * - 앞, 뒤 모두에서 빼야하므로 Deque 자료구조를 사용
 *
 */


fun main() {

    val t = readln().toInt()
    val deque = ArrayDeque<String>()

    repeat(t) {

        deque.clear()
        val funString = readln()
        val cnt = readln().toInt()
        val array = readln().replace("[", "").replace("]", "")

        if (cnt > 1) {
            array.split(",").forEach {
                deque.addLast(it)
            }
        } else deque.addLast(array)

        // D의 개수가 숫자의 개수보다 크면 에러
        if (funString.count { it == 'D' } > cnt) {
            println("error")
            return@repeat
        }

        var rCnt = 0
        funString.forEach { curChar ->

            if (curChar == 'D') {

                if (rCnt % 2 == 0) {
                    deque.removeFirst()
                } else {
                    deque.removeLast()
                }

            } else if (curChar == 'R') {
                rCnt++
            }
        }

        if (rCnt % 2 != 0) deque.reverse()
        println(deque.joinToString(",", "[", "]"))

    }

}

