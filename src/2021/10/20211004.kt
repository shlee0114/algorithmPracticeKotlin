import java.util.*
import kotlin.collections.ArrayList

class fir_1004 {

    data class People(
        var number : Int,
        var winRate : Float,
        var winOverWeight : Int,
        var weight: Int
    )


    fun solution(weights: IntArray, head2head: Array<String>): IntArray {
        val peoples = Array(weights.size){People(it + 1, 0.0f, 0, weights[it])}

        for(i in weights.indices){
            var loseCount = 0
            var winCount = 0

            val result = head2head[i].toCharArray()
            for(resultIndex in result.indices){
                if(result[resultIndex] == 'W'){
                    winCount++
                    if(weights[i] < weights[resultIndex]){
                        peoples[i].winOverWeight++
                    }
                }else if(result[resultIndex] == 'L'){
                    loseCount++
                }
            }

            peoples[i].winRate = if(winCount == 0 || (winCount == 0 && loseCount == 0)){
                 0.0f
            }else if(loseCount == 0){
                100.0f
            }else{
                winCount / (winCount + loseCount).toFloat() * 100.0f
            }
        }
        peoples.sortByDescending { it.weight }
        peoples.sortWith(compareByDescending<People>{it.winRate}.thenByDescending{ it.winOverWeight })

        return IntArray(peoples.size){peoples[it].number}
    }
}

class snd_1004{
    fun solution(enter: IntArray, leave: IntArray): IntArray {
        val innerPeople = mutableListOf<Int>()
        val meetPeople = Array(enter.size){ mutableSetOf<Int>() }

        var leaveIndex = 0

        for(enterPeople in enter){
            innerPeople.add(enterPeople)

            while(leave.size > leaveIndex){
                if(innerPeople.contains(leave[leaveIndex])){
                    for(j in meetPeople.indices){
                        if(innerPeople.contains(j + 1)){
                            meetPeople[j].add(leave[leaveIndex])
                        }
                    }

                    for(i in innerPeople){
                        meetPeople[leave[leaveIndex] - 1].add(i)
                    }

                    innerPeople.remove(leave[leaveIndex])
                    leaveIndex++
                }else{
                    break
                }
            }
        }

        return IntArray(meetPeople.size){ meetPeople[it].size - 1}
    }
}

class thd_1004{
    fun solution(sizes: Array<IntArray>): Int {
        val sortedSizes = Array(sizes.size){sizes[it].sorted()}
        val verticalMaxSize =  sortedSizes.getVerticalMax()
        val horizontalMaxSize = sortedSizes.getHorizontalMax()
        //val verticalMaxSize = sortedSizes.maxOf { it.first() }
       // val horizontalMaxSize = sortedSizes.maxOf { it[1] }


        return verticalMaxSize * horizontalMaxSize /// gcd(verticalMaxSize, horizontalMaxSize)
    }

    private fun Array<List<Int>>.getVerticalMax() : Int{
        var maxVal = 0

        forEach {
            if(maxVal < it[0])
                maxVal = it[0]
        }

        return maxVal
    }

    private fun Array<List<Int>>.getHorizontalMax() : Int{
        var maxVal = 0

        forEach {
            if(maxVal < it[1])
                maxVal = it[1]
        }

        return maxVal
    }

    fun gcd(a: Int, b:Int): Int = if(b != 0) gcd(b, a % b) else a
}

fun main(){
    val test = thd_1004()
    println(test.solution(arrayOf(intArrayOf(60, 50), intArrayOf(30, 70), intArrayOf(60, 30), intArrayOf(18, 7), intArrayOf(80, 40))))
}