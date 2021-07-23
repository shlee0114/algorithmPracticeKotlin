class Fir_20210723{
    //[카카오 인턴]수식 최대화
    private val numbers = arrayListOf<Long>()
    private val operators = arrayListOf<(operator : Long, operand : Long) -> Long>()
    private val innerOperators = arrayListOf<Char>()
    private val canUsedOperators = arrayOf('+', '-', '*')

    private var largestValue = 0L

    fun solution(expression: String): Long {

        divideToNumberAndOperator(expression)

        for(i in canUsedOperators){
            if(innerOperators.contains(i)){
                val cloneLeftNumbers = arrayListOf<Long>().apply { addAll(numbers) }
                val cloneLeftOperators = arrayListOf<(operator: Long, operand: Long) -> Long>().apply { addAll(operators) }

                findAllCalculationExpressions(cloneLeftNumbers, cloneLeftOperators, arrayListOf(i), i)
            }
        }

        return largestValue
    }

    private fun divideToNumberAndOperator(expression: String){
        var number = ""

        for(i in expression){
            if(i.digitToIntOrNull() != null){
                number += i
            }else{
                numbers.add(number.toLong())
                operators.add(stringToOperator(i))

                if(innerOperators.size < 3 && !innerOperators.contains(i))
                    innerOperators.add(i)

                number = ""
            }
        }
        numbers.add(number.toLong())
    }

    private fun findAllCalculationExpressions(leftNumbers : ArrayList<Long>
                                              , leftOperators : ArrayList<(operator : Long, operand : Long) -> Long>
                                              , usedOperators : ArrayList<Char>
                                              , nowOperator : Char){
        var index = 0
        while(index < leftOperators.size){
            if(leftOperators[index] == stringToOperator(nowOperator)){
                leftNumbers[index] = leftOperators[index](leftNumbers[index], leftNumbers[index + 1])

                finishCalculation(leftNumbers, leftOperators, index + 1)
                index--
            }
            index++
        }

        if(usedOperators.size == innerOperators.size){
            leftNumbers[0].whenIsLargerInsertIntoLargestValue()
            return
        }

        for(i in canUsedOperators){
            if(innerOperators.contains(i) && !usedOperators.contains(i)){
                val cloneUsedOperators = arrayListOf<Char>().apply{ addAll(usedOperators); add(i)}
                val cloneLeftNumbers = arrayListOf<Long>().apply { addAll(leftNumbers) }
                val cloneLeftOperators = arrayListOf<(operator: Long, operand: Long) -> Long>().apply { addAll(leftOperators) }

                findAllCalculationExpressions(cloneLeftNumbers, cloneLeftOperators, cloneUsedOperators, i)
            }
        }

    }

    private fun stringToOperator(operator : Char) = when(operator){
        '+' -> this::plus
        '-' -> this::subtract
        '*' -> this::multiplication
        else -> this::plus
    }

    private fun plus(operator : Long, operand : Long) = operator + operand

    private fun subtract(operator : Long, operand : Long)  = operator - operand

    private fun multiplication(operator : Long, operand : Long)  = operator * operand

    private fun finishCalculation(numberArray : ArrayList<Long>
                                  , operatorArray : ArrayList<(operator : Long, operand : Long) -> Long>
                                  , calculatedIndex : Int){
        numberArray.removeCalculatedNumber(calculatedIndex)
        operatorArray.removeCalculatedOperator(calculatedIndex)
    }

    private fun ArrayList<Long>.removeCalculatedNumber(calculatedIndex : Int){
        removeAt(calculatedIndex)
    }

    private fun ArrayList<(operator : Long, operand : Long) -> Long>.removeCalculatedOperator(calculatedIndex : Int){
        removeAt(calculatedIndex - 1)
    }

    private fun Long.whenIsLargerInsertIntoLargestValue(){
        if(largestValue < this.abs())
            largestValue = this.abs()
    }

    private fun Long.abs(): Long {
        return if(this < 0L)
                this * -1
            else
                this
    }
}

fun main(){
    val test = Fir_20210723()
    test.solution("100-200*300-500+20")
}
