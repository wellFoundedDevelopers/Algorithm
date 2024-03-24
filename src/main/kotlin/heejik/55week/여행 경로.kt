package heejik.`55week`

class `여행 경로` {
    var ticketSize = 0
    var answer = listOf<String>()
    lateinit var tickets: Array<Array<String>>
    fun solution(_tickets: Array<Array<String>>): Array<String> {
        tickets = _tickets
        ticketSize = tickets.size

        dfs(start = "ICN", visitedIdxArray = BooleanArray(size = ticketSize), visitedContry = listOf("ICN"))

        return answer.toTypedArray()
    }

    private fun dfs(start: String, visitedIdxArray: BooleanArray, visitedContry: List<String>) {
        if (visitedContry.size == ticketSize + 1) {
            if (answer.isEmpty()) {
                answer = visitedContry
                return
            }
            var v = ""
            var a = ""
            for (i in visitedContry.indices) {
                v += visitedContry[i]
                a += answer[i]
            }
            if (v < a) {
                answer = visitedContry
            }
        }

        for (i in tickets.indices) {
            if (visitedIdxArray[i]) continue
            if (tickets[i][0] == start) {
                visitedIdxArray[i] = true
                dfs(
                    start = tickets[i][1], visitedIdxArray = visitedIdxArray, visitedContry =
                    visitedContry.plus(tickets[i][1])
                )
                visitedIdxArray[i] = false
            }
        }
    }
}