package hyunsoo.`13week`

/**
 * <문제>
 *
 * [주몽](https://www.acmicpc.net/problem/1940)
 */
fun main() {

    val ingredientCnt = readln().toInt()
    val target = readln().toInt()
    val ingredientList = readln().split(" ").map { it.toInt() }.sorted()

    var count = 0
    var start = 0
    var end = ingredientList.lastIndex

    while (start < end) {

        val sumOfIngredient = ingredientList[start] + ingredientList[end]

        if (sumOfIngredient == target) {
            count++
            start++
        } else if (sumOfIngredient < target) {
            start++
        } else {
            end--
        }
    }

    println(count)
}