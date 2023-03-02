package jimin.`25week`

/*
<문제>
[오큰](https://www.acmicpc.net/problem/17298)

<구현 방법>
숫자들을 돌면서 현재의 숫자와 stack의 맨 꼭대기 인덱스 위치의 숫자를 비교한다.
현재의 숫자가 더 클 때까지 pop하고 그 위치에 현재의 숫자를 넣어준다. 그 후, 현재의 인덱스를 stack에 추가해준다.
for문이 끝난 후에도 stack에 남아있는 숫자들은 오큰수가 없는 것으로
해당 인덱스 위치에 -1을 출력해주면된다.

<트러블 슈팅>
아래 블로그 참고
https://st-lab.tistory.com/196
 */
class 오큰수 {
    fun solve() {
        val n = readln().toInt()
        val numbers = readln().split(" ").map{ it.toInt() } as MutableList
        val stack = ArrayDeque<Int>()
        numbers.forEachIndexed{ idx,  num ->
            while (stack.isNotEmpty() && numbers[stack.first()] < num) {
                numbers[stack.removeFirst()] = num
            }
            stack.addFirst(idx)
        }
        stack.forEach{
            numbers[it] = -1
        }
        println(numbers.joinToString(" "))
    }
}

fun main() {
    오큰수().solve()
}