
import java.util.*

//프로그래머스 문제 : N으로 표현
//두 클래스 전부 정답이지만 상단은 내가 직접 푼 것 하단에 잇는 것은 다른 사람이 올린 답을 살짝 수정한 것
//속도 상으로는 아래의 코드가 훨씬 빠르지만 괄호 처리가 안되어있기에 fun main에 있는 예제를 돌려보면 두 개의 답이 다른 것을 확인 할 수 있다.

class Fir_20210628{

    private var target = 0
    private var addNumber = 0
    private var maxCnt = 8
    private var hasAllCalculate = BooleanArray(8){false}


    fun solution(N: Int, number: Int): Int {
        hasAllCalculate = BooleanArray(8){false}
        maxCnt = 8
        target = number
        addNumber = N
        return generateExpressionToUseDp(1, "$N", false)
    }

    private fun generateExpressionToUseDp(cnt : Int, expression : String, whetherParenthesesOpenCount : Boolean) : Int{
        return if(cnt <= maxCnt){
            val result = evaluation(expression, whetherParenthesesOpenCount)
            if(result == target) {
                maxCnt = cnt
                cnt
            }
            else{
                val allCalculators = Array(11){-1}
                if(expression[expression.length - 1] != ')'){
                    if(!hasAllCalculate[0])
                    allCalculators[4] = generateExpressionToUseDp(cnt + 1, "$expression$addNumber", whetherParenthesesOpenCount)
                }
                if(cnt == 1 && !whetherParenthesesOpenCount) {
                    allCalculators[9] = generateExpressionToUseDp(cnt, "( $expression", true)
                    hasAllCalculate[0] = true
                    hasAllCalculate[1] = false
                    hasAllCalculate[2] = false
                    hasAllCalculate[3] = false
                    hasAllCalculate[4] = false
                }
                if(!(cnt == maxCnt - 1 && result > target)){
                    if(!hasAllCalculate[1] )
                        allCalculators[0] = generateExpressionToUseDp(cnt + 1, "$expression + $addNumber", whetherParenthesesOpenCount)
                    if(cnt == 1)
                        hasAllCalculate[1]  = true
                    if(!hasAllCalculate[2] )
                        allCalculators[1] = generateExpressionToUseDp(cnt + 1, "$expression * $addNumber", whetherParenthesesOpenCount)
                    if(cnt == 1)
                        hasAllCalculate[2]  = true
                }
                if(!(cnt == maxCnt - 1 && result <  target)){
                    if(!hasAllCalculate[3])
                    allCalculators[2] = generateExpressionToUseDp(cnt + 1, "$expression - $addNumber", whetherParenthesesOpenCount)
                    if(cnt == 1)
                        hasAllCalculate[3] = true
                    if(!hasAllCalculate[4])
                    allCalculators[3] = generateExpressionToUseDp(cnt + 1, "$expression / $addNumber", whetherParenthesesOpenCount)
                    if(cnt == 1)
                        hasAllCalculate[4] = true
                }
                if(cnt < maxCnt - 1 && !whetherParenthesesOpenCount) {
                    if(!hasAllCalculate[5])
                    allCalculators[5] = generateExpressionToUseDp(cnt + 1, "$expression + ( $addNumber", true)
                    if(cnt == 1)
                        hasAllCalculate[5] = true
                    if(!hasAllCalculate[6])
                    allCalculators[6] = generateExpressionToUseDp(cnt + 1, "$expression - ( $addNumber", true)
                    if(cnt == 1)
                        hasAllCalculate[6] = true
                    if(!hasAllCalculate[7])
                    allCalculators[7] = generateExpressionToUseDp(cnt + 1, "$expression * ( $addNumber", true)
                    if(cnt == 1)
                        hasAllCalculate[7] = true
                    allCalculators[8] = generateExpressionToUseDp(cnt + 1, "$expression / ( $addNumber", true)
                }else if(whetherParenthesesOpenCount && cnt > 1)
                    allCalculators[10] = generateExpressionToUseDp(cnt, "$expression )", false)
                getMinValueExpectNegative(allCalculators)
            }
        }
        else{
            return -1
        }
    }

