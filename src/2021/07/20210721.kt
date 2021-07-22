class Fir_20210721{

    //프로그래머스 배달
    //1에서 시작해서 모든 경로로 가는 길이를 저장한 후 길이가 k나 넘어갈 때 까지 반복하면서 이미 체크햇던 수가 아니면 answer에 1을 더해준다.

    private var answer = 1
    private val locationAndRoadTime = mutableMapOf<Int, ArrayList<Pair<Int, Int>>>()
    private val ableToGoLocation by lazy {
        locationAndRoadTime[1]!!
    }
    private var passedWay = arrayListOf(1)

    private val NOW_LOCATION = 0
    private val NEXT_LOCATION = 1
    private val LOCATION_COST = 2

    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        road.divisionLocation()
        locationAndRoadTime.sortRoadTime()
        ableToGoLocation.sortRoadTime()

        getAllPath()
        passedWay = arrayListOf(1)
        for(i in ableToGoLocation){
            if(i.second > k)
                return answer

            if(!passedWay.contains(i.first)){
                passedWay.add(i.first)
                answer++
            }
        }

        return answer
    }

    private fun Array<IntArray>.divisionLocation(){
        forEach {
            if(!locationAndRoadTime.containsKey(it[NOW_LOCATION])){
                locationAndRoadTime[it[NOW_LOCATION]] = arrayListOf()
            }

            locationAndRoadTime[it[NOW_LOCATION]]?.add(Pair(it[NEXT_LOCATION], it[LOCATION_COST]))

            if(!locationAndRoadTime.containsKey(it[NEXT_LOCATION])){
                locationAndRoadTime[it[NEXT_LOCATION]] = arrayListOf()
            }

            locationAndRoadTime[it[NEXT_LOCATION]]?.add(Pair(it[NOW_LOCATION], it[LOCATION_COST]))
        }
    }

    private fun MutableMap<Int, ArrayList<Pair<Int, Int>>>.sortRoadTime(){
        values.forEach {
            it.sortBy { roadTime -> roadTime.second }
        }
    }

    private fun ArrayList<Pair<Int, Int>>.sortRoadTime(){
        sortBy { roadTime -> roadTime.second }
    }

    private fun getAllPath(){
        val nextLocationAndLength  = getShortestAbleToGoLocation()

        if(nextLocationAndLength.first == 0)
            return

        val nextLocation = nextLocationAndLength.first
        val nextLength = nextLocationAndLength.second

        locationAndRoadTime[nextLocation]?.run{
            forEach {
                ableToGoLocation.add(Pair(it.first, it.second + nextLength))
            }
        }

        ableToGoLocation.sortRoadTime()

        passedWay.add(nextLocation)
        getAllPath()
    }

    private fun getShortestAbleToGoLocation() : Pair<Int, Int>{
        lateinit var nextLocationAndLength : Pair<Int, Int>
        var locationNum = 0
        while(ableToGoLocation.isNotEmpty()){
            if(locationNum == ableToGoLocation.size){
                return Pair(0,0)
            }
            nextLocationAndLength = ableToGoLocation[locationNum]
            locationNum++

            if(!passedWay.contains(nextLocationAndLength.first))
                break
        }
        return nextLocationAndLength
    }
}

fun main(){
    val test = Fir_20210721()
    test.solution(5, arrayOf(intArrayOf(1,2,1),intArrayOf(2,3,3),intArrayOf(5,2,2),intArrayOf(1,4,2),intArrayOf(5,3,1),intArrayOf(5,4,2)), 3)
}