package hyunsoo.`26week`

import kotlin.math.absoluteValue

/**
 *
 * <문제>
 * [절댓값 힙](https://www.acmicpc.net/problem/11286)
 *
 * - 아이디어
 * 배열을 활용해서 절댓값 힙을 구현해보자!
 * - 트러블 슈팅
 * 하... 중간에 변수명은 absoluteValue로 해놓고 실제로는 absoluteValue 처리를 안해놔서 3시간 고생...
 */
class `전현수_절댓값_힙` {

    class AbsoluteValueMinHeap(val size: Int) {

        private var lastNodePos = 0
        private val elements = IntArray(size + 1) {
            EMPTY_VALUE
        }

        fun push(element: Int) {
            lastNodePos++
            elements[lastNodePos] = element
            heapify(isSiftDown = false)
        }

        fun pop(): Int {

            if (isEmpty()) return 0

            val rootValue = elements.root
            elements[ROOT] = elements[lastNodePos]
            elements[lastNodePos] = EMPTY_VALUE
            lastNodePos--
            heapify()

            return rootValue
        }

        private fun heapify(isSiftDown: Boolean = true) {
            if (isSiftDown) siftDown()
            else siftUp()
        }

        private fun siftDown() {

            var curPosition = ROOT

            while (curPosition * 2 + 1 <= lastNodePos) {

                val curAbsoluteValue = elements[curPosition].absoluteValue

                val leftChildPosition = curPosition * 2
                val rightChildPosition = curPosition * 2 + 1

                val leftChildAbsoluteValue = elements[leftChildPosition].absoluteValue
                val rightChildAbsoluteValue = elements[rightChildPosition].absoluteValue

                val smallerAbsoluteValueChildPosition =
                    if (leftChildAbsoluteValue == rightChildAbsoluteValue) {

                        val leftChildValue = elements[leftChildPosition]
                        val rightChildValue = elements[rightChildPosition]

                        if (leftChildValue <= rightChildValue) leftChildPosition
                        else rightChildPosition

                    } else if (leftChildAbsoluteValue <= rightChildAbsoluteValue) leftChildPosition
                    else rightChildPosition

                val smallerChildAbsoluteValue = elements[smallerAbsoluteValueChildPosition].absoluteValue

                // 자식 중 절댓값이 더 작은 값보다 현재(부모)의 절댓값 더 작다면 heapify 그만
                if (curAbsoluteValue < smallerChildAbsoluteValue) break
                // 둘의 절댓값이 같다면, 더 작은 자식의 실제 값이 작을 때만 교환
                else if (curAbsoluteValue == smallerChildAbsoluteValue) {

                    val curValue = elements[curPosition]
                    val smallerChildValue = elements[smallerAbsoluteValueChildPosition]

                    if (curValue <= smallerChildValue) break

                }

                elements.swap(curPosition, smallerAbsoluteValueChildPosition)
                curPosition = smallerAbsoluteValueChildPosition

            }
        }

        private fun siftUp() {

            var curPosition = lastNodePos

            while (ROOT < curPosition) {

                val parentPosition = curPosition / 2

                val curAbsoluteValue = elements[curPosition].absoluteValue
                val parentAbsoluteValue = elements[parentPosition].absoluteValue

                // 부모의 절댓값보다 현재(자식)의 절댓값이 크다면 heapify 그만!
                if (parentAbsoluteValue < curAbsoluteValue) break
                // 둘의 절댓값이 같다면, 부모의 실제 값이 더 클 때만 교환
                else if (parentAbsoluteValue == curAbsoluteValue) {

                    val curValue = elements[curPosition]
                    val parentValue = elements[parentPosition]

                    if (parentValue <= curValue) break

                }

                elements.swap(curPosition, parentPosition)
                curPosition = parentPosition

            }

        }

        private fun IntArray.swap(i: Int, j: Int) {
            val tmp = this[i]
            this[i] = this[j]
            this[j] = tmp
        }

        private val IntArray.root: Int
            get() = this[ROOT]

        private fun isEmpty() = lastNodePos == 0

        companion object {
            private const val EMPTY_VALUE = 0
            private const val ROOT = 1
        }
    }


    fun solution() {

        val commandCnt = readln().toInt()
        val heap = AbsoluteValueMinHeap(commandCnt)

        repeat(commandCnt) {

            when (val command = readln().toInt()) {
                COMMAND_POP -> {
                    println(heap.pop())
                }

                else -> {
                    heap.push(command)
                }
            }
        }

    }

    companion object {
        private const val COMMAND_POP = 0
    }
}

fun main() {

    전현수_절댓값_힙().solution()
}