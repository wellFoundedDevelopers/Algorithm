package heejik.`9week`

class `Java vs C++` {

    data class JAVA(val variable: String) {
        var cpp = ""
        fun toCPP(): String {
            variable.forEach { c ->
                if (c.isUpperCase()) {
                    cpp += '_'
                    cpp += c.lowercaseChar()
                } else {
                    cpp += c
                }
            }
            return cpp
        }
    }

    data class CPP(val variable: String) {
        var java = ""
        fun toJava(): String {
            var isUpper = false
            variable.forEach { c ->
                if (c == '_') {
                    isUpper = true
                } else if (isUpper) {
                    java += c.uppercaseChar()
                    isUpper = false
                } else {
                    java += c
                }
            }
            return java
        }
    }

    fun solve() {
        readln().run {
            if (this.first().isLetter().not() || this.first().isLowerCase().not() || this.last() == '_' || this.contains("__")) {
                println("Error!")
            } else if (this.contains('_') && this.none { it.isUpperCase() }) { // CPP asd_asd
                println(CPP(this).toJava())
            } else if (this.contains('_').not()){  // JAVA
                println(JAVA(this).toCPP())
            } else {
                println("Error!")
            }
        }
    }
}


fun main() {
    `Java vs C++`().solve()
}