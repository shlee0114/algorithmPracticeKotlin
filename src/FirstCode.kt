import java.lang.reflect.Type

class FirstCode{
    fun solution(clothes: Array<Array<String>>) : Int{
        var answer = 1
        var map = mutableMapOf<String, Int>()
        for(i in clothes){
            map[i[1]] =
            if(map[i[1]] == null){
                2
            }else{
                map[i[1]]!! + 1
            }
        }
        map.forEach { (t, u) ->  answer *= u}
        return answer - 1
    }
}
class SecondCode{

    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val value = mutableMapOf<String, MutableMap<Int, Int>>()
        var maxVal = mutableMapOf<String, Int>()
        for(i in 0 until genres.count()){
            if(value[genres[i]] == null){
                value[genres[i]] = mutableMapOf()
                maxVal[genres[i]] = plays[i]
            }else{
                maxVal[genres[i]] = maxVal[genres[i]]!! + plays[i]
            }
            value[genres[i]]!![i] =  plays[i]
        }
        var key = value.keys
        for(i in key){
            value[i] = value[i]!!.toList().sortedWith(compareByDescending { it.second }).toMap() as MutableMap<Int, Int>
        }
        maxVal = maxVal.toList().sortedWith(compareByDescending { it.second }).toMap() as MutableMap<String, Int>

        key = maxVal.keys
        for(i in key){
            val tmpKey = value[i]?.keys ?: continue
            answer.add(tmpKey?.elementAt(0))
            if(tmpKey?.size != 1){
                answer.add(tmpKey?.elementAt(1))
            }
        }
        return answer.toIntArray()
    }
}
fun main(){
    val first = FirstCode()
    val second = SecondCode()
    //print(first.solution(arrayOf(arrayOf("yellowhat", "headgear"), arrayOf("bluesunglasses", "eyewear"), arrayOf("green_turban", "headgear"))))
    print(second.solution(arrayOf("classic", "pop", "classic", "classic", "pop"), intArrayOf(500, 600, 150, 800, 2500)).toString())
}