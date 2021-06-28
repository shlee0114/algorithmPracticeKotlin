
class Fir_20210628{

    private var target = 0
    private var addNumber = 0

    fun solution(N: Int, number: Int): Int {
        target = number
        addNumber = N
        return getSolution(0, "$N", 0)
    }

    private fun getSolution(cnt : Int, expression : String, whetherParenthesesIsOpened : Int) : Int{
        return if(cnt <= 8){
            if(calculation(expression, whetherParenthesesIsOpened) == target)
                cnt
            else{
                val allCalculators = Array(12){-1}
                allCalculators[0] = getSolution(cnt + 1, "$expression + $addNumber", whetherParenthesesIsOpened)
                allCalculators[1] = getSolution(cnt + 1, "$expression - $addNumber", whetherParenthesesIsOpened)
                allCalculators[2] = getSolution(cnt + 1, "$expression * $addNumber", whetherParenthesesIsOpened)
                allCalculators[3] = getSolution(cnt + 1, "$expression / $addNumber", whetherParenthesesIsOpened)
                allCalculators[4] = getSolution(cnt + 1, "$expression % $addNumber", whetherParenthesesIsOpened)
                if(expression[expression.length - 1] != ')')
                allCalculators[5] = getSolution(cnt + 1, "$expression$addNumber", whetherParenthesesIsOpened)
                allCalculators[6] = getSolution(cnt + 1, "$expression + ($addNumber", whetherParenthesesIsOpened+1)
                allCalculators[7] = getSolution(cnt + 1, "$expression - ($addNumber", whetherParenthesesIsOpened+1)
                allCalculators[8] = getSolution(cnt + 1, "$expression * ($addNumber", whetherParenthesesIsOpened+1)
                allCalculators[9] = getSolution(cnt + 1, "$expression / ($addNumber", whetherParenthesesIsOpened+1)
                allCalculators[10] = getSolution(cnt + 1, "$expression % ($addNumber",whetherParenthesesIsOpened+1)
                if(whetherParenthesesIsOpened > 0)
                    allCalculators[11] = getSolution(cnt, "$expression)", whetherParenthesesIsOpened - 1)
                getMinValueExpectNegative(allCalculators)
            }
        }
        else{
            return -1
        }
    }

    private fun calculation(expression: String, whetherParenthesesIsOpened : Int) : Int{
        var completeExpression = openParenthesesClose(expression, whetherParenthesesIsOpened)
        return 1
    }

    private fun openParenthesesClose(expression: String, whetherParenthesesIsOpened : Int) : String{
        var openParenthesesExpression = expression
        for(i in 0..whetherParenthesesIsOpened){
            openParenthesesExpression = "$openParenthesesExpression)"
        }

        return openParenthesesExpression
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

fun main(){
    val test = Fir_20210628()
    test.solution(5, 12)
}