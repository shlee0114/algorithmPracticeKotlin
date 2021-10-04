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

fun main(){
    val test = snd_1004()
    test.solution(intArrayOf(1, 4, 2, 3), intArrayOf(2, 1, 3, 4))
}