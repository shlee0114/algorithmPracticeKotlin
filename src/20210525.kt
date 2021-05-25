
import DataStructure.Queue.Queue

class Fir_20210525{
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val queue = Queue
        for(i in progresses){
            queue.push(i)
        }

        //?.을 사용 시 null이 발생 시 대처 가능
        while (queue.isNotEmpty()){
            for(i in 0 until queue.count()){
                queue[i]!! += speeds[i]//queue[i]?.plusAssign(speeds[i])과 동일


            }
            if(queue[0]!! >= 100){ // queue[0]?.compareTo(100) >= 0과 동일
                while (queue.isNotEmpty()){
                    queue.pop()
                    if(queue.isEmpty())
                        break
                    if(queue[0]!! < 100)// queue[0]?.compareTo(100) == -1과 동일
                        break
                }
            }

        }

        return answer.toIntArray()
    }
}

fun main(){
    val first = Fir_20210525()
    print(first.solution(intArrayOf(95, 90, 99, 99, 80, 99), intArrayOf(1, 1, 1, 1, 1, 1)))
}