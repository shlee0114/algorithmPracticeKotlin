import java.util.*

class Fir_20210712{
    private var frontBalanceString = ""
    private var rearBalanceString = ""
    fun solution(p: String): String {
        if(p == "")
            return ""
        p.generateBalanceString(2)
        val parenthesesChecker = Parentheses()
        var answer = ""
        while(!parenthesesChecker.checkIsRightString(answer + frontBalanceString + rearBalanceString)){
            if(parenthesesChecker.checkIsRightString(frontBalanceString)){
                answer += frontBalanceString
                rearBalanceString.generateBalanceString(2)
            }else{
                frontBalanceString = parenthesesChecker.makeRightParentheses(frontBalanceString)
                frontBalanceString += rearBalanceString
                rearBalanceString = ""
            }
        }
        return answer + frontBalanceString + rearBalanceString
    }

    private fun String.generateBalanceString(index : Int){
        if(index == length) {
            frontBalanceString = this
            return
        }
        val frontString = substring(0, index)
        val rearString = substring(index, length)
        if(frontString.checkCorrectString() && rearString.checkCorrectString()){
            frontBalanceString = frontString
            rearBalanceString = rearString
            return
        }else{
            this.generateBalanceString(index + 2)
        }
    }

    private fun String.checkCorrectString() = toCharArray().filter { it == '(' }.size == toCharArray().filter { it == ')' }.size
}

class Parentheses(){

    private var remainsParenthesesCount = 0

    fun makeRightParentheses(wrongString : String) : String {
        val rightString = wrongString.substring(1, wrongString.length - 1).reverseParentheses()
        return "($rightString)"
    }


    fun checkIsRightString(s : String) : Boolean{
        if(s[0] == ')') return false
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
    print(test.solution("()))((()"))
}