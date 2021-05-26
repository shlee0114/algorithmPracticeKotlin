
import DataStructure.Queue.CustomQueue
import java.util.*
import kotlin.math.ceil

class Fir_20210525{
    //프로그래머스 문제 : 기능개발
    //가장 앞의 작업이 끝나면 그 후에 끝난 작업들과 같이 묶어서 한번에 몇개의 작업이 완성되는 지 배열로 반환하는 작업
    /*데이터를 queue에 저장 후 반복문으로 모든 데이터에 값을 받은 speed만큼 더해준 다음 가장 앞에 있는 값이 100이 되었을 경우
      100이 아닌 값까지 pop으로 데이터를 제거해주면서 카운팅을 한다 카운팅 한 것을 배열에 넣은 다음 반환*/
    //progresses : 잔여 작업률, speeds : 각 작업 별 진행 속도
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val queue = CustomQueue
        for(i in progresses){
            queue.push(i)
        }

        //?.을 사용 시 null이 발생 시 대처 가능
        while (queue.isNotEmpty()){
            var outCnt = 0
            for(i in 0 until queue.count()){
                queue[i]!! += speeds[i]//queue[i]?.plusAssign(speeds[i])과 동일
            }
            if(queue.peek()!! >= 100){ // queue[0]?.compareTo(100) >= 0과 동일
                while (queue.isNotEmpty()){
                    queue.pop()
                    outCnt++
                    if(queue.isEmpty())
                        break
                    if(queue.peek()!! < 100)// queue[0]?.compareTo(100) == -1과 동일
                        break
                }
                answer.add(outCnt)
            }
        }
        answer.forEach { print(it) }
        return answer.toIntArray()
    }

    //위와 동일한 문제이지만 위는 queue를 사용했지만 아래 실 제출에는 빠르고 효율적인 알고리즘으로 작성
    /*목표치인 100에서 받은 작업량을 뺀 후 작업속도를 나눈 후 소수점은 올림으로 처리한 후 tmp에 저장한다.
      tmp에 저장된 값은 해당 작업을 마무리하는데 필요한 작업 수로 낮을 수록 빨리되는 것을 의미한다.
      초기 지정 값은 tmp[0]으로 설정한 후 tmp[0]보다 큰 값이 나올 시 지정 값 변경과 지금까지의 수를 answer에 저장한다 */
   fun programmersSolution(progresses: IntArray, speeds: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        var tmp = mutableListOf<Int>()

        for(i in progresses.indices){
            if(progresses[i] >= 100) { //100이 넘을 시 연산의 필요가 없기에 문제 방지를 위해 0입력
                tmp.add(0)
            } else {//(100 - 현재 진행률) / 진행속도
                tmp.add(ceil((100-progresses[i]).toDouble() / speeds[i].toDouble()).toInt())
            }
        }

        var firValue = tmp[0]
        var cnt = 1
        for(i in 1 until tmp.size){
            if(firValue < tmp[i]){//지정 값 보다 현재 값이 클 경우 데이터 저장 및 초기화
                answer.add(cnt)
                cnt = 1
                firValue = tmp[i]
            }else{
                cnt++
            }
        }
        answer.add(cnt)
        return answer.toIntArray()
    }
}

fun main(){
    val first = Fir_20210525()
    print(first.solution(intArrayOf(95, 90, 99, 99, 80, 99), intArrayOf(1, 1, 1, 1, 1, 1)))
}