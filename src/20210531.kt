import DataStructure.Stack.CustomStack
import java.util.*
import kotlin.math.max

class Fir_20210531 {
    //하나의 메소드에 모든 기능이 몰리지 않도록 최대한 중복되는 기능이 있을 시 별도의 메소드를 만들어서 중복성 최소화

    //메소드 분리하면서 같이 쓰는 변수는 전역변수로 선언
    private var maxVal = 0
    private var answer = 0
    private var location = 0

    //프로그래머스 문제 : 프린터
    //대기열에 중요도가 있어서 현재 사용자가 넣은 문서가 언제 출력되는 지를 구하는 문제
    //스택을 사용했으며, 넣는 값이 최대 값과 같다면 바로 pop으로 데이터를 제거하고 만약
    //한 바퀴를 돌았다면 다시 스택에 쌓으면서 최대 값과 비교하는 식으로 작성
    //priorities : 우선순위들
    //location : 사용자가 지정한 번호
    fun solution(priorities: IntArray, location: Int): Int {
        this.answer = 0
        var stack = CustomStack()
        this.location = location
        this.maxVal = priorities.maxOrNull() ?: 0
        //첫 한 바퀴는 받은 배열만큼 돌면서 만약 최대 값이면 바로 pop을 해준다.
        for(i in priorities.indices){
            if(insertValueStack(stack, intArrayOf(priorities[i], i))){
                return answer
            }
        }

        //첫 한 바퀴 이후는 기존에 저장한 스택의 길이만큼 반복하면서 임시 스택에 위에 했던 것과 동일하게 넣으면서 최대 값과 비교한다.
        while (true){
            val tmpStack = CustomStack()
            for(i in stack){
                if(insertValueStack(tmpStack, i.value as IntArray)){
                    return answer
                }
            }
            stack = tmpStack
        }
        return answer
    }

    //넣는 데이터가 최대 값인지 확인 후 데이터를 넣고 만약 최대 값이고 사용자가 원하는 위치라면 true로 반환
    //stack : 데이터를 넣는 스택
    //insertValue : 스택에 넣는 데이터 배열이며 0에는 값 1에는 위치가 저장되어있다
    private fun insertValueStack(stack : CustomStack, insertValue : IntArray) : Boolean{
        if(insertValue[0] >= this.maxVal) {
            answer++
            if(insertValue[1] == location){
                return true
            }
            getMaxVal(stack)
        }else{
            stack.push(insertValue)
        }
        return false
    }

    //스택을 전부 돌면서 해당 스택 내에 있는 값들의 최대 값을 구하는 작업
    //stack : 최대 값을 구할 스택
    private fun getMaxVal(stack: CustomStack){
        this.maxVal = 0
        if(stack.isNotEmpty()) {
            for (j in stack) {
                if (this.maxVal < (j.value as IntArray)[0]) {
                    this.maxVal = (j.value as IntArray)[0]
                }
            }
        }
    }
}

class Snd_20210531{
    private var maxValue = 0

    //프로그래머스 문제 : 프린터
    //대기열에 중요도가 있어서 현재 사용자가 넣은 문서가 언제 출력되는 지를 구하는 문제
    //위에 스택으로는 문제가 틀리며, 스택보다는 큐를 사용하는 것이 더 적합하다는 판단으로 큐를 사용
    //큐를 지속적으로 돌리면서 최대 값이 나오면 새로운 큐에 저장하지 않은 방식으로 위에 스택과 동일하지만 큐를 사용한 부분은 빠진 부분을 제외하고 최대 값을 구하기에 에러가 없다
    //priorities : 우선순위들
    //location : 사용자가 지정한 번호
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0

        var queue : Queue<IntArray> = LinkedList()

        for(i in priorities.indices){
            queue.add(intArrayOf(priorities[i], i))
        }

        getMaxVal(queue, queue)

        var tmpQueue : Queue<IntArray> = LinkedList()
        while (true){
            queue.poll().apply{
                if(get(0) >= maxValue){ //추출한 값이 가장 큰 값이면 저장하지 않고 answer값 증가 및 최대 값 다시 산출
                    getMaxVal(queue, tmpQueue)
                    answer++
                    if(get(1) == location)
                        return answer
                }else{
                    tmpQueue.add(this)
                }
            }

            if(queue.isEmpty()){
                if(tmpQueue.isEmpty()){
                    return answer
                }
                queue = tmpQueue
                tmpQueue = LinkedList()
            }
        }
    }

    //2개로 나눠진 앞 큐와 뒷 큐 값을 전부 확인하면서 최대 값을 구하는 식
    //queue : 최대 값을 구할 뒷 부분 큐
    //tmpQueue : 최대 값을 구할 앞 부분 큐
    private fun getMaxVal(queue:  Queue<IntArray>, tmpQueue: Queue<IntArray>){
        this.maxValue = 0
        loopQueueGetMax(queue)
        loopQueueGetMax(tmpQueue)
    }

    //같은 식이 두 번이나 반복되어서 하나의 메소드로 구현
    //queue : 최대 값을 구할 큐
    private fun loopQueueGetMax(queue:  Queue<IntArray>){
        if(queue.isNotEmpty()) {
            for (j in queue) {
                if (this.maxValue < j[0]) {
                    this.maxValue = j[0]
                }
            }
        }
    }
}

fun main(){
    val fir = Fir_20210531()
    val snd = Snd_20210531()
    println(snd.solution(intArrayOf(2, 1, 3, 2), 2))
    println(snd.solution(intArrayOf(2, 1, 3, 2), 2))
    println(snd.solution(intArrayOf(9, 3, 1, 11), 2))
    println(snd.solution(intArrayOf(24,51, 13, 25, 43, 12, 43, 11, 32), 2))
    println(snd.solution(intArrayOf(0, 0, 0, 0), 2))
}