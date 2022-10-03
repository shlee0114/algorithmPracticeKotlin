package `2022`.`10`

class Algorithm {
    fun solution(array: IntArray) = array
        .groupBy { it }
        .map { it.value.size to it.key }
        .sortedByDescending { it.first }
        .let {
            if (it.size == 1) it.first().second
            else if (it.first().first == it[1].first) -1
            else it.first().second
        }
}

fun main() {
    Algorithm()
        .solution(intArrayOf(1, 2, 3, 3, 3))
}
