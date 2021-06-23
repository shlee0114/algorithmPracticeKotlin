class Fir_20210623 {
    private lateinit var hasCleared : Array<Boolean>
    private var answer = -1
    private var clearIndex = 0

    fun solution(name: String): Int {
        hasCleared = Array(name.length) { false }

        val nameArray = name.toCharArray()

        var nextIndex = 0
        while(clearIndex < name.length){
            if(nameArray.checkIsAllA())
                return answer
            nextIndex = nameArray.compareLeftAndRight(nextIndex)
            clearIndex++
            answer++
        }

        return answer
    }

    private fun CharArray.checkIsAllA() : Boolean{
        for(i in this.indices){
            if(!hasCleared[i])
                if(this[i] != 'A')
                    return false
        }
        return true
    }

    private fun CharArray.compareLeftAndRight(index : Int) : Int{
        val leftIndex = if(index == 0) this.size - 1 else index - 1
        val rightIndex = if(index == this.size - 1) 0 else index + 1

        var leftCount = getMoveCount(this[leftIndex])
        var rightCount = getMoveCount(this[rightIndex])

        return when {
            hasCleared[leftIndex] -> {
                if(rightCount == 999)
                    rightCount = 0
                moveIndexAndAddCount(rightIndex, rightCount)
            }
            hasCleared[rightIndex] -> {
                if(leftCount == 999)
                    leftCount = 0
                moveIndexAndAddCount(leftIndex, leftCount)
            }
            else -> {
                if(leftCount < rightCount){
                    if(leftCount == 999)
                        leftCount = 0
                    moveIndexAndAddCount(leftIndex, leftCount)
                }else{
                    if(rightCount == 999)
                        rightCount = 0
                    moveIndexAndAddCount(rightIndex, rightCount)
                }
            }
        }
    }

    private fun moveIndexAndAddCount(index : Int, count : Int) : Int{
        hasCleared[index] = true
        answer += count
        return index
    }

    private fun getMoveCount(name : Char) : Int{
        if(name == 'A')
            return 999

        val countFromA = name - 'A'
        val countFromZ = 'Z' - name + 1

        return if(countFromA < countFromZ)
            countFromA
        else
            countFromZ
    }
}

fun main(){
    val test = Fir_20210623()
    println(test.solution("JAN"))
}