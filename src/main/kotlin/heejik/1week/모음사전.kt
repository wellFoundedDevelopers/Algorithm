package heejik.`1week`

class 모음사전 {

    fun solution(word: String): Int {
        val vowel = arrayListOf<String>("A", "E", "I", "O", "U")
        val li = arrayListOf<String>("A", "E", "I", "O", "U")
        val tmp = arrayListOf<String>()

        repeat(4) {
            li.forEach {
                vowel.forEach { v ->
                    tmp.add(it + v)
                }
            }
            li += tmp
            tmp.clear()
        }
        return li.sorted().toSet().indexOf(word)+1
    }

}

fun main() {
    모음사전().solution("AAA")
}