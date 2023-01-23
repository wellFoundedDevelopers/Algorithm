package jimin.`19week`

/*
<문제>
[2xn 타일링](https://www.acmicpc.net/problem/11726)

<구현 방법>
dp를 이용해서 풀었다.
1칸 전은 2x1밖에 안들어가고,
2칸 전은 2x1이 두개 들어간 것은 위에서 들어갔기 때문에, 1x2가 들어가는 경우만 생각했다.
따라서 tiles[i-1] + tiles[i-2] 로 tiles[i]를 구했다

<트러블 슈팅>
처음에는 같은 것이 있는 순열이기 때문에 팩토리얼을 구하는 함수를 만들어서
사용했지만, 숫자가 커지니까 제대로 값이 안나왔다.
*/

fun main() {
    val n = readln().toInt()
    val tiles = MutableList<Int>(n + 1) { 0 }
    tiles[1] = 1
    if (n == 1) {
        println(tiles[n])
        return
    }
    tiles[2] = 2
    for(i in 3 .. n) {
        tiles[i] = (tiles[i-1] + tiles[i-2]) % 10007
    }
    println(tiles[n])
}