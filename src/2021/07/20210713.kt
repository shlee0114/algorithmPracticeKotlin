
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

    private val programingLanguage = mutableMapOf<String, ArrayList<Int>>()
    private val jobGroup = mutableMapOf<String, ArrayList<Int>>()
    private val career = mutableMapOf<String, ArrayList<Int>>()
    private val soulFood = mutableMapOf<String, ArrayList<Int>>()
    private val score = arrayListOf<Int>()

    private var degreeOfConformity = arrayOf<Int>()

    private var passConformityCheck = arrayListOf<Int>()

    fun solution(info: Array<String>, query: Array<String>): IntArray {
        val answer = arrayListOf<Int>()

        for(i in info.indices){
            info[i].replace(",", "").split(" ").insertData(i)
            passConformityCheck.add(i)
        }

        for(i in query){
            degreeOfConformity = Array(info.size){0}
            val desiredInformation = i.replace("and ", "").split(" ")
            answer.add(desiredInformation.conformityCheck())
        }

        return answer.toIntArray()
    }

    private fun List<String>.insertData(index : Int){
        programingLanguage.insertData(get(0), index)
        jobGroup.insertData(get(1), index)
        career.insertData(get(2), index)
        soulFood.insertData(get(3), index)
        score.add(get(4).toInt())
    }

    private fun MutableMap<String, ArrayList<Int>>.insertData(id : String, data : Int){
        if(!containsKey(id)){
            set(id, arrayListOf())
        }

        val nowList = get(id)?.apply {
            add(data)
        }

        set(id, nowList!!)
    }

    private fun List<String>.conformityCheck() : Int{
        val programingLanguageConformity = if(get(0) == "-") passConformityCheck else programingLanguage[get(0)] ?: return 0
        val jobGroupConformity = if(get(1) == "-") passConformityCheck else jobGroup[get(1)] ?: return 0
        val careerConformity = if(get(2) == "-") passConformityCheck else career[get(2)] ?: return 0
        val soulFoodConformity = if(get(3) == "-") passConformityCheck else soulFood[get(3)] ?: return 0

        programingLanguageConformity.addDegreeOfConformity()
        jobGroupConformity.addDegreeOfConformity()
        careerConformity.addDegreeOfConformity()
        soulFoodConformity.addDegreeOfConformity()

        return degreeOfConformity.scoreCheck(get(4).toInt())
    }

    private fun ArrayList<Int>.addDegreeOfConformity(){
        forEach {
            degreeOfConformity[it]++
        }
    }

    private fun Array<Int>.scoreCheck(goalScore : Int) : Int{
        var totalCount = 0
        forEachIndexed { index, i ->
            if(i == 4){
                if(score[index] >= goalScore){
                    totalCount++
                }
            }
        }
        return totalCount
    }
}

fun main(){
    val test4 = Fth_20210713()

    test4.solution(arrayOf("cpp backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","cpp backend junior chicken 80","python backend senior chicken 50"),
        arrayOf("java and backend and junior and pizza 100\",\"python and frontend and senior and chicken 200\",\"cpp and - and senior and pizza 250\",\"- and backend and senior and - 150\",\"- and - and - and chicken 100\",\"- and - and - and - 150"))
}