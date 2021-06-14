import java.util.*

class Fir_20210614 {
    /*
        answer : 총 걸린 시간
        taskHeap : 첫번째 값 기준으로 오름차순 정렬하는 힙
        index : 잡 진행 수
        nowTotal : 총 소요시간
        startNum : 실행가능한 최소 시간
    */
    private var answer = 0
    private val taskHeap = PriorityQueue(kotlin.Comparator<Pair<Int, Int>> { o1, o2 -> lambda(o1.first,o2.first)})
    private var index = 0
    private var nowTotal = 0
    private var startNum = -1

    private var lambda = {a:Int,b:Int-> when{
        a>b -> 1
        a<b -> -1
        else -> 0
    }}

    //프로그래머스 문제 : 디스크 컨트롤러
    //현재 시간과 진행 후 시간 사이에 있는 값을 힙에 넣는다
    //힙에 값이 들어갈 시 현재 시간을 이 전의 진행한 시간으로 변경 후 입력 횟수를 증가시킨다
    //진행 후 시간에 입력될 값의 진행 시간을 넣은 후 answer에는 대기시간을 뺴 준 값을 더한다,
    //jobs : 실행해야하는 일 [[진행시간, 대기시간]]으로 저장되어있음
    fun solution(jobs: Array<IntArray>): Int {
        while(index < jobs.size){
            for(j in jobs){
                if(j[0] in (startNum+1)..nowTotal){
                    taskHeap.offer(Pair(j[1], j[0]))
                }
            }
            if(taskHeap.size != 0){
                startNum = nowTotal
                index += 1
                taskHeap.poll().apply {
                    nowTotal += first
                    answer += nowTotal - second
                }
            }else{
                nowTotal += 1
            }
        }
        return answer / jobs.size
    }
}

fun main(){
    val test = Fir_20210614()
    println(test.solution(arrayOf(intArrayOf(0, 3), intArrayOf(1, 9), intArrayOf(2, 6))))
}