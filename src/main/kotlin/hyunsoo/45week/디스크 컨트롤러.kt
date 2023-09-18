import java.util.PriorityQueue

class `전현수_디스크_컨트롤러` {

    data class JobInfo(val requestTime: Int, val costTime: Int)

    fun solution(jobs: Array<IntArray>): Int {

        var answer = 0
        var currentTime = 0

        val waitingQueue = PriorityQueue<JobInfo>(
            compareBy { it.requestTime }
        )

        val readyQueue = PriorityQueue<JobInfo>(
            compareBy { it.costTime }
        )

        jobs.forEach {
            waitingQueue.add(JobInfo(it.first(), it.last()))
        }

        while (waitingQueue.isNotEmpty() || readyQueue.isNotEmpty()) {

            for (i in 0 until waitingQueue.size) {
                val currentTask = waitingQueue.poll()
                if (currentTime < currentTask.requestTime) {
                    waitingQueue.add(currentTask)
                    break
                } else {
                    readyQueue.add(currentTask)
                }
            }

            if (readyQueue.isEmpty()) {
                currentTime++
                continue
            }

            if (readyQueue.isNotEmpty()) {
                val currentTask = readyQueue.poll()
                currentTime += currentTask.costTime
                answer += currentTime - currentTask.requestTime

                while (readyQueue.isNotEmpty()) {
                    waitingQueue.add(readyQueue.poll())
                }
            }

        }

        return answer / jobs.size

    }
}

fun main() {
    전현수_디스크_컨트롤러().solution(
        arrayOf(
            intArrayOf(0, 10),
            intArrayOf(4, 10),
            intArrayOf(5, 11),
            intArrayOf(15, 2),
        )
    ).apply {
        println(this)
    }
}