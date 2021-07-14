
class Fir_20210713{
    //2개 이하로 다른 비트
    //2진법에서 2개 이하로 다른 이진법을 구하는 문제로
    //무조건 +1을 하고 시작한다.
    //구하는 법은 간단하다 2로 나눠지는수들은 +1을 하고
    //나눠지지 않는 수는 0의 위치를 구한 다음 0을 1로 바꾸고 0다음 수가 1개 이상이라면 해당 0바로 다음 수도 0으로 바꾼다.

    fun solution(numbers: LongArray): LongArray {
        val answer = ArrayList<Long>()

        for(i in numbers){
            if(i % 2L == 0L){
                answer.add(i+1)
            }else{
                val mostSimilarNumber = generateSimilarHighNumber(i)

                answer.add(mostSimilarNumber)
            }
        }
        return answer.toLongArray()
    }

    private fun generateSimilarHighNumber(number : Long) : Long{
        val divideData = divideConversionData(number)
        val convertTargetData = divideData[1]
        val similarHighBinary = divideData[0] + minimumIncrement(convertTargetData)

        return convertBinaryToDecimal(similarHighBinary)
    }

    private fun divideConversionData(number : Long) : Array<String>{
        val binary = convertDecimalToBinary(number)
        val conversionPoint = minimumBinaryConversionPoint(binary)
        return binary.divideConversionArea(conversionPoint)
    }

    private fun convertDecimalToBinary(decimal : Long) : String{
        var number = decimal
        var binary = ""
        while(number > 0L){
            binary += number % 2
            number /= 2
        }
        binary += number
        return binary.reversed()
    }

    private fun minimumBinaryConversionPoint(binary : String)  = binary.lastIndexOf("0")

    private fun String.divideConversionArea(conversionPoint : Int) = arrayOf(substring(0, conversionPoint), substring(conversionPoint, length))

    private fun minimumIncrement(binary : String) : String{
        val binaryArray = binary.toCharArray()
        binaryArray[0] = '1'

        if(binaryArray.size > 1)
            binaryArray[1] = '0'

        return binaryArray.joinToString("")
    }

    private fun convertBinaryToDecimal(binary : String) : Long{
        var decimal = 0L
        var unit = 1L
        for(i in binary.reversed()){
            decimal += i.toString().toLong() * unit
            unit *= 2
        }
        return decimal
    }
}

class Snd_20210713{
    //이진 변환 반복하기
    //0 제거 후 남은 1의 개수를 2진법으로 변환 후 1이 될 때 까지 반복

    var totalZeroCount = 0
    var binaryTransformationCount = 0

    fun solution(s: String): IntArray {
        binaryTransformation(s)

        return intArrayOf(binaryTransformationCount, totalZeroCount)
    }

    private fun binaryTransformation(binary : String){
        val restCount = binary.numberOfIncluded('1')
        val zeroCount = binary.numberOfIncluded('0')

        binaryTransformationCount++
        totalZeroCount += zeroCount

        if(restCount == 1)
            return
        else
            binaryTransformation(convertDecimalToBinary(restCount))

    }

    private fun String.numberOfIncluded(checkWord : Char) = toCharArray().filter { it == checkWord }.size

    private fun convertDecimalToBinary(decimal : Int) : String{
        var number = decimal
        var binary = ""

        while(number > 0L){
            binary += number % 2
            number /= 2
        }

        return binary.reversed()
    }
}

class Trd_20210713{
    //쿼드압축 후 세기
    private val totalAnswer = intArrayOf(0,0)
    private var square = arrayOf(intArrayOf())

    fun solution(arr: Array<IntArray>): IntArray {
        square = arr
        quadZip(0,0, square.size, square.size)
        return totalAnswer
    }

    private fun quadZip(startX : Int, startY : Int, endX : Int, endY : Int){
        if(checkIsAllSameNum(startX, startY, endX, endY))
            return
        else{
            val centerX = (startX + endX) / 2
            val centerY = (startY + endY) / 2

            quadZip(startX, startY, centerX, centerY)
            quadZip(centerX, startY,  endX, centerY )
            quadZip(startX, centerY, centerX,  endY)
            quadZip(centerX, centerY, endX, endY)
        }
    }

    private fun checkIsAllSameNum(startX : Int, startY : Int, endX : Int, endY : Int) : Boolean{
        val num = square[startY][startX]

        for(i in startY until endY){
            for(j in startX until endX){
                if(num != square[i][j])
                    return false
            }
        }
        totalAnswer[num]++
        return true
    }
}

class Fth_20210713{
    //순위검색
    private val conformity = mutableMapOf<String, ArrayList<Int>>()

    private val applicantTestLanguage = arrayOf("cpp", "java", "python")
    private val applicantJob = arrayOf("backend", "frontend")
    private val applicantCareer = arrayOf("junior", "senior")
    private val applicantSoulFood = arrayOf("chicken", "pizza")

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = arrayListOf<Int>()

        for(i in info){
            val keyword = i.split(" ")
            val searchKey = "${keyword[0]}${keyword[1]}${keyword[2]}${keyword[3]}"
            val score = keyword[4].toInt()
            insertData(searchKey, score)
        }

        conformity.forEach { (_, arrayList) ->
            arrayList.sort()
            arrayList.reverse()
        }

        for(i in query){
            val desiredInformation = i.replace("and ", "").split(" ")
            answer.add(desiredInformation.findSuitablePerson())
        }

        return answer.toIntArray()
    }

    private fun insertData(searchKey : String, score : Int){
        val scoreArray = if(!conformity.containsKey(searchKey)){
            arrayListOf()
        }else{
            conformity[searchKey]!!
        }

        scoreArray.add(score)
        conformity[searchKey] = scoreArray
    }

    private fun List<String>.findSuitablePerson() : Int{
        val testLanguage = checkAllInclusive(0, get(0))
        val job = checkAllInclusive(1, get(1))
        val career = checkAllInclusive(2, get(2))
        val soulFood = checkAllInclusive(3, get(3))

        var answer = 0
        for(i in testLanguage){
            for(j in job){
                for(h in career){
                    for(k in soulFood){
                        answer += findSuitablePerson("$i$j$h$k",get(4).toInt())
                    }
                }
            }
        }

        return answer
    }

    private fun checkAllInclusive(type : Int, checkString : String) : Array<String>{
        return if(checkString == "-"){
            getAllInclusiveType(type)
        }else{
            arrayOf(checkString)
        }
    }

    private fun getAllInclusiveType(type : Int) = when(type){
        0 -> applicantTestLanguage
        1 -> applicantJob
        2 -> applicantCareer
        3 -> applicantSoulFood
        else -> arrayOf("")
    }

    private fun findSuitablePerson(key : String, goalScore : Int) : Int{
        var answer = 0
        val scoreArray = if(!conformity.containsKey(key)){
            return 0
        }else{
            conformity[key]!!
        }

        for(i in scoreArray.reversed()){
            if(i < goalScore){
                return answer
            }
            answer++
        }

        return answer
    }
}

fun main(){
    val test4 = Fth_20210713()

    test4.solution(arrayOf("java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"),
        arrayOf("java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"))
}