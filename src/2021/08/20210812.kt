class Fir_20210812{
    //상호평가
    fun solution(scores: Array<IntArray>): String {
        var answer = ""

        val setPoint = Array(scores.size) {IntArray(scores.size)}

        for(i in scores.indices){
            for(j in scores[i].indices){
                setPoint[j][i] = scores[i][j]
            }
        }

        for(i in setPoint.indices){
            val selfEvaluation = setPoint[i][i]
            var count = setPoint.size
            setPoint[i].sort()
            var totalEvaluation = setPoint[i].sum()
            if(setPoint[i].first() == selfEvaluation || setPoint[i].last() == selfEvaluation){
                if(setPoint[i].filter { it == selfEvaluation }.size == 1){
                    totalEvaluation -= selfEvaluation
                    count--
                }
            }
            val average = totalEvaluation / count
            answer += enterGrades(average)
        }

        return answer
    }

    private fun enterGrades(grades : Int) = when{
        grades >= 90 -> 'A'
        grades >= 80 -> 'B'
        grades >= 70 -> 'C'
        grades >= 50 -> 'D'
        else -> 'F'
    }
}

fun main(){
    val test = Fir_20210812()
    test.solution(arrayOf(intArrayOf(100,90,98,88,65),intArrayOf(50,45,99,85,77),intArrayOf(47,88,95,80,67),intArrayOf(61,57,100,80,65),intArrayOf(24,90,94,75,65)))
}