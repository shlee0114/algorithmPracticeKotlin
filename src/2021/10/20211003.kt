class fir{

    private val wordString = arrayOf("", "A", "E", "I", "O", "U")
    private val wordNumber = arrayOf(0,0,0,0,0)

    fun solution(word: String): Int {
        return nextNumber(word, 0)
    }

    private fun nextNumber(targetWord : String, count : Int) : Int{
        var innerCount = count
        for(word in wordNumber.indices){
            if(wordNumber[word] == 0){
                innerCount++
                wordNumber[word] = 1
                if(checkWord(targetWord))
                    return innerCount
            }
        }
        wordNumber[4]++
        innerCount++
        roundUp()

        if(checkWord(targetWord))
            return innerCount

        return nextNumber(targetWord, innerCount)
    }

    private fun checkWord(targetWord: String) : Boolean{
        var word = ""
        for(i in wordNumber){
            if(i > 0){
                word += wordString[i]
            }
        }

        return targetWord == word
    }

    private fun roundUp(){
        for(i in wordNumber.indices.reversed()){
            if(wordNumber[i] > 5){
                wordNumber[i - 1] += wordNumber[i] - 5
                wordNumber[i] -= 6
            }
        }
    }
}

fun main(){
    val test = fir()
    test.solution("I")
}