package jimin.`2week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
https://www.acmicpc.net/problem/17413

<구현 방법>
입력한 문자열에 " "를 붙여서 모든 단어뒤에는 공백이 올 수 있도록 했다.
현재가 태그상태인지 아닌지 구분하기 위해 tagBoolean을 사용해서
태그일 경우에는 그냥 합쳐지도록 하였고,
문자일 경우에는 reversed해서 합치도록 하였다.

<트러블 슈팅>
처음에는 문자열 초기화를 ""이렇게 해주고 문자열에 더할 때 += 이런 식으로 진행하였다.
그렇게 하니 시간초과가 떴다.
https://wonnyhouse.tistory.com/246
StringBuilder()를 써서 문자열을 만들고 append로 추가하면 시간 단축에 용이하다고 한다.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sentence = br.readLine() + " "
    var tagBoolean = false
    var tagString = java.lang.StringBuilder()
    var word = java.lang.StringBuilder()
    var result = java.lang.StringBuilder()
    for (s in sentence) {
        when (s) {
            '<' -> {
                tagBoolean = true
                result.append(word.reversed())
                word = java.lang.StringBuilder()
            }
            '>' -> {
                tagBoolean = false
                result.append("<$tagString>")
                tagString = java.lang.StringBuilder()
            }
            ' ' -> {
                if (!tagBoolean) {
                    result.append("${word.reversed()} ")
                    word = java.lang.StringBuilder()
                } else {
                    tagString.append(s)
                }

            }
            else -> {
                when (tagBoolean) {
                    true -> {
                        tagString.append(s)
                    }
                    false -> {
                        word.append(s)
                    }
                }
            }
        }
    }
    println(result)

}