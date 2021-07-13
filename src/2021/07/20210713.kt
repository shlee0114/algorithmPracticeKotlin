
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

fun main(){
    val test = Fir_20210713()
    val test2 = Snd_20210713()

    test.solution(longArrayOf(2,7))
    test2.solution("1111111")
}