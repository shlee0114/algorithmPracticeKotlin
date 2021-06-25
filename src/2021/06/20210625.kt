class Fir_20210625{

    private val ISLAND1 = 0
    private val ISLAND2 = 1
    private val BRIDGE_COST = 2

    //프로그래머스 문제 : 섬 연결하기
    fun solution(n: Int, costs: Array<IntArray>): Int {
        var answer = 0
        val visitedIsland = Array(n) {false}
        visitedIsland[0] = true


        while(visitedIsland.isAllFalse()){
            val bridgeCost = costs.getVisitedIslandBridgeCost(visitedIsland)
            val minBridgeCostIndex = bridgeCost.getMinVisitedBridgeCostIndex(visitedIsland)
            visitedIsland[minBridgeCostIndex] = true
            answer += bridgeCost[minBridgeCostIndex]
        }

        return answer
    }

    private fun Array<Boolean>.isAllFalse() : Boolean{
        for(i in this){
            if(!i)
                return true
        }
        return false
    }

    private fun Array<IntArray>.getVisitedIslandBridgeCost(visitedIsland : Array<Boolean>) : Array<Int>{
        val islandBridgeCost = Array(visitedIsland.size) {Int.MAX_VALUE}
        for(i in this){
            if(visitedIsland[i[ISLAND1]]){
                islandBridgeCost.insertWhenItLower(i[ISLAND2], i[BRIDGE_COST])
            }
            if(visitedIsland[i[ISLAND2]]){
                islandBridgeCost.insertWhenItLower(i[ISLAND1], i[BRIDGE_COST])
            }
        }
        return islandBridgeCost
    }

    private fun Array<Int>.insertWhenItLower(index : Int, insertData : Int){
        if(this[index] > insertData)
            this[index] = insertData
    }

    private fun Array<Int>.getMinVisitedBridgeCostIndex(visitedIsland : Array<Boolean>) : Int{
        var minBridgeCostIndex = -1
        for(i in this.indices){
            if(!visitedIsland[i]){
                if(minBridgeCostIndex == -1){
                    minBridgeCostIndex = i
                }
                else{
                    if(this[i] < this[minBridgeCostIndex]){
                        minBridgeCostIndex = i
                    }
                }
            }
        }
        return minBridgeCostIndex
    }
}

class Snd_20210625{

    //프로그래머스 문제 : 수박수박수박수박수?
    val SUBAK = "수박"

    fun solution(n: Int) = generateFullText(n)

    private fun generateFullText(number : Int) : String{
        val roundIndex : Int = number / 2
        val additionalIndex = number %  2

        return makeFullValue(roundIndex - 1, additionalIndex)
    }

    private fun makeFullValue(roundIndex : Int, additionalIndex : Int) : String{
        var fullValue = ""
        for(i in 0 .. roundIndex){
            fullValue += SUBAK
        }
        fullValue += SUBAK.substring(0, additionalIndex)
        return fullValue
    }
}

fun main(){
    val test = Fir_20210625()
    test.solution(4, arrayOf(intArrayOf(0,1,1),intArrayOf(0,2,2),intArrayOf(1,2,5),intArrayOf(1,3,1),intArrayOf(2,3,8) ))
}
