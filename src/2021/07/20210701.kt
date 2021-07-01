class Fir_20210701{

    //프로그래머스 문제 : 입국심사

    private var peopleCount = 0
    private var times = intArrayOf()

    fun solution(n: Int, times: IntArray): Long {
        peopleCount = n
        times.sort()
        this.times = times
        val minTime = 1L
        val maxTime = times.last() * n.toLong()

        return binarySearch(minTime, maxTime, (maxTime + minTime) / 2, maxTime)
    }

    private fun binarySearch(minTime : Long, maxTime : Long, midTime : Long, answer : Long) : Long{
        if(minTime > maxTime){
            return answer
        }

        var totalMidTime = 0L

        for(i in times){
            totalMidTime += midTime / i
        }

        return if(totalMidTime >= peopleCount){
            if(answer > midTime){
                binarySearch(minTime, midTime - 1, ((midTime - 1) + minTime) / 2, midTime)
            }else{
                binarySearch(minTime, midTime - 1, ((midTime - 1) + minTime) / 2, answer)
            }
        }else{
            binarySearch(midTime + 1,  maxTime, ((midTime + 1) + maxTime) / 2, answer)
        }
    }
}