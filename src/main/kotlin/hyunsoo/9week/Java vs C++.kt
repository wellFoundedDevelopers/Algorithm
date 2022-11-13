package hyunsoo.`9week`

/**
 * <문제>
 * [Java vs C++](https://www.acmicpc.net/problem/3613)
 *
 * 자바형식은 c++로 c++은 자바로 둘 다 아니라면 Error 출력
 */

enum class Format {
    C_PLUS_PLUS, JAVA, ERROR, NORMAL
}

fun main() {

    val variableName = readln()

    when (checkFormat(variableName)) {
        Format.C_PLUS_PLUS -> {
            convertToJava(variableName).apply {
                println(this)
            }
        }

        Format.JAVA -> {
            convertToCpp(variableName).apply {
                println(this)
            }
        }

        Format.ERROR -> {
            println("Error!")
        }

        Format.NORMAL -> {
            println(variableName)
        }

    }

}

fun checkFormat(variableName: String): Format {

    var isCppFormat = false
    var isJavaFormat = false

    if (variableName.first() == '_' ||
        variableName.first().isUpperCase() ||
        variableName.last() == '_'
    ) return Format.ERROR

    if (variableName.contains("_")) {
        isCppFormat = true
    }

    var lastChar = ' '
    variableName.forEach { alphabet ->
        if (lastChar == '_' && alphabet == '_') return Format.ERROR
        lastChar = alphabet
        if (alphabet.isUpperCase()) isJavaFormat = true
    }

    if (isCppFormat && isJavaFormat) {
        return Format.ERROR
    } else if (isCppFormat) {
        return Format.C_PLUS_PLUS
    } else if (isJavaFormat) {
        return Format.JAVA
    }

    return Format.NORMAL
}

fun convertToCpp(variableName: String): String {

    val cppFormatVariableName = StringBuilder()

    variableName.forEach { alphabet ->

        if (alphabet.isUpperCase()) {
            cppFormatVariableName.append('_')
            cppFormatVariableName.append(alphabet.lowercase())
        } else {
            cppFormatVariableName.append(alphabet)
        }
    }

    return cppFormatVariableName.toString()
}

fun convertToJava(variableName: String): String {

    val javaFormatVariableName = StringBuilder()
    var needToConvertUppercase = false

    variableName.forEach { alphabet ->

        if (needToConvertUppercase) {
            javaFormatVariableName.append(alphabet.uppercase())
            needToConvertUppercase = false
        } else if (alphabet == '_') {
            needToConvertUppercase = true
        } else {
            javaFormatVariableName.append(alphabet)
        }
    }

    return javaFormatVariableName.toString()

}