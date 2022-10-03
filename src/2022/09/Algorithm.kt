package `2022`.`09`

class Algorithm {
    private val language = arrayOf("aya", "ye", "woo", "ma")
    private val dont = arrayOf("ayaaya", "yeye", "woowoo", "mama")
    fun solution(babbling: Array<String>): Int {
        return babbling
            .filter { babble ->
                dont
                    .forEach {
                        if (babble.contains(it)) {
                            return@filter false
                        }
                    }
                true
            }
            .count { babble ->
                babble.length == language
                    .sumOf {
                        babble.getContainsCount(it) * it.length
                    }
            }
    }

    private fun String.getContainsCount(text: String): Int {
        var lastIndex = 0
        var containsCount = 0
        do {
            lastIndex = indexOf(text, lastIndex)
                .takeUnless { it == -1 }
                ?.let {
                    it + 1
                } ?: return containsCount
            containsCount++
        } while (lastIndex in 1 until length)

        return containsCount
    }
}

fun main() {
    Algorithm().solution(arrayOf("aya", "yee", "u", "maa"))
        .let(::print)
}
