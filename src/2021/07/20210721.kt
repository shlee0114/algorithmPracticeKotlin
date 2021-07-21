class Fir_20210721{
    private var answer = 0
    private var totalLength = 0
    private val locationAndRoadTime = mutableMapOf<Int, ArrayList<Pair<Int, Int>>>()
    private var totalCityCount = 0
    private val ableToGoLocation by lazy {
        locationAndRoadTime[1]!!
    }
    private val passedWay = arrayListOf(1)

    private val NOW_LOCATION = 0
    private val NEXT_LOCATION = 1
    private val LOCATION_COST = 2

    fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
        totalLength = k
        totalCityCount = N
        road.divisionLocation()
        locationAndRoadTime.sortRoadTime()
        ableToGoLocation.sortRoadTime()

        getAllPath()

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
        answer++

        var nextLocationAndLength  = getShortestAbleToGoLocation()

        if(nextLocationAndLength.first == 0)
            return

        val nextLocation = nextLocationAndLength.first
        val nextLength = nextLocationAndLength.second

        if(nextLength > totalLength)
            return

        locationAndRoadTime[nextLocation]?.run{
            forEach {
                ableToGoLocation.add(Pair(it.first, it.second + nextLength))
            }
        }

        ableToGoLocation.sortRoadTime()

        while(true){
            nextLocationAndLength = ableToGoLocation[0]

            if(!passedWay.contains(nextLocationAndLength.first))
                break
            else
                ableToGoLocation.removeAt(0)

            if(ableToGoLocation.isEmpty())
                return
        }

        passedWay.add(nextLocation)
        getAllPath()
    }

    private fun getShortestAbleToGoLocation() : Pair<Int, Int>{
        lateinit var nextLocationAndLength : Pair<Int, Int>
        while(true){
            nextLocationAndLength = ableToGoLocation[0]
            ableToGoLocation.removeAt(0)

            if(ableToGoLocation.isEmpty())
                return Pair(0,0)
            if(!passedWay.contains(nextLocationAndLength.first))
                break
        }
        return nextLocationAndLength
    }
}

fun main(){
    val test = Fir_20210721()
    test.solution(5, arrayOf(intArrayOf(1,2,4),intArrayOf(2,3,4),intArrayOf(5,2,24),intArrayOf(1,4,24),intArrayOf(5,3,41),intArrayOf(5,4,24)), 3)
}