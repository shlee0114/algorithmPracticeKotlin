import java.util.*

class Fir_20210712{
    //괄호 변환

    private var frontBalanceString = ""
    private var rearBalanceString = ""
    private val parenthesesChecker = Parentheses()

    fun solution(p: String): String {
        if(p == "" || parenthesesChecker.checkIsRightString(p))
            return p

        return generateRightParentheses(p)
    }

    private fun generateRightParentheses(p : String) : String{
        if(p.isEmpty())
            return ""

        p.generateBalanceString(2)
        val frontString = frontBalanceString

        return if(parenthesesChecker.checkIsRightString(frontString)){
                if(rearBalanceString.isEmpty()){
                    frontString
                }else{
                    (frontString + generateRightParentheses(rearBalanceString))
                }
        }else{
            val middleString = frontString.substring(1, frontString.length - 1)
            val rightFront = if(middleString.isEmpty()){
                ""
            }else{
                parenthesesChecker.makeRightParentheses(middleString)
            }

            "(${generateRightParentheses(rearBalanceString)})$rightFront"
        }
    }

    private fun String.generateBalanceString(index : Int){
        if(index == length) {
            frontBalanceString = this
            rearBalanceString = ""
            return
        }

        val frontString = substring(0, index)
        val rearString = substring(index, length)

        if(frontString.checkCorrectString() &&
            rearString.checkCorrectString()){
            frontBalanceString = frontString
            rearBalanceString = rearString
            return
        }else{
            this.generateBalanceString(index + 2)
        }
    }

    private fun String.checkCorrectString() = toCharArray().filter { it == '(' }.size == toCharArray().filter { it == ')' }.size
}

class Parentheses{

    private var remainsParenthesesCount = 0

    fun makeRightParentheses(wrongString : String) : String {
        val rightString = wrongString.substring(1, wrongString.length - 1).reverseParentheses()

        return "($rightString)"
    }

    fun checkIsRightString(s : String) : Boolean{
        if(s.isEmpty())
            return true

        if(s[0] == ')')
            return false

        val parenthesesChecker = Stack<Boolean>()
        remainsParenthesesCount = s.length

        for(i in s){
            remainsParenthesesCount--
            parenthesesChecker.popOrPush(i)
            if(parenthesesChecker.size > remainsParenthesesCount)
                return false
        }

        return parenthesesChecker.isEmpty()
    }

    private fun String.reverseParentheses() : String{
        var reverseString = ""

        forEach {
            reverseString += it.reverse()
        }

        return reverseString
    }

    private fun Char.reverse() = if(this == ')') '(' else ')'

    private fun Stack<Boolean>.popOrPush(parentheses: Char){
        val isOpened = parentheses.checkIsOpen()

        when{
            isEmpty() -> push(isOpened)
            peek() == isOpened -> push(isOpened)
            peek() != isOpened and !isOpened -> pop()
        }
    }

    private fun Char.checkIsOpen() = this == '('
}

fun main(){
    val test = Fir_20210712()
    print(test.solution(")("))
}