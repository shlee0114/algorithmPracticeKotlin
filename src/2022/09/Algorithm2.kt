package `2022`.`09`

class Algorithm2 {
    fun solution(common: IntArray): Int {
        var checkArithmeticSequence = false
        var arithmeticDepth = -1
        var geometricDepth = -1f
        common
            .reduce { before, after ->
                if (arithmeticDepth == -1) {
                    arithmeticDepth = after - before
                } else {
                    checkArithmeticSequence = arithmeticDepth == after - before
                }

                if (geometricDepth == -1f) {
                    geometricDepth = after / before.toFloat()
                }

                after
            }

        return if (checkArithmeticSequence) {
            common.last() + arithmeticDepth
        } else {
            (common.last() * geometricDepth).toInt()
        }
    }
}

fun main() {
    Algorithm2()
        .solution(
            intArrayOf(-8, -4, -2)
        ).let(::print)
}
