class Fir_20210621{

    //프로그래머스 문제 : 모의고사
    //정해진 3명의 학생이 찍는 답이 얼마나 정답이 많은 지를 측정 후 가장 높은 점수만 출력하는 문제
    //answers : 문제의 답
    fun solution(answers: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val student1 = intArrayOf(1,2,3,4,5)
        val student2 = intArrayOf(2,1,2,3,2,4,2,5)
        val student3 = intArrayOf(3,3,1,1,2,2,4,4,5,5)

        val studentCorrect = intArrayOf(0,0,0)

        val studentLoop = intArrayOf(0,0,0)

        for(i in answers){

            if(i == student1[studentLoop[0]]){
                studentCorrect[0]++
            }
            if(i == student2[studentLoop[1]]){
                studentCorrect[1]++
            }
            if(i == student3[studentLoop[2]]){
                studentCorrect[2]++
            }

            studentLoop[0]++
            studentLoop[1]++
            studentLoop[2]++
            if(studentLoop[0] == 5)
                studentLoop[0] = 0
            if(studentLoop[1] == 8)
                studentLoop[1] = 0
            if(studentLoop[2] == 10)
                studentLoop[2] = 0
        }

        val maxVal = studentCorrect.maxOrNull()

        for(i in studentCorrect.indices){
            if(studentCorrect[i] == maxVal)
                answer.add(i + 1)
        }

        return answer.toIntArray()
    }
}

fun main(){
    val test = Fir_20210621()
    print(test.solution(intArrayOf(1,2,3,4,3,2,1,2,5,1,2,3,4,1,3,4,4,5)))
}