import java.lang.Math.abs

class fir_1007{
    lateinit var interval : IntArray
    var totalCount = 0
    lateinit var wireCount : IntArray
    lateinit var wireTree : Array<ArrayList<Int>>

    fun solution(n: Int, wires: Array<IntArray>): Int {
        wireTree = Array(n + 1){ arrayListOf() }
        totalCount = n
        wireCount = IntArray(n + 1){1}
        interval = IntArray(n)


        wires.makeTree()

        return interval.minOrNull()?:0
    }

    private fun Array<IntArray>.makeTree(){
        for(i in wireTree.indices){
            forEach {
                if(it[0] == i){
                    wireTree[i].add(it[1])
                }else if(it[1] == i){
                    wireTree[i].add(it[0])
                }
            }
        }

        getTreeCount(1, arrayListOf(), -1)

        for(i in 0 until totalCount){
            interval[i] = abs(totalCount - (wireCount[i + 1] * 2))
        }
    }

    private fun getTreeCount(index : Int, passedNum : ArrayList<Int>, parent : Int) : Int{
        val nextNum = wireTree[index]
        passedNum.add(index)

        var count = 1

        for(i in nextNum){
            if(!passedNum.contains(i) && parent != i){
                wireCount[index] += getTreeCount(i, passedNum, index)
                count++
            }
        }

        return wireCount[index]
    }
}

fun main(){
    val test = fir_1007()
    test.solution(9, arrayOf(intArrayOf(1,3),intArrayOf(2,3),intArrayOf(3,4),intArrayOf(4,5),intArrayOf(4,6),intArrayOf(4,7),intArrayOf(7,8),intArrayOf(7,9)))
}