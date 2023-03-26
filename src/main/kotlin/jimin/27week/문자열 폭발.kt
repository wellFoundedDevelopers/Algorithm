package jimin.`27week`

/*
<문제>
[문자열 폭발](https://www.acmicpc.net/problem/9935)

<구현 방법>
str 문자열을 forEach로 돌면서 stack에 추가해주고, 스택 사이즈가 폭발 사이즈보다 커지면
폭발과 같은게 있는지 비교해주고, 같으면 폭발만큼 pop해준다.

<트러블 슈팅>
엄청난 메모리 초과~~~
https://uknowblog.tistory.com/47 해당 블로그를 참고했는데도 메모리 초과가 나서 살펴보니,
boom.reversed()로 하고 forEach해준게 문제였다.
reversed()로 새롭게 안만들고 그냥 인덱스 상에서 고쳐주니 해결되었다.......

*/

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class `문자열 폭발` {
    fun solve() {
        val br = BufferedReader(InputStreamReader(System.`in`))
        val str = br.readLine()
        val boom = br.readLine()
        val stack = Stack<Char>()

        val boomLength = boom.length

        str.forEach{ s ->
            stack.add(s)

            if (stack.size >= boomLength) {
                var same = true
                run {
                    boom.forEachIndexed { idx, b ->
                        if (b != stack[stack.size - boomLength + idx]) {
                            same = false
                            return@run
                        }
                    }
                }
                if (same) {
                    repeat(boomLength) {
                        stack.pop()
                    }
                }
            }
        }

        if (stack.isEmpty()){
            println("FRULA")
        } else {
            val strBuilder = StringBuilder()
            stack.forEach{
                strBuilder.append(it)
            }
            println(strBuilder)
        }
    }

}

fun main() {
    `문자열 폭발`().solve()
}