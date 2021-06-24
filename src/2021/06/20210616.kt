import java.util.*

class Fir_20210616 {

    //최대값을 저장하는 힙과 최소값을 저장하는 힙
    private val maxHeap = PriorityQueue<Int>(reverseOrder())
    private val minHeap = PriorityQueue<Int>()

    //프로그래머스 문제 : 이중 우선 순위 큐
    //앞의 첫글자의 구분에 따라 데이터를 입력하거나 삭제하는 문제
    //데이터를 삭제 시 min과 max에 똑같은 값을 삭제해야하기에 데이터를 poll로 삭제 후 poll로 나온 값으로 다른 힘에 리무브 명령을 준다
    //operations : 명령 배열 예) I 1 : 1 저장, D -1 : 최소 값 삭제, D 1 : 최대 값 삭제
    fun solution(operations: Array<String>): IntArray {
        for(i in operations){
            val inputDivision = i.split(' ')
            when(inputDivision[0]){
                "I" -> insertValue(inputDivision[1].toInt())
                "D" -> deleteValue(inputDivision[1])
            }
        }
        return getMaxAndMin()
    }

    //데이터를 넣는 함수
    private fun insertValue(data : Int){
        maxHeap.add(data)
        minHeap.add(data)
    }

    //삭제 후 동기화 하는 함수
    private fun deleteValue(division : String){
        when(division){
            "-1" -> {
                maxHeap.remove(minHeap.poll())
            }
            "1" -> {
                minHeap.remove(maxHeap.poll())
            }
        }
    }

    //힘에 아무것도 없을 시 에러가 발생하므로 에러 발생 방지 함수
    private fun getMaxAndMin() : IntArray{
        if(maxHeap.size == 0){
            return intArrayOf(0,0)
        }

        return intArrayOf(maxHeap.peek(), minHeap.peek())
    }
}

fun main(){
    val test = Fir_20210616()
    test.solution(arrayOf("I 7","I 5","I 5","I -5","I -5","D -1"))
}