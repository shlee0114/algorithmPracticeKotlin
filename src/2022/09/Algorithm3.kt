package `2022`.`09`

class Algorithm3 {
    fun solution(num: Int, total: Int): IntArray {
        val center = total / num.toFloat()
        return if (num % 2 == 0) {
            (center.toInt() - (num / 2 - 1)..center.toInt() + num / 2)
        } else {
            (center.toInt() - (num - 1) / 2..center.toInt() + (num - 1) / 2)
        }.toList().toIntArray()
    }
}

fun main() {
    Algorithm3()
        .solution(3, 12)
        .map(::print)
}
