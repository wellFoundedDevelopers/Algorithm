/*
<문제>
https://www.acmicpc.net/problem/5555

<구현 방법>
연속적으로 같은 개수를 찾아 개수가 찾고자하는 String과 길이가 같으면 true를 반환하게 함.

<트러블 슈팅>
자꾸 8% 쯤에서 틀렸다고 나와서 반례 찾아봄. -> 아래 반례 틀린거 고치니 해결.

ABCDEFGHIJ
1
BCDEFGHIJA

* */

fun main() {
    val searchString = readLine()
    val num = readLine()?.toInt()
    var rings = arrayListOf<String>()
    var sum = 0
    for (i in 0 until num!!) {
        rings.add(readLine().toString())
        val check = searchStrings(searchString.toString(), rings.last())
        //println(check)
        if (check) sum += 1
    }
    print(sum)
}

fun searchStrings(search: String, ring: String): Boolean {
    //println("case: $ring")
    var matchCheck = 0
    var tmp = 0
    for (i in ring.indices) {
        if (ring[i] == search[0]) {
            for (j in search.indices) {
                tmp = j
                if (i + j < ring.length && ring[i + j] == search[j]) {
                    matchCheck += 1
                    if (matchCheck == search.length) return true
                }else break
            }
            if (matchCheck != 0){
                for (j in tmp until search.length){
                    if (ring[j - tmp] == search[j]){
                        matchCheck += 1
                    }
                    if (matchCheck == search.length) return true
                }
            }
        }
        matchCheck = 0
    }
    return false
}
