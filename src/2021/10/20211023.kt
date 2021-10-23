package `2021`.`10`

class Fir {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        val answer = arrayListOf<Int>()

        for(i in left .. right){


            val quotient = (i / n) + 1
            val remainder = (i % n) + 1

            val result = if(quotient > remainder) quotient else remainder

            answer.add(result.toInt()  )
        }

        return answer.toIntArray()
    }
}

fun main() {
    val test = Fir()

    test.solution(3,2,5)
}