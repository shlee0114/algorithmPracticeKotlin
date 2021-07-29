import kotlin.math.abs

class Fir_20210729{
    //N-Queen

    var answer = 0
    fun solution(n: Int): Int {
        createChessboard(n).start(0, 0, n)
        return answer
    }

    private fun createChessboard(num : Int) = Array(num){Array(num){true}}

    private fun Array<Array<Boolean>>.start(depth : Int, count : Int, number : Int){
        if(count == number){
            answer++
            return
        }

        for(i in get(depth).indices){
            if(get(depth)[i]){
                val tmp = copy()
                tmp.putQueenOnChessboard(i, depth)
                tmp.start(depth + 1, count + 1, number)
            }
        }

    }

    private fun Array<Array<Boolean>>.copy()= Array(size){arrayListOf<Boolean>().apply{ addAll(this@copy[it]) }.toTypedArray()}

    private fun Array<Array<Boolean>>.putQueenOnChessboard(index : Int, depth: Int){

        for(i in this.indices){
                for(j in this[i].indices){
                    if(i == depth) {
                        get(i)[j] = false
                    }
                    if(isDiagonal(index, depth,j,i)){
                        get(i)[j] = false
                    }
                }
            get(i)[index] = false
        }
    }

    private fun isDiagonal(startingIndex : Int, startingDepth : Int, index : Int, depth : Int) : Boolean {
        val indexInterval = startingIndex - index
        val depthInterval = startingDepth - depth

        return abs(indexInterval) == abs(depthInterval)
    }
}

fun main(){
    val test = Fir_20210729()
    test.solution(4)
}