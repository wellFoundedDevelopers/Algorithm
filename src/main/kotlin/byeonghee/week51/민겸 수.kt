package byeonghee.week51

class 소병희_민겸수 {
    companion object {
        fun solve() {
            fun solve() = with(System.`in`.bufferedReader()) {
                var cntM = 0
                val sbMax = StringBuilder()
                val sbMin = StringBuilder()

                readLine().forEach { c ->
                    when(c) {
                        'M' -> {
                            cntM++
                        }
                        'K' -> {
                            sbMax.append(5)

                            if (cntM-- > 0) {
                                sbMax.append(0)
                                sbMin.append(1)
                            }

                            repeat(cntM) {
                                sbMax.append(0)
                                sbMin.append(0)
                            }

                            sbMin.append(5)

                            cntM = 0
                        }
                    }
                }

                if (cntM-- > 0) {
                    sbMax.append(1)
                    sbMin.append(1)
                }

                repeat(cntM) {
                    sbMax.append(1)
                    sbMin.append(0)
                }

                println(sbMax)
                println(sbMin)
            }
        }
    }
}

fun main() {
    소병희_민겸수.solve()
}