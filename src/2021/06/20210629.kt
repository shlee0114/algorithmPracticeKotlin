import java.util.*


class Fir_20210629{

    //프로그래머스 문제 : 크레인 인형뽑기
    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        val result = Stack<Int>()
        var answer = 0

        for(i in moves){
            for(boardHorizontal in board){
                if(boardHorizontal[i - 1] != 0){
                    if(result.size == 0)
                        result.push(boardHorizontal[i - 1])
                    else{
                        if(result.peek() == boardHorizontal[i - 1]){
                            result.pop()
                            answer += 2
                        }else{
                            result.push(boardHorizontal[i - 1])
                        }
                    }
                    boardHorizontal[i - 1] = 0
                    break
                }
            }
        }

        return answer
    }
}

fun main(){
    val test = Fir_20210629()
    test.solution(arrayOf(intArrayOf(0,0,0,0,0), intArrayOf(0,0,1,0,3), intArrayOf(0,2,5,0,1), intArrayOf(4,2,4,4,2), intArrayOf(3,5,1,3,1)), intArrayOf(1,5,3,5,1,2,1,4))
}