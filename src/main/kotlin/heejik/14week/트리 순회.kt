package heejik.`14week`

class `트리 순회` {

    enum class Direction {
        PREORDER, INORDER, POSTORDER,
    }

    data class Node(
        val name: String,
        val leftName: String,
        val rightName: String,
    )

    private val nodes = mutableListOf<Node>()
    private val answers = MutableList(Direction.values().size) { "" }

    fun setting() {
        val n = readln().toInt()

        repeat(n) {
            readln().split(" ").run {
                nodes.add(
                    Node(name = first(), leftName = this[1], rightName = this[2])
                )
            }
        }

        solve()
    }

    fun solve() {
        traverse(nodes.first())
        answers.forEach {
            println(it)
        }
    }

    private fun traverse(node: Node) {
        if (node.name == ".") return

        answers[Direction.PREORDER.ordinal] += node.name

        traverse(nodes.find { it.name == node.leftName } ?: Node(name = ".", leftName = ".", rightName = "."))
        answers[Direction.INORDER.ordinal] += node.name

        traverse(nodes.find { it.name == node.rightName } ?: Node(name = ".", leftName = ".", rightName = "."))
        answers[Direction.POSTORDER.ordinal] += node.name
    }
}

fun main() {
    `트리 순회`().setting()
}