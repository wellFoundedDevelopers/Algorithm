package byeonghee.`4week`

import java.io.*

class `소병희_놀라운 문자열` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var line = ""
        val dPairs = mutableSetOf<String>()
        var dPair = ""
        var isSurprising = true

        fun solution() {
            line = br.readLine()
            while(line != "*") {
                d@ for(d in 1 until line.length) {
                    dPairs.clear()
                    s@ for(i in 0 until line.length - d) {
                        dPair = "${line[i]}${line[i + d]}"
                        if (dPairs.contains(dPair)) {
                            isSurprising = false
                            break@d
                        }
                        else dPairs.add(dPair)
                    }
                }

                println("$line is ${if (isSurprising) "" else "NOT "}surprising.")

                isSurprising = true
                line = br.readLine()
            }
        }
    }
}

fun main() {
    `소병희_놀라운 문자열`.getSolution().solution()
}