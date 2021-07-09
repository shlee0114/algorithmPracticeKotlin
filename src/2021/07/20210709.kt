import java.util.*

class Fir_20210709{

    //괄호 회전

   private val openParentheses = arrayOf('(', '[', '{')
    private val closeParentheses = arrayOf(')', ']', '}')

    private val OPEN_PARENTHESES = true
    private val CLOSE_PARENTHESES = false

    fun solution(s: String): Int {
        var answer = 0
        var parenthesesString = s
        for(i in s.indices){
            if(isRightParentheses(parenthesesString)){
                answer++
            }
            parenthesesString = parenthesesString.parenthesesToTheLeft()
        }

        return answer
    }

    private fun isRightParentheses(s : String) : Boolean{
        if(closeParentheses.contains(s[0]))
            return false

        val parenthesesStack = Stack<Map<Int, Boolean>>()

        for(i in s.toCharArray()){
            val parenthesesMap =
                if(openParentheses.contains(i)){
                    mapOf(Pair(i.openParenthesesToInt(), OPEN_PARENTHESES))
                }else{
                    mapOf(Pair(i.closeParenthesesToInt(), CLOSE_PARENTHESES))
                }
            if(parenthesesStack.isEmpty())
                parenthesesStack.add(parenthesesMap)
            else{
                if(!parenthesesStack.checkRightCloseParenthesesOrOtherParentheses(parenthesesMap)){
                   return false
                }
            }
        }

        if(parenthesesStack.isNotEmpty())
            return false

        return true
    }

    private fun String.parenthesesToTheLeft()  = this.substring(1, this.length) + this[0]

    private fun Char.openParenthesesToInt() = when(this){
        '(' -> 0
        '[' -> 1
        '{' -> 2
        else -> -1
    }

    private fun Char.closeParenthesesToInt() = when(this){
        ')' -> 0
        ']' -> 1
        '}' -> 2
        else -> -1
    }

    private fun Stack<Map<Int, Boolean>>.checkRightCloseParenthesesOrOtherParentheses(map : Map<Int, Boolean>) : Boolean{
        return if(this.isSameOpenOrClose(map)){
            this.insertOtherParentheses(map)
        }else{
            this.closeParentheses(map)
        }
    }

    private fun Stack<Map<Int, Boolean>>.isSameOpenOrClose(parenthesesMap : Map<Int, Boolean>) =
        parenthesesMap.values.first() == this.peek().values.first()

    private fun Stack<Map<Int, Boolean>>.insertOtherParentheses(map : Map<Int, Boolean>) : Boolean {
        this.add(map)
        return true
    }

    private fun Stack<Map<Int, Boolean>>.closeParentheses(map : Map<Int, Boolean>) : Boolean {
        return if(isSameType(map, this.peek())){
            this.pop()
            true
        }else{
            false
        }
    }

    private fun isSameType(parenthesesMap1 : Map<Int, Boolean>, parenthesesMap2 : Map<Int, Boolean>) =
        parenthesesMap1.keys.first() == parenthesesMap2.keys.first()
}

fun main(){
    val test = Fir_20210709()
    print(test.solution("[](){}"))
}