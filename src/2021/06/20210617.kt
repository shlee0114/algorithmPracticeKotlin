class Fir_20210617{

    private val STARTING_POINT = 0
    private val END_POINT = 1
    private val SELECT_POINT = 2

    //프로그래머스 문제 : K번째 수
    //array속에 있는 수를 commands에 있는 수 만큼 자른 후 정렬 후 commands에 있는 수를 출력하는 문제
    //array : 임의의 수
    //commands : 자르는 수의 범위와 출력하는 수의 위치 [[시작점, 끝점, 출력]]
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        val answer = ArrayList<Int>()
        for(i in commands){
            val selectedArray = array.copyOfRange(i[STARTING_POINT] - 1, i[END_POINT])
            selectedArray.sort()
            answer.add(selectedArray[i[SELECT_POINT]-1])
        }
        return answer.toIntArray()
    }
}

class Snd_20210617{

    //프로그래머스 문제 : 가장 큰 수
    //배열에 주어진 수를 이어 붙여서 가장 큰 수를 만드는 문제
    //같은 수를 반복해서 이어 붙인 후에 모든 수의 길이가 같아지면 해당 수들을 비교한 다음 해당 수의 키 값으로 원래 배열을 조회한다.
    //그 후 원래 배열의 값들을 이어 붙여 준다
    //만약 첫 수가 0이면 그 뒤의 수또한 0이기에 바로 0으로 반환한다.
    //numbers : 숫자들
    fun solution(numbers: IntArray): String {
        var answer = ""
        val maxValueRange = numbers.maxOrNull().toString().length
        val sortMap = mutableMapOf<Int, String>()
        numbers.forEachIndexed { index, value ->
            sortMap[index] = generateFullText(value.toString(), maxValueRange)
        }

        val sortedList = sortMap.toList().sortedByDescending { it.second }

        if(sortedList[0].first == 0)
            return "0"

        for(i in sortedList){
            answer += numbers[i.first].toString()
        }
        if(answer == "")
            answer = "0"
        return answer
    }

    //현재 숫자와 가장 큰 숫자의 길이를 비교해서 가장 큰 숫자의 길이만큼 붙이는 작업
    //nowValue : 현재 숫자
    //maxRange : 가장 큰 숫자의 길이
    private fun generateFullText(nowValue : String, maxRange : Int) : String{
        val nowValueRange = nowValue.length
        if(nowValueRange < maxRange){
            val roundIndex : Int = maxRange / nowValueRange //현재 수를 몇번 붙여야하는 지
            val additionalIndex = maxRange %  nowValueRange //수를 붙인 후 나머지 추가해줘야하는 짜투리들

            return makeFullValue(nowValue, roundIndex - 1, additionalIndex)
        }
        return nowValue
    }

    //정해진 만큼 반복해서 수를 뒤에 붙이거나 짜투리들을 추가하는 작업
    //value : 현재 수
    //roundIndex : 현재 수를 반복하는 정도
    //additionalIndex : 현재 수의 몇번째까지 추가로 붙여야하는 지의 정도
    private fun makeFullValue(value : String, roundIndex : Int, additionalIndex : Int) : String{
        var fullValue = value
        for(i in 0 until roundIndex){
            fullValue += value
        }
        fullValue += value.substring(0, additionalIndex)
        return fullValue
    }
}


fun main(){
    val test = Fir_20210617()
    val test2 = Snd_20210617()
    println(test.solution(intArrayOf(1, 5, 2, 6, 3, 7, 4), arrayOf(intArrayOf(2, 5, 3), intArrayOf(4, 4, 1), intArrayOf(1, 7, 3))))
    println(test2.solution(intArrayOf(1, 24, 111, 1233, 4333211)))
}