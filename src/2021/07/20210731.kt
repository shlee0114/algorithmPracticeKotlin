import java.util.*
import kotlin.collections.ArrayList

class Fir_20210731{

    //가장 먼 노드

    private lateinit var nodes : Array<Int>
    private val edgeMap = mutableMapOf<Int, ArrayList<Int>>()
    private var nodeMaxVal = 0

    fun solution(n: Int, edge: Array<IntArray>): Int {
        nodes = Array(n + 1){0}
        edge.toMap()

        edgeMap.getNodeLength()

        nodes.sortDescending()

        return nodes.filter { it == nodes[0] }.size
    }

    private fun Array<IntArray>.toMap(){
        forEach {
            edgeMap.addNumber(it[0],it[1])
            edgeMap.addNumber(it[1],it[0])
        }
    }

    private fun MutableMap<Int, ArrayList<Int>>.addNumber(key : Int, number : Int){
        val value = get(key)?: arrayListOf()
        if(!value.contains(number)){
            value.add(number)
            set(key, value)
        }
    }

    private fun MutableMap<Int, ArrayList<Int>>.getNodeLength(){
        var node: Int
        val nextNodes = PriorityQueue<Int>()
        nextNodes.add(1)
        nodeMaxVal = 0

        while(nextNodes.isNotEmpty()){
            node = nextNodes.poll()
            val selectedNode = get(node) ?: continue
            val nodeValue = nodes[node] + 1

            for(i in selectedNode){
                if(insertNodeLength(i, nodeValue)){
                    nextNodes.addNode(i)
                }
            }
        }
    }

    private fun insertNodeLength(node : Int, length : Int) : Boolean{
        if(node == 1)
            return false
        if(nodes[node] > length || nodes[node] == 0) {
            nodes[node] = length
            return true
        }
        return false
    }

    private fun PriorityQueue<Int>.addNode(node : Int){
        if(!contains(node)){
            add(node)
        }
    }
}

fun main(){
    val test = Fir_20210731()
    test.solution(6, arrayOf(intArrayOf(3, 6), intArrayOf(4, 3), intArrayOf(3, 2), intArrayOf(1, 3), intArrayOf(1, 2), intArrayOf(2, 4), intArrayOf(5, 2)))

}