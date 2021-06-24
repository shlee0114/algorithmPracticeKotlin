class Fir_20210623 {
    private lateinit var hasCleared : Array<Boolean>
    private var answer = 0
    private var leftMoveCount = 0
    private var rightMoveCount = 0

    fun solution(name: String): Int {
        hasCleared = Array(name.length) { false }

        val nameArray = name.toCharArray()

        var nextIndex = 0

        for(i in nameArray){
            answer += getMoveCount(i)
        }
        hasCleared[0] = true

        while(true){
            if(nameArray.checkIsAllA())
                return answer
            nextIndex = nameArray.compareLeftAndRight(nextIndex)
            leftMoveCount = 0
            rightMoveCount = 0
            if(nextIndex == -1)
                return answer
        }
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
        val leftIndex = this.getLeftIndex(index, index)
        val rightIndex = this.getRightIndex(index)

       return  when{
           leftIndex + rightIndex == -2 -> -1

           leftIndex == -1 ->{
                moveIndexAndAddCount(rightIndex, rightMoveCount)

           }
           rightIndex == -1 ->{
               moveIndexAndAddCount(leftIndex, leftMoveCount)
           }
           else -> {
                if(leftMoveCount < rightMoveCount)
                    moveIndexAndAddCount(leftIndex, leftMoveCount)
                else
                    moveIndexAndAddCount(rightIndex,  rightMoveCount)
           }
        }

    }

    private fun CharArray.getLeftIndex(index : Int, firstIndex : Int) : Int{
        val leftIndex = if(index <= 0) this.size - 1 else index - 1
        leftMoveCount++
        if(leftIndex == firstIndex)
            return -1
        if(this[leftIndex] == 'A' || hasCleared[leftIndex])
            return this.getLeftIndex(leftIndex, firstIndex)
        return leftIndex
    }

    private fun CharArray.getRightIndex(index : Int) : Int{
        val rightIndex = index + 1
        rightMoveCount++
        if(rightIndex == this.size)
            return -1
        if(this[rightIndex] == 'A' || hasCleared[rightIndex])
            return this.getRightIndex(rightIndex)
        return rightIndex
    }

    private fun moveIndexAndAddCount(index : Int, count : Int) : Int{
        if(count == 0)
            return index
        hasCleared[index] = true
        answer += count
        return index
    }

    private fun getMoveCount(name : Char) : Int{
        val countFromA = kotlin.math.abs('A' - name)
        val countFromZ = kotlin.math.abs('Z' - name) + 1

        return if(countFromA < countFromZ)
            countFromA
        else
            countFromZ
    }
}

fun main(){
    val test = Fir_20210623()
    println("${test.solution("AABAAAAAAABBB")} 12")
}