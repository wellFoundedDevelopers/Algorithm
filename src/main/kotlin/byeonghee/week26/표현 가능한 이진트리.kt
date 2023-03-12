package byeonghee.week26

class `소병희_표현 가능한 이진트리` {

    val sb = StringBuilder()

    fun solution(numbers: LongArray): IntArray {
        var answer: IntArray = IntArray(numbers.size)

        var trimmedSize = 0
        var perfectSize = 0

        for(i in numbers.indices) {
            sb.clear()
            sb.append(java.lang.Long.toBinaryString(numbers[i]))

            trimmedSize = sb.length
            perfectSize = getTreeSize(trimmedSize)
            sb.insert(0, "0".repeat(perfectSize - trimmedSize))
            answer[i] = checkSubTree(0, perfectSize - 1, 1)
        }

        return answer
    }

    fun getTreeSize(len: Int) : Int {
        var ret = 1
        while(ret <= len) {
            ret *= 2
        }
        return ret - 1
    }

    fun checkSubTree(s: Int, e: Int, parent: Int) : Int {
        val mid = (s + e) / 2
        val root = sb[mid] - '0'
        if (root == 1 && parent == 0) return 0
        if (s == e) return 1

        if (checkSubTree(s, mid - 1, root) == 0) return 0
        return checkSubTree(mid + 1, e, root)
    }
}