    private fun evaluation(expression: String, whetherParenthesesOpenCount : Boolean) : Int{
        val completeExpression = if(whetherParenthesesOpenCount)"$expression )" else expression
        val expressionDivide = completeExpression.split(" ")
        val values = Stack<Int>()
        val operators = Stack<String>()

        var index = 0

        while(index < expressionDivide.size){
            if(expressionDivide[index] == "")
                continue
            if(expressionDivide[index].toIntOrNull() != null){
                values.push(expressionDivide[index].toInt())
            }else{
                when(expressionDivide[index]){
                    "(" -> operators.push(expressionDivide[index])
                    ")" -> {
                        if(operators.isNotEmpty()) {
                            while (operators.peek() != "(") {
                                values.push(operators.pop().calculateAction(values.pop(), values.pop()))
                            }

                            operators.pop()
                        }
                    }
                    else -> {
                        while(operators.isNotEmpty() && hasPrecedence(expressionDivide[index], operators.peek())){
                            values.push(operators.pop().calculateAction(values.pop(), values.pop()))
                        }

                        operators.push(expressionDivide[index])
                    }
                }
            }
            index++
        }
        if(values.size == 1)
            return values.pop()
        while(operators.isNotEmpty()){
            values.push(operators.pop().calculateAction(values.pop(), values.pop()))
        }

        return values.pop()
    }

    private fun String.calculateAction(data1 : Int, data2 : Int) = when(this){
        "+" -> data1 + data2
        "*" -> data1 * data2
        "/" -> {
            if(data1 == 0)
                0
            else
                data2 / data1
        }
        "-" -> data1 - data2
        else -> data2
    }

    private fun hasPrecedence(operator1 : String, operator2: String) : Boolean{
        if(operator2 == "(" || operator2 == ")")
            return false
        return !((operator1 == "*" || operator1 == "/") && (operator2 == "+" || operator2 == "-"))
    }

    private fun getMinValueExpectNegative(array: Array<Int>) : Int{
        var minValue = 9
        for(i in array){
            if(i != -1){
                if(minValue > i)
                    minValue = i
            }
        }
        if(minValue == 9)
            minValue = -1

        return minValue
    }
}


class Solution {
    var answer = -1
    var numberSequence = IntArray(8) { 0 }
    fun solution(N: Int, number: Int): Int {
        numberSequence.makeSequence(N)
        generateExpressionToUseDp(0, 0, number)
        return answer
    }

    private fun generateExpressionToUseDp(count : Int, now : Int, number : Int) {
        if(count > 8)
            return
        if((count < answer || answer == -1) && number == now) {
            answer = count
            return
        }
        for(i in 0 until 8 - count) {
            generateExpressionToUseDp(count + i + 1, now + numberSequence[i], number)
            generateExpressionToUseDp(count + i + 1, now - numberSequence[i], number)
            generateExpressionToUseDp(count + i + 1, now * numberSequence[i], number)
            generateExpressionToUseDp(count + i + 1, now / numberSequence[i], number)
        }
    }

    private fun IntArray.makeSequence(number : Int) {
        this[0] = number
        for(i in 1 until this.size){
            this[i] = this[i-1] * 10 + number
        }
    }
}
fun main(){
    val test = Fir_20210628()
    println("${test.solution(5,12)},4")
    println("${test.solution(2,11)},3")
    println("${test.solution(5,5)},1")
    println("${test.solution(5,10)},2")
    println("${test.solution(5,31168)},-1")
    println("${test.solution(1,1121)},7")
    println("${test.solution(5,1010)},7")
    println("${test.solution(3,4)},3")
    println("${test.solution(5,5555)},4")
    println("${test.solution(5,5550)},5")
    println("${test.solution(5,20)},3")
    println("${test.solution(5,30)},3")
    println("${test.solution(6,65)},4")
    println("${test.solution(5,2)},3")
    println("${test.solution(5,4)},3")
    println("${test.solution(1,1)},1")
    println("${test.solution(1,11)},2")
    println("${test.solution(1,111)},3")
    println("${test.solution(1,1111)},4")
    println("${test.solution(1,11111)},5")
    println("${test.solution(7,7776)},6")
    println("${test.solution(7,7784)},5")
    println("${test.solution(2,22222)},5")
    println("${test.solution(2,22223)},7")
    println("${test.solution(2,22224)},6")
    println("${test.solution(2,11111)},6")
    println("${test.solution(2,11)},3")
    println("${test.solution(2,111)},4")
    println("${test.solution(2,1111)},5")
    println("${test.solution(9,36)},4")
    println("${test.solution(9,37)},6")
    println("${test.solution(9,72)},3")
    println("${test.solution(3,18)},3")
    println("${test.solution(2,1)},2")
    println("${test.solution(4,17)},4")
    println("${test.solution(4,3872)},6")
    val test2 = Solution()
    println("${test2.solution(4,3872)},6")
}