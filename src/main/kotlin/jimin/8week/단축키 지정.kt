package jimin.`8week`

import java.io.BufferedReader
import java.io.InputStreamReader

/*
<문제>
[단축키 지정] https://www.acmicpc.net/problem/1283

<구현 방법>
먼저 단어들의 첫번째 글자를 확인하고,
첫번째 글자가 단축키로 지정이 안되면, 전체 글자를 확인한다.
이때 띄어쓰기가 앞뒤로 잘못 들어갈 수 있어서 trim으로 공백을 빼주었다.

<트러블 슈팅>
 */


fun main(): Unit = with(BufferedReader(InputStreamReader(System.`in`))) {
    val wordList = mutableListOf<String>()
    val shortcut = mutableListOf<String>()
    repeat(readLine().toInt()) {
        val words = readLine().split(" ")
        run {
            words.map { it[0] }.forEachIndexed { index, c ->
                if (shortcut.none { it.equals(c.toString(), true) }) {
                    shortcut.add(c.toString())
                    wordList.add(
                        words.subList(0, index).joinToString(" ")
                                + " [" + c + "]"
                                + words[index].substring(1)
                                + " "
                                + words.subList(index + 1, words.size).joinToString(" ")
                    )
                    return@run
                }
            }
            run {
                words.forEachIndexed { index1, word ->
                    word.forEachIndexed { index2, c ->
                        if (shortcut.none { it.equals(c.toString(), true) }) {
                            shortcut.add(c.toString())
                            wordList.add(
                                words.subList(0, index1).joinToString(" ") + " " +
                                        word.substring(0, index2) + "[" + c + "]" + word.substring(index2 + 1) + " " +
                                        words.subList(index1 + 1, words.size).joinToString(" ")
                            )
                            return@run
                        }
                    }
                }
                wordList.add(words.joinToString(" "))
            }

        }
    }
    wordList.forEach {
        println(it.trim())
    }
}