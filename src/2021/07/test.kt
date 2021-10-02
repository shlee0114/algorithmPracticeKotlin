class test {
    //2레벨 테스트
    private val numberCount = mutableMapOf<Int, Int>()

    fun solution(numbers: String): Int {
        var answer = 0
        val number = numbers.toCharArray().sortedDescending()
        val maxNumber = number.toInt()

        number.classificationNumber()

        for(i in 2 .. maxNumber){
            if(i.isPrimeNumber()){
                if(i.toString().toCharArray().checkIsIncluding()){
                    answer++
                }
                number.classificationNumber()
            }
        }

        return answer
    }
    private fun List<Char>.toInt() = joinToString("").toInt()

    private fun List<Char>.classificationNumber(){
        numberCount.clear()
        forEach {
            numberCount[it.toString().toInt()] = (numberCount[it.toString().toInt()]?:0) + 1
        }
    }

    private fun Int.isPrimeNumber() : Boolean{
        var index = 2

        while(lessThanTheSquareRoot(index)){
            if(this % index == 0){
                return false
            }
            index++
        }
        return true
    }

    private fun CharArray.checkIsIncluding() : Boolean{
        forEach {
            numberCount[it.toString().toInt()] = (numberCount[it.toString().toInt()]?:0) - 1
            if(numberCount[it.toString().toInt()]?:0 < 0)
                return false
        }
        return true
    }

    private fun Int.lessThanTheSquareRoot(number : Int) = number * number <= this
}

fun main(){
    val test = test()
    test.solution("17")
}