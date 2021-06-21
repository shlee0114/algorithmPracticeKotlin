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

class Snd_20210621{

    private val numberKindsCount = mutableMapOf<Int, Int>()

    //프로그래머스 문제 : 소수 찾기
    //입력된 임의의수를 조합해서 나온 소수의 개수를 구하는 문제
    //나온 숫자를 조합해서 모든 수를 구하는 것보다 최대값을 구하고 각 숫자의 개수를 구해서 맵으로 저장해둔다
    //2부터 최대값까지 루프를 돌리면서 숫자들을 맵으로 변환해서 숫자들 맵과 사용자가 입력한 숫자의 맵을 비교한 후 만약 조건이 충족한다면 소수인지 확인한다.
    //numbers : 사용자가 임의로 넣은 숫자
    fun solution(numbers: String): Int {
        var answers = 0
        val divisionNumber = numbers.toCharArray().sortedDescending()

        var limitNum : Int = divisionNumber[0].digitToInt()
        numberKindsCount[limitNum] = 0
        for(i in 1 until divisionNumber.size){
            val number = divisionNumber[i].digitToInt()

            numberKindsCount.increaseCountOrGenerateCount(number)

            limitNum *= 10
            limitNum += number
        }

        for(i in 2 .. limitNum){
            if(numberKindsCount.numberIsExists(i)){
                if(isPrime(i)){
                    answers++
                }
            }
        }

        return answers
    }

    //해당 숫자가 있는 지 확인하는 작업
    //입력 받은 수를 맵으로 변환 후 데이터를 비교한다 데이터가 없거나 생성한 데이터의 값이 더 놓을 시 false를 리턴한다
    //number : 확인할 숫자
    private fun MutableMap<Int, Int>.numberIsExists(number : Int) : Boolean{
        val tmpNumberKindsCount = generateMapByString(number.toString())

        for(i in tmpNumberKindsCount.keys){
            if(this[i] == null)
                return false
            if(this[i]!! < tmpNumberKindsCount[i]!!)
                return false
        }

        return true
    }

    //입력받은 숫자를 맵으로 분리하는 작업
    //받은 텍스트를 숫자로 변환 후 데이터를 넣는다.
    //number : 숫자
    private fun generateMapByString(number : String) : MutableMap<Int, Int>{
        val tmpNumberKindsCount = mutableMapOf<Int, Int>()
        val tmpNumberArray = number.toCharArray()
        for(i in tmpNumberArray){
            tmpNumberKindsCount.increaseCountOrGenerateCount(i.digitToInt())
        }
        return tmpNumberKindsCount
    }

    //맵에 데이터를 넣는 작업
    //단순 데이터의 개수를 구하는 것이기에 데이터가 있으면 기존 데이터에 1을 더하고 없으면 0으로 생성한다
    //number : 키값(입력값)
    private fun MutableMap<Int, Int>.increaseCountOrGenerateCount(number : Int){
        if(this.containsKey(number)){
            this[number] = this[number]?.plus(1)!!
        }else{
            this[number] = 0
        }
    }

    //소수인지 확인 하는 작업
    //입력받은 숫자의 루트값까지 루프를 돌리면서 나머지가 한 번이라도 있으면 false로 리턴
    //루트값까지만 하는 이유는 루트값을 넘어가는 순간 이 전 숫자와 곱해야지 해당 수가 나오기에 의미가 없다.
    //number : 숫자
    private fun isPrime(number : Int) : Boolean{
        var index = 2
        while (index * index <= number ){
            if(number % index == 0)
                return false
            index++
        }
        return true
    }
}

fun main(){
    val test = Fir_20210621()
    val test2 = Snd_20210621()
    println(test2.solution("17"))
    print(test.solution(intArrayOf(1,2,3,4,3,2,1,2,5,1,2,3,4,1,3,4,4,5)))
}