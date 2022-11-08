package heejik.`8week`

class 쇠막대기 {

    fun solve() {
        var cnt = 0
        var answer = 0
        readln().run {
            forEachIndexed { index, c ->
                if (index == this.length -1 ) return@forEachIndexed
                if (c == '(') {
                    if (this[index +1] == ')') {
                        answer += cnt
                    } else {
                        cnt += 1
                    }
                } else {
                    if (this[index -1] == '('){
                        return@forEachIndexed
                    } else{
                        answer += 1
                        cnt -= 1
                    }
                }
            }
        }
        println(answer + 1)
    }
}


fun main() {
    쇠막대기().solve()
}