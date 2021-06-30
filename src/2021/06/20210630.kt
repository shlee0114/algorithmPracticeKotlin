import com.sun.org.apache.xpath.internal.operations.Bool

class Fir_20210630{

    //프로그래머스 문제 : 타겟 넘버
    //주어진 수를 사용해서 더하거나 빼서 지정한 수를 도달하는 개수

    //재귀를 사용하고 깔끔한 코드를 유지하기 위해 재귀에서 사용하지만 선언하거나 인수로 넘기기에는 애매한 것들을 전역으로 선언
    //도달한 개수
    var answer = 0
    //지정한 수
    var target = 0
    //주어진 수의 개수
    var maxCount = 0
    //주어진 수
    var numbers = intArrayOf()

    fun solution(numbers: IntArray, target: Int): Int {
        this.numbers = numbers
        maxCount = numbers.size
        this.target = target
        dfs(0,0)
        return answer
    }

    //깊이 탐색을 사용
    //count : 현재 탐색 정도
    //number : 지금까지 더하거나 뺀 값
    private fun dfs(count : Int, number : Int){
        if(count == maxCount){
            if(number == target){
                answer++
            }
            return
        }

        dfs(count + 1, number + numbers[count])
        dfs(count + 1, number - numbers[count])
    }
}

class Snd_20210630{
    //프로그래머스 문제 : 네트워크
    //한 컴퓨터를 중심으로 이어져 있는 묶음의 개수를 구하는 문제

    //컴퓨터가 연결되어있는 지 여부
    private val connectedComputer by lazy {
        BooleanArray(computerCount){false}
    }
    //컴퓨들이 서로 연결되어있는 정보
    private var computersConnectedList = arrayOf<IntArray>()
    //컴퓨터 수
    private var computerCount = 0
    //연결 묶음 수
    private var networkCount = 0

    //한 컴퓨터를 기준으로 연결되어있는 컴퓨터들을 구한 후 한 번 연결 시작할 때 마다 네트워크 수를 하나 씩 추가한다.
    fun solution(n: Int, computers: Array<IntArray>): Int {
        computerCount = n
        this.computersConnectedList = computers

        for(i in connectedComputer.indices){
            if(!connectedComputer[i]){
                searchConnectedComputer(i, i)
                networkCount++
            }
        }

        return networkCount
    }

    //한 컴퓨터에 연결되어있는 컴퓨터들을 구하는 작업
    //현재 번호와 해당 번호와 연결되어있는 전 번호, 다른 곳과 연결이 완료된 번호는 무한 루프를 돌 가능성이 있기에 연결하지 않는다.
    private fun searchConnectedComputer(computerIndex : Int, beforeIndex : Int){
        connectedComputer[computerIndex] = true
        for(i in 0 until computerCount){
            if(i != computerIndex && i != beforeIndex){
               if(computersConnectedList[computerIndex][i] == 1){
                   if(!connectedComputer[i]){
                       searchConnectedComputer(i, computerIndex)
                   }
               }
            }
        }
    }
}

fun main(){
    val test = Fir_20210630()
    println(test.solution(intArrayOf(1, 1, 1, 1, 1), 3))
    val test2 = Snd_20210630()
    println(test2.solution(1, arrayOf(intArrayOf(1))))
}