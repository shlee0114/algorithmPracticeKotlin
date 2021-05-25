
import DataStructure.Queue.Queue
import java.lang.reflect.Type

class Fir_20210524{
    //프로그래머스 문제 : 위장
    //서로다른 종류의 옷을 입는 경우의 수 문제
    //안입는 경우도 있기에 모든 종류의 옷 수를 구한 후 안입는 경우를 추가한 후 경우의 수를 구하면 되는 간단한 문제
    //clothes : 옷의 종류와 옷의 이름이 배열로 들어가 있는 배열
    fun solution(clothes: Array<Array<String>>) : Int{
        var answer = 1
        var map = mutableMapOf<String, Int>()
        for(i in clothes){
            map[i[1]] =
            if(map[i[1]] == null){
                //2로 시작하는 이유 : 현재 입력된 옷 + 안입는 경우
                2
            }else{
                map[i[1]]!! + 1
            }
        }
        map.forEach { (t, u) ->  answer *= u}
        return answer - 1
    }
}
class Snd_20210524{
    //프로그래머스 문제 : 베스트 엘범
    //장르와 재생횟수로 재생횟수가 가장많은 장르 순으로 재생횟수가 가장 많은 곡과 그 다음 순으로 많은 곡의 순번을 출력하는 문제
    /*장르별 전체 재생횟수와 장르별 재생횟수 두개의 맵을 생성 후 장르별 전체 재생횟수가 높은 순으로
      장르별 재생횟수에서 재생횟수가 높은 2개(1개만 있을 시 1개만)의 순번을 answer에 저장 후 출력*/
    //순번또한 저장해야하기에 장르별 재생횟수에서 재생횟수만 저장하지 않고 순번도 저장해야하기에 맵 내부에 맵으로 관리
    //genres : 장르, plays : 재생 수
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
    val first = Fir_20210524()
    val second = Snd_20210524()
    print(first.solution(arrayOf(arrayOf("yellowhat", "headgear"), arrayOf("bluesunglasses", "eyewear"), arrayOf("green_turban", "headgear"))))
    print(second.solution(arrayOf("classic", "pop", "classic", "classic", "pop"), intArrayOf(500, 600, 150, 800, 2500)).toString())
}