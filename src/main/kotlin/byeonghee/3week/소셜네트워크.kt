package byeonghee.`3week`

/**
 * 비트마스킹으로 팔로잉 관계 표현
 * 한 번 팔로잉 확장 -> N^2
 */

import java.io.*

class  `소셜네트워크_소병희` {
    companion object {
        fun getSolution() : Solution {
            return Solution()
        }
    }

    class Solution {
        val br = BufferedReader(InputStreamReader(System.`in`))

        var N = 0
        var M = 0
        var X = 0
        var Y = 0

        var areAllFriend = 0UL
        var union = 0UL

        var plusCount = 0
        val records = mutableListOf<Int>()

        val friends = MutableList(51){ 0UL }
        val newFriends = MutableList(51){ 0UL }

        fun countOnes(num: ULong) : Int {
            var mask = 1UL
            var ones = 0
            while(num >= mask) {
                if (num and mask != 0UL) {
                    ones++
                }
                mask = mask shl 1
            }
            return ones
        }

        fun solution() {
            br.readLine().split(" ").run {
                N = first().toInt()
                M = last().toInt()
            }

            areAllFriend = (1UL shl N) - 1UL
            repeat(N) { i ->
                friends[i + 1] = (1UL shl i)
            }

            repeat(M) {
                br.readLine().split(" ").run {
                    X = first().toInt()
                    Y = last().toInt()
                }

                friends[X] = friends[X] or (1UL shl (Y-1))
                friends[Y] = friends[Y] or (1UL shl (X-1))
            }

            while(friends.count { it == areAllFriend } < N) {
                plusCount = 0
                repeat(N) { newFriends[it+1] = friends[it+1] }

                for(i in 1..N) for(j in 1..N) {
                    if ( i == j || friends[i] and (1UL shl (j-1)) == 0UL) continue

                    if (newFriends[i] or friends[j] == newFriends[i]) continue

                    union = newFriends[i] or friends[j]
                    plusCount += countOnes((union xor newFriends[i]))
                    newFriends[i] = newFriends[i] or union
                }
                records.add(plusCount / 2)

                repeat(N) { friends[it+1] = newFriends[it+1] }
            }

            println(records.size)
            records.forEach { println(it) }
        }
    }
}

fun main() {
    `소셜네트워크_소병희`.getSolution().solution()
}