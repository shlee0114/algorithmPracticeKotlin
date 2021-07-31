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

class Snd_20210731{

    //순위

    private val win = mutableMapOf<Int, ArrayList<Int>>()
    private val lose = mutableMapOf<Int, ArrayList<Int>>()
    private var answer = 0

    fun solution(n: Int, results: Array<IntArray>): Int {
        results.toMap()
        getRanking(n)
        return answer
    }

    private fun Array<IntArray>.toMap(){
        forEach {
            win.addNumber(it[0],it[1])
            lose.addNumber(it[1],it[0])
        }
    }

    private fun MutableMap<Int, ArrayList<Int>>.addNumber(key : Int, number : Int){
        val value = get(key)?: arrayListOf()
        if(!value.contains(number)){
            value.add(number)
            set(key, value)
        }
    }

    private fun getRanking(peopleCount : Int){
        for(i in 1..peopleCount){
            val winner = win.peopleCount(i, 0, arrayListOf()) - 1
            val loser = lose.peopleCount(i, 0, arrayListOf()) - 1

            if(loser + winner == peopleCount - 1)
                answer++
        }
    }

    private fun MutableMap<Int, ArrayList<Int>>.peopleCount(people : Int, count : Int, peoples : ArrayList<Int>) : Int{
        var totalCount = count + 1

        get(people)?.let {
            for(i in it){
                if(!peoples.contains(i)){
                    peoples.add(i)
                    totalCount = peopleCount(i, totalCount, peoples)
                }
            }
        }

        return totalCount
    }
}

class Trd_20210731{

    //가장 긴 팰린드롬

    fun solution(s: String): Int {
        var deleteStringCount = 0
        if(s.checkPalindrome(0, s.length - 1)){
            return s.length
        }
        while(true){
            deleteStringCount++
            for(i in 0 .. deleteStringCount){
                if(s.checkPalindrome(i,  s.length - (deleteStringCount - i) - 1)){
                    return s.length - deleteStringCount
                }
            }
        }
    }

    private fun String.checkPalindrome(startIndex : Int, endIndex : Int) : Boolean{
        val alpha = toCharArray()
        val centerNumber = (alpha.size - startIndex- (length - endIndex) + 1) / 2
        for(i in 0 .. centerNumber){
            if(alpha[startIndex + i] != alpha[endIndex - i]){
                return false
            }
        }
        return true
    }
}

fun main(){
    val test = Fir_20210731()
    test.solution(6, arrayOf(intArrayOf(3, 6), intArrayOf(4, 3), intArrayOf(3, 2), intArrayOf(1, 3), intArrayOf(1, 2), intArrayOf(2, 4), intArrayOf(5, 2)))

    val test2 = Snd_20210731()
    test2.solution(5, arrayOf(intArrayOf(4, 3), intArrayOf(4, 2), intArrayOf(3, 2), intArrayOf(1, 2), intArrayOf(2, 5)))

    val test3 = Trd_20210731()
    test3.solution("abacde")
}