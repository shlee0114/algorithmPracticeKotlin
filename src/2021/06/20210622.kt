class Fir_20210622{

    //프로그래머스 문제 : 카펫
    //갈색과 노란색 카펫의 길이를 구하는 문제 가로가 더 길거나 갖고 노란색을 갈색이 감싸고 있다는 조건으로 매우 쉬운 문제
    //우선 노란색의 크기가 나오는 곱셈을 구한 후 감싼 직사각형은 무조건 가로, 세로가 2씩 길기에 2를 더한 후 나온 값을 곱해서 크기를 구한다.
    //나온 크기에 노란색 크기를 뺸 후 갈색 크기와 맞다면 해당 가로와 세로를 반환하면 되는 문제
    //brown : 갈색 카펫 크기
    //yellow : 노란색 카펫 크기
    fun solution(brown: Int, yellow: Int): IntArray {
        var index = 1
        while (index * index <= yellow ){
            if(yellow % index == 0){
                val brownHorizontal = (yellow / index) + 2
                val brownVertical = index + 2
                if(brownVertical * brownHorizontal - yellow == brown)
                    return intArrayOf(brownVertical, brownHorizontal)
            }
            index++
        }
        return intArrayOf()
    }
}

class Snd_20210622{

    //프로그래머스 문제 : 체육복
    //주어진 학생 수가 있고 체육복이 없는 학생과 체육복이 남는 학생이 있다.
    //없는 학생은 앞 뒤에 남는 학생에게 빌리는 문제
    //n크기만큼 맵을 만든 후 lost에 해당되는 부분은 -1, reverse에 해당되는 부분은 +1로 저장한다.
    //그 후 lost에 해당 되는 값의 전후에 +1이 있다면 해당 값과 +1된 값을 0으로 저장한다.
    //전부 다 루프를 돈 다음 맵에 0이상인 것들의 수를 샌 후 반환한다.
    //n : 학생 수
    //lost : 없는 학생의 번호 배열
    //reserve : 남은 학생의 배엻
    fun solution(n: Int, lost: IntArray, reserve: IntArray): Int {
        var answer = 0
        val gymSuit = mutableMapOf<Int, Int>()

        for(i in 1 .. n){
            gymSuit[i] = 0
        }

        gymSuit.addDataAtKeyArray(-1, lost)
        gymSuit.addDataAtKeyArray(1, reserve)

        for(i in 1 .. n){
            if(gymSuit.checkDataIsMinus(i)){
                gymSuit.checkFrontAndRearIsPlusAndClearWithNow(i)
            }
        }

        for(i in gymSuit.values){
            if(i >= 0)
                answer++
        }

        return answer
    }

    //데이터를 넣는 함수
    //같은 동작이 중복으로 있기에 생성
    //값을 지정이 아닌 더하는 이유는 남은 학생이 잃어버리는 경우도 있기에 남아서 +1, 잃어버려서 -1 총 0인 연산을 하기 위해
    //data : 입력 데이터로 +1 혹은 -1이 값으로 들어온다.
    //keyArray : 입력 데이터를 더할 키값들의 배열
    private fun MutableMap<Int, Int>.addDataAtKeyArray(data : Int, keyArray : IntArray){
        for(i in keyArray){
            this[i] = this[i]!! + data
        }
    }

    //선택된 키의 값이 -인지 체크
    //index : 선택된 번호
    private fun MutableMap<Int, Int>.checkDataIsMinus(index: Int) : Boolean = this[index]!! < 0

    //만약 앞 혹은 뒤의 값이 +1이라면 앞 혹은 뒤 값과 현재 값을 0으로 만든다.
    //index : 선택된 번호
    private fun MutableMap<Int, Int>.checkFrontAndRearIsPlusAndClearWithNow(index : Int){
        when{
            this.checkIsPlus(index - 1) -> setDataAndClear(index, index - 1)
            this.checkIsPlus(index + 1) -> setDataAndClear(index, index + 1)
            else -> return
        }
    }

    //선택된 키의 값이 +인지 체크
    //index : 선택된 번호
    private fun MutableMap<Int, Int>.checkIsPlus(index: Int) : Boolean{
        if(this.containsKey(index)){
            return this[index]!! > 0
        }
        return false
    }

    //데이터 초기화
    //clearDataKey1 : 선택된 번호
    //clearDataKey2 : 선택된 번호
    private fun MutableMap<Int, Int>.setDataAndClear(clearDataKey1 : Int, clearDataKey2 : Int){
            this[clearDataKey1] = 0
            this[clearDataKey2] = 0
    }
}

fun main(){
    val test = Fir_20210622()
    val test2 = Snd_20210622()
    println(test.solution(24, 24).toString())
    println(test2.solution(5, intArrayOf(2,4), intArrayOf(3)))
}