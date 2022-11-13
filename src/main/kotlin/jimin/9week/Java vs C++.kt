package jimin.`9week`

/*
<문제>
[Java vs C++](https://www.acmicpc.net/problem/3613)

<구현 방법>
_가 앞뒤로 올 때, _가 2개 이상일 때, _와 대문자가 같이 있을 때, 대문자가 맨 처음 올때, 소문자만 있을 때를
Error라고 두고, 이 상황이 아닐 경우에는 반대되는 케이스로 출력하였다.
Error일 때 다 소문자라면 그냥 전체 출력하도록 했다.

<트러블 슈팅>
질문 탭에서 반례를 찾아 해결하였다.
 */

fun main() {
    val naming = readLine()
    var type = "Error!"
    if (naming != null) {
        if (naming.contains('_')) {
            if (naming[0] == '_' || naming[naming.length - 1] == '_' || naming.any { it.isUpperCase() } ||
                    naming.contains("__")) {
                type = "Error!"
            } else {
                type = "C++"
            }
        } else if (naming.filter { it.isUpperCase() }.isNotEmpty()) {
            if (naming[0].isUpperCase()) {
                type == "Error!"
            } else {
                type = "Java"
            }

        }
    }
    when (type) {
        "C++" -> {
            var underBar = false
            naming?.forEach {
                if (underBar) {
                    print(it.uppercaseChar())
                    underBar = false
                } else {
                    if (it.isLowerCase()) {
                        print(it)
                    } else if (it == '_') {
                        underBar = true
                    }
                }
            }
        }

        "Java" -> {
            naming?.forEach {
                if (it.isLowerCase()) {
                    print(it)
                } else {
                    print("_" + it.lowercaseChar())
                }
            }
        }

        "Error!" -> {
            if (naming != null) {
                if (naming.all { it.isLowerCase() }) {
                    println(naming)
                }else {
                    println(type)
                }
            }
        }
    }
}