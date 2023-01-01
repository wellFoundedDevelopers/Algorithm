package byeonghee.`13week`

import java.io.*

class 소병희_DNA {

    companion object {
        val br = BufferedReader(InputStreamReader(System.`in`))
        var hammingDist = 0
        val answer = StringBuilder()

        fun solve() {
            val (n, m) = br.readLine().split(" ").map { it.toInt() }
            val hamming = Array(4) { IntArray(m) }
            val dnaList = Array(n) { br.readLine() }

            for (dna in dnaList) for (j in dna.indices) {
                hamming[convertNucleotideToNumber(dna[j])][j]++
            }

            for (j in 0 until m) {
                var max = 0
                for (i in 1 until 4) {
                    if (hamming[i][j] > hamming[max][j]) {
                        max = i
                    }
                }
                hammingDist += n - hamming[max][j]
                answer.append(convertNumberToNucleotide(max))
            }

            println(answer)
            println(hammingDist)
        }

        fun convertNucleotideToNumber(c : Char): Int {
            return when (c) {
                'A' -> 0
                'C' -> 1
                'G' -> 2
                else -> 3
            }
        }

        fun convertNumberToNucleotide(i : Int): Char {
            return when (i) {
                0 -> 'A'
                1 -> 'C'
                2 -> 'G'
                else -> 'T'
            }
        }
    }
}

fun main() {
    소병희_DNA.solve()
}