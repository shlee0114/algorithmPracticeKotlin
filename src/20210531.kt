import DataStructure.Stack.CustomStack

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

fun main(){
    val fir = Fir_20210531()
    println(fir.solution(intArrayOf(2, 1, 3, 2), 2))
    println(fir.solution(intArrayOf(0, 0, 0, 0), 2))
    println(fir.solution(intArrayOf(9, 3, 1, 11), 2))
    println(fir.solution(intArrayOf(24,51, 13, 2), 2))
}