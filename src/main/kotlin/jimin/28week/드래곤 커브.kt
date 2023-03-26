package jimin.`28week`

/*
<문제>
[드래곤 커브](https://www.acmicpc.net/problem/15685)

<구현 방법>
드래곤 커브의 규칙이 있다.
이전 방향 리스트을 reverse한 것들에 + 1 해서 뒤에 붙이면 이동할 방향들을 구할 수 있다!
이 방향에 맞춰 좌표를 업데이트해주고 4방향 모두 true인 개수를 찾았다.

<트러블 슈팅>
드래곤 규칙을 어떻게 찾는 지 몰라서 구글링했다.
https://yabmoons.tistory.com/60

그림 예제가 x, y가 거꾸로 나타나있어서 헷깔렸다..

*/


class `드래곤 커브` {
    val dragons = MutableList(101) { MutableList(101) { false } }
    val dx = mutableListOf(1, 0, -1, 0)
    val dy = mutableListOf(0, -1,0, 1)
    fun solve() {
        repeat(readln().toInt()) {
            var (x, y, d, g) = readln().split(" ").map { it.toInt() }
            val curves = mutableListOf(d)
            repeat(g) {
                curves.reversed().forEach {
                    curves.add((it + 1) % 4)
                }
            }
            dragons[x][y] = true
            curves.forEach {
                val num = it
                if (x + dx[num] in 0..100 && y + dy[num] in 0..100) {
                    dragons[x + dx[num]][y + dy[num]] = true
                    x += dx[num]
                    y += dy[num]
                }
            }
        }

        var result = 0
        for (i in 0 until 100) {
            for (j in 0 until 100) {
                if (dragons[i][j] && dragons[i + 1][j] && dragons[i][j + 1] && dragons[i + 1][j + 1]) {
                    result += 1
                }
            }
        }
        println(result)
    }
}

fun main() {
    `드래곤 커브`().solve()
}