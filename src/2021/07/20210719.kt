class Fir_20210719{
    //튜플

    private val tuple = arrayListOf<Int>()

    fun solution(s: String): IntArray {
        val sortedTupleElement = s.sortedTupleElement()

        for(i in sortedTupleElement){
            tuple.add(i.findNextElement())
        }

        return tuple.toIntArray()
    }

    private fun String.sortedTupleElement() : List<Array<String>>{
        val tmp = trim().split("},{")
        val tupleElement = ArrayList<Array<String>>()

        for(i in tmp){
            tupleElement.add(i.replace("{", "").replace("}", "").split(",").toTypedArray())
        }
        return tupleElement.sortedBy { it.size }
    }

    private fun Array<String>.findNextElement() : Int{
        forEach { it ->
            if(tuple.isEmpty() || !tuple.contains(it.toInt()))
                return it.toInt()
        }
        return 0
    }
}

class Snd_20210719{
    //삼각 달팽이
    //3개 이상 넘어갈 시 하나의 삼각형이 더 생기는 현상을 이용해서 외각라인 구하고 3빼고를 반복해서 구한다.
    //오른쪽 : 3 * (현재 단계(깊이) -1) - (왼쪽 수 - 2) + (시작 수 * 2)
    private lateinit var triangle : IntArray
    private var totalStep = 0
    private var firstNum = 0
    private var startDepth = 0

    fun solution(n: Int): IntArray {
        totalStep = n
        triangle = IntArray(triangleTotalCount(n))
        triangle[0] = 0
        var startNum = 0

        while(triangle.contains(0)){
            triangleOutline(firstNum + 1, totalStep, startNum)
            firstNum = triangle.getMaxVal()
            startNum = triangle.indexOf(0)
            totalStep -= 3
            startDepth += 2
        }
        return triangle
    }

    private fun triangleTotalCount(depth : Int) : Int{
        var count = 0

        for(i in 1 .. depth){
            count += i
        }

        return count
    }

    private fun triangleOutline(startNum : Int, step : Int, storageLocation : Int){
        var inProgressNum = startNum
        var inProgressStorageLocation = storageLocation

        for(i in 0 until step){
            lineBothEnds(inProgressNum, i, inProgressStorageLocation)
            inProgressNum++
            inProgressStorageLocation += 1 + i + startDepth
        }
    }

    private fun lineBothEnds(startNum : Int, step : Int, storageLocation : Int){
        when(step){
            0 -> triangle[storageLocation] = startNum
            totalStep - 1 -> {
                var numTmp = startNum
                for(i in 0 until totalStep){
                    triangle[storageLocation + i] = numTmp
                    numTmp++
                }
            }
            else -> {
                triangle[storageLocation] = startNum
                triangle[storageLocation + step] = 3 * (totalStep - 1) - (startNum - 2) + (firstNum * 2)
            }
        }
    }

    private fun IntArray.getMaxVal() : Int{
        var maxVal = 0
        forEach {
            if(it > maxVal)
                maxVal = it
        }
        return maxVal
    }

}

fun main(){
    val test = Snd_20210719()
    test.solution(5)
}