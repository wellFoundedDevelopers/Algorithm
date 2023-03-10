package heejik.`26week`

import kotlin.math.ln
import kotlin.math.pow

class `표현 가능한 이진트리` {
    val answers = mutableListOf<Int>()
    var isAdded = false
    fun solution(numbers: LongArray): MutableList<Int> {
        numbers.forEach {
            decimalToBinary(it).setFullBinaryTree().checkBinaryTree()
        }
        return answers
    }

    private fun decimalToBinary(number: Long): StringBuilder {
        var quotient = number
        val binary = StringBuilder()
        while (quotient > 0) {
            binary.insert(0, quotient % 2)
            quotient /= 2
        }
        return binary
    }

    private fun StringBuilder.setFullBinaryTree(): StringBuilder {
        while ((this.length and this.length + 1) != 0) {
            this.insert(0, "0")
        }
        return this
    }

    private fun StringBuilder.checkBinaryTree() {
        val treeHeight = getHeightOfTree(this.length)
        val rootNode = 2.0.pow(treeHeight - 1).toInt() - 1
        val offset = treeHeight - 2

        isAdded = false
        val preAnswerSize = answers.size
        checkBinaryTreeBySubTree(rootNode, offset)
        if (preAnswerSize == answers.size) answers.add(1)

    }


    private fun StringBuilder.checkBinaryTreeBySubTree(rootNode: Int, offset: Int) {
        if (offset < 0) return
        val distanceOfChildTree = 2.0.pow(offset).toInt()
        if (this[rootNode] == '0') {
            if ((this[rootNode - distanceOfChildTree] != '0') or (this[rootNode + distanceOfChildTree] != '0')) {
                if (isAdded.not()) {
                    answers.add(0)
                    isAdded = true
                }
                return
            }
        }


        checkBinaryTreeBySubTree(rootNode - distanceOfChildTree, offset - 1)
        checkBinaryTreeBySubTree(rootNode + distanceOfChildTree, offset - 1)
    }


    fun getHeightOfTree(n: Int): Int {
        return (ln(n.toDouble()) / ln(2.0)).toInt() + 1
    }
}


fun main() {
    val numbers = longArrayOf(63,111,95)
    `표현 가능한 이진트리`().solution(numbers)
}