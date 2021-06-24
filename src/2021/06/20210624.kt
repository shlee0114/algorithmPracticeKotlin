class Fir_20210624{

    //프로그래머스 문제 : 큰 수 만들기
    //주어진 수와 자를 수 있는 기회로 가장 큰 수를 만드는 문제
    //자를 수 있는 기회 + 1 만큼 글자를 자른 후 그 안에 가장 큰 값까지 자르고 자른 만큼 횟수 차감한다.
    //자를 수 있는 횟수가 없거나 자를 수 횟수가 남은 글자보다 더 많을 경우 반환한다.
    //number : 총 숫자
    //k : 자를 수 있는 횟수
    fun solution(number: String, k: Int): String {
        if(number.length <= k)
            return ""
        //남은 횟수
        var remainingCutAction = k
        //자르기 시작점
        var startNum = 0
        var answer = ""
        while(true){
            //총 숫자에서 자르기 시작한 부분부터 남은 횟수 + 1만큼 숫자를 배열로 만들어서 반환한다.
            val cutNumberArray = number.getCutNumberArray(startNum, remainingCutAction)

            //자르는 횟수가 남은 숫자보다 많을 시 바로 answer를 반환하며 끝낸다
            if(remainingCutAction >=  number.substring(startNum,number.length).length)
                return answer

            //배열로 만든 것 중 가장 큰 값의 index를 받는다
            val maxVal = cutNumberArray.getMaxNumberIndex()

            //자른 수 하나만 받는다
            answer += number.substring(startNum + maxVal,startNum + maxVal + 1)

            startNum += maxVal + 1
            remainingCutAction -= maxVal

            //자를 수 있는 횟수가 없을 경우 남은 부분까지 answer뒤에 붙여서 반환한다.
            if(remainingCutAction <= 0){
                answer += number.substring(startNum,number.length)
                break
            }

            //숫자의 길이보다 봐야하는 수가 더 많을 시 숫자에서 뒤를 자른 다음 반환한다.
            if(number.length < startNum + remainingCutAction + 1){
                return number.substring(0, number.length - k)
            }
        }
        return answer
    }

    //숫자를 자른 후 charArray로 반환
    //index : 시작점
    //remainCount : 끝점(남은 자를 횟수)
    private fun String.getCutNumberArray(index : Int, remainCount : Int) : CharArray = this.substring(index,index + remainCount + 1).toCharArray()

    //가장 큰 값의 인덱스를 반환
    private fun CharArray.getMaxNumberIndex() : Int{
        var maxVal = 0
        for(i in this.indices){
            if(this[i].digitToInt() > this[maxVal].digitToInt()){
                maxVal = i
                if(this[i].digitToInt() == 9)
                    break
            }
        }
        return maxVal
    }
}

fun main(){
    val test = Fir_20210624()
    test.solution("77777", 1)
}