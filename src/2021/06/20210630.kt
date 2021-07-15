

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

class Thr_20210630{

    //프로그래머스 문제 : 단어 변환
    //시작 단어를 최종 목표 단어까지 변환하는데 걸리는 단계
    //주어진 단어로만 변할 수 있으며, 한 번에 한 단어 씩 변환이 가능하다

    var targetText = ""
    var wordList = arrayOf<String>()
    var maxStep = 0

    fun solution(begin: String, target: String, words: Array<String>): Int {
        targetText = target
        wordList = words
        maxStep = wordList.size + 1
        textTranslator(begin, 0)
        if(maxStep == wordList.size + 1)
            maxStep = 0
        return maxStep
    }

    private fun textTranslator(word : String, count : Int){
        if(maxStep < count){
            return
        }
        //가장 짧은 단계를 구하는 것이기에 답이 나온 단계 이상은 필요가 없으므로, maxStep을 변경한다.
        if(word == targetText){
            maxStep = count
            return
        }

        for(i in wordList){
            if(i != word)
                if(isChangeOneWord(word, i)){
                    textTranslator(i, count + 1)
                }
        }
    }

    //한 단어만 변경되었는 지 확인하는 모듈
    private fun isChangeOneWord(word : String, toWord : String) : Boolean{
        var changedCount = 0
        val toWordChar = toWord.toCharArray()
        val wordChar = word.toCharArray()
        for(i in wordChar.indices){
            if(wordChar[i] != toWordChar[i])
                changedCount++
            if(changedCount == 2)
                return false
        }
        return true
    }
}


class fth_20210630{

    //프로그래머스 문제 : 여행경로
    //배열 내부에 2개의 String으로 이루어진 배열이 있으며 ICN부터 시작해서 모든 1번과 0번이 일치하는 것을 따라가면서 모든 배열을 돌 수 있는 경로 중 알파벳 순으로 간 것을 출력

    private var tickets = arrayOf<Array<String>>()
    private var answer: Array<String>? = null

    private val START_LOCATION = "ICN"

    private val STARTING_POINT = 0
    private val ARRIVAL_POINT = 1


    fun solution(tickets: Array<Array<String>>): Array<String> {
        tickets.sortBy { it.first() + it.last() } //알파벳 순이 되도록 정렬
        this.tickets = tickets
        for(i in tickets.indices){
            if(tickets[i][STARTING_POINT] == START_LOCATION){

                val availableTicket = BooleanArray(tickets.size){true}
                val alreadyGone = arrayListOf(i)
                val root = generateRoot(tickets[i][ARRIVAL_POINT])

                availableTicket[i] = false
                ticketsTracking(availableTicket, tickets[i][ARRIVAL_POINT], alreadyGone, root)
            }
        }
        return answer!!
    }

    //초기 출발지는 ICN으로 고정이므로 ICN과 그 다음 지역을 배열의 초기값으로 반환
    private fun generateRoot(firstRoot : String) = arrayListOf("ICN", firstRoot)

    //현재 위치와 갈 수 있는 곳을 산정 후 모든 지역을 돌면 바로 반환한다.
    private fun ticketsTracking(availableTicket : BooleanArray, nowLocation : String, alreadyGone : ArrayList<Int>, root : ArrayList<String>){
        if(answer != null)
            return
        if(!availableTicket.contains(true)){
            answer = root.toTypedArray()
            return
        }
        val ableToGo = ArrayList<String>()
        for(i in tickets.indices){
            if(availableTicket[i] && !alreadyGone.contains(i) && tickets[i][0] == nowLocation){
                ableToGo.add("${tickets[i][ARRIVAL_POINT]}_$i")
            }
        }

        if(ableToGo.isEmpty())
            return

        for(i in ableToGo){
            val index = i.split("_")[1].toInt()
            val location = i.split("_")[0]
            alreadyGone.add(index)
            availableTicket[index] = false
            root.add(location)
            ticketsTracking(availableTicket, location, alreadyGone, root)
            alreadyGone.remove(index)
            availableTicket[index] = true
            root.removeAt(root.size - 1)
        }
    }
}

fun main(){
    //val test = Fir_20210630()
    //println(test.solution(intArrayOf(1, 1, 1, 1, 1), 3))
    //val test2 = Snd_20210630()
    //println(test2.solution(1, arrayOf(intArrayOf(1))))
    //val test3 = Thr_20210630()
    //println(test3.solution("hit", "cog", arrayOf("hot", "dot", "dog", "lot", "log", "cog")))
    val test4 = fth_20210630()
    println(test4.solution(arrayOf(arrayOf("ICN", "BOO"), arrayOf("ICN", "COO"), arrayOf("COO", "DOO"), arrayOf("DOO", "COO"), arrayOf("BOO", "DOO"), arrayOf("DOO", "BOO"), arrayOf("BOO", "ICN"), arrayOf("COO", "BOO"))))
}